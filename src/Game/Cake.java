package Game;
import java.awt.Graphics;
import javax.swing.ImageIcon;
public class Cake extends FlyingsObject{                             //定义一个公共类Cake继承其父类
	public  boolean crash = false;
	public Cake() {
		 img = new ImageIcon("bin\\img\\cake.png").getImage();      //声明一个图片cake
		 x=(int)(Math.random()*650);                                //设置x为随机数
		 y=0;
	     speed=10;
	     width = img.getWidth(null);
	     height =img.getHeight(null);
	}
	public void drawpaintSelf(Graphics g,Plane p) {	                //定义一个画方法，传入画笔和飞机对象
		g.drawImage(img, x, y,width,height, null);
		cakeMove();
		if(p.getRect().intersects(getRect())) {                     //判断我机与蛋糕相撞
			p.score+=3;
			p.myblood++;
			crash = true;
			
		}
	}
	public void cakeMove() {                                        //定义一个蛋糕移动的方法
		if(y<800) {
			y+=speed;
		}
	}
}
