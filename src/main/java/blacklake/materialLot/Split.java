package blacklake.materialLot;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;

//忽略序列化问题
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class Split {

    private int sourceMaterialLotId;
    private ArrayList<Targets> targets;

    public Split(int sourceMaterialLotId,ArrayList<Targets> targets) {
        this.sourceMaterialLotId = sourceMaterialLotId;
        this.targets = targets;
    }

    public String toString() {
        return "split{" +
                "sourceMaterialLotId='" + sourceMaterialLotId + '\'' +
                ",targets='" + targets + '\'' +
                '}';
    }
}
