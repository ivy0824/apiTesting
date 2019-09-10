package blacklake.def;

import common.JsonReader;
import config.Environment;
import io.restassured.response.ValidatableResponse;
import common.RequestObject;
import java.util.HashMap;

public class Processs {

    /**
     *
     * @param code
     * @param name
     * @param codeScanNum
     * @param alwaysOneCode 一码到底
     * @param deliverable 不合格投产
     * @param workstations
     * @return
     */
    public static ValidatableResponse createProcess(String code, String name,String codeScanNum,String alwaysOneCode,Boolean deliverable, int[] workstations){
        HashMap<String,Object> map=new HashMap<String, Object>();
        map.put("code",code);
        map.put("name",name);
        map.put("codeScanNum",codeScanNum);
        map.put("alwaysOneCode",alwaysOneCode);
        map.put("deliverable",deliverable);
        map.put("workstations",workstations);
        String body = JsonReader.getJsonToString("/data/def/a.json",map);
        ValidatableResponse response = RequestObject.postRequest(Environment.server_def,"/v1/process",body);
        return response;
    }
}
