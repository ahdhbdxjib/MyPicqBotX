package com.idhclub.picq.singleton;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.PicqConfig;

public class SingletonBot {
    static PicqConfig config = new PicqConfig(31092).setDebug(true);
    static PicqBotX botX = new PicqBotX(config);
    public static PicqBotX getInstance() {
        return botX;
    }
}
