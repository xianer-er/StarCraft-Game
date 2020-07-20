package Game;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
public class AllBullet {                                           //����һ���ӵ�����
	public Image bullets_img;                                              //����һ��image����
	public  int x;                                                 //����x��y��speed��id
	public  int y;
	public  int speed;
	public int id;
	public AllBullet(int id,int x,int y) {                          //����һ�����췽���ӵ���
		this.id=id;                                                 //��this���õ�ǰ���󣬸��������Ը�ֵ
		this.x=x;
		this.y=y;
		if(id==0) {
			bullets_img = new ImageIcon("bin\\img\\bullet.png").getImage();
			speed = 20;
		}
		if(id==1) {
			bullets_img = new ImageIcon("bin\\img\\ebullet.png").getImage();
			bullets_img = new ImageIcon("bin\\img\\ebullet.png").getImage();
			speed = 20;
		}
		if(id==2) {
			bullets_img= new ImageIcon("bin\\img\\bobullet.png").getImage(); 
			speed=15;
		}
	}
	public void drawpaintSelf(Graphics g) {	                         //���л��ӵ�
		if(id==0) {
			g.drawImage(bullets_img, x, y-=speed,bullets_img.getWidth(null),bullets_img.getHeight(null), null);
		}else {
			g.drawImage(bullets_img, x, y+=speed,bullets_img.getWidth(null),bullets_img.getHeight(null), null);
		}
	}
	public Rectangle getRect() {                                      //
		return new Rectangle(x, y, bullets_img.getWidth(null),bullets_img.getHeight(null));
	}
}