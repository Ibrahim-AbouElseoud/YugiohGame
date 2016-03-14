package eg.edu.guc.yugioh.listeners.board;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import eg.edu.guc.yugioh.gui.Main;
import eg.edu.guc.yugioh.gui.board.SetnActivateMenu;
import eg.edu.guc.yugioh.gui.board.buttons.CardB;

public class MouseHoverToCardInfo extends MouseAdapter {
// contains also the set and activate menu
//	@Override
//	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
//		CardB a= (CardB) e.getComponent();
//		SetnActivateMenu m=new SetnActivateMenu(a,e.getX(),e.getY());
		
//	}

//	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		CardB card=(CardB) e.getSource();
		if(card.getCard().isHidden()){
			if(Main.getController().getBoard().getActivePlayer().getField().getMonstersArea().contains(card.getCard())||Main.getController().getBoard().getActivePlayer().getField().getSpellArea().contains(card.getCard())||Main.getController().getBoard().getActivePlayer().getField().getHand().contains(card.getCard()))
					Main.getGameBoard().updateCardInfoPanel(card);
			else {
		//		Main.getGameBoard().getTextArea.setText("Card is face down");
			//	Main.getGameBoard().getCardInfoPicture.setIcon(new ImageIcon("cardsL\\Cardback.jpg"));
				//Main.getGameBoard().getAttack.setText("");
				//Main.getGameBoard().getDefense.setText("");		
			}
		}
		else Main.getGameBoard().updateCardInfoPanel(card);
	
		
	}

//	@Override
//	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
//	}

//	@Override
//	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
//	}

//	@Override
//	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
//	}

}
