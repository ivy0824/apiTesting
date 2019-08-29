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
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static common.RequestObject.workstationId;

public class CreateData {

    @Test(priority = 0)
    void createOrg() {
        // 创建工厂
        ValidatableResponse response = Org.createOrg(DataPrepare.orgCode, DataPrepare.orgCode);
        //判断工厂是否创建成功
        RequestObject.getStatus(response, 200);
        int orgId = response.extract().path("data.id");

        //将创建工厂的获取的 orgId 赋值到RequestObject方法中
        RequestObject.orgId = orgId;

        //创建用户
        ValidatableResponse response1 = Org.createAdmin(orgId);
        //判断用户是否创建成功
        RequestObject.getStatus(response1, 200);
        int userId = response1.extract().path("data.id");

        //将创建工厂的获取的 userId 赋值到RequestObject方法中
        RequestObject.userId = userId;

        //修改用户角色
        CreateUser.updateUser(userId, "系统管理员", "admin");
    }
    @Test(priority = 1)
    void createWorkStationAndStorage() {
        //创建工位
        ValidatableResponse response2 = Workshop.createWorkshop(DataPrepare.workshopCode, DataPrepare.workshopCode);
        //判断车间是否创建成功
        RequestObject.getStatus(response2, 200);
        int workshopId = response2.extract().path("data.id");
        Workshop.updateWorkshopStatus(workshopId);
        int prodLineId = ProdLine.createProdLine(DataPrepare.prodLineCode, DataPrepare.prodLineCode, workshopId).extract().path("data.id");
        ProdLine.updateProdLineStatus(prodLineId);
        ValidatableResponse response3 = Workstation.createWorkstation(DataPrepare.workstationCode, DataPrepare.workstationCode, prodLineId);
        //判断工位是否创建成功
        RequestObject.getStatus(response3, 200);
        int workstationId = response3.extract().path("data.id");

        //存储工位信息
        RequestObject.workstationId = workstationId;

        //
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
    void createEbom(){
        //创建物料
        ValidatableResponse response5 = Material.createMaterial(DataPrepare.materialCode1,DataPrepare.materialCode1);
        RequestObject.getStatus(response5,200);
        Material.createMaterial(DataPrepare.materialCode2,DataPrepare.materialCode2);
        Material.createMaterial(DataPrepare.materialCode3,DataPrepare.materialCode3);
        Material.createMaterial(DataPrepare.materialCode4,DataPrepare.materialCode4);

        // 创建物料清单
        ValidatableResponse response6 = EBOM.createEbom(DataPrepare.materialCode1,DataPrepare.materialCode2,DataPrepare.materialCode3,DataPrepare.materialCode4);
        //判断物料清单是够创建成功
        RequestObject.getStatus(response6,200);
    }
//    @Test(priority = 3)
//    void createMbom(){
//        //创建物料
//        ValidatableResponse response5 = Material.createMaterial(DataPrepare.materialCode1,DataPrepare.materialCode1);
//        RequestObject.getStatus(response5,200);
//        Material.createMaterial(DataPrepare.materialCode2,DataPrepare.materialCode2);
//        Material.createMaterial(DataPrepare.materialCode3,DataPrepare.materialCode3);
//        Material.createMaterial(DataPrepare.materialCode4,DataPrepare.materialCode4);
//
//        // 创建物料清单
//        ValidatableResponse response6 = EBOM.createEbom(DataPrepare.materialCode1,DataPrepare.materialCode2,DataPrepare.materialCode3,DataPrepare.materialCode4);
//        //判断物料清单是够创建成功
//        RequestObject.getStatus(response6,200);
//    }

    @Test(priority = 4)
    void createProcessRoute(){
        //创建工序 workstationId
        int[] workstations = {workstationId};
        Processs.createProcess(DataPrepare.processCode1,"两次扫码","2","0",false, workstations);
        Processs.createProcess(DataPrepare.processCode2,"单词扫码","1","1",false, workstations);
        Processs.createProcess(DataPrepare.processCode3,"一码到底","2","1",true, workstations);

        //创建工艺路线
        ValidatableResponse response7 = ProcessRoute.createProcessRoute(DataPrepare.processRouteCode,"工艺路线",workstations,DataPrepare.processCode1,DataPrepare.processCode2,DataPrepare.processCode3);
        //判断工艺路线是够创建成功
        RequestObject.getStatus(response7,200);
        ProcessRoute.updateProcessRouteStatus(DataPrepare.processRouteCode);
    }

    @Test(priority = 5)
        //创建使用工艺路线的计划工单
    void createTaskWithProcessRoute(){
        //创建计划工单
        PlannedTicket.createPlannedTicket(DataPrepare.PlannedTicketAmount,DataPrepare.PlannedTicketCode1,DataPrepare.materialCode1,DataPrepare.processRouteCode,null,null,"processRoute");

        //排程工序
        ValidatableResponse response8 = TaskSchedule.createScheduleTask(DataPrepare.PlannedTicketCode1,"1");
        RequestObject.getStatus(response8,200);
        String taskCode1 = response8.extract().path("data.tasks[0].taskCode");
        String taskCode2 = TaskSchedule.createScheduleTask(DataPrepare.PlannedTicketCode1,"2").extract().path("data.tasks[0].taskCode");
        String taskCode3 = TaskSchedule.createScheduleTask(DataPrepare.PlannedTicketCode1,"3").extract().path("data.tasks[0].taskCode");


        //下发工序
        ValidatableResponse response9 =  TaskSchedule.distuibute(taskCode1);
        RequestObject.getStatus(response9,200);
        TaskSchedule.distuibute(taskCode2);
        TaskSchedule.distuibute(taskCode3);

        //开始项目
        ValidatableResponse response10 = Project.UpdateProject(DataPrepare.PlannedTicketCode1,"created","running");
        RequestObject.getStatus(response10,200);

        //获取任务id
        int taskId1 = getByCode.getTaskId(taskCode1,DataPrepare.PlannedTicketCode1);
        DataPrepare .PId1 =  taskId1;
        System.out.println(taskCode1);
        int taskId2 = getByCode.getTaskId(taskCode2,DataPrepare.PlannedTicketCode1);
        DataPrepare .PId2 =  taskId2;
        int taskId3 = getByCode.getTaskId(taskCode3,DataPrepare.PlannedTicketCode1);
        DataPrepare .PId3 =  taskId3;
    }
    @Test(priority = 5)
    //创建使用工艺路线+物料清单的计划工单
    void createTaskWithProcessRouteEbom(){
        //创建计划工单
        PlannedTicket.createPlannedTicket(DataPrepare.PlannedTicketAmount,DataPrepare.PlannedTicketCode2,DataPrepare.materialCode1,DataPrepare.processRouteCode,"001",null,"processRouteEbom");

        //排程工序
        ValidatableResponse response1 = TaskSchedule.createScheduleTask(DataPrepare.PlannedTicketCode2,"1");
        RequestObject.getStatus(response1,200);
        String taskCode1 = response1.extract().path("data.tasks[0].taskCode");
        String taskCode2 = TaskSchedule.createScheduleTask(DataPrepare.PlannedTicketCode2,"2").extract().path("data.tasks[0].taskCode");
        String taskCode3 = TaskSchedule.createScheduleTask(DataPrepare.PlannedTicketCode2,"3").extract().path("data.tasks[0].taskCode");

        //下发工序
        ValidatableResponse response9 =  TaskSchedule.distuibute(taskCode1);
        RequestObject.getStatus(response9,200);
        TaskSchedule.distuibute(taskCode2);
        TaskSchedule.distuibute(taskCode3);

        //开始项目
        ValidatableResponse response10 = Project.UpdateProject(DataPrepare.PlannedTicketCode2,"created","running");
        RequestObject.getStatus(response10,200);

        //获取任务id
        int taskId1 = getByCode.getTaskId(taskCode1,DataPrepare.PlannedTicketCode2);
        DataPrepare .PEId1 =  taskId1;
        int taskId2 = getByCode.getTaskId(taskCode2,DataPrepare.PlannedTicketCode2);
        DataPrepare .PEId2 =  taskId2;
        int taskId3 = getByCode.getTaskId(taskCode3,DataPrepare.PlannedTicketCode2);
        DataPrepare .PEId3 =  taskId3;

    }

//    @Test(priority = 6)


}
