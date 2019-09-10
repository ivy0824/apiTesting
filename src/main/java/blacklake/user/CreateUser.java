package blacklake.user;

import config.Environment;
import io.restassured.response.ValidatableResponse;
import common.JsonReader;
import common.RequestObject;

import java.util.HashMap;

public class CreateUser {

    public static ValidatableResponse updateUser(int userId,String name,String username){
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("name",name);
        map.put("username",username);
        String body = JsonReader.getJsonToString("/data/user/updateUserRole.json",map);
        ValidatableResponse response = RequestObject.patchRequest(Environment.server_user,"/v1/users/"+userId,body);
        return response;
    }


}
