package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import core.Manager;
import interfaces.Drawable;
import interfaces.KeyAttentive;
import interfaces.Updatable;

public abstract class Paddle extends Rectangle implements KeyAttentive, Drawable, Updatable 
{

	private static final long serialVersionUID = 1L;
	
	public static final int PLAYER_1 = 0;
	public static final int PLAYER_2 = 1;
	public static final int COMPUTER = 2;
	
	protected double yPos;
	
	protected double speed;
	protected int type;
	protected boolean movingUp;
	protected boolean movingDown;
	protected Color color;
	protected int buffer;
	
	public Paddle(int type, int xPos, int yPos, int width, int height)
	{
		super(xPos, yPos, width, height);
		this.yPos = yPos;
		this.type = type;
		speed = 5;
		color = new Color(69, 230, 114);
		buffer = 5;
	}
	
	protected void move(double delta)
	{
		double deltaY = 0;
		
		if (movingDown)
		{
			deltaY += speed*delta;
		}
		if (movingUp)
		{
			deltaY -= speed*delta;
		}
		
		yPos += deltaY;
		
		int windowHeight = Manager.getManager().getWindowHeight();
		if (yPos + height + buffer > windowHeight)
		{
			yPos = windowHeight - height - buffer;
		}
		else if (yPos < buffer)
		{
			yPos = buffer;
		}
		
		y = (int)yPos;
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_Q)
		{
			movingUp = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_A)
		{
			movingDown = true;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_Q)
		{
			movingUp = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_A)
		{
			movingDown = false;
		}
	}
	
	@Override
	public void update(double delta)
	{
		move(delta);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());
	}
	
}
