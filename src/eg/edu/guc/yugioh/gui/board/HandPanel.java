package eg.edu.guc.yugioh.gui.board;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class HandPanel extends JPanel {
	// use this by just adding buttons
	//alternative idea is to instantiate an array of buttons as it's default attribute then acess and change them
	public HandPanel(){
		super();
		this.setLayout(new GridLayout(1, 40, 10, 1));
	//	JScrollPane scroller = new JScrollPane(this);//TODO: Scroll panel doesn't work
		JScrollPane scroller = new JScrollPane(this,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	//	scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//scroller.setVerticalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//this.add(scroller);
	}

}
