package Game;
import java.awt.Image;
import java.awt.Rectangle;
public class FlyingsObject {                 //����һ������FlyingsObject
     public Image img;
     public int  x,y;
     public int  w,s;
     public int speed,life,myblood,score,timer;
     public int width;
     public int height;      
     public Rectangle getRect() {                                        //��ȡ������ľ���
 		return new Rectangle(x, y,width,height);     //����������ε�λ�ÿ�ߵ�ֵ
 	}
}
