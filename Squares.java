 package Arcanoid;

import java.awt.Color;
import java.awt.Dimension;
public class Squares {
	private int x , y , height = 50 , width;
	private Color color;
	private Color borderColor;
	
	public Squares(int x ,int y  , Color color, Color borderColor, Dimension screenSize) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.width = (int)(screenSize.width / 11);
		this.height = (int)(screenSize.height / 3 / 5);
		this.borderColor = borderColor;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return color;
	}
	public int getHeight() {
		return height;
	}
	
	public Color getBorderColor() {
		return borderColor;
	}
	public int getWidth() {
		return width;
	}
	public int getY() {
		return y;
	}
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
}
