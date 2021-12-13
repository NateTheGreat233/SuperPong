package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import core.Manager;
import interfaces.Drawable;
import interfaces.KeyAttentive;
import interfaces.Updatable;

public abstract class Panel extends JPanel 
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
		manager = Manager.getManager();
		windowWidth = Manager.getManager().getWindowWidth();
		windowHeight = Manager.getManager().getWindowHeight();
		setBackground(Color.black);
		setLayout(null);
		repaint();
		revalidate();
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
