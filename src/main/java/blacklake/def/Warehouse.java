package blacklake.def;

import config.Environment;
import common.RequestObject;

import java.util.HashMap;

public class Warehouse {

    /**
     * 创建仓库
     * @param code
     * @param name
     * @param category
     * @param qualityControlSwitch
     */
    public static String createWarehouse(String code,String name,int category,boolean qualityControlSwitch) {
        HashMap<String, String> body = new HashMap<String, String>();
        body.put("code", code);
        body.put("name", name);
        body.put("category", category+"");
        body.put("qualityControlSwitch", qualityControlSwitch+"");
        RequestObject.postRequest(Environment.server_def,"/v1/warehouse", body);
        System.out.println(code);
        return code;
    }
}
