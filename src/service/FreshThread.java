package service;

import view.GamePanel;
import view.MainFrame;
import view.ScoreDialog;

import java.awt.*;

/**
 * 刷新帧线程
 * @author Lamzy
 */
public class FreshThread extends Thread{
    public static final int FREASH = 20;//刷新时间
    GamePanel p;//游戏面板

    public FreshThread(GamePanel p){
        this.p = p;
    }

    @Override
    public void run() {
        super.run();
        while (!p.isFinish()){
            //游戏还没结束
            p.repaint();//重绘游戏面板
            try {
                Thread.sleep(FREASH);//按照刷新时间休眠
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Container c = p.getParent();//获取面板父容器
        while(!(c instanceof MainFrame)){//如果父容器不是主面板
            c = c.getParent();//继续获取父容器
        }
        MainFrame frame = (MainFrame) c;//将容器强制转换为主窗体类
        new ScoreDialog(frame);//弹出得分对话框
        frame.restart();//主窗体重新开始游戏
    }
}
