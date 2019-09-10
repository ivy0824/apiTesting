package blacklake.def;

import common.JsonReader;
import config.Environment;
import io.restassured.response.ValidatableResponse;
import common.RequestObject;

import java.util.HashMap;

public class Workstation {

    public static int workstationId;

    public static ValidatableResponse createWorkstation(String code, String name,int productionLineId) {
        HashMap<String, String> body = new HashMap<String, String>();
        body.put("code", code);
        body.put("name", name);
        body.put("productionLineId",productionLineId+"");
        body.put("toManyTask","1");
        body.put("groupId","");
        ValidatableResponse response = RequestObject.postRequest(Environment.server_def, "/v1/workstationAreas", body);
        workstationId = response.extract().path("data.id");
        return response;
    }

    public static ValidatableResponse addRelationUser(String job,int userId,int workstationId) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("[0].id", userId+"");
        map.put("[1].id", userId+"");
        map.put("[2].id", userId+"");
        map.put("[3].id", userId+"");
        String body = JsonReader.getJsonToString("/data/def/relateUser.json",map);
        ValidatableResponse response = RequestObject.putRequest(Environment.server_def, "/v1/workstationAreas/"+workstationId+"/workers", body);
        return response;
    }


    public static void updateWorkstationStatus(int id){
        HashMap<String, String> body = new HashMap<String, String>();
        RequestObject.putRequest(Environment.server_def, "/v1/workstationAreas/"+id+"/enabled", body);
    }
}
