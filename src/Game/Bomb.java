package Game;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
public class Bomb {                                                     //����һ��Bomb��
	public int times;                                                        //����һ��ֵΪ0��time����
	public int bomb_width=150;                                                 //����Bomb��Ϊ300
	public int bomb_length=150;                                                //����Bomb��Ϊ300
	public Image[] bomb =new Image[16];	
    public int x,y; 
	public Bomb(int x,int y) {
		this.x=x+120;
		this.y=y+100;
		for(int i=0;i<16;i++) {                                                 //����ı���
			bomb[i]=(new ImageIcon("bin\\img\\e"+(i+1)+".gif")).getImage();
		}
	}
	public void drawpaintSelf(Graphics g) {	
			g.drawImage(bomb[times],x,y,bomb_width,bomb_length,null);
			times++;
	}
}



