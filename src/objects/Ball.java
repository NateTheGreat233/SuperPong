package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import core.Manager;
import display.GamePanel;
import interfaces.Collidable;
import interfaces.Drawable;
import interfaces.Updatable;

public class Ball implements Drawable, Updatable, Collidable
{
	
	private double speed;
	private boolean movingUp;
	private boolean movingRight;
	
	private double x;
	private double y;
	private double width;
	private double height;
	
	private int windowWidth;
	private int windowHeight;
	private GamePanel gamePanel;

	public Ball(double x, double y, double width, double height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		speed = 7.5;
		movingUp = Math.random() < 0.5;
		movingRight = Math.random() > 0.5;
		
		windowWidth = Manager.getManager().getWindowWidth();
		windowHeight = Manager.getManager().getWindowHeight();
		
		gamePanel = (GamePanel)Manager.getManager().getWindow().getCurrentPanel();
	}
	
	@Override
	public void update(double delta)
	{
		double deltaX = movingRight ? speed*delta : -speed*delta;
		double deltaY = movingUp ? -speed*delta : speed*delta;
		
		x += deltaX;
		y += deltaY;
		
		if (x + width < 0)
		{
			gamePanel.changeScore(true, 1);
			gamePanel.removeBall(this);
		}
		else if (x > windowWidth)
		{
			gamePanel.changeScore(false, 1);
			gamePanel.removeBall(this);
		}
		if (y < 0)
		{
			y = 0;
			movingUp = false;
		}
		else if (y + height > windowHeight)
		{
			y = windowHeight - height;
			movingUp = true;
		}
		
	}
	
	@Override
	public void draw(Graphics g)
	{
		g.setColor(Color.white);
		g.fillOval((int)x, (int)y, (int)width, (int)height);
	}

	@Override
	public void onCollision(Collidable other) 
	{
		if (other instanceof Paddle)
		{
			movingRight = !movingRight;
			
			Rectangle paddleBounds = other.getBounds();
			if (movingRight)
			{
				x = paddleBounds.getX() + paddleBounds.getWidth();
			}
			else
			{
				x = paddleBounds.getX() - width;
			}
		}
	}

	@Override
	public Rectangle getBounds() 
	{
		return new Rectangle((int)x, (int)y, (int)width, (int)height);
	}
	
}
