package com.goblin.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author wangpeng
 * @version v1.0
 * @since 2022/12/2
 */
@Configuration
public class RedisConfig {

    /**
     * GenericJackson2JsonRedisSerializer 序列器
     *
     * @param factory
     * @return RedisTemplate<String, Object>
     */
    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        //String的序列化方式
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // 使用GenericJackson2JsonRedisSerializer 替换默认序列化(默认采用的是JDK序列化)
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();

        //key序列化方式采用String类型
        template.setKeySerializer(stringRedisSerializer);
        //value序列化方式采用jackson类型
        template.setValueSerializer(genericJackson2JsonRedisSerializer);
        //hash的key序列化方式也是采用String类型
        template.setHashKeySerializer(stringRedisSerializer);
        //hash的value也是采用jackson类型
        template.setHashValueSerializer(genericJackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * Jackson2JsonRedisSerializer 序列器
     *
     * @param factory
     * @return RedisTemplate<String, Object>
     */
    //@Bean
    //@ConditionalOnMissingBean(name = "redisTemplate")
    //public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
    //    RedisTemplate<String, Object> template = new RedisTemplate<>();
    //    template.setConnectionFactory(factory);
    //    //String的序列化方式
    //    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    //    //使用Jackson2JsonRedisSerialize 替换默认序列化(默认采用的是JDK序列化)
    //    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
    //    //如果采用Jackson2JsonRedisSerializer序列化方式，没有ObjectMapper配置在强转对象的时候会反序列化失败，也就是User user = (User) redisTemplate.opsForValue().get(key) 会失败;
    //    ObjectMapper objectMapper = new ObjectMapper();
    //    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    //    objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
    //    jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
    //    //key序列化方式采用String类型
    //    template.setKeySerializer(stringRedisSerializer);
    //    //value序列化方式采用jackson类型
    //    template.setValueSerializer(jackson2JsonRedisSerializer);
    //    //hash的key序列化方式也是采用String类型
    //    template.setHashKeySerializer(stringRedisSerializer);
    //    //hash的value也是采用jackson类型
    //    template.setHashValueSerializer(jackson2JsonRedisSerializer);
    //    template.afterPropertiesSet();
    //    return template;
    //}


}
