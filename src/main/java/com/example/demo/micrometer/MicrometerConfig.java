//package com.example.demo.micrometer;
//
//
//import org.springframework.context.annotation.Profile;
//
//
//
//
//        import io.micrometer.core.instrument.config.MeterFilter;
//        import io.micrometer.core.instrument.util.NamedThreadFactory;
//        import java.net.InetAddress;
//        import java.net.UnknownHostException;
//        import java.time.Duration;
//        import org.springframework.beans.factory.annotation.Value;
//        import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
//        import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
//        import org.springframework.boot.actuate.autoconfigure.metrics.export.simple.SimpleMetricsExportAutoConfiguration;
//        import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//        import org.springframework.boot.autoconfigure.AutoConfigureBefore;
//        import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//        import org.springframework.context.annotation.Bean;
//        import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@AutoConfigureBefore({
//        CompositeMeterRegistryAutoConfiguration.class,
//        SimpleMetricsExportAutoConfiguration.class
//})
//@AutoConfigureAfter(MetricsAutoConfiguration.class)
//@ConditionalOnClass(NewRelicRegistry.class)
//@Profile({"monitoring"})
//public class MicrometerConfig{
//
//        @Value("${new.relic.license.key:}")
//    private String newRelicLicenseKey;
//
//    @Value("${new.relic.app.name:}")
//    private String newRelicAppName;
//
//    @Bean
//    public NewRelicRegistryConfig newRelicConfig() {
//        return new NewRelicRegistryConfig() {
//            @Override
//            public String get(String key) {
//                return null;
//            }
//
//            @Override
//            public String apiKey() {
//                return newRelicLicenseKey;
//            }
//
//            @Override
//            public Duration step() {
//                return Duration.ofSeconds(5);
//            }
//
//            @Override
//            public String serviceName() {
//                return newRelicAppName;
//            }
//
//            @Override
//            public boolean useLicenseKey() {
//                return true;
//            }
//        };
//    }
//
//    @Bean
//    public NewRelicRegistry newRelicMeterRegistry(NewRelicRegistryConfig config)
//            throws UnknownHostException {
//        NewRelicRegistry newRelicRegistry =
//                NewRelicRegistry.builder(config)
//                        .commonAttributes(
//                                new Attributes().put("host", InetAddress.getLocalHost().getHostName()))
//                        .build();
//        newRelicRegistry.config().meterFilter(MeterFilter.ignoreTags("plz_ignore_me"));
//        newRelicRegistry.config().meterFilter(MeterFilter.denyNameStartsWith("jvm.threads"));
//        newRelicRegistry.start(new NamedThreadFactory("newrelic.micrometer.registry"));
//        return newRelicRegistry;
//    }
//}