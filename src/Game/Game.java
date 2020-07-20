package Game;  //此界面为游戏主界面
import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class Game extends JFrame implements KeyListener {  
	public static void main(String[] args) {
		new Game();
		
	}
//创建一个Begin类加入一个顶层容器JFrame介入动作监听接口
	ArrayList<Bomb> Bomb = new ArrayList<Bomb>();              // 创建数组列表对象Bomb
	ArrayList<Enemy> Enemies= new ArrayList<Enemy>();          // 创建数组列表对象Enemies
	ArrayList<Cake> Cake= new ArrayList<Cake>();               // 创建数组列表对象Cake
	private JPanel jp ;
	public int timer = 1000;
	public int x1=0,y1=-400;
	public int x2=0,y2=-1645;
	Plane plane = new Plane();                                 //创建我的飞船对象p
	Boss boss; 
	public int [] a =new int [10];
	
	public int time = 0;	                                   //声明一个整数型对象time并对它赋值为0
	public boolean pause =false;                               //声明一个布尔型变量初始值为false
	public Game(String str) {
		super(str);
		
	}
	public Game() {                                            //定义一个Game的构造方法                                           
		this.setLayout(null);                        
		this.setSize(716, 800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		jp = new GamePanel(this);                              //在jframe创建面板对象jp
		this.add(jp);
		this.addKeyListener(this);
	}
	@Override
	public void keyPressed(KeyEvent e) {                       //按下按键时的处理操作
		plane.moveStart(e);                                    //调用plane中的moveStart方法
		if(e.getKeyCode()==KeyEvent.VK_ENTER  ){			   //按下enter键暂停游戏
			if(pause==false) {
				pause=true;
			}
			else {
				pause=false;
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {                       //松开按键时的按键操作
		plane.moveStop(e);                                      //调用plane中的moveStop方法
		plane.makeBullet(e);                                    //调用plane中的makeBullets方法
	}
	class GamePanel extends JPanel {                                            //创建一个游戏面板继承与JPanel
		private Image bg2 = new ImageIcon("bin\\img\\lunbo.jpg").getImage();    //两张背景图
		private Image copybg2 = new ImageIcon("bin\\img\\lunbo.jpg").getImage();
	
		private JFrame jf;                                 //   声明一个私有对象jf       
		public GamePanel(JFrame jf) {                      // 面板的基础设置
			this.setSize(720, 800);
			this.setLayout(null);
			this.setLocation(0, 0);
			this.setVisible(true);
			new PaintThread().start();                      // 以匿名对象开启画图线程
			this.jf=jf;                                     //调用if
		}
		public void move() {                                        //构造一个move方法
			if(y1 <=800) {
				y1+=1;
			} else{
				y1 = -1645;
			}
			if(y2 <=800) {
				y2+=1;
			} else{
				y2 = -1645  ;
			}
		}
		@Override
		public void paint(Graphics g) {                     // 画面板背景图，飞机，子弹等
			g.drawImage(bg2, x1, y1, this);                 //画出背景图2
			g.drawImage(copybg2,x2, y2, this);
			g.setFont(new Font("楷体",Font.ITALIC,20));
			g.setColor(Color.WHITE);                        //设置字体颜色
			move();
			plane.paintSelf(g,Enemies,boss,Bomb,time);      //画我机        
			bornObject();                                   //调用bornobject方法
			if(time>timer) {	
				boss.drawpaintSelf(g,plane);                //画boss
			}
			//在敌人容器中取出敌人并画出来（数组的遍历）
			for (int ii = 0; ii < Enemies.size(); ii++) {
				Enemies.get(ii).drawpaintSelf(g,plane);
				//判段如果敌机和我机取矩形出现交集
				if(Enemies.get(ii).getRect().intersects(plane.getRect())) {
					plane.myblood--;
					//调用爆炸类，实现爆炸
					Bomb lalal=new Bomb(Enemies.get(ii).x-170,Enemies.get(ii).y-170);
					Bomb.add(lalal);
					Enemies.remove(ii);
				}else {
					//判断敌机超出界面宽度，移除它
					if(Enemies.get(ii).y > 800) {
						Enemies.remove(ii);
					}
				}
			}
			//在爆炸容器中取出爆炸并画出来（数组的遍历）
			for(int i=0;i<Bomb.size();i++) {
				Bomb.get(i).drawpaintSelf(g);
				if(Bomb.get(i).times==16) {
					Bomb.remove(i);
				}
			}
			//在蛋糕容器中取出蛋糕并画出来（数组的遍历）
			for(int i = 0;i < Cake.size();i++) {
				if(Cake.get(i).crash) {
					Cake.remove(i);
				}else {
					Cake.get(i).drawpaintSelf(g,plane);
				}
			}
			//设置time一直增加
			time++;
			if(time >timer) {
				//如果boss的生命为零实现游戏胜利
				if(boss.life==0 ) {
					pause=true;
					JOptionPane.showMessageDialog(this, "游戏结束", "提示", JOptionPane.YES_OPTION);
					jf.dispose();
					new Over(0, plane.score);
				}
			}
			//如果分数为零实现游戏失败 
			{
				if( plane.myblood<=0) {
					pause=true;
					JOptionPane.showMessageDialog(this, "游戏结束", "提示", JOptionPane.YES_OPTION);
					jf.dispose();
					new Over(1, plane.score);
				}
			}
			//画出time，score，life显示的位置
			g.drawString("time:"+time/50, 30, 20);
			g.drawString("score:"+plane.score, 120, 20);
			g.drawString(
					"myblood:"+plane.myblood,220, 20);
			if(time >timer) {
				g.drawString("boss life:"+boss.life, 450, 20);
			}
		}
	}

	public void bornObject() {                 //创建一个born object方法来判断敌机，蛋糕的出现时间间隔
		//当时间对100取余等于零时，加入敌机
		if(time%100==0)
		{
			Enemies.add(new Enemy());
		}
		//当时间等于固定值时出现boss
		if(time == timer) {
			boss = new Boss();
		}
		//当时间对1000取余时出现蛋糕
		if(time!=0 && time%800==0)
		{
			Cake.add(new Cake());
		}
	}
	//画线程
	public class PaintThread extends Thread {
		@Override
		public void run() {                                 //线程的起点
			while (true) {                     
				while (pause) {						        //暂停死循环
					try {
						Thread.sleep(200);                  // 延迟，限制线程睡眠后才能往下执行
					} catch (InterruptedException e) {         
						e.printStackTrace();                //输出当前异常对象的堆栈使用轨迹
					}
				}
				repaint();                                  // 手动重画	
				try {
					Thread.sleep(20);                       // 延迟，限制线程睡眠后才能往下执行
				} catch (InterruptedException e) { 
					e.printStackTrace();                    // 输出当前异常对象的堆栈使用轨迹
				}
			}
		}
	}                  
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}