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
    public String getDataToSave(String s,int paramcode,String type,String stationcode) throws JSONException {
        StringBuilder sb = new StringBuilder();
        JSONObject job = new JSONObject(s);
        if(paramcode==1){
            JSONArray jar = job.getJSONObject("sum").names();
            for(int i=0; i<jar.length(); i++){
                String dataTSend=getDatenTime()+","+stationcode+","+paramcode+","+type+","+jar.get(i).toString()+","+job.getJSONObject("sum").get((String) jar.get(i)).toString()+System.getProperty("line.separator");
                sb.append(dataTSend);
            }
        }else if(paramcode==2){
            sb.append(job.getJSONObject("real_time").get("1 HOUR").toString());
        }
        return sb.toString();
    }

    public static String getDatenTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
