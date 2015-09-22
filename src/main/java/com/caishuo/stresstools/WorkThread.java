package com.caishuo.stresstools;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * 工作线程
 * 
 * @author Tim
 *
 */
public class WorkThread extends Thread {
    private Logger log = LoggerFactory.getLogger(WorkThread.class);

    private Config config;

    private OrderValue orderValue;

    private Gson gson = new Gson();

    public WorkThread(Config config, OrderValue orderValue) {
        this.config = config;
        this.orderValue = orderValue;
    }

    /**
     * 主运行
     */
    @SuppressWarnings("all")
    public void run() {
        int count = config.sendCount / config.sendThread;

        long beginTime = System.currentTimeMillis();
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(config.url);
        for (int i = 0; i < count; i++) {
            //执行
            try {
                StringRequestEntity entity =
                    new StringRequestEntity(gson.toJson(orderValue), "application/json", "utf-8");

                post.setRequestEntity(entity);
                client.executeMethod(post);

                String result = new String(post.getResponseBodyAsString().getBytes("utf-8"));
                log.info(result);

                //休眠间隔
                if (config.interval > 0) {
                    Thread.sleep(config.interval);
                } //if (config.interval > 0)
            } catch (Exception e) {
                log.error("stressTool Error!", e);
            }
        }

        log.info(
            "execute finish and total time = " + (System.currentTimeMillis() - beginTime) + "ms");
    }
}
