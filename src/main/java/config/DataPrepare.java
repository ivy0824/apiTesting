package config;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DataPrepare {

    //创建基础数据
    public static String orgCode = "827009";//6位数字
    public static String workshopCode ="82701";
    public static String prodLineCode ="82701-1";
    public static String workstationCode ="82701-1-1";
    public static String warehouseCode ="w82701";
    public static String storage1Code ="w82701-1";
    public static String storage2Code ="w82701-1-1";
    public static String materialCode1 ="C82701";
    public static String materialCode2 ="C82702";
    public static String materialCode3 ="C82703";
    public static String materialCode4 ="C82704";
    public static String materialCode5 ="C82705";
    public static String processCode1 ="P82701";//两次扫码
    public static String processCode2 ="P82702";//单次扫码
    public static String processCode3 ="P82703";//一码到底
    public static String processRouteCode ="PR82701";
    public static String PlannedTicketCode1 ="PT82911";//计划工单编号-工艺路线
    public static String PlannedTicketCode2 ="PT829122";//计划工单编号-工艺路线+物料清单
    public static String PlannedTicketCode3 ="PT82703";
    public static int PlannedTicketAmount = 100;//计划工单产出数量
    public static String selectType = "processRoute";//processRoute,processRouteEbom,mbom

    //保留基础数据
    public static int orgId = 283;
    public static int userId = 12251;
    public static String userName = "admin";
    public static int workstationId = 4634;
    public static int storageId = 12743;
    public static String unitName = "单位4776";

    //创建任务获取的任务id
    //根据工艺路线创建的任务id
    public static int PId1 ;
    public static int PId2;
    public static int PId3;
    //根据工艺路线+物料清单创建的任务id
    public static int PEId1 = 134110;
    public static int PEId2 = 134111;
    public static int PEId3 = 134112;



}
