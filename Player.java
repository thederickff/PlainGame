import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Player extends GameObject {

	private double speed;
	private double stop;
	private double gravity;
	private double jump;
	private double maxSpeed;
	private double maxFall;
	private boolean left;
	private boolean right; 
	private boolean jumping;
	private boolean falling;

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.id = ID.player;
		
		this.speed = 0.3;
		this.stop = 0.2;
		this.jump = -11;
		this.gravity = 3.2;
		this.maxSpeed = 4.3;
		this.maxFall = 12.4;
	}

	@Override
	public void update() {
		moveLeftOrRight();
		this.x += this.dx;
		this.y += this.dy;
	}
	
	private void moveLeftOrRight() {
		if (left) {
			this.dx -= speed;
			if (this.dx < -maxSpeed) {
				this.dx = -maxSpeed;
			}
		} else if (right) {
			this.dx += speed;
			if (this.dx > maxSpeed) {
				this.dx = maxSpeed;
			}
		} else {
			if (this.dx > 0) {
				this.dx -= stop;
				if (this.dx < 0) {
					this.dx = 0;
				}
			} else if (this.dx < 0) {
				this.dx += stop;
				if (this.dx > 0) {
					this.dx = 0;
				}			
			}
		}
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.blue);
		g.fillRect(this.x, this.y, 20, 20);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, 20, 20);
	}
	
	public void keyPressed(KeyEvent e) {
			
		if (e.getKeyCode() == KeyEvent.VK_A) {
			this.left = true;
		}	
		
		if (e.getKeyCode() == KeyEvent.VK_D) {
			this.right = true;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A) {
			this.left = false;
		}	
		
		if (e.getKeyCode() == KeyEvent.VK_D) {
			this.right = false;
		}	
	}
}
