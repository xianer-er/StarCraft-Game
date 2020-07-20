package Game;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
public class Plane extends FlyingsObject {                                                        //定义一个公共类Plane                                                
	public boolean up=false,down=false,left=false,right=false;              //声明布尔型变量，各个按键初始为false
	ArrayList<AllBullet>Bullets = new ArrayList<AllBullet>();               // 创建数组列表对象Bullets
	ArrayList<AllBullet>RestBullets = new ArrayList<AllBullet>();           // 创建数组列表对象RestBullets
	public Plane() {
		timer=1000;
		myblood=10;
		score=0;
		x=500;                                                        //声明一个整型变量x并赋值为500
		y=500;                                                        //声明一个整型变量y并赋值为500
		speed=18;                                                      //声明一个整型变量speed并赋值为8
		img = new ImageIcon("bin\\img\\myship.png").getImage();      //声明一个我的飞机的图片
		width = img.getWidth(null);
		height =img.getHeight(null);
	}
	public void paintSelf(Graphics g,ArrayList<Enemy> Enemies,Boss boss,ArrayList<Bomb> bomb,int time) {
		if(up&&y>0) {                 //如果按下up且y>0，y轴一直减小
			y-=speed;
		}
		if(down&&y<640) {             //如果按下down且y<640，y轴一直增加  
			y+=speed;
		}
		if(left&&x>0) {               //如果按下left且x>0，x轴一直减小
			x-=speed;
		}
		if(right&&x<705-100) {        //如果按下right且x<655，x轴一直增加
			x+=speed;
		}
		g.drawImage(img,x,y,width,height,null);   //画出图片，并设置座标尺寸
		drawBullet(g,Enemies,boss,bomb,time);                            //画子弹
	}
	public void drawBullet(Graphics g,ArrayList<Enemy> Enemies,Boss boss,ArrayList<Bomb> bomb,int time) {

		// 取出自己的所有子弹
		a:for (int i = 0; i < Bullets.size(); i++) {
			//画第i颗子弹
			Bullets.get(i).drawpaintSelf(g);
			//拿第i颗子弹和boss做交集
			if( time >timer) {
				if(Bullets.get(i).getRect().intersects(boss.getRect())) {
					boss.life--;
					score+=2;
					Bullets.remove(i);
					break a;
				}
			}
			//取出所有的敌机
			for(int j = 0;j < Enemies.size() ; j++) {
				//拿第i颗子弹和第j个敌机做交集
				if(Enemies.get(j).getRect().intersects(Bullets.get(i).getRect())){
					//调用爆炸实例化爆炸的大小
					Bomb lalal=new Bomb(Enemies.get(j).x-170,Enemies.get(j).y-170);
					bomb.add(lalal);
					//取出敌机中的子弹
					for(int k=0; k < Enemies.get(j).EBullets.size() ; k++) {
						//剩余的子弹（敌机被销毁留下来的）存入剩余的子弹中
						RestBullets.add(Enemies.get(j).EBullets.get(k));
					}
					Enemies.remove(j);                  //如果发生碰撞移除敌人
					score++;                            //加一分
				}
			}
			if(Bullets.get(i).y<0) {                     //如果我的子弹飞出界面，移除掉
				Bullets.remove(i);
			}

		}
	//取出剩余的子弹并画出来
	for(int h=0;h<RestBullets.size();h++) {
		RestBullets.get(h).drawpaintSelf(g);
		if(RestBullets.get(h).getRect().intersects(getRect())) {      //判断剩余的子弹是否与我发生了碰撞 
			myblood--;
			RestBullets.remove(h); 
		}else if(RestBullets.get(h).y>800) {                          //如果没有发生碰撞且飞出了屏幕，移除它
			RestBullets.remove(h);
		}
	}
	}
	public void moveStart(KeyEvent e) {                                   //动作开始的方法
		if(e.getKeyCode()==KeyEvent.VK_UP)                                //按下up
		{
			up=true;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN)                              //按下down
		{
			down=true;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT)                              //按下left
		{
			left=true;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)                             //按下right
		{
			right=true;
		}
	}
	public void moveStop(KeyEvent e) {                                   //动作结束的方法
		if(e.getKeyCode()==KeyEvent.VK_UP)                               //松开up
		{
			up=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN)                             //松开down
		{
			down=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT)                             //松开left
		{
			left=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)                            //松开right
		{
			right=false;
		}
	}
	public void makeBullet(KeyEvent e) {                                //定义一个生成子弹的方法
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {                      //按下space，生成一个子弹
			setBullets();
		}
	}
	public void setBullets() {                                          //定义一个生成子弹的方法
		Bullets.add(new AllBullet(0,x+35, y));                          //加入Allbullet调用0结果，并设置相对于我的飞机的位置
	}
}







