package manufacture.produce;

import blacklake.manufacture.ScanUse;
import common.RequestObject;
import config.DataPrepare;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import params.ScanUseParams;

public class TestScanUse {

    @Test(dataProvider = "getScanUseDate",dataProviderClass = ScanUseParams.class)
    private void testScanUse(int taskId,String code,int amount,String unit,int useMode,Boolean forced,int status,String msg){
        System.out.println(msg);
        ValidatableResponse response = ScanUse.scanUse(taskId,code,amount,unit,useMode);
        RequestObject.getStatus(response,status);
        RequestObject.getResponseMessage(response,"message",msg);

        ScanUse.inputSummary(DataPrepare.PEId1);
    }

}
