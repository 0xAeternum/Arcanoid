package Arcanoid;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * 
 * @author Alpay Vodenicharov 2015\
 * Simple arcanoid game
 *
 */
public class Arcanoid {
	public static ArrayList<Squares> squares = new ArrayList<Squares>();
	public int screenHeight, screenWidth;
	public int margin = 10;
	static boolean running = true;
	static Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	static JFrame frame = new JFrame();
	static public int ballXcoord = screensize.width/2;
	static public int ballYcoord = screensize.height/2;
	static boolean down = true;
	static boolean right = false;
	static public int score = 0;
	static public int speed;
	public void run() {
		// making vars
		// gametimer
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(screensize.width, screensize.height);
		frame.getContentPane().setBackground(Color.black);
		frame.addMouseListener(new MouseEventListener());
		frame.setVisible(true);
		/*
		 * BorderLayout layout = new BorderLayout(); frame.add(layout);
		 */
		frame.getContentPane().add(new MyCanvas());
		// frame.getContentPane().add(new asd());
		// Make squares
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 4; j++) {
				Squares temp = new Squares(
						(int) ((screensize.width / 11) + (int) (screensize.width / 11 / 11)) * i
								+ (int) (screensize.width / 11 / 11),
						((int) (screensize.height / 3 / 5) + (int) (screensize.width / 3 / 5 / 5)) * j
								+ (int) (screensize.height / 3 / 5 / 5),
						Color.BLUE, Color.CYAN, screensize);
				squares.add(temp);
			}
		}

		while (running) {
			frame.repaint();
			if (Arcanoid.ballYcoord >= Arcanoid.screensize.height ) {
				Arcanoid.running = false;
			}
			
			if (score < 5) {
				speed = 2;
			} else if (score < 10) {
				speed = 3;
			} else if (score < 15) {
				speed = 4;	
			} else if (score < 20) {
				speed = 5;
			} else if (score < 25) {
				speed = 6;
			} else if (score < 30) {
				speed = 7;
			} else if (score < 35) {
				speed = 8;
			} else if (score < 40) {
				speed = 9;
			}
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				System.out.println("interrupted");
			}
		}
		
		
	}

	public static void main(String[] args) {
		new Arcanoid().run();
	}
}

class MyCanvas extends JComponent {

