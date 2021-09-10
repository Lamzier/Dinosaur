package modle;

import org.w3c.dom.css.Rect;
import view.BackgroundImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * 障碍类
 * @author Lamzy
 */
public class Obstacle {
    public int x,y;//坐标
    public BufferedImage image;
    private  BufferedImage stone;//石头图片
    private BufferedImage cacti;//仙人掌图片
    private int speed;//移动速度

    public Obstacle(){
        try {
            stone = ImageIO.read(new File("src/image/石头.png"));
            cacti = ImageIO.read(new File("src/image/木.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Random r = new Random();//创建随机对象
        if (r.nextInt(2) == 0){//从0和1中取一个值
            image = cacti;
            //使用木头
        }else{
            image = stone;//使用石头
        }
        x = 800;//初始横坐标
        y = 200 - image.getHeight();//纵坐标
        speed = BackgroundImage.SPEED;//移动速度与背景同步
    }

    /**
     * 移动
     */
    public void move(){
        x -= speed;//横坐标递减
    }

    /**
     * 获取边界
     */
    public Rectangle getBounds(){
        if (image == cacti) {//如果使用仙人掌
            return new Rectangle(x + 7 , y , 15 , image.getHeight());
        }
        //使用石头
        return new Rectangle(x + 5 , y + 4 , 23 , 21);
    }

    /**
     * 是否存活
     */
    public boolean isLive(){
        //如果障碍移出界面
        if (x <= -image.getWidth()){
            return false;//死亡
        }
        return true;//存活
    }


}


