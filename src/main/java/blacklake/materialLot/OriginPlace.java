package blacklake.materialLot;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

//忽略序列化问题
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class OriginPlace {

    private String province;
    private String city;

    public OriginPlace() {

    }

    public OriginPlace(String province,String city) {
        this.province = province;
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String toString() {
        return "OriginPlace{" +
                "province='" + province + '\'' +
                "city='" + city + '\'' +

                '}';
    }
}
