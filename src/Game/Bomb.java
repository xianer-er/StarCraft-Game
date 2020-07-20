package Game;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
public class Bomb {                                                     //定义一个Bomb类
	public int times;                                                        //声明一个值为0的time对象
	public int bomb_width=150;                                                 //声明Bomb宽为300
	public int bomb_length=150;                                                //声明Bomb高为300
	public Image[] bomb =new Image[16];	
    public int x,y; 
	public Bomb(int x,int y) {
		this.x=x+120;
		this.y=y+100;
		for(int i=0;i<16;i++) {                                                 //数组的遍历
			bomb[i]=(new ImageIcon("bin\\img\\e"+(i+1)+".gif")).getImage();
		}
	}
	public void drawpaintSelf(Graphics g) {	
			g.drawImage(bomb[times],x,y,bomb_width,bomb_length,null);
			times++;
	}
}



