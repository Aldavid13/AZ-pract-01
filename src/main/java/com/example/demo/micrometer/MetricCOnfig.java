package com.example.demo.micrometer;


import com.microsoft.applicationinsights.TelemetryConfiguration;
import com.microsoft.applicationinsights.extensibility.TelemetryModule;
import com.microsoft.applicationinsights.telemetry.RequestTelemetry;
import com.microsoft.applicationinsights.web.extensibility.modules.WebTelemetryModule;
import com.microsoft.applicationinsights.web.internal.RequestTelemetryContext;
import com.microsoft.applicationinsights.web.internal.ThreadContext;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Collectors;


@Configuration   
public class MetricCOnfig implements WebTelemetryModule, TelemetryModule {
    @Override
    public void initialize(TelemetryConfiguration telemetryConfiguration) {

    }

    @Override
    public void onBeginRequest(ServletRequest req, ServletResponse res) {

        HttpServletRequest httpRequest = (HttpServletRequest) req;
        Map<String, String> headers = Collections.list(httpRequest.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(h -> h, httpRequest::getHeader));


        RequestTelemetryContext context = ThreadContext.getRequestTelemetryContext();
        RequestTelemetry requestTelemetry = context.getHttpRequestTelemetry();
        requestTelemetry.getProperties().put("cliente", headers.get("cliente"));
        requestTelemetry.getProperties().put("usuario", headers.get("usuario"));


    }

    @Override
    public void onEndRequest(ServletRequest req, ServletResponse res) {

    }
}
