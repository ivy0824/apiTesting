package common;

import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import jdk.nashorn.internal.ir.ObjectNode;

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

    public static HashMap getJson(String path,HashMap<String,Object> map){
        System.out.println(JsonReader.class.getResourceAsStream(path));
        DocumentContext documentContext= JsonPath.parse(JsonReader.class
                .getResourceAsStream(path));
        map.entrySet().forEach(entry->{
            documentContext.set(entry.getKey(), entry.getValue());
        });
        System.out.println(documentContext);
        return documentContext.json();
    }


}
