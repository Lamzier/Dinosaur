package view;

import service.ScoreRecorder;
import service.Sound;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * 主窗体
 * @author Lamzy
 */
public class MainFrame extends JFrame {//继承窗体类

    public MainFrame(){
        //构造方法
        setVisible(true);
        restart();//开始游戏
        setSize(820,300);//设置大小
        setLocationRelativeTo(null);//居中显示
        setTitle("RUN!!!");//这是标题
        Sound.background();//播放背景音乐
        ScoreRecorder.init();//读取得分记录
        addListener();//添加监听方法 , 窗体监听
        setDefaultCloseOperation(EXIT_ON_CLOSE);//关闭停止窗体运行
    }

    /**
     * 重新开始
     */
    public void restart(){
        Container c = getContentPane();//获取对象主容器对象
        c.removeAll();//删除容器所有组件
        GamePanel panel = new GamePanel();//创建新的游戏面板
        c.add(panel);
        addKeyListener(panel);//添加键盘监听事件
        c.validate();//验证所有容器组件
    }

    /**
     * 添加监听
     */
    private void addListener(){
        addWindowListener(new WindowListener() {//添加监听
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {//窗体正在关闭时
                ScoreRecorder.saveScore();//保存比分
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }
}
