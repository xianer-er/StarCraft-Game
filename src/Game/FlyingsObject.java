package Game;
import java.awt.Image;
import java.awt.Rectangle;
public class FlyingsObject {                 //定义一个父类FlyingsObject
     public Image img;
     public int  x,y;
     public int  w,s;
     public int speed,life,myblood,score,timer;
     public int width;
     public int height;      
     public Rectangle getRect() {                                        //获取飞行物的矩形
 		return new Rectangle(x, y,width,height);     //返回这个矩形的位置宽高的值
 	}
}
