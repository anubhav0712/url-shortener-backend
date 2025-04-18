package com.anubhav.url_shortener_backend.strategy.base62;

import com.anubhav.url_shortener_backend.strategy.ShortenStrategy;
import com.anubhav.url_shortener_backend.strategy.StrategyType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class Base62ShortenStrategy implements ShortenStrategy {

    private static final String COUNTER_KEY = "url:counter";
    private static final String URL_HASH_KEY = "url:mappings";

    private final StringRedisTemplate redisTemplate;
    private final Base62Helper base62Helper;

    public Base62ShortenStrategy(StringRedisTemplate redisTemplate, Base62Helper base62Helper) {
        this.redisTemplate = redisTemplate;
        this.base62Helper = base62Helper;
    }

    @Override
    public String shorten(String url) {
        // Check if URL already exists
        String existingId = (String) redisTemplate.opsForHash().get(URL_HASH_KEY, url);
        if (existingId != null) {
            return base62Helper.encode(Long.parseLong(existingId));
        }

        // Get a new unique ID by incrementing the counter
        Long newId = redisTemplate.opsForValue().increment(COUNTER_KEY);
        String shortCode = strategyShortKey()+base62Helper.fixed_length_encode(newId, 5);
        // Store the mapping
        redisTemplate.opsForHash().put(URL_HASH_KEY, url, newId.toString());
        redisTemplate.opsForHash().put(URL_HASH_KEY + ":reverse", shortCode, url);

        // Convert the ID to Base62 and return
        return shortCode;
    }

    @Override
    public String expand(String shortCode) {
        return (String) redisTemplate.opsForHash().get(URL_HASH_KEY + ":reverse", shortCode);
    }

    @Override
    public StrategyType strategyType() {
        return StrategyType.BASE_62;
    }

    @Override
    public String strategyShortKey() {
        return "b62";
    }
}
