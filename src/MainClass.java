/**
 * Created by Dell on 5/27/2016.
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

public class MainClass {
    public static void main(String[] arg) throws IOException, JSONException {
        JsonParser jp=new JsonParser();
        Httpcall call=new Httpcall();

        /*
        Object[] obj = jp.get();
        String[] url = (String[]) obj[0];
        String[] data = (String[]) obj[1];
        int x = (int) obj[2];
        for(int i=0; i<x; i++){
            FileHandler fh = new FileHandler(jp.getSrcName(i));
            fh.write(call.postData(url[i],data[i]));
        }
        */

        ArrayList<Carrier> list = jp.get();
        for(Carrier car : list){
            //System.out.println(call.postData(url[i],data[i]));
            FileHandler fh = new FileHandler(jp.getSrcName(list.indexOf(car)));
            String jsonData = call.postData(car.getUrl(),car.getData());
            fh.write(jsonData);
        }
    }
}
