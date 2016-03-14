package eg.edu.guc.yugioh.gui.board;

import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.gui.Main;

public class SacrificeBox extends JDialog{ //remember to update GUI this is just the engine but we pass the value we didn't give the engine the values yet
	JPanel checkboxPanel=new JPanel();
	static int sacrificeNum=0;
	private ArrayList <MonsterCard> sacrificesChosen= new ArrayList<MonsterCard>();
	static ArrayList <String> sacrificeNames= new ArrayList<String>();
	ArrayList <MonsterCard>monstersOnField;
	Boolean isCanceled=false; //false not canceled
	

	public SacrificeBox(ArrayList<MonsterCard>monstersOnField,int sacrificeNeeded){
		super(Main.getGameBoard(),"Choose sacrifices",true);
		JButton sacrifice=new JButton("Sacrifice");
		JButton cancel=new JButton("Cancel");
		this.monstersOnField=monstersOnField;
		this.populate(monstersOnField);
		this.add(checkboxPanel);
		checkboxPanel.add(sacrifice);
	    checkboxPanel.add(cancel);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		sacrifice.addActionListener(new ActionListener(){
			@Override//remember to clear
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(sacrificeNum==sacrificeNeeded){

					sacrificesChosen=transform(sacrificeNames,monstersOnField);
				}
				sacrificeNames.clear();
				sacrificeNum=0;
				dispose();
			}
		});

		cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sacrificeNames.clear();
				sacrificeNum=0;
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
						sacrificeNum++;
						sacrificeNames.add(checkbox.getLabel());
					}
					if(e.getStateChange()==ItemEvent.DESELECTED){
						sacrificeNum--;
						sacrificeNames.remove(checkbox.getLabel());
					}
				}

			}	);
		}
	}
	public ArrayList<MonsterCard> transform(ArrayList<String> monsterNames,ArrayList<MonsterCard>monstersOnField){
		ArrayList<MonsterCard> result=new ArrayList<MonsterCard>();
		//monsterNames.con
		for(int i=0;i<monstersOnField.size();i++){
			for(int j=0;j<monsterNames.size();j++){
				if(monsterNames.get(j).equals(monstersOnField.get(i).getName())){
					result.add(monstersOnField.get(i));
				}
			}
		}
		return result;

	}
	public ArrayList <MonsterCard> getSacrificesChosen() {
		return sacrificesChosen;
	}
	public void setSacrificesChosen(ArrayList <MonsterCard> sacrificesChosen) {
		this.sacrificesChosen = sacrificesChosen;
	}
	public Boolean getIsCanceled() {
		return isCanceled;
	}
}
