package blacklake.def;


import common.JsonReader;

import java.util.HashMap;

public class InputMaterials {
    private String materialCode;
    private int amount;
    private int currentUnitId;
    private int materialProductionMode;

    public InputMaterials(String materialCode, int amount, int currentUnitId, int materialProductionMode) {
        this.materialCode = materialCode;
        this.amount = amount;
        this.currentUnitId = currentUnitId;
        this.materialProductionMode = materialProductionMode;

    }

    /**
     * 读取ArrayJSon
     * @param materialCode
     * @return
     */
//    public static String inputMaterials(String...materialCode){
//        HashMap<String,Object> map=new HashMap();
//        map.put("[0].materialCode",materialCode[0]);
//        map.put("[1].materialCode",materialCode[1]);
//        map.put("[2].materialCode",materialCode[2]);
//        String body = JsonReader.getJsonToString("/data/def/inputMaterial.json",map);
//        return body;
//    }

}
