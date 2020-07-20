package Game;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;
public class Enemy extends FlyingsObject{              //����һ��������Enemy�̳���FlyingsObject
	public int a=(int)(Math.random()*2);               //����һ�������a  int��ʾǿ��ת�� ��˫���ȸ�����ת��Ϊ������
	public int speed;                                 //����һ�����ͱ���speed
	public Image enemies;                             //����һ��ͼƬenemies
	ArrayList<AllBullet>EBullets = new ArrayList<AllBullet>();        // ���������б����EBullets
	public Enemy() {                                                  //����Enemy�Ĺ��췽��
		 x =(int)(Math.random()*650)+10;        //�������x����
		 y=0;                                   //�������ͱ���b����ֵΪ0
		switch(a){                                                    //ʹ��һ��switch��ѡ����䣬xΪ���ʽ
		case 0:                                                       //���x����0
			enemies = new ImageIcon("bin\\img\\enemy1.png").getImage();        //����ͼƬenemy1���������ٶ�Ϊ2
			speed = 2;
			break;                                                             //ִ�к�����case0
		case 1:                                                                //���x����1
			enemies = new ImageIcon("bin\\img\\enemy2.png").getImage();        //����ͼƬenemy2���������ٶ�Ϊ4
			speed = 4;
			break;                                                             //ִ�к�����case1
		}
	}
	public void drawpaintSelf(Graphics g,Plane p) {                            //����һ��������
		g.drawImage(enemies,x, y,enemies.getWidth(null), enemies.getHeight(null), null);   //�����л�
		enemyMove();                                                                      //���õл��ƶ��ķ���
		makeAEBullet();                                                                   //�������ɵ����ӵ��ķ���
		for(int i=0;i<EBullets.size();i++) {                                              //ȡ�����˵��ӵ���������
			EBullets.get(i).drawpaintSelf(g);
			if(EBullets.get(i).getRect().intersects(p.getRect())) {                       //�ж��Ƿ�����ӵ����һ�������ײ
				p.myblood--;                                                                  //�����ײ���ҵķ�������
				EBullets.remove(i);                                                       //�Ƴ������ӵ�
			}
		}
	}
	public void enemyMove() {                                 //����һ�������ƶ��ķ���
		if(y<=800) {                                          //����л�������С��800�����������ƶ�
			y+=speed;
		}
	}

	public void makeAEBullet() {                               //����һ�����ɵ����ӵ��ķ���
		if(y%100==0) {                                         //����������100ȡ�����0��ʼ�����ӵ�
			EBullets.add(new AllBullet(1,x+18,y+35));          //�ѵ����ӵ������ܵ��У����õ���1����������Եл���λ��
		}
	}
	@Override
	public Rectangle getRect() {                               //��ȡ�л��ľ���
		return new Rectangle(x, y, enemies.getWidth(null),  enemies.getHeight(null));  //����������ε�λ�ÿ�ߵ�ֵ
	}
}