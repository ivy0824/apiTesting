package common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import config.DataPrepare;
import config.Environment;
import io.restassured.response.ValidatableResponse;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class RequestObject {

//     1. baseUrl
    public static final String url = Environment.url;

    //common data
    public static int orgId = DataPrepare.orgId;
    public static int userId = DataPrepare.userId;
    public static String userName = "admin";


    /**
     * post请求方法,创建工厂专用的请求
     * @param path
     * @param body
     */
    public static ValidatableResponse post(String server, String path, Object body){

        return given()
                .contentType("application/json")
                .body(body)
                .log().all()
                .when().post("http://"+server+url + path)
                .then()
                .log().all();
    }

    /**
     * post请求方法,通用
     * @param path
     * @param body
     */
    public static ValidatableResponse postRequest(String server, String path, Object body){

        return given()
                .contentType("application/json")
                .header("X-Org-Id", orgId)
                .header("X-User-Id",userId)
                .header("X-User-Name",userName)
                .body(body)
                .log().all()
                .when().post("http://"+server+url + path)
                .then()
                .log().all();
    }

    /**
     * put请求方法
     * @param path
     * @param body
     */
    public static ValidatableResponse putRequest(String server, String path, Object body){

        return given()
                .contentType("application/json")
                .header("X-Org-Id", orgId)
                .header("X-User-Id",userId)
                .header("X-User-Name",userName)
                .body(body)
                .log().all()
                .when().put("http://"+server+url + path)
                .then()
                .log().all();
    }

    /**
     * patch请求
     * @param server
     * @param path
     * @param body
     * @return
     */

    public static ValidatableResponse patchRequest(String server, String path, Object body){

        return given()
                .contentType("application/json")
                .header("X-Org-Id", orgId)
                .header("X-User-Id",userId)
                .header("X-User-Name",userName)
                .body(body)
                .log().all()
                .when().patch("http://"+server+url + path)
                .then()
                .log().all();
    }

    public static ValidatableResponse getRequest(String server, String path, HashMap param){

        return given()
                .contentType("application/json")
                .header("X-Org-Id", orgId)
                .header("X-User-Id",userId)
                .header("X-User-Name",userName)
                .params(param)
                .log().all()
                .when().get("http://"+server+url + path)
                .then()
                .log().all();
    }


    /**
     * 判断返回状态是多少
     * @param response
     * @param statusCode
     */
    public static void getStatus(ValidatableResponse response, int statusCode){
        response.statusCode(statusCode);
    }


    /**
     * 判断返回响应体信息
     * @param response
     * @param jsonPath
     * @param responseMessage  String
     */
    public static void getResponseMessage(ValidatableResponse response, String jsonPath, String responseMessage){
        response.body(jsonPath, equalTo(responseMessage));
    }

    /**
     * 判断返回响应体信息
     * @param response
     * @param jsonPath
     * @param responseMessage  int
     */
    public static void getResponseMessage(ValidatableResponse response, String jsonPath, int responseMessage){
        response.body(jsonPath, equalTo(responseMessage));
    }

    /**
     * 判断返回响应体信息是否包含某些信息
     * @param response
     * @param jsonPath
     * @param responseMessage
     */
    public static void getContainsString(ValidatableResponse response, String jsonPath, String responseMessage){
        response.body(jsonPath, containsString(responseMessage));
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }
}