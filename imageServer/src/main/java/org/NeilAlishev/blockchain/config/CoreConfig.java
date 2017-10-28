package org.NeilAlishev.blockchain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

/**
 * @author NeilAlishev
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = {
        "org.NeilAlishev.blockchain.service", "org.NeilAlishev.blockchain.util"}
)
public class CoreConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
