package eg.edu.guc.yugioh.gui.board;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.gui.Main;
import eg.edu.guc.yugioh.listeners.board.ActionDrawCardP1;
import eg.edu.guc.yugioh.listeners.board.ActionDrawCardP2;
import eg.edu.guc.yugioh.listeners.board.ActionEndPhase;
//highlights the current phase to change phase use changePhase
public class PhaseIndicator extends JPanel {
	private PhaseBorder main1=new PhaseBorder();
	private PhaseBorder battle=new PhaseBorder();
	private PhaseBorder main2=new PhaseBorder();
	public PhaseBorder getMain1() {
		return main1;
	}
	public PhaseBorder getBattle() {
		return battle;
	}
	public PhaseBorder getMain2() {
		return main2;
	}
	public PhaseIndicator(){
		super();
		this.setOpaque(false);
		this.setBounds(110, 0, 410, 42);//110 to 520 x-axis , y-axis 0-42 
		this.setLayout(new GridLayout(1, 3, 20, 1));
		this.add(main1);
		this.add(battle);
		this.add(main2);
		main1.toggleBorder();

	}
	public void changePhase(){
		Player P=Main.getController().getBoard().getActivePlayer();
		if(main1.isHighlighted()){
			P.endPhase();
			main1.toggleBorder();
			battle.toggleBorder();
			return;
		}
		if(battle.isHighlighted()){
			P.endPhase();
			battle.toggleBorder();
			main2.toggleBorder();
			return;
		}
		if(main2.isHighlighted()){
			P.endPhase();
			JOptionPane.showMessageDialog(Main.getGameBoard(),"Please pass device to "+Main.getController().getBoard().getActivePlayer().getName());
		//	Main.getGameBoard().getph reput the action listeners
			main2.toggleBorder();
			main1.toggleBorder();
			Main.getGameBoard().getEndPhase().setVisible(false);
			Main.getGameBoard().getEndTurn().setVisible(false);
			//Main.getGameBoard().getEndPhase().removeActionListener(new ActionEndPhase());
			//Main.getGameBoard().getEndPhase().removeActionListener(new ActionEndPhase());
			if(Main.getGameBoard().getTabArea().isEnabledAt(0)){
			Main.getGameBoard().getTabArea().setEnabledAt(1, true);
			Main.getGameBoard().getTabArea().setEnabledAt(0, false);
			Main.getGameBoard().getTabArea().setSelectedIndex(1);
			}
			else {
				Main.getGameBoard().getTabArea().setEnabledAt(0, true);
				Main.getGameBoard().getTabArea().setEnabledAt(1, false);
				Main.getGameBoard().getTabArea().setSelectedIndex(0);
			}
			if(Main.getGameBoard().getP1LabelName().getText().equals(Main.getController().getBoard().getActivePlayer().getName()))
				Main.getGameBoard().getDeckBP1().addActionListener(new ActionDrawCardP1());
			else Main.getGameBoard().getDeckBP2().addActionListener(new ActionDrawCardP2());
			
			
		}

	}
	public void EndTurn(){
		Player P=Main.getController().getBoard().getActivePlayer();
		P.endTurn();
		JOptionPane.showMessageDialog(Main.getGameBoard(),"Please pass device to "+Main.getController().getBoard().getActivePlayer().getName());
		main2.resetborderOff();
		main1.setBorderOn();
		battle.resetborderOff();
		Main.getGameBoard().getEndPhase().setVisible(false);
		Main.getGameBoard().getEndTurn().setVisible(false);
		if(Main.getGameBoard().getTabArea().isEnabledAt(0)){
		Main.getGameBoard().getTabArea().setEnabledAt(1, true);
		Main.getGameBoard().getTabArea().setEnabledAt(0, false);
		Main.getGameBoard().getTabArea().setSelectedIndex(1);
		}
		else {
			Main.getGameBoard().getTabArea().setEnabledAt(0, true);
			Main.getGameBoard().getTabArea().setEnabledAt(1, false);
			Main.getGameBoard().getTabArea().setSelectedIndex(0);
		}
		if(Main.getGameBoard().getP1LabelName().getText().equals(Main.getController().getBoard().getActivePlayer().getName()))
			Main.getGameBoard().getDeckBP1().addActionListener(new ActionDrawCardP1());
		else Main.getGameBoard().getDeckBP2().addActionListener(new ActionDrawCardP2());
	}
}
