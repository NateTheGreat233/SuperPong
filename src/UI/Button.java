package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import core.Manager;
import display.Panel;
import display.Window;

public class Button extends JButton 
{

	private static final long serialVersionUID = 1L;

	//prioritizes button size
	//x,y,width,height are proportions of the window size
	public Button(String text, Font f, UIColor colors, Window w, boolean addListeners, String panelName, double xPos, double yPos, double width, double height, double bufferX, double bufferY)
	{
		int windowWidth = w.getWidth();
		int windowHeight = w.getHeight();
		
		JButton button = new JButton(text);
		button.setFocusable(false);
		button.setFont(f);
		button.setForeground(colors.getTextColor());
		button.setBackground(colors.getBackgroundColor());
		button.setBorder(BorderFactory.createLineBorder(colors.getBorderColor()));
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
		} while (tempFont.getStringBounds(text, frc).getWidth() < width*Window.SCREEN_WIDTH*(1 - bufferX) &&
				tempFont.getStringBounds(text, frc).getHeight() < height*Window.SCREEN_HEIGHT*(1 - bufferY));
		
		button.setFont(new Font(fontName, fontType, tempSize - 1));
		
		button.setBounds((int)(xPos*windowWidth - width*windowWidth/2), (int)(yPos*windowHeight - height*windowHeight/2), (int)(width*windowWidth), (int)(height*windowHeight));
	
		if (addListeners)
		{
			addListeners(panelName, colors.getHoverColor(), colors.getBackgroundColor(), colors.getClickColor());
		}
	}
	
	private void addListeners(String name, Color hoverColor, Color backgroundColor, Color clickColor)
	{
		addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  Manager.getManager().getWindow().changePanel(name);
			  } 
		});
		
		addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        setBackground(hoverColor);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        setBackground(backgroundColor);
		    }
		});
	}
	
}
