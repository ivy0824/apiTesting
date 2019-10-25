package blacklake.manufacture.produce_task;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import common.RequestObject;
import config.Environment;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class Obsolete {
    private int taskId;
    private String[] codes;

    public Obsolete(int taskId, String[] codes) {
        this.taskId = taskId;
        this.codes = codes;
    }

    public static ValidatableResponse obsolete(int taskId, String[] codes){
        HashMap<String,Object> body=new HashMap<String, Object>();
        body.put("taskId",taskId);
        body.put("codes",codes);
        ValidatableResponse response = RequestObject.postRequest(Environment.server_manufacture,"/v1/produce/material/"+taskId+"/obsolete",body);
        return response;
    }
}
