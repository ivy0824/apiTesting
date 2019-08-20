package blacklake.def;

import config.Environment;
import io.restassured.response.ValidatableResponse;
import common.RequestObject;

import java.util.HashMap;

public class ProdLine {

    public static ValidatableResponse createProdLine(String code, String name,int workshopId) {
        HashMap<String, String> body = new HashMap<String, String>();
        body.put("code", code);
        body.put("name", name);
        body.put("workshopId",workshopId+"");
        ValidatableResponse response = RequestObject.postRequest(Environment.server_def, "/v1/productionLines", body);
        return response;
    }

    public static void updateProdLineStatus(int id){
        HashMap<String, String> body = new HashMap<String, String>();
        RequestObject.putRequest(Environment.server_def, "/v1/productionLines/"+id+"/enabled", body);
    }
}
