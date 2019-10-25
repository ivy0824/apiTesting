package manufacture.produce;

import blacklake.manufacture.ScanUse;
import blacklake.manufacture.produce_task.getByCode;
import common.RequestObject;
import config.DataPrepare;
import config.ManufactureData;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import params.manufacture.ScanUseParams;

import static config.ManufactureData.taskCode4;

public class TestScanUse {

    static int PEId1= getByCode.getTaskId(taskCode4,DataPrepare.PlannedTicketCode2) ;

    @Test(dataProvider = "getScanUseDate",dataProviderClass = ScanUseParams.class)
    private void testScanUse(int taskId,String code,int amount,String unit,int useMode,Boolean forced,int status,String msg){
        System.out.println(msg);
        ValidatableResponse response = ScanUse.scanUse(taskId,code,amount,unit,useMode);
        RequestObject.getStatus(response,status);
        RequestObject.getResponseMessage(response,"message",msg);

        ScanUse.inputSummary(PEId1);
    }


}
