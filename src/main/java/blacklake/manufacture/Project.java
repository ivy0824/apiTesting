package blacklake.manufacture;

import common.RequestObject;
import config.Environment;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;

public class Project {

    /**
     * 更改项目状态："created"，"running"，"paused"，"done"，
     * @param projectCode
     * @param fromStatus
     * @param toStatus
     */
    public static ValidatableResponse UpdateProject(String projectCode, String fromStatus, String toStatus) {
        HashMap<String, Object> body = new HashMap<String, Object>();
        body.put("fromStatus", fromStatus);
        body.put("toStatus", toStatus);
        ValidatableResponse response = RequestObject.putRequest(Environment.server_manufacture, "/v1/project/"+projectCode+"/status", body);
        return response;

    }
}
