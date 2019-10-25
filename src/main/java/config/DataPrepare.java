package config;


import blacklake.def.Storage;
import blacklake.def.Unit;
import blacklake.manufacture.produce_task.getByCode;

public class DataPrepare {

    static String randomNum = "101703";

    //保留基础数据
    public static int orgId = 283;
    public static int userId = 12251;
    public static String userName = "admin";
    public static String unitName = "kg";

    //创建基础数据
    public static String orgCode = "827009";//6位数字
    public static String workshopCode = randomNum+"917011";
    public static String prodLineCode =randomNum+"917011-1";
    public static String workstationCode =randomNum+"917011-1-1";
    public static String warehouseCode =randomNum+"w91701";
    public static String storage1Code =randomNum+"w91701-1";
    public static String storage2Code =randomNum+"w91701-1-1";
    public static String materialCode1 =randomNum+"C91701";
    public static String materialCode2 =randomNum+"C91702";
    public static String materialCode3 =randomNum+"C91703";
    public static String materialCode4 =randomNum+"C91704";
    public static String materialCode5 =randomNum+"C91705";
    public static String eBOMVerison =randomNum+"91701";
    public static String processCode1 =randomNum+"P91701";//两次扫码
    public static String processCode2 =randomNum+"P91702";//单次扫码
    public static String processCode3 =randomNum+"P91703";//一码到底
    public static String processRouteCode1 =randomNum+"PR917001";
    public static String processRouteCode2 =randomNum+"PR917002";
    public static String processRouteCode3 =randomNum+"PR917003";
    public static String mBOMVerison =randomNum+"91804";
    public static String PlannedTicketCode1 =randomNum+"PT91701";//计划工单编号-工艺路线
    public static String PlannedTicketCode2 =randomNum+"PT91702";//计划工单编号-工艺路线+物料清单
    public static String PlannedTicketCode3 =randomNum+"PT91703";
    public static int PlannedTicketAmount = 100;//计划工单产出数量
    public static String selectType = "processRoute";//processRoute,processRouteEbom,mbom


//    public static int unitId = Unit.getUnitId(unitName);
//    public static int storageId = Storage.getStorageId(storage2Code);




}
