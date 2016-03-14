package eg.edu.guc.yugioh.listeners.board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eg.edu.guc.yugioh.gui.board.buttons.CardB;

public class ActionTestName implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		CardB a= (CardB) e.getSource();
		System.out.println(a.getName());
	}

}
