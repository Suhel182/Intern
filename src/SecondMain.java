import org.json.JSONException;
import java.io.IOException;
import java.util.ArrayList;

public class SecondMain {
    public static void main(String[] arg) throws IOException, JSONException {
        JsonParser jp=new JsonParser();
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    while(true){
                        Httpcall call=new Httpcall();
                        ArrayList<Carrier> list = jp.get();
                        for(Carrier car : list){
                            FileHandler fh = new FileHandler(jp.getSrcName(list.indexOf(car)));
                            fh.write(call.postData(car.getUrl(),car.getData()));
                        }
                        Thread.sleep(jp.getInterval());
                    }
                }catch(Exception e){

                }
            }
        });
        th.start();
    }
}
