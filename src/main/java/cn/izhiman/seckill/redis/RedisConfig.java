package cn.izhiman.seckill.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhiman
 * @date 2019/9/22
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {
    private String host;
    private String password;
    private int port;
    private int timeout;
    private int poolMaxActive;
    private int poolMaxIdle;
    private int poolMaxWait;
}
