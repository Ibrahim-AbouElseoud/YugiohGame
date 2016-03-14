package eg.edu.guc.yugioh.listeners.board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.DefenseMonsterAttackException;
import eg.edu.guc.yugioh.exceptions.MonsterMultipleAttackException;
import eg.edu.guc.yugioh.exceptions.MultipleMonsterAdditionException;
import eg.edu.guc.yugioh.exceptions.NoMonsterSpaceException;
import eg.edu.guc.yugioh.exceptions.WrongPhaseException;
import eg.edu.guc.yugioh.gui.Main;
import eg.edu.guc.yugioh.gui.board.AttackBox;
import eg.edu.guc.yugioh.gui.board.SacrificeBox;
import eg.edu.guc.yugioh.gui.board.buttons.CardB;
import eg.edu.guc.yugioh.gui.board.buttons.MonsterCardB;
import eg.edu.guc.yugioh.gui.board.buttons.SpellCardB;

public class ActionMenuMonster extends MouseAdapter {
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		ArrayList <MonsterCard> monstersOnField=Main.getController().getBoard().getActivePlayer().getField().getMonstersArea();
		MonsterCardB monsterButton= (MonsterCardB) e.getComponent();
		//		SetnActivateMenu m=new SetnActivateMenu(a,e.getX(),e.getY());
		JPopupMenu menu=new JPopupMenu();
		JMenuItem set = new JMenuItem("Set");
		JMenuItem summon=new JMenuItem("Summon");
		JMenuItem attack = new JMenuItem("Attack");
		JMenuItem changeMode=new JMenuItem("Change mode");
		menu.add(set);
		menu.add(summon);
		menu.add(attack);
		menu.add(changeMode);
		menu.show(monsterButton, e.getX(), e.getY());
		
		if(Main.getController().getBoard().getActivePlayer().getField().getMonstersArea().contains(monsterButton.getMonster())||Main.getController().getBoard().getActivePlayer().getField().getHand().contains(monsterButton.getMonster())){
		set.setVisible(!monsterButton.getOnField());
		summon.setVisible(!monsterButton.getOnField());
		changeMode.setVisible(monsterButton.getOnField());
		attack.setVisible(monsterButton.getOnField());
		}
		else {
			menu.setVisible(false);
			set.setVisible(false);
			summon.setVisible(false);
			changeMode.setVisible(false);
			attack.setVisible(false);
		}




