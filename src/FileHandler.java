/**
 * Created by Dell on 5/27/2016.
 */
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.*;
public class FileHandler {
    static StringBuilder sb;
    static JSONObject jobj;
    static JSONArray jstationarr,jparmarr;
    static String srcname;

    public FileHandler(String srcname) throws FileNotFoundException, IOException, JSONException{
        this.srcname = srcname;
    }

    public static String getDate(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        return df.format(d);
    }
    public static void write(String data) throws JSONException, FileNotFoundException, IOException{
        JSONObject jdata = new JSONObject(data);
        BufferedReader br = new BufferedReader(new FileReader("asdf.txt"));
        String line = "";
        sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }
        jobj = new JSONObject(sb.toString());
        jstationarr = jobj.getJSONObject("DATA_SOURCE").getJSONObject(srcname).getJSONObject("STATION_MAP").names();
        jparmarr = jobj.getJSONObject("DATA_SOURCE").getJSONObject(srcname).getJSONObject("PARAMETER_MAP").names();

        for(int i=0; i<jstationarr.length(); i++){
            for(int j=0; j<jparmarr.length();j++){
                String stationcode = jobj.getJSONObject("DATA_SOURCE").getJSONObject(srcname).getJSONObject("STATION_MAP").get((String) jstationarr.get(i)).toString();
                String paramcode = jobj.getJSONObject("DATA_SOURCE").getJSONObject(srcname).getJSONObject("PARAMETER_MAP").get((String) jparmarr.get(j)).toString();
                String path = "C:\\suhel\\"+srcname+"\\"+stationcode+"\\"+paramcode+"\\"+getDate();
                if(!new File(path).exists()){
                    new File(path).mkdirs();
                }
                try{
                    String sdata = jdata.getJSONObject(stationcode).getJSONObject("param").get(paramcode).toString();
                    BufferedWriter fw = new BufferedWriter(new FileWriter(path+"\\"+paramcode+".txt"));
                    fw.write(sdata);
                    fw.close();
                }catch(Exception e){
                }
            }
        }

    }
}

