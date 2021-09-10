package service;

import java.io.FileNotFoundException;

/**
 * 音效类
 * @author Lamzy
 */
public class Sound {
    static final String DIR = "src/music/";//音乐文件夹
    static final String BACKGROUND = "background.wav";//背景音乐
    static final String JUMP = "jump.wav";//跳跃音乐
    static final String HIT = "hit.wav";//撞击音乐

    /**
     * 播放跳跃音乐
     */
    public static void jump(){
        play(DIR + JUMP , false);//播放一次跳跃音效
    }

    /**
     * 播放装机音效
     */
    public static void hit(){
        play(DIR + HIT , false);//播放一次撞击音效
    }

    /**
     * 播放背景音乐
     */
    public static void background(){
        play(DIR + BACKGROUND , true);//循环播放背景音乐
    }

    /**
     * 播放
     * @param file 音乐完成名称
     * @param circulate 是否循环播放
     */
    private static void play(String file, boolean circulate){
        try {
            MusicPlayer player = new MusicPlayer(file , circulate);
            player.play();//播放器开始播放
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


}
