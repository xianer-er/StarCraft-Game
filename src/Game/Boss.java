package Game;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.ImageIcon;
public class Boss extends FlyingsObject{                                 //定义一个公共类Boss
	ArrayList<AllBullet>BOEBullets = new ArrayList<AllBullet>();         // 创建数组列表对象BOEBullets
    private boolean left =false,right = true;                            //声明布尔型变量，各个按键初始为false
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
	public void drawpaintSelf(Graphics g,Plane p) {                     //定义一个画方法
		g.drawImage(img, x, y,width,height, null);                      //画出boss
		Bossmove();                                                     //调用boss移动的方法
		makeABOEBullet();                                               //调用生成boss子弹的方法
		//取出boss子弹并画出来
				if(p.getRect().intersects(getRect())) {                         //我机和boss相撞
			p.myblood=0;
		}
		for(int i=0;i<BOEBullets.size();i++) {
			BOEBullets.get(i).drawpaintSelf(g);
			if(BOEBullets.get(i).getRect().intersects(p.getRect())) {  //判断boss子弹与我机是否相撞
				p.myblood-=2;                                          //分数减二
				BOEBullets.remove(i);                                  //移除boss子弹
			}else if(BOEBullets.get(i).y>800) {                        //判断boss超出边界，移除它
				BOEBullets.remove(i);
			}
			}
				}
	//定义一个boss移动的方法
	void Bossmove() {
		if(x>=w) {                          //横坐标等于450时，向左移动
			left = true;
			right = false;
		} 
		if(x<=s) {                          //横坐标等于1时，向右移动
			left = false;
			right = true;
		}
		if(left) {                         //当left时横坐标减少
			x-=speed;
		}
		if(right) {                        //当right时横坐标增加
			x+=speed;
		}
	}
	public void makeABOEBullet() {                                    //定义一个生成boss子弹的方法
		if(x%75==0) {                                                  //判断横坐标对75取余等于零开始发射子弹
			BOEBullets.add(new AllBullet(2,x+80,y+200));               //加入Allbullet调用2结果，并设置相对于boss的位置
		}
	}
}
