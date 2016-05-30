import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Dell on 5/29/2016.
 */
public class JsonToCsv {
    public String jsonObjectToCsvRow(JSONObject job,String stationcode,String paramcode,String type) throws JSONException {
        StringBuilder sb = new StringBuilder();
        JSONArray jTypeArrr = job.getJSONObject(type).names();
        for(int i=0;i<jTypeArrr.length();i++){
            String value = job.getJSONObject(type).get((String) jTypeArrr.get(i)).toString();
            String dataTSend=DateandTime.getDatenTime()+","+stationcode+","+paramcode+","+type+","+jTypeArrr.get(i).toString()+","+value+System.getProperty("line.separator");
            sb.append(dataTSend);
        }
        sb.append(System.getProperty("line.separator"));
        return sb.toString();
    }  
}
