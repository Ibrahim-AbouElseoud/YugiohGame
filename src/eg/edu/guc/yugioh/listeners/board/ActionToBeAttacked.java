package eg.edu.guc.yugioh.listeners.board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eg.edu.guc.yugioh.gui.Main;
import eg.edu.guc.yugioh.gui.board.buttons.MonsterCardB;
// use by clicking on the opponent monster do this by replacing the listeners when it is active player turn , then in opposing player turn
// invert the listeners by giving him the attack and to the previous player the to be attacked listeners
//also don't forget to do if field==empty then call the attack directly method
public class ActionToBeAttacked implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		MonsterCardB monsterToBeAttacked=(MonsterCardB) e.getSource();
		Main.getGameBoard().setMonsterToBeAttacked(monsterToBeAttacked);
	}

}
