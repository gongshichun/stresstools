package com.caishuo.stresstools;

public class Config {
    //并发线程个数
    public int sendThread = 1;

    //总发送次数
    public int sendCount = 1;

    //发送间隔
    public int interval = 0;

    //data域数，数值范围[1,n]，1表示单域，>1表示每个datapack包有多域
    public int recordCnt = 1;

    //url
    public String url;
}
