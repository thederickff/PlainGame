import javax.swing.JPanel;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable, KeyListener {
	
	private final int FPS = 60;
	private final long TARGET_TIME = 1000 / FPS;
	private int frames;
	private boolean running;
	private Thread thread;
	private Handler handler;
	private Random random;
	private Player player;

	public GamePanel() {
		this.random = new Random();
		this.handler = new Handler();

		/*for (int i = 0; i < 10; i++) {
			this.handler.add(new Enemy(random.nextInt(400), random.nextInt(300)));
		}*/
		this.handler.add(new Block(400, 400));
		this.player = new Player(400, 0, handler);
		init();
	}
	
	public synchronized void init() {
		setFocusable(true);
		addKeyListener(this);
		this.thread = new Thread(this);
		this.running = true;
		this.frames = 0;
		thread.start();
	}
	
	public synchronized void stop() {
		try {
			this.running = false;
			this.thread.join();
		} catch (InterruptedException e) {
		   System.out.printf("An error occurred: %s\n", e);
		}
	}
	
	@Override
	public void run() {
		long startTime;
		long elapsedTime;
		long waitTime;
		
		while (running) {
			startTime = System.nanoTime();
			
			update();
			repaint();
			
			elapsedTime = (System.nanoTime() - startTime) / 1000000;
			waitTime = TARGET_TIME - elapsedTime;
			

			this.frames = 1000 / (int) waitTime;
			
			try {
	   			if (waitTime > 0) {
					Thread.sleep(waitTime);
				}		
			} catch (Exception e) {
				System.out.printf("An error occurred: %s\n", e);
			}
		}
		stop();
	}
	
	public void update() {
		Toolkit.getDefaultToolkit().sync();
		this.player.update();
		this.handler.update();						
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.black);
		g2.fillRect(0, 0, 800, 600);
		g2.setColor(Color.yellow);
		g2.drawString("FPS: " + this.frames, 50, 20);

		this.player.draw(g2);		
		this.handler.draw(g2);
		
		g.dispose();
	}

	public void keyTyped(KeyEvent e) {
	}	
	
	public void keyPressed(KeyEvent e) {
		this.player.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e) {
		this.player.keyReleased(e);
	}

}
