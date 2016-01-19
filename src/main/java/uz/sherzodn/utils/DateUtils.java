package uz.sherzodn.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sherzod Nurjonov
 */
public class DateUtils {

    public static Date getBeginOfTheDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static boolean isDateAfterAnyAM(Date date, int hourAM){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hourAM);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date dateHourAM = calendar.getTime();
        return date.after(dateHourAM);
    }
}
