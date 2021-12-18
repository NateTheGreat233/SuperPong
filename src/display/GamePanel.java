package display;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import interfaces.Updatable;
import objects.Ball;
import objects.Computer;
import objects.Player;

public class GamePanel extends Panel implements Updatable
{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Ball> balls;
	private JLabel leftScoreText;
	private JLabel rightScoreText;
	private int leftScore;
	private int rightScore;

	@Override
	public String getName() 
	{
		return "game";
	}
	
	@Override
	public void initializePanel()
	{
		super.initializePanel();
		
		leftScore = 0;
		rightScore = 0;
		leftScoreText = createText("" + leftScore, Color.gray, TITLE_FONT, SwingConstants.CENTER, SwingConstants.CENTER, 0.45, 0.05, 0.3, 0.15);
		rightScoreText = createText("" + rightScore, Color.gray, TITLE_FONT, SwingConstants.CENTER, SwingConstants.CENTER, 0.55, 0.05, 0.3, 0.15);
		
		balls = new ArrayList<Ball>();
		Player player = new Player(5, windowHeight/2 - 100, 35, 200);	
		
		updatables.addAll(Arrays.asList(player, this));
		collidables.addAll(Arrays.asList(player));
		drawables.addAll(Arrays.asList(player));
		keyAttentives.add(player);
		add(leftScoreText);
		add(rightScoreText);
	}
	
	public void removeBall(Ball b)
	{
		toRemove.add(b);
		balls.remove(b);
		Ball ball = new Ball(windowWidth/2, windowHeight/2, 30, 30);
		balls.add(ball);
		toAdd.add(ball);
	}
	
	public ArrayList<Ball> getBalls()
	{
		return balls;
	}
	
	public void changeScore(boolean addRight, int delta)
	{
		if (addRight)
		{
			rightScore += delta;
			rightScoreText.setText("" + rightScore);
		}
		else
		{
			leftScore += delta;
			leftScoreText.setText("" + leftScore);
		}
	}
	
	@Override
	public void onEnter()
	{
		Computer cpu = new Computer(windowWidth - 40, windowHeight/2 - 100, 35, 200);
		Ball ball = new Ball(windowWidth/2, windowHeight/2, 30, 30);
		balls.add(ball);
		toAdd.add(cpu);
		toAdd.add(ball);
	}
	
	@Override
	public void onExit()
	{
		balls.clear();
	}

	@Override
	public void update(double delta) 
	{		
		for (int i = 0; i < collidables.size(); i++)
		{
			for (int j = i + 1; j < collidables.size(); j++)
			{
				Rectangle rect1 = collidables.get(i).getBounds();
				Rectangle rect2 = collidables.get(j).getBounds();
				
				if (rect1.intersects(rect2))
				{
					collidables.get(i).onCollision(collidables.get(j));
					collidables.get(j).onCollision(collidables.get(i));
				}
			}
		}
	}
}
