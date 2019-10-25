package preCondition;

import blacklake.def.*;
import blacklake.lakers.Org;
import blacklake.manufacture.Project;
import blacklake.manufacture.produce_task.getByCode;
import blacklake.schedule.PlannedTicket;
import blacklake.schedule.TaskSchedule;
import blacklake.user.CreateUser;
import config.DataPrepare;
import common.RequestObject;
import config.ManufactureData;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import java.util.ArrayList;

import static blacklake.def.Workstation.workstationId;
import static config.DataPrepare.unitName;
//import static common.RequestObject.workstationId;

public class CreateData {

//   CreateData @Test(priority = 0)
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

    @Test(priority = 2)
    void createMaterial(){
        //创建物料
        ValidatableResponse response5 = Material.createMaterial(DataPrepare.materialCode1,DataPrepare.materialCode1);
        RequestObject.getStatus(response5,200);
        Material.createMaterial(DataPrepare.materialCode2,DataPrepare.materialCode2);
        Material.createMaterial(DataPrepare.materialCode3,DataPrepare.materialCode3);
        Material.createMaterial(DataPrepare.materialCode4,DataPrepare.materialCode4);

    }
    @Test(priority = 3)
    void createEbom(){
        // 创建物料清单
        ValidatableResponse response6 = EBOM.createEbom(DataPrepare.eBOMVerison,DataPrepare.materialCode1,DataPrepare.materialCode2,DataPrepare.materialCode3,DataPrepare.materialCode4);
        //判断物料清单是够创建成功
        RequestObject.getStatus(response6,200);
    }

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

    static int unitId = Unit.getUnitId(unitName);

    @Test(priority = 4)//创建两次扫码为首道工序的MBOM
    void createMbom2(){
        int[] workstations = {workstationId};
        ArrayList inputMaterial = MBOM.inputMaterial(DataPrepare.materialCode2,DataPrepare.materialCode3,DataPrepare.materialCode4);
        ArrayList processList = MBOM.processList(workstations,"",inputMaterial,DataPrepare.processCode1,DataPrepare.processCode2,DataPrepare.processCode3);
        ValidatableResponse response = MBOM.createMbom(DataPrepare.materialCode1,unitId,DataPrepare.mBOMVerison,DataPrepare.eBOMVerison,DataPrepare.processRouteCode1,processList);
        ValidatableResponse response1 = MBOM.updateMBOM(response.extract().path("data.id"));
        RequestObject.getStatus(response1,200);
    }

    @Test(priority = 4)//创建单次扫码为首道工序的MBOM
    void createMbom1(){
        int[] workstations = {workstationId};
        ArrayList inputMaterial = MBOM.inputMaterial(DataPrepare.materialCode2,DataPrepare.materialCode3,DataPrepare.materialCode4);
        ArrayList processList = MBOM.processList(workstations,DataPrepare.materialCode2,inputMaterial,DataPrepare.processCode2,DataPrepare.processCode1,DataPrepare.processCode3);
        ValidatableResponse response = MBOM.createMbom(DataPrepare.materialCode1,unitId,DataPrepare.mBOMVerison+1_2,DataPrepare.eBOMVerison,DataPrepare.processRouteCode2,processList);
        ValidatableResponse response1 = MBOM.updateMBOM(response.extract().path("data.id"));
        RequestObject.getStatus(response1,200);
    }

    @Test(priority = 4)//创建一码到底为首道工序的MBOM
    void createMbom1_2(){
        int[] workstations = {workstationId};
        ArrayList inputMaterial = MBOM.inputMaterial(DataPrepare.materialCode2,DataPrepare.materialCode3,DataPrepare.materialCode4);
        ArrayList processList = MBOM.processList(workstations,DataPrepare.materialCode2,inputMaterial,DataPrepare.processCode3,DataPrepare.processCode1,DataPrepare.processCode3);
        ValidatableResponse response = MBOM.createMbom(DataPrepare.materialCode1,unitId,DataPrepare.mBOMVerison+2,DataPrepare.eBOMVerison,DataPrepare.processRouteCode3,processList);
        ValidatableResponse response1 = MBOM.updateMBOM(response.extract().path("data.id"));
        RequestObject.getStatus(response1,200);
    }

