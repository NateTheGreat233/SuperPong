package objects;

import java.awt.Rectangle;

public abstract class Paddle extends Rectangle {

	private static final long serialVersionUID = 1L;
	
	public static final int PLAYER_1 = 0;
	public static final int PLAYER_2 = 1;
	public static final int COMPUTER = 2;
	
	protected int xPos;
	protected int yPos;
	protected int width;
	protected int height;
	
	protected int type;
	
	public Paddle(int type, int xPos, int yPos, int width, int height)
	{
		super(xPos, yPos, width, height);
		this.type = type;
	}
	
}
