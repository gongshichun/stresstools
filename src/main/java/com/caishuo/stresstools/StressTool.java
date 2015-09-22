package com.caishuo.stresstools;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.PropertyConfigurator;

/**
 * 启动类
 * 
 * @author Tim
 */
public class StressTool {
    /**
     * @param args
     * @throws  
     */
    public static void main(String[] args) {
        //加载Log4j
        PropertyConfigurator.configure("config/log4j.properties");

        try {
            //读取配置文件
            CompositeConfiguration conf = new CompositeConfiguration();
            conf.addConfiguration(new PropertiesConfiguration("config/comm.properties"));

            //解析文件
            Config config = new Config();
            config.sendThread = conf.getInt("sendthread");
            config.sendCount = conf.getInt("sendcount");
            config.interval = conf.getInt("interval");
            config.recordCnt = conf.getInt("recordCnt");
            config.url = conf.getString("url");

            //委托下单信息
            OrderValue order = new OrderValue();
            order.broker_id = conf.getString("broker_id");
            order.caishuo_id = conf.getString("caishuo_id");
            order.function_code = conf.getString("function_code");
            order.itn_broker_id = conf.getString("itn_broker_id");
            order.order_id = conf.getString("order_id");

            //下单dataMap
            order.data.put("entrust_prop", conf.getString("entrust_prop"));
            order.data.put("entrust_bs", conf.getString("entrust_bs"));
            order.data.put("entrust_price", conf.getString("entrust_price"));
            order.data.put("trade_session", conf.getString("trade_session"));
            order.data.put("exchange_type", conf.getString("exchange_type"));
            order.data.put("stock_account", conf.getString("stock_account"));
            order.data.put("stock_code", conf.getString("stock_code"));
            order.data.put("entrust_amount", conf.getString("entrust_amount"));

            //启用线程下单
            for (int i = 0; i < config.sendThread; i++) {
                WorkThread workThread = new WorkThread(config, order);
                workThread.start();
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
