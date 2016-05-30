/**
 * Created by Dell on 5/27/2016.
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.*;
public class JsonParser {
    private StringBuilder sb;
    private JSONObject raw_data;
    private JSONArray data_sourceArray;
    private JSONObject data_source;

    public JsonParser() throws FileNotFoundException, IOException, JSONException{
        getConfig();
    }
    
    private void getConfig() throws FileNotFoundException, IOException, JSONException{
        BufferedReader br = new BufferedReader(new FileReader("asdf.txt"));
        String line = "";
        sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }
        raw_data = new JSONObject(sb.toString());
        data_source=raw_data.getJSONObject("DATA_SOURCE");
        data_sourceArray = data_source.names();
    }
   
    public ArrayList get() throws FileNotFoundException, IOException, JSONException{
        ArrayList<Carrier> list = new ArrayList<>();
        
        for(int i=0;i<data_sourceArray.length();i++){
            String url=data_source.getJSONObject((String) data_sourceArray.get(i)).get("URL").toString();
            String data=data_source.getJSONObject((String) data_sourceArray.get(i)).get("REQUEST_BODY").toString();
            list.add(new Carrier(url,data));
        }
        return list;
    }


    public String getSrcName(int i) throws JSONException{
        return (String)data_sourceArray.get(i);
    }
    public int getInterval() throws JSONException {
        return Integer.valueOf(raw_data.get("REFRESH_INTERVAL").toString());
    }

    
}
