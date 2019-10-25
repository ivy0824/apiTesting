package preCondition;

import blacklake.def.MBOM;
import blacklake.def.Unit;
import common.RequestObject;
import config.DataPrepare;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import java.util.ArrayList;

import static blacklake.def.Workstation.workstationId;
import static config.DataPrepare.unitName;

public class CreateMbom {

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
}
