package com.daqsoft.config;

/** 一些用到的常量
 * Created by Administrator on 2017/7/11 0011.
 */
public class ConstantWords {

    public final  static  String TASK_H = "h";

    public  final  static  int STATUS =1;

    public  final  static  int HTTP_SESSFUL_STATUS =0;
    public  final  static  int HTTP_ERROR_STATUS =1;
    public  final  static  Long REDIS_TIMEUNIT =60*60*60L;

    public  final  static  String REDIS_SESSION_KEY ="REDIS_SESSION_KEY_ACCESS_TOKEN";


    public  final  static  String URL_SESSION_KEY ="sessionid";
    public  final  static  String URL_DAY_KEY ="date_id";

    public  final  static  String DAY_H ="yyyyMMddhh";
    public  final  static  String DAY_D ="yyyyMMdd";

    public  final  static  String NULL_EXPRESSION = "空指针";
    public  final  static  String URL_EXPRESSION = "获取session失败；";

    public  final  static  int TABLE_UPDAYE_ROW_STATUS_TRUE =0;
    public  final  static  int TABLE_UPDAYE_ROW_STATUS_FALSE =1;


}
