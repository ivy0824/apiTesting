package blacklake.def;

import config.Environment;
import common.RequestObject;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;

public class Storage {

    /*
    @Param code
    @Param name
    @Param parentCode
    @Param level
     */
    public static ValidatableResponse createStorage1(String code, String name, String parentCode, int level) {
        HashMap<String, String> body = new HashMap<String, String>();
        body.put("code", code);
        body.put("name", name);
        body.put("parentCode", parentCode);
        body.put("level", level+"");
        ValidatableResponse response = RequestObject.postRequest(Environment.server_def,"/v1/storage", body);
        return response;
    }

    public static ValidatableResponse createStorage2(String code,String name,String parentCode,int level) {
        HashMap<String, String> body = new HashMap<String, String>();
        body.put("code", code);
        body.put("name", name);
        body.put("parentCode", parentCode);
        body.put("level", level+"");
        ValidatableResponse response = RequestObject.postRequest(Environment.server_def,"/v1/storage", body);
        return response;
    }

    public static int getStorageId(String code){
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("code", code);
        ValidatableResponse response = RequestObject.getRequest(Environment.server_def,"/v1/storage",param);
        int storageId = response.extract().path("data[0].id");
        return storageId;
    }
}
