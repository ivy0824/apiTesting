package blacklake.materialLot;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

//忽略序列化问题
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Deliver {
    private String materialCode;
    private int[] materialLotIdList;

    public Deliver(String materialCode, int[] materialLotIdList) {
        this.materialCode = materialCode;
        this.materialLotIdList = materialLotIdList;
    }

    @Override
    public String toString() {
        return "Deliver{" +
                "materialCode='" + materialCode + '\'' +
                ", materialLotIdList='" + materialLotIdList + '\'' +
                '}';
    }


}
