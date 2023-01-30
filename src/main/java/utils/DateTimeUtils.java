package utils;

public class DateTimeUtils extends LoggerUtils {


    public static void wait (int seconds) {

        try {
            Thread.sleep(1000L * seconds);
        } catch (InterruptedException e) {
            log.warn("InnterruptedException in Thread.sleep(). Message: " + e.getMessage());
        }


    }


}
