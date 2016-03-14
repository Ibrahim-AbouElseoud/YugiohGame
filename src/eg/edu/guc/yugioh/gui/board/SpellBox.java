package eg.edu.guc.yugioh.gui.board;

import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.gui.Main;

public class SpellBox  extends JDialog {
		JPanel checkboxPanel=new JPanel();
		//REMEMBER to make a boolean for this and monster that is called isCanceled
		Boolean isCanceled=false;
		static int selectedCard=0;
		private MonsterCard monsterChosen; //this returns this card
		static ArrayList <String> monsterNames= new ArrayList<String>();
		ArrayList <MonsterCard>monstersOnField;
		JLabel comment= new JLabel("Please choose the monster you want to activate this spell card on");
		
		public SpellBox(ArrayList<MonsterCard>monstersOnField ){ //self true if pick monster from own field , false if pick monster from opponent field
			super(Main.getGameBoard(),"Choose The Monster",true); //which ever the effect pass the correct monstersOnField list whether active player or opponent
			checkboxPanel.add(comment);
			JButton activate=new JButton("Activate");
			JButton cancel=new JButton("Cancel");
			this.monstersOnField=monstersOnField;
			this.populate(monstersOnField);
			this.add(checkboxPanel);
			
			checkboxPanel.add(activate);
		    checkboxPanel.add(cancel);
			this.setLocationRelativeTo(null);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			activate.addActionListener(new ActionListener(){
				@Override//remember to clear
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(selectedCard==1){
						monsterChosen=transform(monsterNames,monstersOnField);
					}
					else JOptionPane.showMessageDialog(Main.getGameBoard(),"Can only selectone monster to activate spell!");
					monsterNames.clear();
					selectedCard=0;
					dispose();
				}
			});

			cancel.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					monsterChosen=null;
					selectedCard=0;
					isCanceled=true;
					dispose();

				}

			});
			pack(); 
			this.setVisible(true);

		}
		public void populate(ArrayList<MonsterCard>monstersOnField){
			for(int i=0;i<monstersOnField.size();i++){
				Checkbox checkbox=new Checkbox(monstersOnField.get(i).getName());
				checkboxPanel.add(checkbox);
				checkbox.addItemListener(new ItemListener(){

					@Override
					public void itemStateChanged(ItemEvent e) {
						Checkbox checkbox=(Checkbox) e.getSource();
						// TODO Auto-generated method stub
						if(e.getStateChange()==ItemEvent.SELECTED){
							selectedCard++;
							monsterNames.add(checkbox.getLabel());
						}
						if(e.getStateChange()==ItemEvent.DESELECTED){
							selectedCard--;
							monsterNames.remove(checkbox.getLabel());
						}
					}

				}	);
			}
		}
		public MonsterCard transform(ArrayList<String> monsterNames,ArrayList<MonsterCard>monstersOnField){
			MonsterCard result = null;
			for(int i=0;i<monstersOnField.size();i++){
				for(int j=0;j<monsterNames.size();j++){
					if(monsterNames.get(j).equals(monstersOnField.get(i).getName())){
						result=monstersOnField.get(i);
					}
				}
			}
			return result;

		}
		public MonsterCard getmonstersChosen() {
			return monsterChosen;
		}
		public void setSacrificesChosen(MonsterCard monstersChosen) {
			this.monsterChosen = monstersChosen;
		}
		public Boolean getIsCanceled() {
			return isCanceled;
		}
}
