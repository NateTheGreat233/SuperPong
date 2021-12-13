package display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.Manager;
import interfaces.KeyAttentive;

public class KeyDetector implements KeyListener 
{
	
	private Manager manager;
	
	public KeyDetector()
	{
		manager = Manager.getManager();
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		Panel p = manager.getWindow().getCurrentPanel();
		
		for (KeyAttentive keyListener : p.getKeyAttentives())
		{
			keyListener.keyPressed(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		Panel p = manager.getWindow().getCurrentPanel();
		
		for (KeyAttentive keyListener : p.getKeyAttentives())
		{
			keyListener.keyReleased(e);
		}
	}

}
