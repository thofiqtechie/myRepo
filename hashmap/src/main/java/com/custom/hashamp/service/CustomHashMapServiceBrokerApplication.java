package com.custom.hashamp.service;

import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.servicebroker.model.Catalog;
import org.springframework.cloud.servicebroker.model.Plan;
import org.springframework.cloud.servicebroker.model.ServiceDefinition;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.HashMap;

import static java.util.Collections.*;

@SpringBootApplication
public class CustomHashMapServiceBrokerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomHashMapServiceBrokerApplication.class, args);
    }

    @Bean
    Catalog catalog() {
        return new Catalog(singletonList(new ServiceDefinition(
                "hashmap-broker",
                "p-hashmap",
                "Custom Hashmap Service",
                true,
                false,
                singletonList(new Plan("thofiqhashmap", "thofiqhashmap", "Thofiq Custom Hash Map",
                        new HashMap<String, Object>() {{
                            put("costs", singletonList(new HashMap<String, Object>() {
                                {
                                    put("amount", singletonMap("usd", 0.0));
                                    put("unit", "MONTHLY");
                                }
                            }));
                            put("bullets", "hashmap");
                        }}, true)),
                Arrays.asList("tag A", "tag B", "tag C"),
                new HashMap<String, Object>() {{
                    put("displayName", "Custom Hashmap Service");
                    put("longDescription", "Custom Hashmap Service");
                    put("imageUrl", "https://www.cloudfoundry.org/wp-content/uploads/2015/10/CF_rabbit_Blacksmith_rgb_trans_back-269x300.png");
                    put("providerDisplayName", "@making");
                }},
                null,
                null
        )));
    }

    @Bean
    RequestDumperFilter requestDumperFilter() {
        return new RequestDumperFilter();
    }
}
