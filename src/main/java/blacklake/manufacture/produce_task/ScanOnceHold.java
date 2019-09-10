package blacklake.manufacture.produce_task;

import common.JsonReader;
import common.RequestObject;
import config.Environment;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;

public class ScanOnceHold {
    public static ValidatableResponse scanOnceHold(int taskId, String code, int amount, String unit, int storageId){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("taskId",taskId);
        map.put("code",code);
        map.put("amount",amount);
        map.put("unit",unit);
        map.put("storageId",storageId);
        String body = JsonReader.getJsonToString("/data/manufacture/scanOnceHold.json",map);
        ValidatableResponse response = RequestObject.postRequest(Environment.server_manufacture, "/v1/produce/scanOnceHold", body);
        return response;
    }
}
