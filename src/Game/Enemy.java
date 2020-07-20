package Game;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;
public class Enemy extends FlyingsObject{              //定义一个公共类Enemy继承于FlyingsObject
	public int a=(int)(Math.random()*2);               //声明一个随机数a  int表示强制转换 把双精度浮点型转化为整数型
	public int speed;                                 //声明一个整型变量speed
	public Image enemies;                             //声明一个图片enemies
	ArrayList<AllBullet>EBullets = new ArrayList<AllBullet>();        // 创建数组列表对象EBullets
	public Enemy() {                                                  //定义Enemy的构造方法
		 x =(int)(Math.random()*650)+10;        //随机生成x坐标
		 y=0;                                   //声明整型变量b并赋值为0
		switch(a){                                                    //使用一个switch的选择语句，x为表达式
		case 0:                                                       //如果x等于0
			enemies = new ImageIcon("bin\\img\\enemy1.png").getImage();        //调用图片enemy1，并设置速度为2
			speed = 2;
			break;                                                             //执行后跳出case0
		case 1:                                                                //如果x等于1
			enemies = new ImageIcon("bin\\img\\enemy2.png").getImage();        //调用图片enemy2，并设置速度为4
			speed = 4;
			break;                                                             //执行后跳出case1
		}
	}
	public void drawpaintSelf(Graphics g,Plane p) {                            //定义一个画方法
		g.drawImage(enemies,x, y,enemies.getWidth(null), enemies.getHeight(null), null);   //画出敌机
		enemyMove();                                                                      //调用敌机移动的方法
		makeAEBullet();                                                                   //调用生成敌人子弹的方法
		for(int i=0;i<EBullets.size();i++) {                                              //取出敌人的子弹并画出来
			EBullets.get(i).drawpaintSelf(g);
			if(EBullets.get(i).getRect().intersects(p.getRect())) {                       //判断是否敌人子弹和我机发生碰撞
				p.myblood--;                                                                  //如果碰撞，我的分数减少
				EBullets.remove(i);                                                       //移除敌人子弹
			}
		}
	}
	public void enemyMove() {                                 //定义一个敌人移动的方法
		if(y<=800) {                                          //如果敌机纵坐标小于800，不断向下移动
			y+=speed;
		}
	}

	public void makeAEBullet() {                               //定义一个生成敌人子弹的方法
		if(y%100==0) {                                         //如果纵坐标对100取余等于0则开始发射子弹
			EBullets.add(new AllBullet(1,x+18,y+35));          //把敌人子弹加入总弹夹，调用弹夹1，设置其相对敌机的位置
		}
	}
	@Override
	public Rectangle getRect() {                               //获取敌机的矩形
		return new Rectangle(x, y, enemies.getWidth(null),  enemies.getHeight(null));  //返回这个矩形的位置宽高的值
	}
}