import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected ID id;
	protected int x;
	protected int y;
	protected double dx;
	protected double dy;
	protected int width;
	protected int height;
	
	public abstract void update();
	public abstract void draw(Graphics2D g);
	public abstract Rectangle getBounds();
	
	public ID getID() {
		return id;
	}
	
	public int getX() { 
		return x; 
	}
	
	public void setX(int x) { 
		this.x = x;
	}
	
	public int getY() { 
		return y; 
	}
	
	public void setY(int y) {
		this.y = y; 
	}
}
