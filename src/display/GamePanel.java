package display;

import objects.Player;

public class GamePanel extends Panel 
{
	private static final long serialVersionUID = 1L;

	@Override
	public String getName() 
	{
		return "game";
	}
	
	@Override
	public void initializePanel()
	{
		super.initializePanel();
		
		Player player = new Player(0, 5, 5, 35, 200);
		
		updatables.add(player);
		drawables.add(player);
		keyAttentives.add(player);
	}
	
	@Override
	public void onEnter()
	{
		
	}
	
	@Override
	public void onExit()
	{
		
	}

}
