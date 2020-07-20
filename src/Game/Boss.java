package Game;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.ImageIcon;
public class Boss extends FlyingsObject{                                 //����һ��������Boss
	ArrayList<AllBullet>BOEBullets = new ArrayList<AllBullet>();         // ���������б����BOEBullets
    private boolean left =false,right = true;                            //���������ͱ���������������ʼΪfalse
	public Boss() {
		img =new ImageIcon("bin\\img\\enemy4.png").getImage();
		x=-10;                                                            
		y=20;                                                         
		w=500;
		s=20;
		width=img.getWidth(null);
		height=img.getHeight(null);
		speed=5;                                                       
		life = 10;  
	}
	public void drawpaintSelf(Graphics g,Plane p) {                     //����һ��������
		g.drawImage(img, x, y,width,height, null);                      //����boss
		Bossmove();                                                     //����boss�ƶ��ķ���
		makeABOEBullet();                                               //��������boss�ӵ��ķ���
		//ȡ��boss�ӵ���������
				if(p.getRect().intersects(getRect())) {                         //�һ���boss��ײ
			p.myblood=0;
		}
		for(int i=0;i<BOEBullets.size();i++) {
			BOEBullets.get(i).drawpaintSelf(g);
			if(BOEBullets.get(i).getRect().intersects(p.getRect())) {  //�ж�boss�ӵ����һ��Ƿ���ײ
				p.myblood-=2;                                          //��������
				BOEBullets.remove(i);                                  //�Ƴ�boss�ӵ�
			}else if(BOEBullets.get(i).y>800) {                        //�ж�boss�����߽磬�Ƴ���
				BOEBullets.remove(i);
			}
			}
				}
	//����һ��boss�ƶ��ķ���
	void Bossmove() {
		if(x>=w) {                          //���������450ʱ�������ƶ�
			left = true;
			right = false;
		} 
		if(x<=s) {                          //���������1ʱ�������ƶ�
			left = false;
			right = true;
		}
		if(left) {                         //��leftʱ���������
			x-=speed;
		}
		if(right) {                        //��rightʱ����������
			x+=speed;
		}
	}
	public void makeABOEBullet() {                                    //����һ������boss�ӵ��ķ���
		if(x%75==0) {                                                  //�жϺ������75ȡ������㿪ʼ�����ӵ�
			BOEBullets.add(new AllBullet(2,x+80,y+200));               //����Allbullet����2����������������boss��λ��
		}
	}
}
