package modle;

import service.FreshThread;
import service.Sound;

import javax.imageio.ImageIO;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 恐龙类
 * @author Lamzy
 */
public class Dinosaur {
    public BufferedImage image;//主图片
    private BufferedImage image1 , image2 , image3;//跑步图片
    public int x, y;//坐标
    private int jumpValue = 0;//跳跃的增变量
    private  boolean jumpState = false;//跳跃状态
    private  int stepTimer = 0;//踏步计时器
    private final int JUMP_HIGHT = 100;//最大起跳高度
    private final int LOWSET_Y = 120;//落地最低坐标
    private  final int FREASH = FreshThread.FREASH;//刷新时间

    public Dinosaur(){
        //构造方法
        x = 50;
        y = LOWSET_Y;
        try{
            image1 = ImageIO.read(new File("src/image/恐龙1.png"));
            image2 = ImageIO.read(new File("src/image/恐龙2.png"));
            image3 = ImageIO.read(new File("src/image/恐龙3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move(){
        //移动
        step();//不断踏步
        if (jumpState){//正在跳跃
            if (y >= LOWSET_Y){//如果纵坐标大于等于最低点
                jumpValue = -4;//增标量为负值
                //负及准备往上走
            }
            if (y <= LOWSET_Y - JUMP_HIGHT){//如果跳过了最高点
                jumpValue = 4;//增变量为正值
            }
            y += jumpValue;//写入坐标
            if (y >= LOWSET_Y){//已经完成落地动作，完成跳跃
                jumpState = false;//停止跳跃
            }
        }
    }

    public void jump(){//给面板控制的
        if (!jumpState){
            //没有处于跳跃状态
            Sound.jump();//播放音乐
        }
        jumpState = true;//处于跳跃状态
    }

    private void step(){
        //每过250毫秒更换一张图片，因为共有三张，所以除以3求余，轮流展示这三张图片
        int tmp = stepTimer / 250 % 3;
        switch (tmp){
            case 1 :
                image = image1;
                break;
            case 2 :
                image = image2;
                break;
            default:
                image = image3;
                break;
        }
        stepTimer += FREASH;//计时器递增
    }

    /**
     * 足部边界区域
     */
    public Rectangle getFootBounds(){
        return new Rectangle(x + 15, y + 59, 29 , 18);
        //矩形的坐标，矩形的宽高
    }

    /**
     * 头部边界区域
     */
    public Rectangle getHeadBounds(){
        //return new Rectangle(x + 66, y + 25 , 32 , 22);
        return new Rectangle(x + 15, y + 25 , 32 , 22);
    }
}
