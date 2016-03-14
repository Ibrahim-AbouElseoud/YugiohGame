package eg.edu.guc.yugioh.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eg.edu.guc.yugioh.gui.Main;
import eg.edu.guc.yugioh.gui.MainMenu;

public class ActionExitGame implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
		//Main.getMainMenu().setVisible(false);
	}

}
