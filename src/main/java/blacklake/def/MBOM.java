package blacklake.def;

import common.JsonReader;
import common.RequestObject;
import config.Environment;
import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import java.util.HashMap;


public class MBOM {

    public static ArrayList inputMaterial(String...materialCode){
        HashMap<String,Object> map=new HashMap();
        map.put("[0].materialCode",materialCode[0]);
        map.put("[1].materialCode",materialCode[1]);
        map.put("[2].materialCode",materialCode[2]);
        ArrayList body = JsonReader.getJson("/data/def/inputMaterial.json",map);
        return body;
    }

//    public static ArrayList inputMaterials(ArrayList...inputMaterial){
//        ArrayList list = new ArrayList();
//        list.add(inputMaterial[0]);
//        list.add(inputMaterial[1]);
//        list.add(inputMaterial[2]);
//        return list;
//    }

    public static ArrayList processList(int[] workstations,String primaryMaterialCode,ArrayList inputMaterial,String...processCode){
        HashMap<String,Object> map=new HashMap();
        map.put("[0].nodes[0].primaryMaterialCode",primaryMaterialCode);
        map.put("[0].nodes[0].workstations",workstations);
        map.put("[1].nodes[0].workstations",workstations);
        map.put("[2].nodes[0].workstations",workstations);
        map.put("[0].nodes[0].processCode",processCode[0]);
        map.put("[0].nodes[1].processCode",processCode[1]);
        map.put("[0].nodes[2].processCode",processCode[2]);
        map.put("[0].nodes[0].inputMaterials",inputMaterial);
        map.put("[1].nodes[0].inputMaterials",inputMaterial);
        map.put("[2].nodes[0].inputMaterials",inputMaterial);
        ArrayList body = JsonReader.getJson("/data/def/processList.json",map);
        System.out.println(body);
        return body;
    }

    public static ValidatableResponse createMbom(String materialCode,int unitId,String version,String ebomVersion,String processRoutingCode,ArrayList processList){
        HashMap<String,Object> map=new HashMap<String, Object>();
        map.put("materialCode",materialCode);
        map.put("unitSelections[0].id",unitId);
        map.put("currentUnitId",unitId);
        map.put("version",version);
        map.put("ebomVersion",ebomVersion);
        map.put("processRoutingCode",processRoutingCode);
        map.put("processList", processList);
        String body = JsonReader.getJsonToString("/data/def/mBOM.json",map);
        ValidatableResponse response = RequestObject.postRequest(Environment.server_def,"/v1/mbom",body);
        return response;
    }

    public static ValidatableResponse updateMBOM(int id){
        HashMap body = new HashMap();
        body.put("id",id);
        body.put("status",1);
        ValidatableResponse response = RequestObject.putRequest(Environment.server_def,"/v1/mbom/"+id+"/status",body);
        return response;
    }
}
