package Game;
import java.awt.Graphics;
import javax.swing.ImageIcon;
public class Cake extends FlyingsObject{                             //����һ��������Cake�̳��丸��
	public  boolean crash = false;
	public Cake() {
		 img = new ImageIcon("bin\\img\\cake.png").getImage();      //����һ��ͼƬcake
		 x=(int)(Math.random()*650);                                //����xΪ�����
		 y=0;
	     speed=10;
	     width = img.getWidth(null);
	     height =img.getHeight(null);
	}
	public void drawpaintSelf(Graphics g,Plane p) {	                //����һ�������������뻭�ʺͷɻ�����
		g.drawImage(img, x, y,width,height, null);
		cakeMove();
		if(p.getRect().intersects(getRect())) {                     //�ж��һ��뵰����ײ
			p.score+=3;
			p.myblood++;
			crash = true;
			
		}
	}
	public void cakeMove() {                                        //����һ�������ƶ��ķ���
		if(y<800) {
			y+=speed;
		}
	}
}
