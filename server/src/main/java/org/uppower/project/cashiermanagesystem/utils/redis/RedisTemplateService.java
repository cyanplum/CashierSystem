package org.uppower.project.cashiermanagesystem.utils.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * █████▒█      ██  ▄████▄   ██ ▄█▀       ██████╗ ██╗   ██╗ ██████╗
 * ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒        ██╔══██╗██║   ██║██╔════╝
 * ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░        ██████╔╝██║   ██║██║  ███╗
 * ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄        ██╔══██╗██║   ██║██║   ██║
 * ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄       ██████╔╝╚██████╔╝╚██████╔╝
 * ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒       ╚═════╝  ╚═════╝  ╚═════╝
 * ░     ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░
 * ░ ░    ░░░ ░ ░ ░        ░ ░░ ░
 * ░     ░ ░      ░  ░
 *
 * @author ：涂齐康
 * @date ：Created in 2019-07-31 15:03
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class RedisTemplateService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public <T> boolean set(String key, T value) {

        try {

            //任意类型转换成String
            String val = beanToString(value);

            if (val == null || val.length() <= 0) {
                return false;
            }

            stringRedisTemplate.opsForValue().set(key, val);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public <T> T get(String key, Class<T> clazz) {
        try {
            String value = stringRedisTemplate.opsForValue().get(key);

            return stringToBean(value, clazz);
        } catch (Exception e) {
            return null;
        }
    }

    public <T> boolean set(String key, T value, long expire) {
        try {
            //任意类型转换成String
            String val = beanToString(value);

            if (val == null || val.length() <= 0) {
                return false;
            }

            stringRedisTemplate.opsForValue().set(key, val, expire, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean incr(String key, long l) {
        try {
            stringRedisTemplate.opsForValue().increment(key, l);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean del(String key) {
        try {
            return stringRedisTemplate.delete(key);
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T stringToBean(String value, Class<T> clazz) {
        if (value == null || value.length() <= 0 || clazz == null) {
            return null;
        }

        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(value);
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(value);
        } else if (clazz == String.class) {
            return (T) value;
        } else {
            return JSON.toJavaObject(JSON.parseObject(value), clazz);
        }
    }

    /**
     * @param value
     * @param <T>
     * @return
     */
    private <T> String beanToString(T value) {

        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String) value;
        } else {
            return JSON.toJSONString(value);
        }
    }
}