    @Test(priority = 5)
        //创建使用工艺路线的计划工单
    void createTaskWithProcessRoute() {
        //创建计划工单
        PlannedTicket.createPlannedTicket(DataPrepare.PlannedTicketAmount, DataPrepare.PlannedTicketCode1, DataPrepare.materialCode1, DataPrepare.processRouteCode1, null, null, "processRoute");

        //排程工序
        ValidatableResponse response8 = TaskSchedule.createScheduleTask(DataPrepare.PlannedTicketCode1, "1");
        RequestObject.getStatus(response8, 200);
        String taskCode1 = response8.extract().path("data.tasks[0].taskCode");
        String taskCode2 = TaskSchedule.createScheduleTask(DataPrepare.PlannedTicketCode1, "2").extract().path("data.tasks[0].taskCode");
        String taskCode3 = TaskSchedule.createScheduleTask(DataPrepare.PlannedTicketCode1, "3").extract().path("data.tasks[0].taskCode");

        //把获取的taskcode传到manufactureData中
        ManufactureData.taskCode1 = taskCode1;
        ManufactureData.taskCode2 = taskCode2;
        ManufactureData.taskCode3 = taskCode3;


        //下发工序
        ValidatableResponse response9 = TaskSchedule.distuibute(taskCode1);
        RequestObject.getStatus(response9, 200);
        TaskSchedule.distuibute(taskCode2);
        TaskSchedule.distuibute(taskCode3);

        //开始项目
        ValidatableResponse response10 = Project.UpdateProject(DataPrepare.PlannedTicketCode1, "created", "running");
        RequestObject.getStatus(response10, 200);
    }

        @Test(priority = 5)
        //创建使用工艺路线+物料清单的计划工单
        void createTaskWithProcessRouteEbom() {
            //创建计划工单
            PlannedTicket.createPlannedTicket(DataPrepare.PlannedTicketAmount, DataPrepare.PlannedTicketCode2, DataPrepare.materialCode1, DataPrepare.processRouteCode1, DataPrepare.eBOMVerison, null, "processRouteEbom");

            //排程工序
            ValidatableResponse response1 = TaskSchedule.createScheduleTask(DataPrepare.PlannedTicketCode2, "1");
            RequestObject.getStatus(response1, 200);
            String taskCode1 = response1.extract().path("data.tasks[0].taskCode");
            String taskCode2 = TaskSchedule.createScheduleTask(DataPrepare.PlannedTicketCode2, "2").extract().path("data.tasks[0].taskCode");
            String taskCode3 = TaskSchedule.createScheduleTask(DataPrepare.PlannedTicketCode2, "3").extract().path("data.tasks[0].taskCode");

            //把获取的taskcode传到manufactureData中
            ManufactureData.taskCode4 = taskCode1;
            ManufactureData.taskCode5 = taskCode2;
            ManufactureData.taskCode6 = taskCode3;

            //下发工序
            ValidatableResponse response9 = TaskSchedule.distuibute(taskCode1);
            RequestObject.getStatus(response9, 200);
            TaskSchedule.distuibute(taskCode2);
            TaskSchedule.distuibute(taskCode3);

            //开始项目
            ValidatableResponse response10 = Project.UpdateProject(DataPrepare.PlannedTicketCode2, "created", "running");
            RequestObject.getStatus(response10, 200);
        }

    @Test(priority = 5)
        //创建使用生产BOM的计划工单
    void createTaskWithMbom() {
        //创建计划工单
        PlannedTicket.createPlannedTicket(DataPrepare.PlannedTicketAmount, DataPrepare.PlannedTicketCode3, DataPrepare.materialCode1, null, null, DataPrepare.mBOMVerison, "mbom");

        //排程工序
        ValidatableResponse response1 = TaskSchedule.createScheduleTask(DataPrepare.PlannedTicketCode3, "1");
        RequestObject.getStatus(response1, 200);
        String taskCode1 = response1.extract().path("data.tasks[0].taskCode");
        String taskCode2 = TaskSchedule.createScheduleTask(DataPrepare.PlannedTicketCode3, "2").extract().path("data.tasks[0].taskCode");
        String taskCode3 = TaskSchedule.createScheduleTask(DataPrepare.PlannedTicketCode3, "3").extract().path("data.tasks[0].taskCode");

        //把获取的taskcode传到manufactureData中
        ManufactureData.taskCode7 = taskCode1;
        ManufactureData.taskCode8 = taskCode2;
        ManufactureData.taskCode9 = taskCode3;

        //下发工序
        ValidatableResponse response9 = TaskSchedule.distuibute(taskCode1);
        RequestObject.getStatus(response9, 200);
        TaskSchedule.distuibute(taskCode2);
        TaskSchedule.distuibute(taskCode3);

        //开始项目
        ValidatableResponse response10 = Project.UpdateProject(DataPrepare.PlannedTicketCode3, "created", "running");
        RequestObject.getStatus(response10, 200);
    }
}
