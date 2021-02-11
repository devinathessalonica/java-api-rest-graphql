package javaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javaapi.model.Barang;

@SpringBootApplication
public class JavaApiApplication {
	@Bean
	JedisConnectionFactory jedisConnectionFactory(){
	  return new JedisConnectionFactory();
	}
  
	@Bean
	RedisTemplate<String, Barang> redisTemplate(){
	  RedisTemplate<String,Barang> redisTemplate = new RedisTemplate<String, Barang>();
	  redisTemplate.setConnectionFactory(jedisConnectionFactory());
	  return redisTemplate;
	}
	public static void main(String[] args) {
		SpringApplication.run(JavaApiApplication.class, args);
	}

}
