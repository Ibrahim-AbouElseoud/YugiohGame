package eg.edu.guc.yugioh.gui.board;

import javax.swing.JButton;

public class InvisibleButton extends JButton{
	public InvisibleButton(){
		super();
		this.setOpaque(false);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
	}

}
