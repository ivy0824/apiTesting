package blacklake.manufacture.produce_task;


import common.RequestObject;
import config.Environment;
import io.restassured.response.ValidatableResponse;
import java.util.HashMap;

public class getByCode {
    public static int getTaskId(String taskCode, String projectCode){
        HashMap<String, Object> body = new HashMap<String, Object>();
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("taskCode",taskCode);
        param.put("projectCode",projectCode);
        int taskId = RequestObject.getRequest(Environment.server_manufacture, "/v1/project/produce_task/_get_by_code",param).extract().path("data.id");
        return taskId;
    }

    public static ValidatableResponse getTaskInfo(String taskCode, String projectCode){
        HashMap<String, Object> body = new HashMap<String, Object>();
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("taskCode",taskCode);
        param.put("projectCode",projectCode);
        ValidatableResponse response = RequestObject.getRequest(Environment.server_manufacture, "/v1/project/produce_task/_get_by_code",param);
        return response;
    }
}
