package Game;                                                 //此界面为游戏开始界面

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
public class Begin extends JFrame implements ActionListener{    //创建一个class类加入一个顶层容器JFrame介入动作监听接口
	ImageIcon bg1=new ImageIcon("bin\\img\\bg1.jpg");           //插入背景图片
	ImageIcon btnone=new ImageIcon("D:/java/text6/src/img/btn1.png");
	ImageIcon btntwo=new ImageIcon("D:/java/text6/src/img/btn2.png");
	ImageIcon btnthree=new ImageIcon("D:/java/text6/src/img/btn3.png");
	ImageIcon btnfour=new ImageIcon("D:/java/text6/src/img/btn4.png");
	JLabel jlab = new JLabel(bg1);                              //创建一个标签，加入图片
	JButton btn1=new JButton("开始",btnone);                     //创建按钮
	JButton btn2=new JButton("规则",btntwo);
	JButton btn3=new JButton("关于",btnthree);
	JButton btn4=new JButton("退出",btnfour);
	public Begin() {                                            //Game的构造方法                                               
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置为单击关闭窗口时并结束程序
		this.setLocation(400,10);                               //设置窗口显示的位置
		this.setLayout(null);                                   //取消窗口的默认布局管理器 
		this.setSize(716,800);                                  //设置窗口尺 寸
		this.setResizable(false);                               //设置窗口大小不可改变
		this.setTitle("StarCraft");                             //设置窗口标题
		jlab.setOpaque(true);                                   //设置标签为不透明的       
		jlab.setSize(716,800);                                  //设置标签尺寸
		jlab.setLocation(0, -10);                               //设置标签位置
		JButton[] but={btn1,btn2,btn3,btn4};                   //建立一个数组存放按钮
		for(int i=0;i<=3;i++) {                                //for循环
			but[i].setBounds(250,300+i*100,200,80);            //设置每个按钮的尺寸位置
			this.add(but[i]);                                  //把按钮加入面板
			but[i].addActionListener(this);                    //为按钮加入动作监听
		}
		this.add(jlab);
		this.setVisible(true);                                  //将窗口显示出来
	}
	@Override
	public void  actionPerformed(ActionEvent e)                 //事件发生时的处理操作
	{
		JButton btn = (JButton)e.getSource();                   //创建按钮对象并获取它的资源
		if(btn==btn1) {                                         //用if语句来执行判断
			dispose();                                          //关闭本窗口
			new Game();                                         //调用Begin类
		}
		else if(btn==btn2) {
			//创建一个消息对话框
			JOptionPane.showMessageDialog(this, "按键发射子弹，你的初始声明为20。\n 若击中Boss20次，则胜利。\n 若得分为零，则失败。\n 被敌机射中减1分，被boss射中减2分。吃到蛋糕加3分并且可以加一滴血。", "游戏规则", JOptionPane.CLOSED_OPTION);	
		}
		else if(btn==btn3) 
		{
			//创建一个消息对话框
			JOptionPane.showMessageDialog(this, "制作人：三组-郝庆会", "关于", JOptionPane.CLOSED_OPTION);
		}
		else if(btn==btn4) 
		{ 
			//创建一个确认对话框
			int n=JOptionPane.showConfirmDialog(this,"你确定要丢下我走了么？","starcraft",JOptionPane.YES_NO_OPTION);
			//如果按下no按钮出现的操作
			if(n==JOptionPane.NO_OPTION) {
				JOptionPane.showMessageDialog(this, "我们一起玩耍吧", "( •̀ ω •́ )✧",  JOptionPane.CLOSED_OPTION);
			}
			//如果按下yes按钮出现的操作
			if(n==JOptionPane.YES_OPTION) {
				this.dispose();
				
			}
		}
	}public static void main(String[] args) {                        //游戏开始的入口
		new Begin();
	}
}