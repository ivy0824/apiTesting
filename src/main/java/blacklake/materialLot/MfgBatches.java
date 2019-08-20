package blacklake.materialLot;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

//忽略序列化问题
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class MfgBatches {

    private String mfgBatchNo;

    public MfgBatches(String mfgBatchNo) {
        this.mfgBatchNo = mfgBatchNo;
    }

    public String getMfgBatchNo() {
        return mfgBatchNo;
    }

    public void setMfgBatchNo(String mfgBatchNo) {
        this.mfgBatchNo = mfgBatchNo;
    }

    public String toString() {
        return "MfgBatches{" +
                "mfgBatchNo='" + mfgBatchNo + '\'' +

                '}';
    }
}
