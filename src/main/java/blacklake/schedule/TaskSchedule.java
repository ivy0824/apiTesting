package blacklake.schedule;

import common.JsonReader;
import common.RequestObject;
import config.Environment;
import io.restassured.response.ValidatableResponse;
import java.util.HashMap;

public class TaskSchedule {

    static Boolean locked = false;
    static int planAmount = 100;
    static int workstation = RequestObject.workstationId;
    static int[] executorsIds = {RequestObject.userId};
    static String planBeginTime = "1566208800000";
    static String planEndTime = "1566209100000";

    /**
     * 排程工序任务
     * @param workOrderCode
     * @param processSeq
     * @return
     */
    public static ValidatableResponse createScheduleTask(String workOrderCode, String processSeq) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("[0].workOrderCode", workOrderCode);
        map.put("[0].processSeq", processSeq);
        map.put("[0].planAmount",planAmount);
        map.put("[0].workstationId",workstation);
        map.put("[0].planBeginTime",planBeginTime);
        map.put("[0].planEndTime",planEndTime);
        map.put("[0].executorIds",executorsIds);
        String body = JsonReader.getJson("/data/schedule/taskSchedule.json",map);
        ValidatableResponse response = RequestObject.postRequest(Environment.server_scheduling, "/v1/tasks/_bulk_manual", body);
        return response;
    }

    /**
     * 下发工序任务
     * @param code
     */
    public static ValidatableResponse distuibute(String code){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("tasks[0].code",code);
        String body = JsonReader.getJson("/data/schedule/distribute.json",map);
        ValidatableResponse response = RequestObject.putRequest(Environment.server_scheduling, "/v1/tasks/_bulk_distribute", body);
        return response;
    }

}
