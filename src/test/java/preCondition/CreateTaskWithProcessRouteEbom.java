package preCondition;

import blacklake.manufacture.Project;
import blacklake.manufacture.produce_task.getByCode;
import blacklake.schedule.PlannedTicket;
import blacklake.schedule.TaskSchedule;
import common.RequestObject;
import config.DataPrepare;
import config.ManufactureData;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

public class CreateTaskWithProcessRouteEbom {

    @Test(priority = 5)
        //创建使用工艺路线+物料清单的计划工单
    void createTaskWithProcessRouteEbom(){
        //创建计划工单
        PlannedTicket.createPlannedTicket(DataPrepare.PlannedTicketAmount,DataPrepare.PlannedTicketCode2,DataPrepare.materialCode1,DataPrepare.processRouteCode1,DataPrepare.eBOMVerison,null,"processRouteEbom");

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
//        int taskId1 = getByCode.getTaskId(taskCode1,DataPrepare.PlannedTicketCode2);
//        ManufactureData.PEId1 =  taskId1;
//        int taskId2 = getByCode.getTaskId(taskCode2,DataPrepare.PlannedTicketCode2);
//        ManufactureData .PEId2 =  taskId2;
//        int taskId3 = getByCode.getTaskId(taskCode3,DataPrepare.PlannedTicketCode2);
//        ManufactureData .PEId3 =  taskId3;

    }
}
