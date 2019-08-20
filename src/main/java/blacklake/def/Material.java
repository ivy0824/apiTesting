package blacklake.def;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import config.Environment;
import common.RequestObject;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
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
     * 创建一个随机单位的物料并获取响应值
     * @param code
     * @param name
     * @param jsonPath
     * @return
     */
    public static String getMaterialResponse(String code, String name, String jsonPath) {
        String unitName = (int) (Math.random() * 10000) + "";
        String unitId = Unit.getUnitResponse(unitName, "data.id");
        HashMap<String, Object> body = new HashMap<String, Object>();
        body.put("code", "code" + code);
        body.put("name", "material" + name);
        body.put("status", "1");
        body.put("unitId", unitId);
        body.put("fifo","false");
        String materialResponse = RequestObject.postRequest(Environment.server_def,"/v1/material", body).extract().path(jsonPath);
        return materialResponse;
    }

    /**
     * 创建一个随机单位的物料并获取单位ID
     * @param code
     * @param name
     * @return
     */
    public static int getUnitID(String code, String name) {
        String unitName = (int) (Math.random() * 10000) + "";
        String unitId = Unit.getUnitResponse(unitName, "data.id");
        HashMap<String, Object> body = new HashMap<String, Object>();
        body.put("code", "code" + code);
        body.put("name", "material" + name);
        body.put("status", "1");
        body.put("unitId", unitId);
        int unitID = RequestObject.postRequest(Environment.server_def,"/v1/material", body).extract().path("data.unitID");
        return unitID;
    }

    /**
     * 创建物料
     * @param code
     * @param name
     */
    public static void createMaterial(String code, String name) {
        String unitName = (int) (Math.random() * 10000) + "";
        String unitId = Unit.getUnitResponse(unitName, "data.id");
        HashMap<String, Object> body = new HashMap<String, Object>();
        body.put("code", code);
        body.put("name", name);
        body.put("status", "1");
        body.put("unitId", unitId);
        body.put("fifo","false");
        RequestObject.postRequest(Environment.server_def,"/v1/material", body);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }
}
