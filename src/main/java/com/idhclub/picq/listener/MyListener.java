package com.idhclub.picq.listener;

import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.event.events.message.EventPrivateMessage;
import com.idhclub.picq.pojo.ShopifyStats;
import com.idhclub.picq.spider.GetProductData;
import org.apache.log4j.Logger;

public class MyListener extends IcqListener {

    private static Logger logger = Logger.getLogger(MyListener.class);
    @EventHandler
    public void sendMsg(EventPrivateMessage privateMessage){
        logger.info("get message");
        if(privateMessage.getMessage().equals("czw")){
            ShopifyStats data =  GetProductData.getDta();
            privateMessage.respond(data.toString(), false);
            for (String product:
                 data.getProducts()) {
                privateMessage.respond(product, false);
            }
            privateMessage.respond("***********************", false);
        }


    }
}
