package params.manufacture;


import blacklake.manufacture.produce_task.getByCode;
import config.DataPrepare;
import config.ManufactureData;
import org.testng.annotations.DataProvider;

public class ObloseteParams extends ManufactureData {

    static int PEId1= getByCode.getTaskId(taskCode4,DataPrepare.PlannedTicketCode2) ;

    @DataProvider
    public  Object[][] getObsoleteDate(){


        return new Object[][]{
                {PEId1,10, DataPrepare.unitName, 1, true, 200, ""},//部分投产成功
                {PEId1, 0, DataPrepare.unitName, 1, true, 400, "数量必须大于0"},
                {PEId1, 100, DataPrepare.unitName, 1, true, 400, "投产数量 100 已超过物料总量 90，请检查"},
                {PEId1, 100, DataPrepare.unitName, 1, true, 400, "eBom中没有该物料，不能投产"}
        };
    }
}
