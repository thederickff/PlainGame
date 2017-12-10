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
	
	private boolean moveLeft;
	private boolean moveRight;
	private boolean moveUp;
	private boolean moveDown;
	
	private GameObject bottomObject;

	public Player(int x, int y, Handler handler) {
		this.x = x;
		this.y = y;
		this.width = 20;
		this.height = 20;
		this.id = ID.player;
		this.handler = handler;		
		this.speed = 0.3;
		this.stop = 0.2;
		this.jump = -15;
		this.gravity = 1.1;
		this.maxSpeed = 4.3;
		this.maxFall = 12.4;
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

		///////////////////////////// X Axis ///////////////////////////////////						
		for (GameObject object : this.handler.getObjects()) {
			if (object.getID() == ID.block) {
				if (getBoundsToX(desiredX).intersects(object.getBounds())) {
					if (left) {
						this.moveLeft = false;
						break;
					} else if (right) {
						this.moveRight = false;
						break;
					}
				}
			}
		}
		
		if (this.moveLeft && this.moveRight) {
			this.x = desiredX;
		}
		if (!moveLeft) {
			this.moveLeft = true;
		}
		
		if (!moveRight) { 
			this.moveRight = true;
		}
		
		///////////////////////////// Y Axis ///////////////////////////////////
		for (GameObject object : this.handler.getObjects()) {
			if (object.getID() == ID.block) {		
				if (getBoundsToY(desiredY).intersects(object.getBounds())) {
					if (falling) {
						this.dy = 0;
						this.falling = false;
						bottomObject = object;
						break;
					}
				}
			}
		}
		if (falling) {
			this.y = desiredY;
		}
				
		if (bottomObject != null) {
			if (!getBoundsToY(this.y + 1).intersects(bottomObject.getBounds())) {
				this.falling = true;
				this.bottomObject = null;
			}					
		} else {
			this.falling = true;
		}
		
		
	    System.out.println(falling);
	    
	    
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.blue);
		g.fillRect(this.x, this.y, this.width, this.height);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
	
	public Rectangle getBoundsToX(int x) {
		return new Rectangle(x, this.y, this.width, this.height);
	}

	public Rectangle getBoundsToY(int y) {
		return new Rectangle(this.x, y, this.width, this.height);
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
