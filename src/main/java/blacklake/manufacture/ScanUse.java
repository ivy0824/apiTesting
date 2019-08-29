package blacklake.manufacture;

import common.JsonReader;
import common.RequestObject;
import config.Environment;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;

public class ScanUse {

    public static ValidatableResponse scanUse(String taskId,String code,String amount){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("taskId",taskId);
        map.put("code",code);
        map.put("amount",amount);
        String body = JsonReader.getJson("/data/manufacture/scanUse.json",map);
        ValidatableResponse response = RequestObject.postRequest(Environment.server_manufacture, "/v1/produce/scanUse", body);
        return response;
    }
}
