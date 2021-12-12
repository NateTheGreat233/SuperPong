package display;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;

public class Window extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	private KeyDetector keyDetector;
	private Panel currentPanel;
	private ArrayList<Panel> panels;
	
	private int width;
	private int height;

	public Window(int width, int height)
	{
		keyDetector = new KeyDetector();
		this.width = width;
		this.height = height;
		
		addKeyListener(keyDetector);
		setSize((int)(width), (int)(height)); 
		setUndecorated(true);
		setLocationRelativeTo(null);
		setVisible(true);
		
		initializePanels();
	}
	
	public void changePanel(String name) 
	{
		for (Panel p : panels)
		{
			if (p.getName().equals(name) && p != currentPanel)
			{
				if (currentPanel != null) 
				{
					currentPanel.onExit();
					remove(currentPanel);
				}
				currentPanel = p;
				add(currentPanel);
				currentPanel.onEnter();
				return;
			}
		}
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public Panel getCurrentPanel()
	{
		return currentPanel;
	}
	
	private void initializePanels()
	{
		panels = new ArrayList<Panel>();
		
		MenuPanel menuPanel = new MenuPanel();
		
		panels.addAll(Arrays.asList(menuPanel));
		changePanel("menu");
	}
	
}
