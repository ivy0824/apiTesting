package blacklake.def;

import config.Environment;
import io.restassured.response.ValidatableResponse;
import common.RequestObject;

import java.util.HashMap;

public class Unit {

    public static int unitId;


    public static ValidatableResponse createUnit(String name,String des){
        HashMap<String,String> body=new HashMap<String, String>();
        body.put("name",name);
        body.put("desc",des);
        ValidatableResponse response = RequestObject.postRequest(Environment.server_def,"/v1/unit",body);
        unitId = response.extract().path("data.id");
        System.out.println(unitId);
        return response;
    }


    public static int getUnitId(String name){
        HashMap<String,String> param=new HashMap<String, String>();
        param.put("name",name);
        int unitId = RequestObject.getRequest(Environment.server_def,"/v1/unit",param).extract().path("data[0].id");
        return unitId;
    }

    public static void createUnit(String name){
        HashMap<String, String> body = new HashMap<String, String>();
        body.put("name", "单位" + name);
        RequestObject.postRequest(Environment.server_def,"/v1/unit", body);
    }

}
