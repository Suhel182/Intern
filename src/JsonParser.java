/**
 * Created by Dell on 5/27/2016.
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.*;
public class JsonParser {
    private StringBuilder sb;
    private JSONObject jConfigObj;
    private JSONArray jDataSrcArr;

    public JsonParser() throws FileNotFoundException, IOException, JSONException{
        BufferedReader br = new BufferedReader(new FileReader("asdf.txt"));
        String line = "";
        sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }
        jConfigObj = new JSONObject(sb.toString());
        jDataSrcArr = jConfigObj.getJSONObject("DATA_SOURCE").names();
    }
    /*
    public Object[] get() throws FileNotFoundException, IOException, JSONException{
        String url[]=new String[2];
        String data[] = new String[2];
        for(int i=0;i<jar.length();i++){
            url[i]=jobj.getJSONObject("DATA_SOURCE").getJSONObject((String) jar.get(i)).get("URL").toString();
            data[i]=jobj.getJSONObject("DATA_SOURCE").getJSONObject((String) jar.get(i)).get("REQUEST_BODY").toString();
        }
        Object[] obj = new Object[]{url,data,jar.length()};
        return obj;
    }
    */
    public ArrayList get() throws FileNotFoundException, IOException, JSONException{
        ArrayList<Carrier> list = new ArrayList<Carrier>();
        HashMap<String,String> hmap = new HashMap<String,String>();
        //Carrier.setDataSourceLength(jar.length());
        for(int i=0;i<jDataSrcArr.length();i++){
            String a=jConfigObj.getJSONObject("DATA_SOURCE").getJSONObject((String) jDataSrcArr.get(i)).get("URL").toString();
            String b=jConfigObj.getJSONObject("DATA_SOURCE").getJSONObject((String) jDataSrcArr.get(i)).get("REQUEST_BODY").toString();
            list.add(new Carrier(a,b));
        }
        return list;
    }


    public String getSrcName(int i) throws JSONException{
        return (String)jDataSrcArr.get(i);
    }
    public int getInterval() throws JSONException {
        return Integer.valueOf(jConfigObj.get("REFRESH_INTERVAL").toString());
    }
}
