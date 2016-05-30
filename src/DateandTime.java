
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateandTime {
      public static String getDatenTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
      
        public static String getDate(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        return df.format(d);
    }
}
