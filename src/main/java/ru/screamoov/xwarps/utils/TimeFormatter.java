package ru.screamoov.xwarps.utils;

public class TimeFormatter {
    public TimeFormatter() {
    }

    public static String format(long sec) {
        if (sec < 1L) {
            return "0 секунд";
        } else {
            long m = sec / 60L;
            sec %= 60L;
            long h = m / 60L;
            m %= 60L;
            long d = h / 24L;
            h %= 24L;
            long y = d / 365L;
            d %= 365L;
            String time = "";
            if (y > 0L) {
                time = time + y + " " + formatTime(y, "год", "лет", "лет");
                if (d > 0L || h > 0L || m > 0L || sec > 0L) {
                    time = time + " ";
                }
            }

            if (d > 0L) {
                time = time + d + " " + formatTime(d, "день", "дня", "дней");
                if (h > 0L || m > 0L || sec > 0L) {
                    time = time + " ";
                }
            }

            if (h > 0L) {
                time = time + h + " " + formatTime(h, "час", "часа", "часов");
                if (m > 0L || sec > 0L) {
                    time = time + " ";
                }
            }

            if (m > 0L) {
                time = time + m + " " + formatTime(m, "минута", "минуты", "минут");
                if (sec > 0L) {
                    time = time + " ";
                }
            }

            if (sec > 0L) {
                time = time + sec + " " + formatTime(sec, "секунда", "секунды", "секунд");
            }

            return time;
        }
    }

    private static String formatTime(long num, String single, String lessfive, String others) {
        if (num % 100L > 10L && num % 100L < 15L) {
            return others;
        } else {
            switch ((int)(num % 10L)) {
                case 1:
                    return single;
                case 2:
                case 3:
                case 4:
                    return lessfive;
                default:
                    return others;
            }
        }
    }
}
