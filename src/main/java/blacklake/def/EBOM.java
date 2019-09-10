package blacklake.def;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import config.Environment;
import io.restassured.response.ValidatableResponse;
import common.JsonReader;
import common.RequestObject;

import java.util.ArrayList;
import java.util.HashMap;

//忽略序列化问题
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class EBOM {

    private String productMaterialCode;
    private String productMaterialName;
    private int currentUnitId;
    private int status;
    private String version;
    private ArrayList<RawMaterial> rawMaterialList;

    public EBOM(String productMaterialCode, String productMaterialName, int currentUnitId, int status, String version, ArrayList<RawMaterial> rawMaterialList) {
        this.productMaterialCode = productMaterialCode;
        this.productMaterialName = productMaterialName;
        this.currentUnitId = currentUnitId;
        this.status = status;
        this.version = version;
        this.rawMaterialList = rawMaterialList;

    }

    public String toString() {
        return "EBOM{" +
                "productMaterialCode='" + productMaterialCode + '\'' +
                ", productMaterialName='" + productMaterialName + '\'' +
                ",currentUnitId;" + currentUnitId + '\''+
                ", status=" + status +
                ", version='" + version + '\'' +
                ", rawMaterialList=" + rawMaterialList +
                '}';
    }

    public static ValidatableResponse createEbom(String... materialCode){
        HashMap<String,Object> map=new HashMap<String, Object>();
        map.put("productMaterialCode",materialCode[0]);
        map.put("currentUnitId", Unit.unitId+"");
        map.put("rawMaterialList[0].materialCode",materialCode[1]);
        map.put("rawMaterialList[1].materialCode",materialCode[2]);
        map.put("rawMaterialList[2].materialCode",materialCode[3]);
        String body = JsonReader.getJsonToString("/data/def/eBOM.json",map);
        ValidatableResponse response = RequestObject.postRequest(Environment.server_def,"/v1/ebom",body);
        return response;
    }




}
