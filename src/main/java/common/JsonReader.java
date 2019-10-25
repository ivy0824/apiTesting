package common;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.util.ArrayList;
import java.util.HashMap;


public class JsonReader {

    public static String getJsonToString(String path, HashMap<String,Object> map){
        System.out.println(JsonReader.class.getResourceAsStream(path));
        DocumentContext documentContext= JsonPath.parse(JsonReader.class
                .getResourceAsStream(path));
        map.entrySet().forEach(entry->{
            documentContext.set(entry.getKey(), entry.getValue());
        });
        System.out.println(documentContext.jsonString());
        return documentContext.jsonString();
    }

    public static ArrayList getJson(String path, HashMap<String,Object> map){
        System.out.println(JsonReader.class.getResourceAsStream(path));
        DocumentContext documentContext= JsonPath.parse(JsonReader.class
                .getResourceAsStream(path));
        map.entrySet().forEach(entry->{
            documentContext.set(entry.getKey(), entry.getValue());
        });
        System.out.println(documentContext);
        return documentContext.json();
    }

    public static HashMap<String, Object> stringToMap(String str_json) {
        HashMap<String, Object> res = null;
        Gson gson = new Gson();
        res = gson.fromJson(str_json, new TypeToken<HashMap<String, Object>>() {
        }.getType());
        return res;
    }





}
