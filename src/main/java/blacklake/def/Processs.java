package blacklake.def;

import config.Environment;
import io.restassured.response.ValidatableResponse;
import common.RequestObject;
import java.util.HashMap;

public class Processs {

    public static ValidatableResponse createProcess(String code, String name,String codeScanNum,Boolean deliverable, int[] workstations){
        HashMap<String,Object> body=new HashMap<String, Object>();
        body.put("code",code);
        body.put("name",name);
        body.put("codeScanNum",codeScanNum);
        body.put("deliverable",deliverable);
        body.put("fifo","1");
        body.put("status","1");
        body.put("workstations",workstations);
        ValidatableResponse response = RequestObject.postRequest(Environment.server_def,"/v1/process",body);
        return response;
    }
}
