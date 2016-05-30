/**
 * Created by Dell on 5/27/2016.
 */
import java.io.*;
import org.json.*;
public class FileHandler {
    static StringBuilder sb;
    static JSONObject requestBodyWithValue;
    static JSONArray stationMapArray,parameterMapArray,jsumarr;
    static String srcname;

    public FileHandler(String srcname) throws FileNotFoundException, IOException, JSONException{
        FileHandler.srcname = srcname;
    }  

    public static void write(String data) throws JSONException, FileNotFoundException, IOException{
        JSONObject jdata = new JSONObject(data);
        BufferedReader br = new BufferedReader(new FileReader("asdf.txt"));
        String line = "";
        sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }
        
        
        requestBodyWithValue = new JSONObject(sb.toString());
        JSONObject stationMap=requestBodyWithValue.getJSONObject("DATA_SOURCE").getJSONObject(srcname).getJSONObject("STATION_MAP");
        JSONObject parameterMap=requestBodyWithValue.getJSONObject("DATA_SOURCE").getJSONObject(srcname).getJSONObject("PARAMETER_MAP");
        
        stationMapArray = stationMap.names();
        parameterMapArray = parameterMap.names();


        for(int i=0; i<stationMapArray.length(); i++){
            for(int j=0; j<parameterMapArray.length();j++){
                String stationcode = stationMap.get((String) stationMapArray.get(i)).toString();
                String paramcode = parameterMap.get((String) parameterMapArray.get(j)).toString();
                String path = "C:\\suhel\\"+srcname+"\\"+stationcode+"\\"+paramcode+"\\"+DateandTime.getDate();
                
                if(!new File(path).exists()){
                    new File(path).mkdirs();
                }
                
                try{
                    JSONObject params=jdata.getJSONObject(stationcode).getJSONObject("param");
                    String specificData = params.get(paramcode).toString();
                    JSONObject jsdataobj = new JSONObject(specificData);
                    String type = params.getJSONObject(paramcode).names().get(0).toString();
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path+"\\"+paramcode+".txt",true));
                    JsonToCsv jsonToCsv = new JsonToCsv();
                    String fileToSave = jsonToCsv.jsonObjectToCsvRow(jsdataobj,stationcode,paramcode,type);
                    bufferedWriter.write(fileToSave);
                    bufferedWriter.close();
                }catch(Exception e){
                }
            }
        }

    }
}

