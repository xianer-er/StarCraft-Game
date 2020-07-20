package Game;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
public class Plane extends FlyingsObject {                                                        //����һ��������Plane                                                
	public boolean up=false,down=false,left=false,right=false;              //���������ͱ���������������ʼΪfalse
	ArrayList<AllBullet>Bullets = new ArrayList<AllBullet>();               // ���������б����Bullets
	ArrayList<AllBullet>RestBullets = new ArrayList<AllBullet>();           // ���������б����RestBullets
	public Plane() {
		timer=1000;
		myblood=10;
		score=0;
		x=500;                                                        //����һ�����ͱ���x����ֵΪ500
		y=500;                                                        //����һ�����ͱ���y����ֵΪ500
		speed=18;                                                      //����һ�����ͱ���speed����ֵΪ8
		img = new ImageIcon("bin\\img\\myship.png").getImage();      //����һ���ҵķɻ���ͼƬ
		width = img.getWidth(null);
		height =img.getHeight(null);
	}
	public void paintSelf(Graphics g,ArrayList<Enemy> Enemies,Boss boss,ArrayList<Bomb> bomb,int time) {
		if(up&&y>0) {                 //�������up��y>0��y��һֱ��С
			y-=speed;
		}
		if(down&&y<640) {             //�������down��y<640��y��һֱ����  
			y+=speed;
		}
		if(left&&x>0) {               //�������left��x>0��x��һֱ��С
			x-=speed;
		}
		if(right&&x<705-100) {        //�������right��x<655��x��һֱ����
			x+=speed;
		}
		g.drawImage(img,x,y,width,height,null);   //����ͼƬ������������ߴ�
		drawBullet(g,Enemies,boss,bomb,time);                            //���ӵ�
	}
	public void drawBullet(Graphics g,ArrayList<Enemy> Enemies,Boss boss,ArrayList<Bomb> bomb,int time) {

		// ȡ���Լ��������ӵ�
		a:for (int i = 0; i < Bullets.size(); i++) {
			//����i���ӵ�
			Bullets.get(i).drawpaintSelf(g);
			//�õ�i���ӵ���boss������
			if( time >timer) {
				if(Bullets.get(i).getRect().intersects(boss.getRect())) {
					boss.life--;
					score+=2;
					Bullets.remove(i);
					break a;
				}
			}
			//ȡ�����еĵл�
			for(int j = 0;j < Enemies.size() ; j++) {
				//�õ�i���ӵ��͵�j���л�������
				if(Enemies.get(j).getRect().intersects(Bullets.get(i).getRect())){
					//���ñ�ըʵ������ը�Ĵ�С
					Bomb lalal=new Bomb(Enemies.get(j).x-170,Enemies.get(j).y-170);
					bomb.add(lalal);
					//ȡ���л��е��ӵ�
					for(int k=0; k < Enemies.get(j).EBullets.size() ; k++) {
						//ʣ����ӵ����л��������������ģ�����ʣ����ӵ���
						RestBullets.add(Enemies.get(j).EBullets.get(k));
					}
					Enemies.remove(j);                  //���������ײ�Ƴ�����
					score++;                            //��һ��
				}
			}
			if(Bullets.get(i).y<0) {                     //����ҵ��ӵ��ɳ����棬�Ƴ���
				Bullets.remove(i);
			}

		}
	//ȡ��ʣ����ӵ���������
	for(int h=0;h<RestBullets.size();h++) {
		RestBullets.get(h).drawpaintSelf(g);
		if(RestBullets.get(h).getRect().intersects(getRect())) {      //�ж�ʣ����ӵ��Ƿ����ҷ�������ײ 
			myblood--;
			RestBullets.remove(h); 
		}else if(RestBullets.get(h).y>800) {                          //���û�з�����ײ�ҷɳ�����Ļ���Ƴ���
			RestBullets.remove(h);
		}
	}
	}
	public void moveStart(KeyEvent e) {                                   //������ʼ�ķ���
		if(e.getKeyCode()==KeyEvent.VK_UP)                                //����up
		{
			up=true;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN)                              //����down
		{
			down=true;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT)                              //����left
		{
			left=true;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)                             //����right
		{
			right=true;
		}
	}
	public void moveStop(KeyEvent e) {                                   //���������ķ���
		if(e.getKeyCode()==KeyEvent.VK_UP)                               //�ɿ�up
		{
			up=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN)                             //�ɿ�down
		{
			down=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT)                             //�ɿ�left
		{
			left=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)                            //�ɿ�right
		{
			right=false;
		}
	}
	public void makeBullet(KeyEvent e) {                                //����һ�������ӵ��ķ���
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {                      //����space������һ���ӵ�
			setBullets();
		}
	}
	public void setBullets() {                                          //����һ�������ӵ��ķ���
		Bullets.add(new AllBullet(0,x+35, y));                          //����Allbullet����0�����������������ҵķɻ���λ��
	}
}







