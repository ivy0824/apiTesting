package blacklake.materialLot;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

//忽略序列化问题
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class CodeAndAmount {
    private Double amount;
    private String code;
    private String unit;

    public CodeAndAmount(Double amount, String code, String unit) {
        this.amount = amount;
        this.code = code;
        this.unit = unit;
    }

    public String toString() {
        return "CodeAndAmount{" +
                "amount='" + amount + '\'' +
                ",code='" + code + '\'' +
                ",unit;" + unit + '\'' +
                '}';
    }
}