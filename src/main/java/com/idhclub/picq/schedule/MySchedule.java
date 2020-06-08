package com.idhclub.picq.schedule;


import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.sender.IcqHttpApi;
import com.idhclub.picq.Application;
import com.idhclub.picq.pojo.ShopifyStats;
import com.idhclub.picq.singleton.SingletonBot;
import com.idhclub.picq.spider.GetProductData;
import com.idhclub.picq.utils.SendMsgUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MySchedule implements Job {
    private static Logger logger = Logger.getLogger(Application.class);

    static String domain ="***********************";
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        PicqBotX botX = SingletonBot.getInstance();
        logger.info("定时任务执行成功");
        ShopifyStats data = GetProductData.getDta();
        if (data.equals("") || data.toString().length() < 5) {
//            SendMsgUtils.sendPrivateMsg(botX, 471825757L, "数据暂时未获取到");
//            SendMsgUtils.sendPrivateMsg(botX, 1074792207L, "数据暂时未获取到");
            botX.getAccountManager().getNonAccountSpecifiedApi().sendPrivateMsg(471825757L, "数据暂时未获取到");
            botX.getAccountManager().getNonAccountSpecifiedApi().sendPrivateMsg(1074792207L, "数据暂时未获取到");
        }


        botX.getAccountManager().getNonAccountSpecifiedApi().sendPrivateMsg(471825757L, data.toString());
        for (String product:
                data.getProducts()) {
            botX.getAccountManager().getNonAccountSpecifiedApi().sendPrivateMsg(471825757L, product);
        }
        botX.getAccountManager().getNonAccountSpecifiedApi().sendPrivateMsg(471825757L, domain);



        botX.getAccountManager().getNonAccountSpecifiedApi().sendPrivateMsg(1074792207L, data.toString());
        for (String product:
                data.getProducts()) {
            botX.getAccountManager().getNonAccountSpecifiedApi().sendPrivateMsg(1074792207L, product);
        }
        botX.getAccountManager().getNonAccountSpecifiedApi().sendPrivateMsg(1074792207L, domain);

    }
}
