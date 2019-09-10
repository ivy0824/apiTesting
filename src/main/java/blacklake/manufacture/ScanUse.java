package blacklake.manufacture;

import common.JsonReader;
import common.RequestObject;
import config.Environment;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;

public class ScanUse {

    public static ValidatableResponse scanUse(int taskId,String code,int amount,String unit,int userMode){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("taskId",taskId);
        map.put("code",code);
        map.put("amount",amount);
        map.put("unit",unit);
        map.put("userMode",userMode);
        String body = JsonReader.getJsonToString("/data/manufacture/scanUse.json",map);
        ValidatableResponse response = RequestObject.postRequest(Environment.server_manufacture, "/v1/produce/scanUse", body);
        return response;
    }

    public static ValidatableResponse inputSummary(int taskId){
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("taskId",taskId);
        ValidatableResponse response = RequestObject.getRequest(Environment.server_manufacture,"/v1/produce_summary/input",param);
        return response;
    }
}
