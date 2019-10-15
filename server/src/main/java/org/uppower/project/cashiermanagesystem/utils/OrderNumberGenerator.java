package org.uppower.project.cashiermanagesystem.utils;

import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.uppower.project.cashiermanagesystem.utils.redis.RedisTemplateService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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
 * @date ：Created in 2019/10/15 4:42 下午
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class OrderNumberGenerator {
    private static final FastDateFormat pattern = FastDateFormat.getInstance("yyyyMMddHHmmss");

    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    private static final int DIVISOR = 10;

    private static final int BASE = 1000;

    private static final int LIMIT = 9000;

    /**
     * 【生成策略】
     * 1：线程hashcode取一位余数
     * 2-5：随机的一个4位数
     * 6-19：当前时间
     * 20-23 随机的一个4位数
     *
     * @param thread
     * @return 长码机制 23位
     */
    public static String getOrderCode(Thread thread) {
        StringBuilder builder = new StringBuilder();
        builder.append(thread.hashCode() % DIVISOR);
        builder.append(BASE + RANDOM.nextInt(LIMIT));
        builder.append(pattern.format(Instant.now().toEpochMilli()));
        builder.append(BASE + RANDOM.nextInt(LIMIT));
        return builder.toString();
    }
}
