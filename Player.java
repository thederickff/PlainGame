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
	private Handler handler;

	public Player(int x, int y, Handler handler) {
		this.x = x;
		this.y = y;
		this.id = ID.player;
		
		this.speed = 0.3;
		this.stop = 0.2;
		this.jump = -15;
		this.gravity = 0.9;
		this.maxSpeed = 4.3;
		this.maxFall = 12.4;
		this.handler = handler;
		this.falling = true;
	}

	@Override
	public void update() {
		movement();

		checkColisions();
	}
	
	private void movement() {
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
		
		if (jumping) {
			this.dy = jump;
			this.jumping = false;
			this.falling = true;
		}
		
		if (falling) {
			this.dy += gravity;
			if (this.dy > maxFall) {
				this.dy = maxFall;
			}
		}
	}
	
	private void checkColisions() {
		int desiredX = this.x + (int) this.dx;
		int desiredY = this.y + (int) this.dy;
		
		for (GameObject object : this.handler.getObjects()) {
						
			if (object.getID() == ID.block) {
				if (getBoundsX(desiredX).intersects(object.getBounds())) {
				} else {
					this.x = desiredX;
				}
				
				if (getBoundsY(desiredY).intersects(object.getBounds())) {
					this.dy = 0;
					this.falling = false;
				} else {
	 			   this.y = desiredY;
				}
				
				if (!falling) {
					if (!getBoundsY(this.y+1).intersects(object.getBounds())) {
						this.falling = true;
					}					
				}
				
				if (this.x+10 != object.getX()-16) {
					object.setX((this.x + 10) - 16);
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
	
	public Rectangle getBoundsX(int x) {
		return new Rectangle(x, this.y, 20, 20);
	}

	public Rectangle getBoundsY(int y) {
		return new Rectangle(this.x,y, 20, 20);
	}
	
	public void keyPressed(KeyEvent e) {
			
		if (e.getKeyCode() == KeyEvent.VK_A) {
			this.left = true;
		}	
		
		if (e.getKeyCode() == KeyEvent.VK_D) {
			this.right = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (!falling) {
				this.jumping = true;
			}
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
