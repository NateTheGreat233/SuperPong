package objects;

import java.util.ArrayList;

import core.Manager;
import display.GamePanel;
import interfaces.Collidable;
import interfaces.Drawable;
import interfaces.Updatable;

public class Computer extends Paddle implements Drawable, Updatable, Collidable {

	private static final long serialVersionUID = 1L;
	
	private GamePanel gamePanel;
	private int windowHeight;

	public Computer(int xPos, int yPos, int width, int height) 
	{
		super(xPos, yPos, width, height);
		gamePanel = (GamePanel)Manager.getManager().getWindow().getCurrentPanel();
		windowHeight = Manager.getManager().getWindowHeight();
	}
	
	@Override
	public void move(double delta)
	{
		ArrayList<Ball> balls = gamePanel.getBalls();
		if (balls.size() == 0)
		{
			if (yPos + height/2 > windowHeight/2)
			{
				yPos -= speed*delta;
			}
			else
			{
				yPos += speed*delta;
			}
		}
		else
		{
			Ball closestBall = balls.get(0);
			for (int i = 1; i < balls.size(); i++)
			{
				Ball currentBall = balls.get(i);
				if (currentBall.getBounds().getX() > closestBall.getBounds().getX())
				{
					closestBall = currentBall;
				}
			}
			
			double ballY = closestBall.getBounds().getY();
			double ballHeight = closestBall.getBounds().getHeight();
			if (ballY + ballHeight/2 > yPos + height/2)
			{
				yPos += speed*delta;
			}
			else
			{
				yPos -= speed*delta;
			}
		}
		
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

}
