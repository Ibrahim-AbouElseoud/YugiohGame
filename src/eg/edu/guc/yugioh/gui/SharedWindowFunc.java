package eg.edu.guc.yugioh.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SharedWindowFunc extends JFrame{
	static File iconImage= new File("MainIcon1.png"); //reads the image file
	//private Image titleIcon=ImageIO.read(iconImage); //converts file to image
	public SharedWindowFunc(String title) throws IOException{

		super(title);// title is the window top name
		Image titleIcon=ImageIO.read(iconImage); //converts file to image
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setIconImage(titleIcon);
		this.setResizable(false);



	}
}
