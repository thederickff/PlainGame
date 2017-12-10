import javax.swing.JFrame;

public class Game {
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Game");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(new GamePanel());
		frame.setVisible(true);
	}
}
