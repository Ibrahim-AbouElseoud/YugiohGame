package eg.edu.guc.yugioh.gui.board;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import eg.edu.guc.yugioh.gui.Main;
import eg.edu.guc.yugioh.gui.board.buttons.CardB;

public class SetnActivateMenu extends JPopupMenu{
	private JMenuItem set = new JMenuItem("Set");
	private JMenuItem activate=new JMenuItem("Activate");
	private CardB button;
	public SetnActivateMenu(CardB button,int x,int y){
		super();
		this.add(set);
		this.add(activate);
		this.button=button;
		this.show(button, x, y);
	}
	public CardB getButton() {
		return button;
	}
	public void setButton(CardB button) {
		this.button = button;
	}



}
//JKB Games -> Mega man