package preCondition;

import blacklake.def.Material;
import common.RequestObject;
import config.DataPrepare;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

public class CreateMaterial {

    @Test(priority = 2)
    void createMaterial(){
        //创建物料
        ValidatableResponse response5 = Material.createMaterial(DataPrepare.materialCode1,DataPrepare.materialCode1);
        RequestObject.getStatus(response5,200);
        Material.createMaterial(DataPrepare.materialCode2,DataPrepare.materialCode2);
        Material.createMaterial(DataPrepare.materialCode3,DataPrepare.materialCode3);
        Material.createMaterial(DataPrepare.materialCode4,DataPrepare.materialCode4);

    }
}
