package blacklake.def;

import common.JsonReader;
import common.RequestObject;
import config.Environment;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;

public class ProcessRoute {

    public static ValidatableResponse createProcessRoute(String code,String name,int[] workstationId,String... processCode){
        HashMap<String,Object> map=new HashMap<String, Object>();
        map.put("code",code);
        map.put("name",name);
        map.put("processList[0].nodes[0].workstations",workstationId);
        map.put("processList[1].nodes[0].workstations",workstationId);
        map.put("processList[2].nodes[0].workstations",workstationId);
        map.put("processList[0].nodes[0].processCode",processCode[0]);
        map.put("processList[1].nodes[0].processCode",processCode[1]);
        map.put("processList[2].nodes[0].processCode",processCode[2]);
        String body = JsonReader.getJson("/data/def/processRoute.json",map);
        ValidatableResponse response = RequestObject.postRequest(Environment.server_def,"/v1/process_routes",body);
        return response;
    }

    public static void updateProcessRouteStatus(String code){
        HashMap<String, String> body = new HashMap<String, String>();
        RequestObject.putRequest(Environment.server_def, "/v1/process_routes/"+code+"/status", body);
    }
}
