import java.util.LinkedList;
import java.awt.Graphics2D;

public class Handler {

	private LinkedList<GameObject> objects;
	private int xOffset;
	private int yOffset;
	
	public Handler() {
		this.objects = new LinkedList<>();
	}
	
	public void add(GameObject object) {
		this.objects.add(object);
	}
	
	public void remove(GameObject object) {
		this.objects.remove(object);
	}
	
	public LinkedList<GameObject> getObjects() {
		return objects;
	}
	
	public void update() {
		for (GameObject object : this.objects) {
			object.update();
		}
	}
	
	public void draw(Graphics2D g) {
		for (GameObject object : this.objects) {
			object.draw(g, xOffset, yOffset);
		}
	}
	
	public void setXOffset(int xOffset) {
		this.xOffset = xOffset;
	}
	
	public void setYOffset(int yOffset) {
		this.yOffset = yOffset;
	}
}
