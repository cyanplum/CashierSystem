package org.uppower.project.cashiermanagesystem.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

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
 * @date ：Created in 2019-08-01 17:54
 * @description：
 * @modified By：
 * @version:
 */
public class DateUtils {

    /**
     * 时间戳转LocalDate
     *
     * @param timestamp
     * @return
     */
    public static final LocalDate timestampToLocalDate(long timestamp) {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDate();
    }

    /**
     * 时间戳转LocalDateTime
     *
     * @param timestamp
     * @return
     */
    public static final LocalDateTime timestampToLocalDateTime(long timestamp) {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
    }

    /**
     * LocalDate转时间戳
     *
     * @param localDate
     * @return
     */
    public static final long LocalDateToTimestamp(LocalDate localDate) {
        return localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();
    }

    /**
     * LocalDateTime转时间戳
     *
     * @param localDateTime
     * @return
     */
    public static final long localDateTimeToTimestamp(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    public static final Date LocalDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant());
    }

    public static final LocalDate DateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDate();
    }

    public static final Date LocalDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneOffset.ofHours(8)).toInstant());
    }

    public static final LocalDateTime DateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
    }

}
