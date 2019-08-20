package blacklake.schedule;

import common.RequestObject;
import config.Environment;

import java.util.HashMap;

public class PlannedTicket {

    /**
     * 创建计划工单
     * @param amount
     * @param code
     * @param materialCode
     * @param processRouteCode
     * @param selectType
     * @return
     */
    public static void createPlannedTicket(int amount, String code, String materialCode, String processRouteCode, String selectType) {

        HashMap<String, Object> body = new HashMap<String, Object>();
        int[] id = new int[1];
        id[0] = RequestObject.userId;
        body.put("amount", amount);
        body.put("code", code);
        body.put("materialCode", materialCode);
        body.put("processRouteCode", processRouteCode);
        body.put("selectType", selectType);
        body.put("managerId",id);
        body.put("plannerId", id);
        body.put("type", 1);
        body.put("priority", 1);
        RequestObject.postRequest(Environment.server_scheduling, "/v1/work_order", body);

    }
}
