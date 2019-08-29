package params;

import blacklake.materialLot.Admit;
import config.DataPrepare;
import org.testng.annotations.DataProvider;

public class ScanUseParams {

    @DataProvider
    public Object[][] getScanUseDate(){
        Admit.admit(DataPrepare.materialCode2,"",DataPrepare.storageId);
        return new Object[][]{

        };
    }
}
