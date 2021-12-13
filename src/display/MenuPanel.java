package display;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import UI.Button;
import UI.UIColor;
import core.Manager;

public class MenuPanel extends Panel 
{

	private static final long serialVersionUID = 1L;
	
	private Color green;
	private Color hoverColor;
	private Color clickColor;
	
	private JLabel title;
	private JButton startButton;
	private JButton instructionsButton;
	private JButton exitButton;

	@Override
	public void initializePanel() 
	{
		super.initializePanel();
		
		green = new Color(69, 230, 114);
		hoverColor = new Color(43, 107, 61);
		clickColor = new Color(27, 66, 38);
		
		//UIColor buttonColors = new UIColor(green, green, Color.black, new Color(43, 107, 61), new Color(43, 107, 61));
		
		title = createText("Super Pong", green, TITLE_FONT, SwingConstants.CENTER, SwingConstants.CENTER, 0.5, 0.1, 0.5, 0.35);
		
		startButton = createButton("Start", BUTTON_TEXT_FONT, green, Color.black, green, 0.5, 0.5, 0.25, 0.1, 0.25, 0.25);
		startButton.addActionListener(new ActionListener() 
		{ 
			  public void actionPerformed(ActionEvent e) 
			  { 
				  Manager.getManager().getWindow().changePanel("game");
			  } 
		});
		startButton.addMouseListener(new java.awt.event.MouseAdapter() 
		{
		    public void mouseEntered(java.awt.event.MouseEvent evt) 
		    {
		        startButton.setBackground(hoverColor);
		    }
		    
		    public void mousePressed(java.awt.event.MouseEvent evt)
		    {
		    	startButton.setBackground(clickColor);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) 
		    {
		        startButton.setBackground(Color.BLACK);
		    }
		});
		
		instructionsButton = createButton("Instructions", BUTTON_TEXT_FONT, green, Color.black, green, 0.5, 0.65, 0.25, 0.1, 0.5, 0.5);
		instructionsButton.addActionListener(new ActionListener() 
		{ 
			  public void actionPerformed(ActionEvent e) 
			  { 
				  Manager.getManager().setShouldRun(false);
			  } 
		});
		instructionsButton.addMouseListener(new java.awt.event.MouseAdapter() 
		{
		    public void mouseEntered(java.awt.event.MouseEvent evt) 
		    {
		    	instructionsButton.setBackground(new Color(43, 107, 61));
		    }
		    
		    public void mousePressed(java.awt.event.MouseEvent evt)
		    {
		    	instructionsButton.setBackground(clickColor);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) 
		    {
		        instructionsButton.setBackground(Color.BLACK);
		    }
		});
		
		exitButton = createButton("Exit", BUTTON_TEXT_FONT, green, Color.black, green, 0.5, 0.8, 0.25, 0.1, 0.25, 0.25);
		exitButton.addActionListener(new ActionListener() 
		{ 
			  public void actionPerformed(ActionEvent e) 
			  { 
				  Manager.getManager().setShouldRun(false);
			  } 
		});
		exitButton.addMouseListener(new java.awt.event.MouseAdapter() 
		{
		    public void mouseEntered(java.awt.event.MouseEvent evt) 
		    {
		        exitButton.setBackground(new Color(43, 107, 61));
		    }
		    
		    public void mousePressed(java.awt.event.MouseEvent evt)
		    {
		    	exitButton.setBackground(clickColor);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) 
		    {
		    	exitButton.setBackground(Color.BLACK);
		    }
		});
	}
	
	@Override
	public void onEnter()
	{
		add(title);
		add(startButton);
		add(instructionsButton);
		add(exitButton);
	}
	
	public void onExit()
	{
		remove(title);
		remove(startButton);
		remove(instructionsButton);
		remove(exitButton);
	}

	@Override
	public String getName() {
		return "menu";
	}

}