package view;

import modle.Dinosaur;
import service.ScoreRecorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 成绩对话框
 * @author Lamzy
 */
public class ScoreDialog extends JDialog {

    /**
     * 构造方法
     * @param frame 父窗体
     */
    public ScoreDialog(JFrame frame){
        super(frame , true);//调用父类构造方法，阻塞父类窗体
        int[] scores = ScoreRecorder.getScores();//获取当前前三名成绩
        JPanel scoreP = new JPanel(new GridLayout(4,1));//成绩面板4行1列
        scoreP.setBackground(Color.WHITE);//白色背景
        JLabel title = new JLabel("得分排行榜" , JLabel.CENTER);//标题标签，居中显示
        title.setFont(new Font("黑体" , Font.BOLD , 20));//设置字体
        title.setForeground(Color.RED);//设置前景色，就是字体颜色
        JLabel first = new JLabel("第一名" + scores[2] , JLabel.CENTER);//第一名
        JLabel second = new JLabel("第二名" + scores[1] , JLabel.CENTER);//第二名
        JLabel third = new JLabel("第三名" + scores[0] , JLabel.CENTER);//第三名
        JButton restart = new JButton("重新开始");//重新开始按钮
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //监听
                dispose();//摧毁对话框
            }
        });
        scoreP.add(title);
        scoreP.add(first);
        scoreP.add(second);
        scoreP.add(third);
        Container c = getContentPane();//获取主容器
        c.setLayout(new BorderLayout());//设置边界布局
        c.add(scoreP , BorderLayout.CENTER);//成绩面板在中间
        setTitle("游戏结束");//对话框标题
        int width , height;
        width = height = 200;//对话框高度均为200
        //获得主窗体中居中位置的横坐标
        int x = frame.getX() + (frame.getWidth() - width) / 2;
        int y = frame.getY() + (frame.getHeight() - height) / 2;
        setBounds(x , y , width , height);
        setVisible(true);//显示对话框
    }
}
