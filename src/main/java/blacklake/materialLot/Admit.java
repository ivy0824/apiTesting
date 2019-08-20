package blacklake.materialLot;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import common.RequestObject;
import config.Environment;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/*
 * materialCode
 * codeAndAmount
 * MfgBatches
 * storageId
 * validityPeriod
 * supplierCode：	00000001
 * originPlace
 * remark
 */
//忽略序列化问题
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class Admit {

    private String materialCode;
    private ArrayList<CodeAndAmount> codeAndAmount;
    private ArrayList<MfgBatches> mfgBatches;
    private Object storageId;
    private Date validityPeriod;
    private String supplierCode;
    private OriginPlace originPlace;
    private String remark;

    public void admitAll(String materialCode, ArrayList<CodeAndAmount> codeAndAmount, ArrayList<MfgBatches> MfgBatches, Object storageId, Date validityPeriod, String supplierCode, OriginPlace originPlace, String remark ) {
        this.materialCode = materialCode;
        this.codeAndAmount = codeAndAmount;
        this.mfgBatches = MfgBatches;
        this.storageId = storageId;
        this.validityPeriod = validityPeriod;
        this.supplierCode = supplierCode;
        this.originPlace = originPlace;
        this.remark = remark;
    }

    public void admitNeed(String materialCode, ArrayList<CodeAndAmount> codeAndAmount, Object storageId ) {
        this.materialCode = materialCode;
        this.codeAndAmount = codeAndAmount;
        this.storageId = storageId;
    }

    public static HashMap<String,Integer> admit(String materialCode, ArrayList codeAndAmount, Object storageId){

        Admit admit = new Admit();
        admit.admitNeed(materialCode,codeAndAmount,storageId);
        System.out.println(admit.toString());
        HashMap<String, Integer> allIds = RequestObject.postRequest(Environment.server_manufacture,"/v2/materialLot/_admit", admit).extract().path("data");
        System.out.println(allIds);
        return allIds;
    }

    public String toString() {
        return "Admit{" +
                "materialCode='" + materialCode + '\'' +
                ",codeAndAmount='" + codeAndAmount + '\'' +
                ",MfgBatches;" + mfgBatches + '\''+
                ", storageId=" + storageId + '\'' +
                ", validityPeriod=" + validityPeriod + '\'' +
                ", supplierCode=" + supplierCode + '\'' +
                ", originPlace=" + originPlace + '\'' +
                ", remark=" + remark + '\'' +
                '}';
    }
}
