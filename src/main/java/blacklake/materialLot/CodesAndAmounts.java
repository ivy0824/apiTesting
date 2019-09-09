package blacklake.materialLot;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

//忽略序列化问题
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class CodesAndAmounts {
    private Double amount;
    private String code;

    public CodesAndAmounts(Double amount, String code) {
        this.amount = amount;
        this.code = code;
    }

    public String toString() {
        return "CodesAndAmounts{" +
                "amount='" + amount + '\'' +
                ",code='" + code + '\'' +
                '}';
    }
}