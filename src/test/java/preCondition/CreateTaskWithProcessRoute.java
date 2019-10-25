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

public class CreateTaskWithProcessRoute {


    @Test(priority = 5)
        //创建使用工艺路线的计划工单
    void createTaskWithProcessRoute(){
        //创建计划工单
        PlannedTicket.createPlannedTicket(DataPrepare.PlannedTicketAmount,DataPrepare.PlannedTicketCode1,DataPrepare.materialCode1,DataPrepare.processRouteCode1,null,null,"processRoute");

        //排程工序
        ValidatableResponse response8 = TaskSchedule.createScheduleTask(DataPrepare.PlannedTicketCode1,"1");
        RequestObject.getStatus(response8,200);
        String taskCode1 = response8.extract().path("data.tasks[0].taskCode");
        String taskCode2 = TaskSchedule.createScheduleTask(DataPrepare.PlannedTicketCode1,"2").extract().path("data.tasks[0].taskCode");
        String taskCode3 = TaskSchedule.createScheduleTask(DataPrepare.PlannedTicketCode1,"3").extract().path("data.tasks[0].taskCode");

        //把获取的taskcode传到manufactureData中
        ManufactureData.taskCode1=taskCode1;
        ManufactureData.taskCode2=taskCode2;
        ManufactureData.taskCode3=taskCode3;


        //下发工序
        ValidatableResponse response9 =  TaskSchedule.distuibute(taskCode1);
        RequestObject.getStatus(response9,200);
        TaskSchedule.distuibute(taskCode2);
        TaskSchedule.distuibute(taskCode3);

        //开始项目
        ValidatableResponse response10 = Project.UpdateProject(DataPrepare.PlannedTicketCode1,"created","running");
        RequestObject.getStatus(response10,200);

//        //获取任务id
//        int taskId1 = getByCode.getTaskId(taskCode1,DataPrepare.PlannedTicketCode1);
//        ManufactureData.PId1 =  taskId1;
//        System.out.println(taskCode1);
//        int taskId2 = getByCode.getTaskId(taskCode2,DataPrepare.PlannedTicketCode1);
//        ManufactureData .PId2 =  taskId2;
//        int taskId3 = getByCode.getTaskId(taskCode3,DataPrepare.PlannedTicketCode1);
//        ManufactureData .PId3 =  taskId3;
    }
}
