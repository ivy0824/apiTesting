package blacklake.schedule;

import common.JsonReader;
import common.RequestObject;
import config.Environment;

import java.util.HashMap;

public class PlannedTicket {

    static int[] Id = {RequestObject.userId};

    /**
     * 创建计划工单
     * @param amount
     * @param code
     * @param materialCode
     * @param processRouteCode
     * @param selectType
     * @return
     */
    public static void createPlannedTicket(int amount, String code, String materialCode, String processRouteCode,String ebomVersion,String mbomVersion, String selectType) {

        HashMap<String, Object> map = new HashMap<String, Object>();
        int[] id = new int[1];
        id[0] = RequestObject.userId;
        map.put("amount", amount);
        map.put("code", code);
        map.put("materialCode", materialCode);
        map.put("processRouteCode", processRouteCode);
        map.put("ebomVersion",ebomVersion);
        map.put("mbomVersion",mbomVersion);
        map.put("selectType", selectType);
        map.put("managerId",id);
        map.put("plannerId", id);
        String result;
        HashMap<String, Object> body = new HashMap<String, Object>();
        result = JsonReader.getJsonToString("/data/schedule/workOrder.json",map);
        body = JsonReader.stringToMap(result);
        if(ebomVersion==null & mbomVersion==null){
            body.remove("ebomVersion");
            body.remove("mbomVersion");
            RequestObject.postRequest(Environment.server_scheduling, "/v1/work_order", body);
        }else if(mbomVersion==null) {
            body.remove("mbomVersion");
            RequestObject.postRequest(Environment.server_scheduling, "/v1/work_order", body);
        }else if(processRouteCode==null & ebomVersion==null){
            body.remove("processRouteCode");
            body.remove("ebomVersion");
            RequestObject.postRequest(Environment.server_scheduling, "/v1/work_order", body);
        }

    }
}
