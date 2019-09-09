package blacklake.materialLot;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;

//忽略序列化问题
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class PurchaseAdmit {

    private int procureOrderId;
    private int procureOrderDetailId;
    private ArrayList<CodesAndAmounts> codesAndAmounts;
    private String supplierCode;
    private String materialCode;
    private Object storageId;


    public PurchaseAdmit(int procureOrderId, int procureOrderDetailId, ArrayList<CodesAndAmounts> codesAndAmounts, String supplierCode, String materialCode, Object storageId ) {
        this.procureOrderId = procureOrderId;
        this.procureOrderDetailId = procureOrderDetailId;
        this.codesAndAmounts = codesAndAmounts;
        this.supplierCode = supplierCode;
        this.materialCode = materialCode;
        this.storageId = storageId;
    }

    public String toString() {
        return "Admit{" +
                "procureOrderId='" + procureOrderId + '\''+
                ",procureOrderDetailId='" + procureOrderDetailId + '\''+
                ",codesAndAmounts='" + codesAndAmounts + '\'' +
                ",supplierCode;" + supplierCode + '\''+
                ",materialCode='" + materialCode + '\'' +
                ",storageId=" + storageId +
                '}';
    }
}
