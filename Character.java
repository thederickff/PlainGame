import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public abstract class Character extends GameObject {

	protected double speed;
	protected double stop;
	protected double gravity;
	protected double jump;
	protected double maxSpeed;
	protected double maxFall;
	protected boolean left;
	protected boolean right; 
	protected boolean jumping;
	protected boolean falling;
	protected Handler handler;
	protected boolean moveLeftOrRight;
	protected GameObject bottomObject;

	public Character(int x, int y, Handler handler) {
		this.x = x;
		this.y = y;
		this.handler = handler;
		this.speed = 0.3;
		this.stop = 0.2;
		this.jump = -16;
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
					this.moveLeftOrRight = false;
					break;
				}
			}
		}
		
		if (moveLeftOrRight) {
			this.x = desiredX;
		}
		
		if (!moveLeftOrRight) moveLeftOrRight = true;
		
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
	 
	}
	
	public Rectangle getBoundsToX(int x) {
		return new Rectangle(x, this.y, this.width, this.height);
	}

	public Rectangle getBoundsToY(int y) {
		return new Rectangle(this.x, y, this.width, this.height);
	}
}
