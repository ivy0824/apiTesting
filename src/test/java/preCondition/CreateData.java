package preCondition;

import blacklake.manufacture.Project;
import blacklake.schedule.PlannedTicket;
import blacklake.schedule.TaskSchedule;
import common.RequestObject;
import org.testng.annotations.Test;

public class CreateData {

    @Test
    void createData() {
        // 创建工厂
//        ValidatableResponse response = Org.createOrg("812004", "812004");
//        int orgId = response.extract().path("data.id");
//
//        //将创建工厂的获取的 orgId 赋值到RequestObject方法中
//        RequestObject.orgId = orgId;
//
//        // 创建用户
//        ValidatableResponse response1 = Org.createAdmin(orgId);
//        int userId = response1.extract().path("data.id");
//
//        //将创建工厂的获取的 userId 赋值到RequestObject方法中
//        RequestObject.userId = userId;
//
//
//        //创建工位
//        int workshopId = Workshop.createWorkshop("042504","042504").extract().path("data.id");
//        Workshop.updateWorkshopStatus(workshopId);
//        int prodLineId = ProdLine.createProdLine("042504-2","042504-2",workshopId).extract().path("data.id");
//        ProdLine.updateProdLineStatus(prodLineId);
//        int workstationId = Workstation.createWorkstation("042504-2-1","042504-2-1",prodLineId).extract().path("data.id");
//        RequestObject.workstationId = workstationId;
//        Workstation.updateWorkstationStatus(workstationId);
//        Workstation.addRelationUser("PS", RequestObject.userId,2337);
//
//        //创建仓位
//        Warehouse.createWarehouse("0510001","0510001",1,true);
//        Storage.createStorage1("0510001-1","0510001-1","051001",1);
//        Storage.createStorage2("0510001-1-1","0510001-1-1","0510001-1",2);
//
////        创建单位
//        Unit.createUnit("kg","kg");
//
////        创建物料
//        Material.createMaterial("material242301","material242301");
//        Material.createMaterial("material242302","material242302");
//        Material.createMaterial("material242303","material242303");
//        Material.createMaterial("material242304","material242304");

//        创建物料清单
//        EBOM.createEbom("codematerial242301","codematerial242302","codematerial242303","codematerial242304");

//        创建工序 workstationId
        int[] workstations = {2337};
//        Processs.createProcess("819001","两次扫码81901","2",false, workstations);
//        Processs.createProcess("819002","单词扫码81902","1",false, workstations);
//        Processs.createProcess("819003","一码到底81903","2",true, workstations);

        //创建工艺路线
//        ProcessRoute.createProcessRoute("00000002","00000002",workstations,"819001","819002","819003");
//        ProcessRoute.updateProcessRouteStatus("00000002");

        //创建计划工单
//        PlannedTicket.createPlannedTicket(1000,"81902","codematerial242301","00000002","processRoute");

        //排程工序
//        String taskCode1 = TaskSchedule.createScheduleTask("81902","1").extract().path("data.tasks[0].taskCode");
//        String taskCode2 = TaskSchedule.createScheduleTask("81902","2").extract().path("data.tasks[0].taskCode");
//        String taskCode3 = TaskSchedule.createScheduleTask("81902","3").extract().path("data.tasks[0].taskCode");

        //下发工序
//        TaskSchedule.distuibute(taskCode1);
//        TaskSchedule.distuibute(taskCode2);
//        TaskSchedule.distuibute(taskCode3);

        //开始项目
//        Project.UpdateProject(81902,"created","running");




    }
}
