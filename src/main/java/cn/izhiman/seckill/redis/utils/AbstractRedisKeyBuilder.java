package cn.izhiman.seckill.redis.utils;

import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * @author zhiman
 * @date 2019/9/22
 */
@AllArgsConstructor
public abstract class AbstractRedisKeyBuilder implements RedisKeyBuilder {
    private int expireSec;
    private String prefix;

    public AbstractRedisKeyBuilder(String prefix) {
        this(0, prefix);
    }

    @Override
    public int expireSec() {
        return expireSec;
    }

    @Override
    public String getPrefix() {
        return this.getClass().getSimpleName() + ":" + prefix;
    }

    @Override
    public String build(String str) {
        if (StringUtils.isEmpty(str)) {
            throw new NullPointerException("redis key can not be empty!");
        }
        return getPrefix() + ":" + str;
    }
}
