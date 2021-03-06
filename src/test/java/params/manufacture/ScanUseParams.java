package params.manufacture;

import blacklake.def.Storage;
import blacklake.manufacture.produce_task.getByCode;
import blacklake.materialLot.Admit;
import blacklake.materialLot.CodesAndAmounts;
import config.DataPrepare;
import config.ManufactureData;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;

import static config.DataPrepare.storage2Code;
import static config.ManufactureData.taskCode4;

public class ScanUseParams {

    static int storageId = Storage.getStorageId(storage2Code);
    static int PEId1= getByCode.getTaskId(taskCode4,DataPrepare.PlannedTicketCode2) ;

    /*
    工序+物料创建的项目的任务验证数据
     */
    @DataProvider
    public <codesAndAmounts> Object[][] getScanUseDate(){

        //创建入厂二维码数据
        String [] materialCodes = {DataPrepare.materialCode2,DataPrepare.materialCode5};
        String qcCode = (int) (Math.random() * 100000) + "";

         for (int i = 0 ;i < materialCodes.length; i++) {
             ArrayList<CodesAndAmounts> codesAndAmounts = new ArrayList<CodesAndAmounts>();
             CodesAndAmounts codesAndAmountsM1 = new CodesAndAmounts(100.0, qcCode+i);
             codesAndAmounts.add(codesAndAmountsM1);
             Admit.admit(materialCodes[i], codesAndAmounts,storageId);
         }
        return new Object[][]{
                {PEId1, qcCode+0, 10, DataPrepare.unitName, 1, true, 200, ""},//部分投产成功
                {PEId1, qcCode+0, 0, DataPrepare.unitName, 1, true, 400, "数量必须大于0"},
                {PEId1, qcCode+0, 100, DataPrepare.unitName, 1, true, 400, "投产数量 100 已超过物料总量 90，请检查"},
                {PEId1, qcCode+1, 100, DataPrepare.unitName, 1, true, 400, "eBom中没有该物料，不能投产"}
        };
    }
}
