package com.huatu.ehr.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	public RedisConnectionFactory connectionFactory(JedisPoolConfig jedisPoolConfig) {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
		jedisConnectionFactory.setUsePool(true);
		jedisConnectionFactory.setHostName(redis_host);
		jedisConnectionFactory.setPort(redis_port);
		jedisConnectionFactory.setPassword(redis_pass);
		jedisConnectionFactory.setTimeout(redis_timeout);
		jedisConnectionFactory.setDatabase(redis_default_db);
		return jedisConnectionFactory;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
		//RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
		//redisTemplate.setConnectionFactory(connectionFactory);
		//return redisTemplate;
		StringRedisTemplate template = new StringRedisTemplate(connectionFactory);
        //定义key序列化方式
        //RedisSerializer<String> redisSerializer = new StringRedisSerializer();//Long类型会出现异常信息;需要我们上面的自定义key生成策略，一般没必要
        //定义value的序列化方式
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        
       // template.setKeySerializer(redisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
	}
	
}
