import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected ID id;
	protected int x;
	protected int y;
	protected double dx;
	protected double dy;
	
	public abstract void update();
	public abstract void draw(Graphics2D g);
	public abstract Rectangle getBounds();
}
