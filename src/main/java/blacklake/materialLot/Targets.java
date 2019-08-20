package blacklake.materialLot;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

//忽略序列化问题
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class Targets {

    private String code;
    private Object amount;

    public Targets(String code,Object amount) {
        this.code = code;
        this.amount = amount;
    }

    public String toString() {
        return "targets{" +
                "code='" + code + '\'' +
                ",amount='" + amount + '\'' +
                '}';
    }

}
