package Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
public class Over extends JFrame implements ActionListener{
	ImageIcon btnone=new ImageIcon("D:/java/text6/src/img/overbtn1.png");     //������ťͼƬ
	ImageIcon btntwo=new ImageIcon("D:/java/text6/src/img/overbtn2.png");
	ImageIcon btnthree=new ImageIcon("D:/java/text6/src/img/overbtn3.png");
	JButton btn1=new JButton("����һ��",btnone);                                 //������ť
	JButton btn2=new JButton("������",btntwo);
	JButton btn3=new JButton("�˳���Ϸ",btnthree);
	public Image image;                                                       //����ͼƬ����
	public int id;                                                            //����һ�����α���
	private int score;                                                        //����һ��score����
	public Over(int id ,int score) {                                          //����һ�����εĹ��췽=
		this.id=id;
		this.score=score;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);               //���ùرհ�ťʧЧ
		this.setLocation(400, 10);
		this.setLayout(null);
		this.setSize(716,800);
		this.setResizable(false);
		this.setTitle("StarCraft");
		this.setVisible(true);
		JButton[] but = {btn1,btn2,btn3};
		for(int i=0;i<=2;i++) {
			but[i].setBounds(100+200*i, 600, 148,45);
			this.add(but[i]);
			but[i].addActionListener(this);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();                           //���¼�Դע�����ť
		if(btn==btn1) {
			new Game();
			this.dispose();
		}		
		if(btn==btn2) {
			new Begin();
			this.dispose();
		}
		if(btn==btn3) {
		System.exit(0);
		}
	}
	@Override
	public void paint(Graphics g) {
		if(id==0) {
			image = new ImageIcon("bin\\img\\victory.jpg").getImage();
		}
		if(id==1) {
			image = new ImageIcon("bin\\img\\fail.jpg").getImage();   
		}
		g.drawImage(image, 0, 0, this);
		g.setFont(new Font("����",Font.ITALIC,100));
		g.setColor(Color.WHITE);   
		btn1.repaint();
		btn2.repaint();
		btn3.repaint();
		g.drawString("score:"+score, 180,350);
	}
}
