package display;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import core.Manager;

public class MenuPanel extends Panel {

	private static final long serialVersionUID = 1L;

	@Override
	public void initializePanel() 
	{
		super.initializePanel();
		
		JLabel title = createText("Super Pong", Color.green, TITLE_FONT, SwingConstants.CENTER, SwingConstants.CENTER, 0.5, 0.1, 0.5, 0.35);
		JButton startButton = createButton("Start", BUTTON_TEXT_FONT, Color.green, Color.black, Color.green, 0.5, 0.5, 0.25, 0.1, 0.25, 0.25);
		startButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  Manager.getManager().getWindow().changePanel("start");
			  } 
		});
		JButton instructionsButton = createButton("Instructions", BUTTON_TEXT_FONT, Color.green, Color.black, Color.green, 0.5, 0.65, 0.25, 0.1, 0.25, 0.25);
		instructionsButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  Manager.getManager().setShouldRun(false);
			  } 
		});
		JButton exitButton = createButton("Exit", BUTTON_TEXT_FONT, Color.green, Color.black, Color.green, 0.5, 0.8, 0.25, 0.1, 0.25, 0.25);
		exitButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  Manager.getManager().setShouldRun(false);
			  } 
		});
		
		add(title);
		add(startButton);
		add(instructionsButton);
		add(exitButton);
	}

	@Override
	public String getName() {
		return "menu";
	}

}