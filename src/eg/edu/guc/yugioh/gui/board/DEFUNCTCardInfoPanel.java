package eg.edu.guc.yugioh.gui.board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.gui.board.buttons.MonsterCardB;
import eg.edu.guc.yugioh.gui.board.buttons.SpellCardB;

@SuppressWarnings("serial")
public class DEFUNCTCardInfoPanel extends JPanel{

	private JTextArea textArea=new JTextArea("Card description");
	private JLabel title=new JLabel();
	private JLabel picture=new JLabel();
	private JLabel attack=new JLabel();
	private JLabel defense=new JLabel();
	private ImageIcon image=new ImageIcon("backButton9570.jpg");
	//160 -270 y-axis for picture, 270-490 y-axis for text 
	//attack and def : y-axis:450-490 , x-axis 860 & 960
	//900-imgsize     , 860-1015 x-axis for txtArea
	public DEFUNCTCardInfoPanel(){//TODO: how to make null layout
		super();
		this.setLayout(null);
		//this.setLayout(new GridLayout(5,1,1,1));
		//this.setLayout(new FlowLayout());
		this.setBounds(813, 70, 207, 450);
		image=new ImageIcon("cards\\backButton9570.jpg");
		picture.setIcon(image);
		//picture.setPreferredSize(new Dimension(70, 95));
		picture.setBounds(844, 94, 155, 190);//room to increase
		//picture.setLocation(900, 160);
		title=new JLabel("Card Title ");
		title.setFont(new Font("street soul", Font.BOLD, 30));
//		title.setBounds(895, 260, 120, 30);
		//title.setLocation(895, 260);
		textArea=new JTextArea("Card description");
		textArea.setEditable(false);
//		textArea.setPreferredSize(new Dimension(155, 158));
		textArea.setFont(new Font("street soul", Font.PLAIN, 22));
		textArea.setOpaque(false);
		textArea.setBounds(830, 295, 180, 134);
		//textArea.setLocation(860, 291);
		attack=new JLabel("Attack: ");
		attack.setFont(new Font("street soul", Font.PLAIN, 24));
		attack.setForeground(Color.WHITE);
		attack.setBounds(825, 441, 120, 23);
		//attack.setLocation(860, 450);
		defense=new JLabel("Defense: ");
		defense.setFont(new Font("street soul", Font.PLAIN, 24));
		defense.setBounds(825, 475, 120, 23);
		defense.setForeground(Color.YELLOW);
		//defense.setLocation(960, 450);
		this.add(picture);
		this.add(title);
		this.add(textArea);
		this.add(attack);
		this.add(defense);
		/*this.repaint();
		this.setVisible(true);
		picture.setVisible(true);
		title.setVisible(true);
		//pack();
		*/
		this.setOpaque(false);
	}
	public void changeToCard(MonsterCardB monsterB){
		MonsterCard monster=monsterB.getMonster();
		title.setText(monster.getName());
		textArea.setText(monster.getDescription());
		image=monsterB.getPicture();
		picture.setIcon(image);
		attack.setText(""+monster.getAttackPoints());
		defense.setText(""+monster.getDefensePoints());
	}
	public void changeToCard(SpellCardB spellB){
		SpellCard spell=spellB.getSpellCard();
		title.setText(spell.getName());
		textArea.setText(spell.getDescription());
		image=spellB.getPicture();
		picture.setIcon(image);
		attack.setText("");
		defense.setText("");
	}
	
	public JLabel getPicture() {
		return picture;
	}
	public void setPicture(JLabel picture) {
		this.picture = picture;
	}
	public JTextArea getTextArea() {
		return textArea;
	}
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
}
