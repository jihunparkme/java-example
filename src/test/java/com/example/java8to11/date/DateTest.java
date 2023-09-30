package com.example.java8to11.date;

import org.assertj.core.api.InstantAssert;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTest {

    @Test
    void machine_time() throws Exception {
        Instant instant = Instant.now();
        System.out.println(instant); // 2023-09-30T12:44:46.452980Z
        System.out.println(instant.atZone(ZoneId.of("UTC"))); // 2023-09-30T12:44:46.452980Z[UTC]
        System.out.println(instant.atZone(ZoneId.of("GMT"))); // 2023-09-30T12:45:17.336132Z[GMT]

        ZoneId zone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = instant.atZone(zone);
        System.out.println(zone); // Asia/Seoul
        System.out.println(zonedDateTime); // 2023-09-30T21:44:46.452980+09:00[Asia/Seoul]
    }

    @Test
    void human_time() throws Exception {
        LocalDateTime now = LocalDateTime.now(); // 현재 시스템 Zone 일시
        System.out.println(now); // 2023-09-30T21:57:26.029797

        LocalDateTime today = LocalDateTime.of(20023, Month.SEPTEMBER, 30, 0, 0, 0, 0);
        System.out.println(today); // +20023-09-30T00:00

        ZonedDateTime nowInLosAngeles = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
        System.out.println(nowInLosAngeles); // 2023-09-30T05:57:26.033318-07:00[America/Los_Angeles]

        Instant instant = Instant.now();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("America/Los_Angeles"));
        System.out.println(zonedDateTime); // 2023-09-30T05:57:26.034100-07:00[America/Los_Angeles]
    }

    @Test
    void machine_time_duration() throws Exception {
        Instant now = Instant.now();
        Instant plus = now.plus(10, ChronoUnit.SECONDS);
        Duration between = Duration.between(now, plus);
        System.out.println(between.getSeconds()); // 10
    }

    @Test
    void human_time_period() throws Exception {
        LocalDate today = LocalDate.now();
        LocalDate christmas = LocalDate.of(2023, Month.DECEMBER, 25);

        Period period = Period.between(today, christmas);
        System.out.println(period.getMonths()); // 2

        Period until = today.until(christmas);
        System.out.println(until.getDays()); // 25
    }

    @Test
    void formatting() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        System.out.println(now.format(formatter)); // 2023/09/30

        DateTimeFormatter isoLocalDate = DateTimeFormatter.ISO_LOCAL_DATE;
        System.out.println(now.format(isoLocalDate)); // 2023-09-30
    }

    @Test
    void parsing() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate parse = LocalDate.parse("2023/09/30", formatter);
        System.out.println(parse); // 2023-09-30
    }

    @Test
    void legacy_to_new() throws Exception {
        Date date = new Date(); // Sat Sep 30 22:24:04 KST 2023
        Instant instant = date.toInstant(); // 2023-09-30T13:24:04.618Z
        Date dateFromInstant = Date.from(instant);

        GregorianCalendar gregorianCalendar = new GregorianCalendar(); // java.util.GregorianCalendar[time=1696080458867,areFieldsSet=true,areAl...
        ZonedDateTime zonedDateTime = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault()); // 2023-09-30T22:27:38.867+09:00[Asia/Seoul]
        GregorianCalendar gregorianCalendarFromZonedDateTime = GregorianCalendar.from(zonedDateTime);

        ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId(); // America/Los_Angeles
        TimeZone timeZone = TimeZone.getTimeZone(zoneId); // sun.util.calendar.ZoneInfo[id="America/Los_Angeles",of
        ZoneId timeZoneFromZonId = timeZone.toZoneId();
    }

    @Test
    void calculation() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime plusDay = now.plus(10, ChronoUnit.DAYS);
        LocalDateTime plusMonth = now.plus(2, ChronoUnit.MONTHS);
    }
}
