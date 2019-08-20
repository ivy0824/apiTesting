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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
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