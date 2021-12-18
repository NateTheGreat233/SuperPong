package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import core.Manager;
import interfaces.Collidable;
import interfaces.Drawable;
import interfaces.KeyAttentive;
import interfaces.Updatable;

public abstract class Panel extends JPanel implements KeyAttentive
{

	private static final long serialVersionUID = 1L;
	
	protected static final double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	protected static final double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	protected static final Font TITLE_FONT = new Font("Impact", Font.BOLD, 150);
	protected static final Font TEXT_FONT = new Font("Impact", Font.ITALIC, 30);
	protected static final Font BUTTON_TEXT_FONT = new Font("Impact", Font.PLAIN, 50);
	
	protected Manager manager;
	protected ArrayList<Drawable> drawables;
	protected ArrayList<KeyAttentive> keyAttentives;
	protected ArrayList<Updatable> updatables;
	protected ArrayList<Collidable> collidables;
	protected ArrayList<Object> toRemove;
	protected ArrayList<Object> toAdd;
	
	protected int windowWidth;
	protected int windowHeight;
	
	public Panel()
	{
		initializePanel();
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		revalidate();
		for (Drawable drawable : drawables)
		{
			drawable.draw(g);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			Manager.getManager().setShouldRun(false);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) 
	{
	}
	
	public void updateObjects(double delta)
	{
		for (Updatable updatable : updatables)
		{
			updatable.update(delta);
		}
	}
	
	public ArrayList<Drawable> getDrawables()
	{
		return drawables;
	}
	
	public ArrayList<KeyAttentive> getKeyAttentives()
	{
		return keyAttentives;
	}
	
	public abstract String getName();
	
	protected void initializePanel()
	{
		drawables = new ArrayList<Drawable>();
		keyAttentives = new ArrayList<KeyAttentive>();
		updatables = new ArrayList<Updatable>();
		collidables = new ArrayList<Collidable>();
		keyAttentives.add(this);
		toRemove = new ArrayList<Object>();
		toAdd = new ArrayList<Object>();
		manager = Manager.getManager();
		windowWidth = Manager.getManager().getWindowWidth();
		windowHeight = Manager.getManager().getWindowHeight();
		setBackground(Color.black);
		setLayout(null);
		repaint();
		revalidate();
	}
	
	public void cleanUp() 
	{
		for (Object o : toRemove)
		{
			if (drawables.contains(o))
			{
				drawables.remove(o);
			}
			if (updatables.contains(o))
			{
				updatables.remove(o);
			}
			if (keyAttentives.contains(o))
			{
				keyAttentives.remove(o);
			}
			if (collidables.contains(o))
			{
				collidables.remove(o);
			}
		}
		toRemove.clear();
		
		for (Object o : toAdd)
		{
			if (o instanceof Drawable && !drawables.contains(o))
			{
				drawables.add((Drawable)o);
			}
			if (o instanceof Updatable && !updatables.contains(o))
			{
				updatables.add((Updatable)o);
			}
			if (o instanceof KeyAttentive && !keyAttentives.contains(o))
			{
				keyAttentives.add((KeyAttentive)o);
			}
			if (o instanceof Collidable && !collidables.contains(o))
			{
				collidables.add((Collidable)o);
			}
		}
		toAdd.clear();
	}
	
	protected void onEnter()
	{
		repaint();
		revalidate();
	}
	
	protected void onExit()
	{
		repaint();
		revalidate();
	}
	
	//set font size
	protected JButton createButton(String text, Font f, Color textColor, Color backgroundColor, Color borderColor, double xPos, double yPos, double bufferX, double bufferY)
	{
		JButton button = new JButton(text);
		button.setFocusable(false);
		button.setFont(f);
		button.setForeground(textColor);
		button.setBackground(backgroundColor);
		button.setBorder(BorderFactory.createLineBorder(borderColor));
		button.setVerticalAlignment(SwingConstants.CENTER);
		button.setHorizontalAlignment(SwingConstants.CENTER);
		button.setVerticalTextPosition(SwingConstants.CENTER);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
		double textWidth = f.getStringBounds(text, frc).getWidth();
		double textHeight = f.getStringBounds(text, frc).getHeight();
		
		button.setBounds((int)(xPos*windowWidth + textWidth*0.5/(bufferX - 1)), (int)(yPos*windowWidth + textHeight*0.5/(bufferY - 1)), (int)(-textWidth/(bufferX - 1)), (int)(-textHeight/(bufferY - 1)));
		
		return button;
	}
	
	//set button size
	protected JButton createButton(String text, Font f, Color textColor, Color backgroundColor, Color borderColor, double xPos, double yPos, double width, double height, double bufferX, double bufferY)
	{
		JButton button = new JButton(text);
		button.setFocusable(false);
		button.setFont(f);
		button.setForeground(textColor);
		button.setBackground(backgroundColor);
		button.setBorder(BorderFactory.createLineBorder(borderColor));
		button.setVerticalAlignment(SwingConstants.CENTER);
		button.setHorizontalAlignment(SwingConstants.CENTER);
		button.setVerticalTextPosition(SwingConstants.CENTER);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
		
		String fontName = f.getFontName();
		int fontType = f.getStyle();
		int tempSize = 0;
		Font tempFont;
		do
		{
			tempSize++;
			tempFont = new Font(fontName, fontType, tempSize);
		} while (tempFont.getStringBounds(text, frc).getWidth() < width*SCREEN_WIDTH*(1 - bufferX) &&
				tempFont.getStringBounds(text, frc).getHeight() < height*SCREEN_HEIGHT*(1 - bufferY));
		
		button.setFont(new Font(fontName, fontType, tempSize - 1));
		
		button.setBounds((int)(xPos*windowWidth - width*windowWidth/2), (int)(yPos*windowHeight - height*windowHeight/2), (int)(width*windowWidth), (int)(height*windowHeight));
		
		return button;
	}
	
	//set width & height
	protected JLabel createText(String text, Color color, Font f, int horizontalAlignment, int verticalAlignment, double xPos, double yPos, double width, double height)
	{
		JLabel l = new JLabel(text);
		l.setForeground(color);
		l.setHorizontalAlignment(horizontalAlignment);
		l.setVerticalAlignment(verticalAlignment);
		
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
		double buffer = 0.05;
		
		String fontName = f.getFontName();
		int fontType = f.getStyle();
		int tempSize = 0;
		Font tempFont;
		do
		{
			tempSize++;
			tempFont = new Font(fontName, fontType, tempSize);
		} while (tempFont.getStringBounds(text, frc).getWidth() < width*windowWidth*(1 - buffer) &&
				tempFont.getStringBounds(text, frc).getHeight() < height*windowHeight*(1 - buffer));
		
		l.setFont(new Font(fontName, fontType, tempSize - 1));
		
		l.setBounds((int)(xPos*windowWidth - width*windowWidth/2), (int)(yPos*windowHeight - height*windowHeight/2), (int)(width*windowWidth), (int)(height*windowWidth));
		
		return l;
	}
	
	//set font size
	protected JLabel createText(String text, Color color, Font f, int horizontalAlignment, int verticalAlignment, double xPos, double yPos)
	{
		JLabel l = new JLabel(text);
		l.setForeground(color);
		l.setHorizontalAlignment(horizontalAlignment);
		l.setVerticalAlignment(verticalAlignment);
		l.setFont(f);
		
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
		double textWidth = f.getStringBounds(text, frc).getWidth();
		double textHeight = f.getStringBounds(text, frc).getHeight();
		double buffer = 0.05;
		
		l.setBounds((int)(xPos*windowWidth + textWidth*0.5/(buffer - 1)), (int)(yPos*windowHeight + textHeight*0.5/(buffer - 1)), (int)(-textWidth/(buffer - 1)), (int)(-textHeight/(buffer - 1)));
		
		return l;
	}
	
}
