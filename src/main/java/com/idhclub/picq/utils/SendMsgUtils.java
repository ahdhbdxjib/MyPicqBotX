package com.idhclub.picq.utils;

import cc.moecraft.icq.PicqBotX;

import java.util.List;

public class SendMsgUtils {

    public static void sendPrivateMsg(PicqBotX botX,long QQ,String msg){
        botX.getAccountManager().getNonAccountSpecifiedApi().sendPrivateMsg(QQ, msg);
    }


    public static void sendPrivateMsg(PicqBotX botX, long QQ, List<String> msg){
        for (String item :
                msg) {
            botX.getAccountManager().getNonAccountSpecifiedApi().sendPrivateMsg(QQ, item);
        }

    }
}
