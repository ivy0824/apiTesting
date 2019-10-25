package preCondition;

import blacklake.def.EBOM;
import common.RequestObject;
import config.DataPrepare;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

public class CreateEbom {

    @Test(priority = 3)
    void createEbom(){
        // 创建物料清单
        ValidatableResponse response6 = EBOM.createEbom(DataPrepare.eBOMVerison,DataPrepare.materialCode1,DataPrepare.materialCode2,DataPrepare.materialCode3,DataPrepare.materialCode4);
        //判断物料清单是够创建成功
        RequestObject.getStatus(response6,200);
    }
}
