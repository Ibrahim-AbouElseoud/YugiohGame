package eg.edu.guc.yugioh.gui.board;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class PhaseBorder extends JLabel {
	private boolean isHighlighted=false;
	public PhaseBorder(){
		super();
		this.setPreferredSize(new Dimension(120,42));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		this.setOpaque(false);
		this.setVisible(false);
		
	}

	public boolean isHighlighted() {
		return isHighlighted;
	}

	public void setHighlighted(boolean isHighlighted) {
		this.isHighlighted = isHighlighted;
	}
	public void toggleBorder(){
		if(isHighlighted){
			this.setVisible(false);
			isHighlighted=false;
		}
		else {this.setVisible(true);
		isHighlighted=true;
			
		}
	}
	public void resetborderOff(){
		this.setVisible(false);
		isHighlighted=false;
	}
	public void setBorderOn(){
		this.setVisible(true);
		isHighlighted=true;
	}

}
