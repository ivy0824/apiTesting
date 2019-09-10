package blacklake.def;

import common.JsonReader;
import common.RequestObject;
import config.Environment;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;

public class MBOM {

    public static HashMap inputMaterial(String...materialCode){
        HashMap<String,Object> map=new HashMap();
        map.put("inputMaterials[0].materialCode",materialCode[0]);
        map.put("inputMaterials[1].materialCode",materialCode[1]);
        map.put("inputMaterials[2].materialCode",materialCode[2]);
        HashMap<String,Object> body=new HashMap();
        body = JsonReader.getJson("/data/def/inputMaterial.json",map);
        return body;
    }

    public static HashMap processList(String workstations,String...processCode){
        HashMap<String,Object> map=new HashMap();
        map.put("processList[0].nodes[0].workstations",workstations);
        map.put("processList[0].nodes[1].workstations",workstations);
        map.put("processList[0].nodes[2].workstations",workstations);
        map.put("processList[0].nodes[0].processCode[1]",processCode[1]);
        HashMap<String,Object> body=new HashMap();
        body = JsonReader.getJson("/data/def/processList.json",map);
        return body;
    }

    public static ValidatableResponse createMbom(int unitId,String version,String ebomVersion,String processRoutingCode,String...materialCode){
        HashMap<String,Object> map=new HashMap<String, Object>();
        map.put("","");

        String body = JsonReader.getJsonToString("/data/def/mBOM.json",map);
        ValidatableResponse response = RequestObject.postRequest(Environment.server_def,"/v1/mbom",body);
        return response;
    }
}
