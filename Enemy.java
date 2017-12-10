import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

public class Enemy extends GameObject {
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 16;
		this.height = 16;
		this.id = ID.enemy;
		double direction = Math.random() * 100 - 1;
		if (direction >= 50) {
			this.dx = direction % 10;
		} else {
		    this.dx = -(direction % 10);
		}
		
		direction = Math.random() * 100 - 1;
		if (direction >= 50) {
			this.dy = (direction % 10);
		} else {
		    this.dy = -(direction % 10);
		}
	}
	
	@Override
	public void update() {
		checkWindowBorder();
		this.x += (int) dx;	
		this.y += (int) dy;
	}
	
	private void checkWindowBorder() {
		if (this.x < 0 || this.x > 800) {
			this.dx *= -1;
		}
		if (this.y < 0 || this.y > 550) {
			this.dy *= -1;
		}
	}
	
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.red);
		g.fillOval(this.x, this.y, this.width, this.height);	
	}
	
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
	
 }
