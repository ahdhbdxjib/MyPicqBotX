package com.idhclub.picq;

import cc.moecraft.icq.PicqBotX;
import com.idhclub.picq.listener.MyListener;
import com.idhclub.picq.schedule.MySchedule;
import com.idhclub.picq.singleton.SingletonBot;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;


public class Application {
    private static Logger logger = Logger.getLogger(Application.class);


    public static void main(String[] args) {
        PicqBotX botX = SingletonBot.getInstance();

        botX.addAccount("bot1", "127.0.0.1", 31091);

        botX.getEventManager().registerListeners(new MyListener());

        // 启用指令管理器
        // 这些字符串是指令前缀, 比如指令"!help"的前缀就是"!"
        botX.enableCommandManager("bot -", "!", "/", "~");

        botX.startBot();
        try {
            scheduleStart();
        } catch (Exception e) {
            logger.error("schedule start faild");
        }
        botX.getAccountManager().getNonAccountSpecifiedApi().sendPrivateMsg(1074792207L, "机器人启动成功~~");
        logger.info("bot start success");
    }

    public static void scheduleStart() throws SchedulerException, InterruptedException {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        // 开始
        scheduler.start();
        // job 唯一标识 test.test-1
        JobKey jobKey = new JobKey("job41", "group1");
        JobDetail jobDetail = JobBuilder.newJob(MySchedule.class).withIdentity(jobKey).build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/30 * * * ? "))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);

        scheduler.start();

    }


}
