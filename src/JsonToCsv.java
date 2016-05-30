import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dell on 5/29/2016.
 */
public class JsonToCsv {
    public String jsonObjectToCSVRow(JSONObject job) throws JSONException {
        StringBuilder sb = new StringBuilder();

        String type;
        
        JSONObject paramObject = object.get(type);
        String[] keys = paramObject.keys();
        
        for (String key: keys) {
            String dataToSend = getDatenTime()+","+stationcode+","+paramcode+","+type+","+paramObject.get(key)+System.getProperty("line.separator");
        }

        return sb.toString();
    }

    public static String getDatenTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
