package com.example.demo;




import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
public class VariableController {

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




        return new ResponseEntity<>(Map.of("Status", "App is up"), HttpStatus.OK);
    }


    @GetMapping(value = "/testing-endpoint", produces = "application/json")
    public ResponseEntity<Map<String,String>> endpointTest() {




        return new ResponseEntity<>(Map.of("Status", "App is up"), HttpStatus.OK);
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
        valor.put("data",value);
        return new ResponseEntity<>(valor, HttpStatus.OK);
    }
}
