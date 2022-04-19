package com.pedrolsoares.marketplace.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
public class RedisConfiguration {

    @Bean
    public JedisConnectionFactory jedis(@Value("${spring.redis.host}") final String host,
                                       @Value("${spring.redis.port}") final int port) {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(host, port);

        return new JedisConnectionFactory(config);
    }

}
