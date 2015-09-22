package com.caishuo.stresstools;

import java.util.HashMap;
import java.util.Map;

public class OrderValue {
    //brokerId
    public String broker_id;

    //caishuoId
    public String caishuo_id;

    //功能号
    public String function_code = "";

    //itn_broker_id
    public String itn_broker_id;

    //orderId
    public String order_id;

    //data
    public Map<String, String> data = new HashMap<String, String>();
}
