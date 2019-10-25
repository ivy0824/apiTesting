package preCondition;

import blacklake.def.ProcessRoute;
import blacklake.def.Processs;
import common.RequestObject;
import config.DataPrepare;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static blacklake.def.Workstation.workstationId;


public class CreateProcessRoute {

    @Test(priority = 3)
    void createProcessRoute(){
        //创建工序 workstationId
        int[] workstations = {workstationId};
        Processs.createProcess(DataPrepare.processCode1,"两次扫码","2","0",false, workstations);
        Processs.createProcess(DataPrepare.processCode2,"单词扫码","1","1",false, workstations);
        Processs.createProcess(DataPrepare.processCode3,"一码到底","2","1",true, workstations);

        //创建两次扫码开始的工艺路线
        ValidatableResponse response1 = ProcessRoute.createProcessRoute(DataPrepare.processRouteCode1,"工艺路线",workstations,DataPrepare.processCode1,DataPrepare.processCode2,DataPrepare.processCode3);
        //判断工艺路线是够创建成功
//        RequestObject.getStatus(response1,200);
        ProcessRoute.updateProcessRouteStatus(DataPrepare.processRouteCode1);

        //创建单次扫码开始的工艺路线
        ValidatableResponse response2 = ProcessRoute.createProcessRoute(DataPrepare.processRouteCode2,"工艺路线",workstations,DataPrepare.processCode2,DataPrepare.processCode1,DataPrepare.processCode3);
        //判断工艺路线是够创建成功
        RequestObject.getStatus(response2,200);
        ProcessRoute.updateProcessRouteStatus(DataPrepare.processRouteCode2);

        //创建一码到底扫码开始的工艺路线
        ValidatableResponse response3 = ProcessRoute.createProcessRoute(DataPrepare.processRouteCode3,"工艺路线",workstations,DataPrepare.processCode3,DataPrepare.processCode1,DataPrepare.processCode3);
        //判断工艺路线是够创建成功
        RequestObject.getStatus(response3,200);
        ProcessRoute.updateProcessRouteStatus(DataPrepare.processRouteCode3);

    }
}
