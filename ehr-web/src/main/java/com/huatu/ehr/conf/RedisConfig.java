package com.huatu.ehr.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource("classpath:redis.properties")
public class RedisConfig {
	
	@Value("${redis.host}")
	private String redis_host;
	@Value("${redis.port}")
	private Integer redis_port;
	@Value("${redis.pass}")
	private String redis_pass;
	@Value("${redis.timeout}")
	private Integer redis_timeout;
	@Value("${redis.default.db}")
	private Integer redis_default_db;
	
	
	@Bean
	public JedisPoolConfig JedisPoolConfig() {
		return new JedisPoolConfig();
	}

	@SuppressWarnings("deprecation")
	@Bean
	public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
		jedisConnectionFactory.setUsePool(true);
		jedisConnectionFactory.setHostName(redis_host);
		jedisConnectionFactory.setPort(redis_port);
		jedisConnectionFactory.setPassword(redis_pass);
		jedisConnectionFactory.setTimeout(redis_timeout);
		jedisConnectionFactory.setDatabase(redis_default_db);
		return jedisConnectionFactory;
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(connectionFactory);
		return redisTemplate;
	}
}
