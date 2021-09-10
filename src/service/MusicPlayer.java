package service;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 音乐播放器
 * @author Lamzy
 */
public class MusicPlayer implements  Runnable{
    File soundFile;//音乐文件
    Thread thread;//父线程，音乐线程
    boolean circulate;//是否循环播放

    /**
     * 默认不循环播放
     * @param filepath
     * @throws FileNotFoundException 完整音乐文件名称
     */
    public MusicPlayer(String filepath) throws FileNotFoundException{
        this(filepath , false);

    }

    public MusicPlayer(String filepath, boolean circulate)throws FileNotFoundException {
        this.circulate = circulate;
        soundFile = new File(filepath);
        if (!soundFile.exists()){
            //文件不存在
            throw new FileNotFoundException(filepath + "未找到文件");
        }
    }

    @Override
    public void run() {
        byte[] auBuffer = new byte[1024 * 128];//创建128k缓冲区
        do{
            AudioInputStream audioInputStream = null;//创建音频输入流
            SourceDataLine auline = null;//混音器源数据行
            try {
                audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                AudioFormat format = audioInputStream.getFormat();//获取音频格式
                //按照源数据行类型和指定音频格式创建数据行对象
                DataLine.Info info = new DataLine.Info(SourceDataLine.class , format);
                //利用音频系统类获与指定lineInfo 对象中的描述匹配行，并转换为源数据行类型
                auline = (SourceDataLine) AudioSystem.getLine(info);
                auline.open(format);//按照指定格式打开源数据行
                auline.start();//源数据行开启读写活动
                int byteCount = 0;//记录音频输入流读出字节数
                while(byteCount != -1){//如果音频输入流不为-1
                    //从音频数据流中读出128k的数据
                    byteCount = audioInputStream.read(auBuffer, 0,auBuffer.length);
                    if (byteCount >= 0){
                        //如果读出有效数据
                        auline.write(auBuffer , 0 , byteCount);//将有效数据写入数据行中
                    }
                }
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }finally {
                auline.drain();//清空数据行
                auline.close();//关闭数据行
            }
        }while(circulate);//根据循环标志是否循环播放
    }

    /**
     * 播放
     */
    public void play(){
        thread = new Thread(this);//创建线程对象
        thread.start();//启动
    }

    /**
     * 停止播放
     */
    public void stop(){
        thread.stop();//强制关闭线程
    }


}
