import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.awt.Graphics2D;

public class MapLoader {
	
	private File file;
	private int totalRows;
	private int totalColumns;
	private int[][] map;
	private Handler handler;
	
	private final int WORLD_SIZE_X;
	private final int WORLD_SIZE_Y;
	private final int minOffsetX;
	private final int minOffsetY;
	private final int maxOffsetX;
	private final int maxOffsetY;
	
	public MapLoader(String path, Handler handler) {
		this.file = new File(path);
		this.handler = handler;
		loadMap();
		
		WORLD_SIZE_X = this.totalColumns * 32;
		WORLD_SIZE_Y = this.totalRows * 32;
		
		this.minOffsetX = 0;
		this.minOffsetY = 0;
		this.maxOffsetX = WORLD_SIZE_X - Game.WIDTH;
		this.maxOffsetY = WORLD_SIZE_Y - Game.HEIGHT;
	}
	
	private void loadMap() {
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			
		    this.totalRows = Integer.parseInt(bufferedReader.readLine());
		    this.totalColumns = Integer.parseInt(bufferedReader.readLine());
		    this.map = new int[totalRows][totalColumns];
		    
		    for (int row = 0; row < this.totalRows; row++) {
				String line = bufferedReader.readLine();
				String[] tokens = line.split(" ");
				for (int col = 0; col < this.totalColumns; col++) {
					this.map[row][col] = Integer.parseInt(tokens[col]);
					if (this.map[row][col] == 1) {
						this.handler.add(new Block(col * 32, row * 32));
					}
				}						   			
		    }
		} catch (IOException e) {
		    System.out.printf("An error occurred: %s\n", e);
		}
		
	}
	
	public int getMinOffsetX() {
		return minOffsetX;
	}
	
	public int getMinOffsetY() {
		return minOffsetY;
	}
	
	public int getMaxOffsetX() {
		return maxOffsetX;
	}
	
	public int getMaxOffsetY() {
		return maxOffsetY;
	}
}
