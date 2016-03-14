package eg.edu.guc.yugioh.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eg.edu.guc.yugioh.listeners.ActionExitGame;
import eg.edu.guc.yugioh.listeners.ActionOpenBoard;

@SuppressWarnings("serial")
public class MainMenu extends SharedWindowFunc{
	//static File iconImage= new File("MainIcon1.png"); //reads the image file
	//private Image titleIcon=ImageIO.read(iconImage); //converts file to image
	public MainMenu(String title) throws IOException{
		
		super(title);// title is the window top name
		//Main.setMainMenu(this); not necessecry
		//Image titleIcon=ImageIO.read(iconImage); //converts file to image
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.setIconImage(titleIcon);
		this.setLayout(new BorderLayout());// this is to put image in center
		this.setContentPane(new JLabel(new ImageIcon("Background.jpg")));
		this.setLayout(new FlowLayout()); //to put stuff next to each other
		
		
		JButton start = new JButton ("Start");
		start.addActionListener(new ActionOpenBoard() );
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionExitGame());
		//this.add(exit);
		this.setSize(1024, 768);
		JPanel buttons = new JPanel();
		//buttons.setLayout(new FlowLayout());
		//buttons.setLayout (new BoxLayout (this, BoxLayout.Y_AXIS)); 
		buttons.setLayout(new GridLayout(2,1));
		start.setPreferredSize(new Dimension(100, 50));
		exit.setPreferredSize(new Dimension(100, 50));
		buttons.add(start);
		buttons.add(exit);
		exit.setVisible(true);
		start.setVisible(true);
		buttons.setVisible(true);
		this.add(buttons,BorderLayout.SOUTH);//TODO: how come doesn't work??
		
		
		//this.setResizable(false);
		this.validate();
	}

}
