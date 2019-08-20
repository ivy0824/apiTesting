package blacklake.user;

import config.Environment;
import io.restassured.response.ValidatableResponse;
import common.JsonReader;
import common.RequestObject;

import java.util.HashMap;

public class CreateUser {

    public static ValidatableResponse createUser(String name, String username){
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("name",name);
        map.put("username",username);
        String body = JsonReader.getJson("/data/lakers/createOrg.json",map);
        ValidatableResponse response = RequestObject.postRequest(Environment.server_user,"/v1/users",body);
        return response;

    }

}
