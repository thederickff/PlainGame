import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Player extends Character {

	private MapLoader mapLoader;
	private int camX;
	private int camY;

	public Player(int x, int y, Handler handler, MapLoader mapLoader) {
		super(x, y, handler);
		this.width = 32;
		this.height = 48;
		this.id = ID.player;
		this.mapLoader = mapLoader;		
	}

	@Override
	public void update() {
		super.update();
		this.camX = this.x - Game.WIDTH / 2;
		this.camY = this.y - Game.HEIGHT / 2;
		checkCamera();
	}
	
	private void checkCamera() {
		if (this.camX > this.mapLoader.getMaxOffsetX() ) {
			this.camX = this.mapLoader.getMaxOffsetX();
		} else if (this.camX < this.mapLoader.getMinOffsetX()) {
			this.camX = this.mapLoader.getMinOffsetX();
		}
		
		if (this.camY > this.mapLoader.getMaxOffsetY() + 32) {
			this.camY = this.mapLoader.getMaxOffsetY()+ 32;
		} else if (this.camY < this.mapLoader.getMinOffsetY()) {
			this.camY = this.mapLoader.getMinOffsetY();
		}
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
	
	public int getCamX() {
		return camX;
	}
	
	public int getCamY() {
		return camY;
	}
}
