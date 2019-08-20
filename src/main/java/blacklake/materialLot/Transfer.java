package blacklake.materialLot;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

//忽略序列化问题
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class Transfer {

    private int inStorageId;
    private int materialLotIdList[];
    private int materialLotContainerIdList[];

    public Transfer(int inStorageId, int materialLotIdList[], int materialLotContainerIdList[]) {
        this.inStorageId = inStorageId;
        this.materialLotIdList = materialLotIdList;
        this.materialLotContainerIdList = materialLotContainerIdList;
    }

    public String toString() {
        return "split{" +
                "inStorageId='" + inStorageId + '\'' +
                ",materialLotIdList='" + materialLotIdList + '\'' +
                ",materialLotContainerIdList='" + materialLotContainerIdList + '\'' +
                '}';
    }
}
