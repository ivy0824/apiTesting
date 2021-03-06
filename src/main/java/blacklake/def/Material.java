package blacklake.def;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import common.JsonReader;
import config.DataPrepare;
import config.Environment;
import common.RequestObject;
import io.restassured.response.ValidatableResponse;
import org.springframework.context.annotation.Bean;
import java.util.HashMap;

public class Material {

    private String materialCode;
    private String materialName;
    private int UnitId;
    private int status;

    public Material(String materialCode, String materialName, int UnitId, int status) {
        this.materialCode = materialCode;
        this.materialName = materialName;
        this.UnitId = UnitId;
        this.status = status;

    }

    /**
     * 创建物料
     * @param code
     * @param name
     */
    public static ValidatableResponse createMaterial(String code, String name) {
        int unitId = Unit.getUnitId(DataPrepare.unitName);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("code", code);
        map.put("name", name);
        map.put("unitId",unitId);
        String body = JsonReader.getJsonToString("/data/def/material.json",map);
        ValidatableResponse response = RequestObject.postRequest(Environment.server_def,"/v1/material", body);
        return response;
    }

    /**
     * 创建一个随机单位的物料并获取响应值
     * @param code
     * @param name
     * @param jsonPath
     * @return
     */
    public static ValidatableResponse getMaterialResponse(String code, String name, String jsonPath) {
        int unitId = Unit.getUnitId(DataPrepare.unitName);
        HashMap<String, Object> body = new HashMap<String, Object>();
        body.put("code", "code" + code);
        body.put("name", "material" + name);
        body.put("status", "1");
        body.put("unitId", unitId);
        body.put("fifo","false");
        ValidatableResponse materialResponse = RequestObject.postRequest(Environment.server_def,"/v1/material", body).extract().path(jsonPath);
        return materialResponse;
    }




    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }
}