	public void paint(Graphics g) {
		Graphics2D canvas = (Graphics2D) g;
		int i = 0;
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		int mouseX = (int) b.getX();
		int mouseY = (int) b.getY();
		Arcanoid.frame.repaint();
		g.setColor(Color.WHITE);
		if (Arcanoid.ballYcoord >= Arcanoid.screensize.height ) {
			Arcanoid.running = false;
			Font font = new Font("hi", 100 , 100);
			g.setFont(font);
			g.setColor(Color.RED);
			g.drawString("Game over", (Arcanoid.screensize.width/3)+100, (Arcanoid.screensize.height/2)+100);
			Arcanoid.frame.repaint();
		}
		if (Arcanoid.ballXcoord >= Arcanoid.screensize.width ) {
			Arcanoid.right = false;
		}
		else if (Arcanoid.ballXcoord <= 0 ) {
				Arcanoid.right = true;
			}
		else if ( Arcanoid.ballXcoord >= mouseX-50 && Arcanoid.ballXcoord <= mouseX+50 && Arcanoid.ballYcoord >= 
				Arcanoid.screensize.height-150 && Arcanoid.ballYcoord <= Arcanoid.screensize.height - 140) {
				Arcanoid.down = false;
			}
		else if (Arcanoid.ballYcoord <= 0 ) {
				Arcanoid.down = true;
			}
		
		if (Arcanoid.down && Arcanoid.right) {
			Arcanoid.ballXcoord += Arcanoid.speed;
			Arcanoid.ballYcoord += Arcanoid.speed;
		}
		else if (Arcanoid.down && !Arcanoid.right) {
			Arcanoid.ballXcoord -= Arcanoid.speed;
			Arcanoid.ballYcoord += Arcanoid.speed;
		}
		else if (!Arcanoid.down && Arcanoid.right) {
			Arcanoid.ballXcoord += Arcanoid.speed;
			Arcanoid.ballYcoord -= Arcanoid.speed;
		}
		else if (!Arcanoid.down && !Arcanoid.right) {
			Arcanoid.ballXcoord -= Arcanoid.speed;
			Arcanoid.ballYcoord -= Arcanoid.speed;
		}
		g.fillOval(Arcanoid.ballXcoord, Arcanoid.ballYcoord, 20, 20);
		g.setColor(Color.GREEN);
		g.fillRect(mouseX - 50, Arcanoid.screensize.height - 150, 100, 30);
		g.setColor(Color.RED);
		g.drawRect(mouseX - 50, Arcanoid.screensize.height - 150, 100, 30);
		
		boolean change = false;
		
		while (Arcanoid.squares.size() > i) {
			
			g.setColor(Arcanoid.squares.get(i).getColor());
			g.fillRect(Arcanoid.squares.get(i).getX(), Arcanoid.squares.get(i).getY(),
					Arcanoid.squares.get(i).getWidth(), Arcanoid.squares.get(i).getHeight());
			g.setColor(Arcanoid.squares.get(i).getBorderColor());
			g.drawRect(Arcanoid.squares.get(i).getX(), Arcanoid.squares.get(i).getY(),
					Arcanoid.squares.get(i).getWidth(), Arcanoid.squares.get(i).getHeight());
			
			if (Arcanoid.ballXcoord >= Arcanoid.squares.get(i).getX() && Arcanoid.ballXcoord <= Arcanoid.squares.get(i).getX() + 10 && 
					Arcanoid.ballYcoord >= Arcanoid.squares.get(i).getY() && Arcanoid.ballYcoord <= (Arcanoid.squares.get(i).getY() + Arcanoid.squares.get(i).getHeight()) ||
					Arcanoid.ballXcoord <= Arcanoid.squares.get(i).getX() + Arcanoid.squares.get(i).getWidth() && Arcanoid.ballXcoord >= Arcanoid.squares.get(i).getX() + Arcanoid.squares.get(i).getWidth() - 10 && 
					Arcanoid.ballYcoord >= Arcanoid.squares.get(i).getY() && Arcanoid.ballYcoord <= (Arcanoid.squares.get(i).getY() + Arcanoid.squares.get(i).getHeight())) {
				
				if (Arcanoid.right == true) {
					Arcanoid.right = false;
				} else {
					Arcanoid.right = true;
				}
				
				Arcanoid.squares.remove(i);
				Arcanoid.score++;
				
			}
			
			else if (Arcanoid.ballYcoord >= Arcanoid.squares.get(i).getY() && Arcanoid.ballYcoord <= Arcanoid.squares.get(i).getY() + 10 && 
					Arcanoid.ballXcoord >= Arcanoid.squares.get(i).getX() && Arcanoid.ballYcoord <= (Arcanoid.squares.get(i).getX() + Arcanoid.squares.get(i).getWidth()) ||
					Arcanoid.ballYcoord <= Arcanoid.squares.get(i).getY() + Arcanoid.squares.get(i).getHeight() && Arcanoid.ballYcoord >= Arcanoid.squares.get(i).getY() + Arcanoid.squares.get(i).getHeight() - 10 && 
					Arcanoid.ballXcoord >= Arcanoid.squares.get(i).getX() && Arcanoid.ballXcoord <= (Arcanoid.squares.get(i).getX() + Arcanoid.squares.get(i).getWidth())) {
				
				if (Arcanoid.down == true) {
					Arcanoid.down = false;
				} else {
					Arcanoid.down = true;
				}
				
				Arcanoid.score++;
				Arcanoid.squares.remove(i);
			}
			
			/**if (Arcanoid.ballXcoord >= Arcanoid.squares.get(i).getX() && Arcanoid.ballXcoord <= (Arcanoid.squares.get(i).getX() + Arcanoid.squares.get(i).getWidth()) && 
					Arcanoid.ballYcoord >= Arcanoid.squares.get(i).getY() && Arcanoid.ballYcoord <= (Arcanoid.squares.get(i).getY() + Arcanoid.squares.get(i).getHeight())) {
				Arcanoid.squares.remove(i);
				change = true;
			}
			
			if (change == true) {
				change = false;
				
				if (Arcanoid.down == true) {
					Arcanoid.down = false;
				} else {
					Arcanoid.down = true;
				}
				
				if (Arcanoid.right == true) {
					Arcanoid.right = false;
				} else {
					Arcanoid.right = true;
				}
			} */
			
			i++;
			
			
		}
		
		g.setColor(Color.MAGENTA);
		g.drawString(Arcanoid.score + " / 40", 10, Arcanoid.screensize.height - 50);
	}
}

class MouseEventListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		Arcanoid.ballXcoord = Arcanoid.screensize.width/2;
		Arcanoid.ballYcoord = Arcanoid.screensize.height/2;
		Arcanoid.down = true;
		Arcanoid.right = false;
		Arcanoid.score = 0;
		Arcanoid.running = true;
		Arcanoid.squares.clear();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 4; j++) {
				Squares temp = new Squares(
						(int) ((Arcanoid.screensize.width / 11) + (int) (Arcanoid.screensize.width / 11 / 11)) * i
								+ (int) (Arcanoid.screensize.width / 11 / 11),
						((int) (Arcanoid.screensize.height / 3 / 5) + (int) (Arcanoid.screensize.width / 3 / 5 / 5)) * j
								+ (int) (Arcanoid.screensize.height / 3 / 5 / 5),
						Color.BLUE, Color.CYAN, Arcanoid.screensize);
				Arcanoid.squares.add(temp);
			}
		}

		Arcanoid.frame.repaint();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

class asd extends JComponent {
	public void paint(Graphics g) {
		Graphics2D asde = (Graphics2D) g;
		PointerInfo a = MouseInfo.getPointerInfo();
	}
}