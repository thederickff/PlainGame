import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;

public class Block extends GameObject {

	public Block(int x, int y) {
		this.x = x;
		this.y = y;
		this.id = ID.block;
	}
	
	@Override
	public void update() {
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.gray);
		g.fillRect(this.x, this.y, 32, 32);
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, 32, 32);
	}
	
}

