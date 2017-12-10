import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

public class Enemy extends Character {
	
	private long startTime;
	
	public Enemy(int x, int y, Handler handler) {
		super(x, y, handler);
		this.width = 10;
		this.height = 10;
		this.id = ID.enemy;
		this.startTime = System.currentTimeMillis();
	}
	
	@Override
	public void update() {
		super.update();
		
		long elapsedTime = System.currentTimeMillis();
		
		if (elapsedTime - startTime > 600) {
			if (!falling) {
				this.jumping = true;
			}
		}
		if (elapsedTime - startTime > 2000) {
			if (left) {
				this.left = false;
				this.right = true;
			} else {
				this.left = true;
				this.right = false;
			}
			
			this.startTime = elapsedTime;
		}
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.red.darker().darker());
		g.fillRect(this.x, this.y, this.width, this.height);	
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
	
 }
