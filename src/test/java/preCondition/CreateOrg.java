package preCondition;

import blacklake.lakers.Org;
import blacklake.user.CreateUser;
import common.RequestObject;
import config.DataPrepare;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

//public class CreateOrg {
//    @Test(priority = 0)
//    void createOrg() {
//        // 创建工厂
//        ValidatableResponse response = Org.createOrg(DataPrepare.orgCode, DataPrepare.orgCode);
//        //判断工厂是否创建成功
//        RequestObject.getStatus(response, 200);
//        int orgId = response.extract().path("data.id");
//
//        //将创建工厂的获取的 orgId 赋值到RequestObject方法中
//        RequestObject.orgId = orgId;
//
//        //创建用户
//        ValidatableResponse response1 = Org.createAdmin(orgId);
//        //判断用户是否创建成功
//        RequestObject.getStatus(response1, 200);
//        int userId = response1.extract().path("data.id");
//
//        //将创建工厂的获取的 userId 赋值到RequestObject方法中
//        RequestObject.userId = userId;
//
//        //修改用户角色
//        CreateUser.updateUser(userId, "系统管理员", "admin");
//    }
//}
