package org.uppower.project.cashiermanagesystem.utils;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
 * @date ：Created in 2019/10/14 2:52 下午
 * @description：
 * @modified By：
 * @version:
 */
public class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    private static ObjectMapper objectMapper = null;

    static {

        objectMapper = new ObjectMapper();

        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        module.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        module.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        objectMapper.registerModule(module);
    }

    /*
    public static JsonUtil getInstance() {

        if (instance == null) {
            synchronized (JsonUtil.class) {
                if (instance == null) {
                    instance = new JsonUtil();
                }
            }
        }

        return instance;
    }
    */

    public static String stringify(Object object) {

        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    public static String stringify(Object object, String... properties) {

        try {
            return objectMapper
                    .writer(new SimpleFilterProvider().addFilter(
                            AnnotationUtils.getValue(
                                    AnnotationUtils.findAnnotation(object.getClass(), JsonFilter.class)).toString(),
                            SimpleBeanPropertyFilter.filterOutAllExcept(properties)))
                    .writeValueAsString(object);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    public static void stringify(OutputStream out, Object object) {

        try {
            objectMapper.writeValue(out, object);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void stringify(OutputStream out, Object object, String... properties) {

        try {
            objectMapper
                    .writer(new SimpleFilterProvider().addFilter(
                            AnnotationUtils.getValue(
                                    AnnotationUtils.findAnnotation(object.getClass(), JsonFilter.class)).toString(),
                            SimpleBeanPropertyFilter.filterOutAllExcept(properties)))
                    .writeValue(out, object);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static <T> T parse(String json, Class<T> clazz) {

        if (json == null || json.length() == 0) {
            return null;
        }

        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

}