		summon.addActionListener(new ActionListener(){
//			NoMonsterSpaceException,WrongPhaseException,MultipleMonsterAdditionException
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MonsterCard monster = (MonsterCard) monsterButton.getMonster();
				if(monster.getLevel()<=4){
					try{
						Main.getController().getBoard().getActivePlayer().summonMonster(monster);
						//Main.getGameBoard().updateHandnMonsterBySummon(monster);
						Main.getGameBoard().updateAll();
					}catch(NoMonsterSpaceException e1){
						JOptionPane.showMessageDialog(Main.getGameBoard(),"No space in monster area!");
					}
				catch(WrongPhaseException e2){
					JOptionPane.showMessageDialog(Main.getGameBoard(),"In wrong phase!");
				}
			catch(MultipleMonsterAdditionException e3){
				JOptionPane.showMessageDialog(Main.getGameBoard(),"Can't add multiple monsters in same phase!");
			}
			
				}
				// send player the name of monsters on his field and based on that he chooses
				else if(monster.getLevel()<7){
					if(Main.getController().getBoard().getActivePlayer().getField().getMonstersArea().size()>=1){


						SacrificeBox a=new SacrificeBox(monstersOnField,1);
						ArrayList <MonsterCard>sacrifices= a.getSacrificesChosen();
						ArrayList <MonsterCard>sacrificesCopy=new ArrayList<MonsterCard>(sacrifices);
						System.out.println(sacrifices.size());
						if(!a.getIsCanceled())
						try{
							if(Main.getController().getBoard().getActivePlayer().summonMonster(monster, sacrifices))
								Main.getGameBoard().updateAll();
								//Main.getGameBoard().updateHandnMonsterBySummon(monster,sacrificesCopy);
							else System.out.println("ay 7agah!");
						}catch(NoMonsterSpaceException e1){
							JOptionPane.showMessageDialog(Main.getGameBoard(),"No space in monster area!");
						}
					catch(WrongPhaseException e2){
						JOptionPane.showMessageDialog(Main.getGameBoard(),"In wrong phase!");
					}
				catch(MultipleMonsterAdditionException e3){
					JOptionPane.showMessageDialog(Main.getGameBoard(),"Can't add multiple monsters in same phase!");
				}
					}else{

						JOptionPane.showMessageDialog(Main.getGameBoard(), "Monster needs sacrifices!");
					}

				}

				else{
					if(Main.getController().getBoard().getActivePlayer().getField().getMonstersArea().size()>=2){
						SacrificeBox a=new SacrificeBox(monstersOnField,2);
						ArrayList <MonsterCard>sacrifices= a.getSacrificesChosen();
						ArrayList <MonsterCard>sacrificesCopy=new ArrayList<MonsterCard>(sacrifices);
						if(!a.getIsCanceled())
						try{
							Main.getController().getBoard().getActivePlayer().summonMonster(monster, sacrifices);
							//Main.getGameBoard().updateHandnMonsterBySummon(monster,sacrificesCopy);
							Main.getGameBoard().updateAll();
							//							Main.getGameBoard().updateMonsterFieldnHandSummon(monster);//put update statement here
						}catch(NoMonsterSpaceException e1){
							JOptionPane.showMessageDialog(Main.getGameBoard(),"No space in monster area!");
						}
					catch(WrongPhaseException e2){
						JOptionPane.showMessageDialog(Main.getGameBoard(),"In wrong phase!");
					}
				catch(MultipleMonsterAdditionException e3){
					JOptionPane.showMessageDialog(Main.getGameBoard(),"Can't add multiple monsters in same phase!");
				}
					}else{

						JOptionPane.showMessageDialog(Main.getGameBoard(), "Monster needs sacrifices!");
					}
				}


			}



		});

		set.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MonsterCard monster = (MonsterCard) monsterButton.getMonster();
				if(monster.getLevel()<=4){
					try{
						Main.getController().getBoard().getActivePlayer().setMonster(monster);
						//Main.getGameBoard().updateHandnMonsterBySummon(monster);
						Main.getGameBoard().updateAll();
						monsterButton.makeHidden();
					}catch(NoMonsterSpaceException e1){
						JOptionPane.showMessageDialog(Main.getGameBoard(),"No space in monster area!");
					}
				catch(WrongPhaseException e2){
					JOptionPane.showMessageDialog(Main.getGameBoard(),"In wrong phase!");
				}
			catch(MultipleMonsterAdditionException e3){
				JOptionPane.showMessageDialog(Main.getGameBoard(),"Can't add multiple monsters in same phase!");
			}
				}
				// send player the name of monsters on his field and based on that he chooses
				else if(monster.getLevel()<7){
					if(Main.getController().getBoard().getActivePlayer().getField().getMonstersArea().size()>=1){


						SacrificeBox a=new SacrificeBox(monstersOnField,1);
						ArrayList <MonsterCard>sacrifices= a.getSacrificesChosen();
						ArrayList <MonsterCard>sacrificesCopy=new ArrayList<MonsterCard>(sacrifices);
						System.out.println(sacrifices.size());
						if(!a.getIsCanceled())
						try{
							if(Main.getController().getBoard().getActivePlayer().setMonster(monster, sacrifices))
								Main.getGameBoard().updateAll();
								//Main.getGameBoard().updateHandnMonsterBySummon(monster,sacrificesCopy);
							monsterButton.makeHidden();
							//							else System.out.println("ay 7agah!");
						}catch(NoMonsterSpaceException e1){
							JOptionPane.showMessageDialog(Main.getGameBoard(),"No space in monster area!");
						}
					catch(WrongPhaseException e2){
						JOptionPane.showMessageDialog(Main.getGameBoard(),"In wrong phase!");
					}
				catch(MultipleMonsterAdditionException e3){
					JOptionPane.showMessageDialog(Main.getGameBoard(),"Can't add multiple monsters in same phase!");
				}
					}else{

						JOptionPane.showMessageDialog(Main.getGameBoard(), "Monster needs sacrifices!");
					}

				}

				else{
					if(Main.getController().getBoard().getActivePlayer().getField().getMonstersArea().size()>=2){
						SacrificeBox a=new SacrificeBox(monstersOnField,2);
						ArrayList <MonsterCard>sacrifices= a.getSacrificesChosen();
						ArrayList <MonsterCard>sacrificesCopy=new ArrayList<MonsterCard>(sacrifices);
						if(!a.getIsCanceled())
						try{
							Main.getController().getBoard().getActivePlayer().setMonster(monster, sacrifices);
							Main.getGameBoard().updateAll();
							//Main.getGameBoard().updateHandnMonsterBySummon(monster,sacrificesCopy);
							monsterButton.makeHidden();
							//							Main.getGameBoard().updateMonsterFieldnHandSummon(monster);//put update statement here
						}catch(NoMonsterSpaceException e1){
							JOptionPane.showMessageDialog(Main.getGameBoard(),"No space in monster area!");
						}
					catch(WrongPhaseException e2){
						JOptionPane.showMessageDialog(Main.getGameBoard(),"In wrong phase!");
					}
				catch(MultipleMonsterAdditionException e3){
					JOptionPane.showMessageDialog(Main.getGameBoard(),"Can't add multiple monsters in same phase!");
				}
					}else{

						JOptionPane.showMessageDialog(Main.getGameBoard(), "Monster needs sacrifices!");
					}
				}
			}

		}

				);

		attack.addActionListener(new ActionListener(){
//DefenseMonsterAttackException,MonsterMultipleAttackException,WrongPhaseException
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Main.getController().getBoard().getOpponentPlayer().getField().getMonstersArea().size()>0){
					AttackBox a=new AttackBox(Main.getController().getBoard().getOpponentPlayer().getField().getMonstersArea());
					MonsterCard chosenMonster= a.getmonstersChosen();
					if(!a.getIsCanceled())
					try{
						Main.getController().getBoard().getActivePlayer().declareAttack(monsterButton.getMonster(), chosenMonster);
						Main.getGameBoard().updateAll();
				}catch(MonsterMultipleAttackException e1){
					JOptionPane.showMessageDialog(Main.getGameBoard(),"Not Allowed to multiple attack with same monster!");
				}
			catch(WrongPhaseException e2){
				JOptionPane.showMessageDialog(Main.getGameBoard(),"In wrong phase!");
			}
		catch(DefenseMonsterAttackException e3){
			JOptionPane.showMessageDialog(Main.getGameBoard(),"Can't attack with a defense monster!");
		}
				}
				else try{
					Main.getController().getBoard().getActivePlayer().declareAttack(monsterButton.getMonster());
					Main.getGameBoard().updateAll();
				}
			catch(MonsterMultipleAttackException e1){
				JOptionPane.showMessageDialog(Main.getGameBoard(),"Not Allowed to multiple attack with same monster!");
			}
		catch(WrongPhaseException e2){
			JOptionPane.showMessageDialog(Main.getGameBoard(),"In wrong phase!");
		}
	catch(DefenseMonsterAttackException e3){
		JOptionPane.showMessageDialog(Main.getGameBoard(),"Can't attack with a defense monster!");
	}
				
				Main.getGameBoard().updateLPLabels();

			}

		});

		changeMode.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{
					Main.getController().getBoard().getActivePlayer().switchMonsterMode(monsterButton.getMonster());
					if(monsterButton.getMonster().getMode()==Mode.ATTACK){
						monsterButton.setAttack();
					}
					else monsterButton.setDefense();
				}
				catch(WrongPhaseException e3){
					JOptionPane.showMessageDialog(Main.getGameBoard(), "In Wrong Phase!");
				}
			}

		});


	}

}