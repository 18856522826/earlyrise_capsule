package redisson.config;

import org.checkerframework.checker.units.qual.C;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import redisson.RedisTemplate;

import java.io.IOException;

/**
 * Notice:
 *
 * @author xuxu
 * @version 1.0
 * @date 2020/10/28
 * @since 1.0
 */
@Configuration
public class RedissonConfiguration {
    @Value("${redisson.config.location:}")
    private String redissonConfigLocation;

    @Bean(name = "redissonClient")
    public RedissonClient config() throws IOException {
        Resource location = new ClassPathResource(redissonConfigLocation);
        Config config = Config.fromYAML(location.getFile());
        return Redisson.create(config);
    }
    @Bean
    public RedisTemplate<String> stringRedisTemplate(RedissonClient redissonClient){
        return  new RedisTemplate<>(redissonClient, StringCodec.INSTANCE);
    }

    @Bean
    @Lazy
    public RedisTemplate<Object> redisTemplate(RedissonClient redissonClient){
        return  new RedisTemplate<>(redissonClient);
    }
}
