import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;

public class Block extends GameObject {

	public Block(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 32;
		this.height = 32;
		this.id = ID.block;
	}
	
	@Override
	public void update() {
	}
	
	@Override
	public void draw(Graphics2D g, int xOffset, int yOffset) {
		g.setColor(Color.orange.darker().darker().darker());
		g.fillRect(this.x + xOffset, this.y + yOffset, this.width, this.height);
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
	
}

