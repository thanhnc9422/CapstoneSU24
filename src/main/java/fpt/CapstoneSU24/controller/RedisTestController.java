package fpt.CapstoneSU24.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class RedisTestController {


    private final RedisTemplate<String, Object> redisTemplate;
    @Autowired
    public RedisTestController(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/test-redis")
    public String testRedisConnection() {
        try {
            redisTemplate.opsForValue().set("testKey", "meomeeoo");
            String value = (String) redisTemplate.opsForValue().get("testKey");
            return "Redis is working: " + value;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}