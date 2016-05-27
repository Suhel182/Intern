/**
 * Created by Dell on 5/27/2016.
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.JSONException;
public class MainClass {
    public static void main(String[] arg) throws IOException, JSONException {
        JsonParser jp=new JsonParser();
        Object[] obj = jp.get();
        String[] url = (String[]) obj[0];
        String[] data = (String[]) obj[1];
        int x = (int) obj[2];
        Httpcall call=new Httpcall();
        for(int i=0; i<x; i++){
            //System.out.println(call.postData(url[i],data[i]));
            FileHandler fh = new FileHandler(jp.getSrcName(i));
            fh.write(call.postData(url[i],data[i]));
        }
    }
}
