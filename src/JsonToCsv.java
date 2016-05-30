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
        JSONArray jar = job.getJSONObject(type).names();;
        if(paramcode==1){
            for(int i=0; i<jar.length(); i++){
                String dataTSend=getDatenTime()+","+stationcode+","+paramcode+","+type+","+jar.get(i).toString()+","+job.getJSONObject(type).get((String) jar.get(i)).toString()+System.getProperty("line.separator");
                sb.append(dataTSend);
            }
        }
        if(paramcode==2){
            String dataTSend=getDatenTime()+","+stationcode+","+paramcode+","+type+","+jar.get(0).toString()+","+job.getJSONObject(type).get((String) jar.get(0)).toString();
            sb.append(dataTSend);
        }
        return sb.toString();
    }

    public static String getDatenTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
