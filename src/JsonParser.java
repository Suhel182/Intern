/**
 * Created by Dell on 5/27/2016.
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.*;
public class JsonParser {
    StringBuilder sb;
    JSONObject jobj;
    JSONArray jar;
    public JsonParser() throws FileNotFoundException, IOException, JSONException{
        BufferedReader br = new BufferedReader(new FileReader("asdf.txt"));
        String line = "";
        sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }
        jobj = new JSONObject(sb.toString());
        jar = jobj.getJSONObject("DATA_SOURCE").names();
    }
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
    public String getSrcName(int i) throws JSONException{
        return (String)jar.get(i);
    }
}
