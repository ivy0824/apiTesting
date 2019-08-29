package common;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class StringToMap {
    public static HashMap StringToMap(String String){
        Gson gson = new Gson();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map = gson.fromJson(String, map.getClass());
        return map;
    }

}
