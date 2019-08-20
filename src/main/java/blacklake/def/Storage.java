package blacklake.def;

import config.Environment;
import common.RequestObject;

import java.util.HashMap;

public class Storage {

    /*
    @Param code
    @Param name
    @Param parentCode
    @Param level
     */
    public static String createStorage1(String code,String name,String parentCode,int level) {
        HashMap<String, String> body = new HashMap<String, String>();
        body.put("code", code);
        body.put("name", name);
        body.put("parentCode", parentCode);
        body.put("level", level+"");
        RequestObject.postRequest(Environment.server_def,"/v1/storage", body);
        return code;
    }

    public static String createStorage2(String code,String name,String parentCode,int level) {
        HashMap<String, String> body = new HashMap<String, String>();
        body.put("code", code);
        body.put("name", name);
        body.put("parentCode", parentCode);
        body.put("level", level+"");
        RequestObject.postRequest(Environment.server_def,"/v1/storage", body);
        return code;
    }
}
