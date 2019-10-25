package preCondition;

import blacklake.def.*;
import common.RequestObject;
import config.DataPrepare;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

public class CreateWorkStationAndStorage {

    @Test(priority = 1)
    void createWorkStationAndStorage() {

        //创建车间
        ValidatableResponse response2 = Workshop.createWorkshop(DataPrepare.workshopCode, DataPrepare.workshopCode);
        //判断车间是否创建成功
        RequestObject.getStatus(response2, 200);
        //启用车间
        int workshopId = response2.extract().path("data.id");
        Workshop.updateWorkshopStatus(workshopId);

        //创建产线并判断产线是否创建成功
        int prodLineId = ProdLine.createProdLine(DataPrepare.prodLineCode, DataPrepare.prodLineCode, workshopId).extract().path("data.id");
        ProdLine.updateProdLineStatus(prodLineId);
        ValidatableResponse response3 = Workstation.createWorkstation(DataPrepare.workstationCode, DataPrepare.workstationCode, prodLineId);

        //创建工位并判断工位是否创建成功
        RequestObject.getStatus(response3, 200);
        int workstationId = response3.extract().path("data.id");
        Workstation.updateWorkstationStatus(workstationId);

        //增加工位的排程员
        Workstation.addRelationUser("PS", RequestObject.userId, workstationId);

        //创建仓位
        Warehouse.createWarehouse(DataPrepare.warehouseCode, DataPrepare.warehouseCode, 1, true);
        Storage.createStorage1(DataPrepare.storage1Code, DataPrepare.storage1Code, DataPrepare.warehouseCode, 1);
        ValidatableResponse response4 = Storage.createStorage2(DataPrepare.storage2Code, DataPrepare.storage2Code, DataPrepare.storage1Code, 2);
        //判断二级仓位是够创建成功
        RequestObject.getStatus(response4, 200);
    }
}
