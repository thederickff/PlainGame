import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Game");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(new GamePanel());
		frame.setVisible(true);
	}
}
