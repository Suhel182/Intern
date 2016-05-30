/**
 * Created by Dell on 5/29/2016.
 */
public class Carrier {
    private String url,data;

    public Carrier(String s, String d){
        url=s;
        data=d;
    }
    public String getData(){
        return data;
    }
    public String getUrl(){
        return url;
    }
}
