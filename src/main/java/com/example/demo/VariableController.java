package com.example.demo;




import io.micrometer.azuremonitor.AzureMonitorMeterRegistry;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@Slf4j
public class VariableController {

    @Autowired BlobService blobService;

    @Autowired
    private AzureMonitorMeterRegistry azureMonitorMeterRegistry;


    public VariableController(){

    }


    @GetMapping(value = "api/imprimir", produces = "application/json")
    public ResponseEntity<String> validateToken() {

        String value = System.getenv("ambiente");
        if (value == null)
            value = "No hay variables de entorno";
        else {
            if (value.equals("dev") || value.equals("prod"))
                value = "hola  desde el ambiente" + value;
            else value = "no encuentro el ambiente dev ni prod";

        }





        return new ResponseEntity<>(value, HttpStatus.OK);
    }

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<Map<String,String>> HealthCheck() {
//        azureMonitorMeterRegistry.counter("probando counter");
//        azureMonitorMeterRegistry.gauge("valores", 20);
//        azureMonitorMeterRegistry.timer("timer","timer1");



        return new ResponseEntity<>(Map.of("Status", "App is up raiz"), HttpStatus.OK);
    }


    @GetMapping(value = "/testing-endpoint", produces = "application/json")
    public ResponseEntity<Map<String,String>> endpointTest() {

        return new ResponseEntity<>(Map.of("Status", "App is up testing"), HttpStatus.OK);
    }




    @GetMapping(value = "api/imprimirTexto", produces = "application/json")
    public ResponseEntity<Map<String,String>> imprimirTexto() {
        Map<String,String> valor = new HashMap<>();

        String value = System.getenv("texto");
        if (value == null || System.getenv("texto").isEmpty())
            value = "No texto estas enviando el texto como variable de entorno";
        else if(System.getenv("nombre")==null || System.getenv("nombre").isEmpty())
            value ="No estas colocando la variable nombre";
        else if(!value.toLowerCase().contains(System.getenv("nombre").toLowerCase())){
            value=" No estas reemplazando bien tu nombre en el archivo: \n \n  ESTAS IMPRIMIENDO: \n \n"+ value;
        }
        System.out.println("data impresa %s".formatted(value));
        valor.put("data",value);
        log.info("data: %s".formatted(value));
        return new ResponseEntity<>(valor, HttpStatus.OK);
    }


    @PostMapping(value = "api/subirArchivo", produces = "application/json", consumes = "multipart/form-data")
    public ResponseEntity<Object> createFile(MultipartFile file ){
        blobService.uploadFile(file);

        return new ResponseEntity<>(Map.of("Mensaje", "Archivo arriba"), HttpStatus.OK);

    }


    @GetMapping(value = "api/descargar", produces = "txt/csv")
    public ResponseEntity<byte[]> download(@RequestParam String fileName ){

        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.CONTENT_TYPE, "text/csv");
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName);

        return new ResponseEntity<>( blobService.downloadFilesCSV(fileName), header, HttpStatus.OK);

    }


    @GetMapping(value = "api/leer", produces = "application/json")
    public ResponseEntity<List<String>> download(){
        System.out.println("Leyendo archivo");
        return new ResponseEntity<>( blobService.learArchivos(), HttpStatus.OK);

    }

}
