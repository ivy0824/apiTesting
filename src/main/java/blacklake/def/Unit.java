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

    /**
     * 创建单位并获取单位响应值
     * @param name
     * @param jsonPath
     * @return
     */
    public static String getUnitResponse(String name,String jsonPath){
        HashMap<String, String> body = new HashMap<String, String>();
        body.put("name", "单位" + name);
        String unitResponse = RequestObject.postRequest(Environment.server_def,"/v1/unit", body).extract().path(jsonPath)+"";
        return unitResponse;
    }

    public static void createUnit(String name){
        HashMap<String, String> body = new HashMap<String, String>();
        body.put("name", "单位" + name);
        RequestObject.postRequest(Environment.server_def,"/v1/unit", body);
    }

}
