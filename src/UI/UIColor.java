package UI;

import java.awt.Color;

public class UIColor 
{

	private Color textColor;
	private Color borderColor;
	private Color backgroundColor;
	private Color hoverColor;
	private Color clickColor;
	
	//Used for buttons
	public UIColor(Color textColor, Color borderColor, Color backgroundColor, Color hoverColor, Color clickColor)
	{
		this.textColor = textColor;
		this.borderColor = borderColor;
		this.backgroundColor = backgroundColor;
		this.hoverColor = hoverColor;
		this.clickColor = clickColor;
	}
	
	//Used for text
	public UIColor(Color textColor)
	{
		this.textColor = textColor;
	}
	
	public Color getTextColor()
	{
		return textColor;
	}
	
	public Color getBorderColor()
	{
		return borderColor;
	}
	
	public Color getBackgroundColor()
	{
		return backgroundColor;
	}
	
	public Color getHoverColor()
	{
		return hoverColor;
	}
	
	public Color getClickColor()
	{
		return clickColor;
	}
	
}
