package blacklake.def;

import config.Environment;
import io.restassured.response.ValidatableResponse;
import common.RequestObject;

import java.util.HashMap;

public class Workshop {

    public static ValidatableResponse createWorkshop(String code, String name) {
        HashMap<String, String> body = new HashMap<String, String>();
        body.put("code", code);
        body.put("name", name);
        ValidatableResponse response = RequestObject.postRequest(Environment.server_def, "/v1/workshops", body);
        return response;
    }

    public static void updateWorkshopStatus(int id){
        HashMap<String, String> body = new HashMap<String, String>();
        RequestObject.putRequest(Environment.server_def, "/v1/workshops/"+id+"/enabled", body);
    }
}
