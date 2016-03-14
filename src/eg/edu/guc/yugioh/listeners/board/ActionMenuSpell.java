package eg.edu.guc.yugioh.listeners.board;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.ChangeOfHeart;
import eg.edu.guc.yugioh.cards.spells.MagePower;
import eg.edu.guc.yugioh.cards.spells.MonsterReborn;
import eg.edu.guc.yugioh.cards.spells.Raigeki;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.DefenseMonsterAttackException;
import eg.edu.guc.yugioh.exceptions.MonsterMultipleAttackException;
import eg.edu.guc.yugioh.exceptions.NoSpellSpaceException;
import eg.edu.guc.yugioh.exceptions.WrongPhaseException;
import eg.edu.guc.yugioh.gui.Main;
import eg.edu.guc.yugioh.gui.board.SetnActivateMenu;
import eg.edu.guc.yugioh.gui.board.SpellBox;
import eg.edu.guc.yugioh.gui.board.buttons.CardB;
import eg.edu.guc.yugioh.gui.board.buttons.MonsterCardB;
import eg.edu.guc.yugioh.gui.board.buttons.SpellCardB;

public class ActionMenuSpell extends MouseAdapter{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		SpellCardB spellButton= (SpellCardB) e.getComponent();
//		SetnActivateMenu m=new SetnActivateMenu(a,e.getX(),e.getY());
		JPopupMenu menu=new JPopupMenu();
		 JMenuItem set = new JMenuItem("Set");
		 JMenuItem activate=new JMenuItem("Activate");
			menu.add(set);
			menu.add(activate);
			menu.show(spellButton, e.getX(), e.getY());
			
			set.setVisible(!spellButton.getOnField());
			menu.setVisible((Main.getController().getBoard().getActivePlayer().getField().getSpellArea().contains(spellButton.getSpellCard())||Main.getController().getBoard().getActivePlayer().getField().getHand().contains(spellButton.getSpellCard())));
			
			activate.addActionListener(new ActionListener(){
//NoSpellSpaceException,WrongPhaseException
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					ArrayList <MonsterCard> monstersOnField=Main.getController().getBoard().getActivePlayer().getField().getMonstersArea();
					SpellCard spellCard = spellButton.getSpellCard();
					
					if(Main.getController().getBoard().getActivePlayer().getField().getHand().contains(spellCard)){
						//put on field and make it appear for 3 seconds sleep(3000)
						if(spellCard instanceof MagePower || spellCard instanceof ChangeOfHeart){
							if(spellCard instanceof MagePower){
								SpellBox a = new SpellBox(monstersOnField);
								if(!a.getIsCanceled()){
									MonsterCard monsterChosen=a.getmonstersChosen();
									try{
										
										Main.getController().getBoard().getActivePlayer().activateSpell(spellCard, monsterChosen);
										Main.getGameBoard().updateAll();
									
								}catch(NoSpellSpaceException e1){
									JOptionPane.showMessageDialog(Main.getGameBoard(),"No space in spell Area");
								}
							catch(WrongPhaseException e2){
								JOptionPane.showMessageDialog(Main.getGameBoard(),"In wrong phase!");
								}
						}
							}else {SpellBox a = new SpellBox(Main.getController().getBoard().getOpponentPlayer().getField().getMonstersArea());
						if(!a.getIsCanceled()){
							MonsterCard monsterChosen=a.getmonstersChosen();
							try{
								Main.getController().getBoard().getActivePlayer().activateSpell(spellCard, monsterChosen);
								Main.getGameBoard().updateAll();
							
						}catch(NoSpellSpaceException e1){
							JOptionPane.showMessageDialog(Main.getGameBoard(),"No space in spell Area");
						}
					catch(WrongPhaseException e2){
						JOptionPane.showMessageDialog(Main.getGameBoard(),"In wrong phase!");
						}
						}
							
						}
					}
						else{
							try{
								Main.getController().getBoard().getActivePlayer().activateSpell(spellCard,null);
								Main.getGameBoard().updateAll();
							}catch(NoSpellSpaceException e1){
								JOptionPane.showMessageDialog(Main.getGameBoard(),"No space in spell Area");
							}
						catch(WrongPhaseException e2){
							JOptionPane.showMessageDialog(Main.getGameBoard(),"In wrong phase!");
							}
						}
					}else if(Main.getController().getBoard().getActivePlayer().getField().getSpellArea().contains(spellCard)){
						if(spellCard instanceof MagePower || spellCard instanceof ChangeOfHeart){
							if(spellCard instanceof MagePower){
								SpellBox a = new SpellBox(monstersOnField);
								if(!a.getIsCanceled()){
									MonsterCard monsterChosen=a.getmonstersChosen();
									try{
										//System.out.println("hfghdfghdf");
										if(Main.getController().getBoard().getActivePlayer().activateSpell(spellCard, monsterChosen))
											System.out.println("hfghdfghdf");
										Main.getGameBoard().updateAll();
									}catch(NoSpellSpaceException e1){
										JOptionPane.showMessageDialog(Main.getGameBoard(),"No space in spell Area");
									}
								catch(WrongPhaseException e2){
									JOptionPane.showMessageDialog(Main.getGameBoard(),"In wrong phase!");
									}
								}
						}else {SpellBox a = new SpellBox(Main.getController().getBoard().getOpponentPlayer().getField().getMonstersArea());
						if(!a.getIsCanceled()){
							MonsterCard monsterChosen=a.getmonstersChosen();
							try{
								//System.out.println("hfghdfghdf");
								if(Main.getController().getBoard().getActivePlayer().activateSpell(spellCard, monsterChosen))
									System.out.println("hfghdfghdf");
								Main.getGameBoard().updateAll();
							}catch(NoSpellSpaceException e1){
								JOptionPane.showMessageDialog(Main.getGameBoard(),"No space in spell Area");
							}
						catch(WrongPhaseException e2){
							JOptionPane.showMessageDialog(Main.getGameBoard(),"In wrong phase!");
							}
						}
							
						}
					}
						else{
							try{
								//System.out.println("hfghdfghdf");
								if(Main.getController().getBoard().getActivePlayer().activateSpell(spellCard,null))
									System.out.println("hfghdfghdf");
								Main.getGameBoard().updateAll();
							}catch(NoSpellSpaceException e1){
								JOptionPane.showMessageDialog(Main.getGameBoard(),"No space in spell Area");
							}
						catch(WrongPhaseException e2){
							JOptionPane.showMessageDialog(Main.getGameBoard(),"In wrong phase!");
							}
						}
						
					}
					
					
					}
				
			});
			
			set.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try{
					Main.getController().getBoard().getActivePlayer().setSpell(spellButton.getSpellCard());
					spellButton.makeHidden();
					Main.getGameBoard().updateAll();
					}catch(NoSpellSpaceException e1){
						JOptionPane.showMessageDialog(Main.getGameBoard(),"No space in spell Area");
					}
				catch(WrongPhaseException e2){
					JOptionPane.showMessageDialog(Main.getGameBoard(),"In wrong phase!");
					}
					
					
				}
				
			});
		
		
				
			}	

}
