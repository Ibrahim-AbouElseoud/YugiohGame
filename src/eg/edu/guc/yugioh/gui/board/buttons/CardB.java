package eg.edu.guc.yugioh.gui.board.buttons;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.gui.board.InvisibleButton;
import eg.edu.guc.yugioh.listeners.board.ActionMenuMonster;
import eg.edu.guc.yugioh.listeners.board.ActionMenuSpell;
import eg.edu.guc.yugioh.listeners.board.MouseHoverToCardInfo;

public class CardB extends JButton{//could make it back to InvisibleButton
private String name;
private String description;
private Card card;
private Boolean onField=false;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public ImageIcon getPicture() {
	return picture;
}
public void setPicture(ImageIcon picture) {
	this.picture = picture;
}
private ImageIcon picture;
public CardB(Card card){
	super();
	this.card=card;
	this.name=card.getName();
	this.description=card.getDescription();
	picture =new ImageIcon("cards\\"+card.getName()+".jpg");
	this.setIcon(picture);
	this.setPreferredSize(new Dimension(75,95));
	this.addMouseListener(new MouseHoverToCardInfo());
	//this.addMouseListener(new ActionMenuMonster());
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public void updateIconSet(){
	this.setIcon(new ImageIcon("cards\\Card Back.jpg"));
}
public void updateIconActivate(){
	this.setIcon(new ImageIcon("cards\\"+this.getName()+".jpg"));
}
public Card getCard() {
	return card;
}
public Boolean getOnField() {
	return onField;
}
public void setOnField(Boolean onField) {
	this.onField = onField;
}
public void makeHidden(){
	//this.name="";
	//this.description="";
	picture =new ImageIcon("cards\\Card Back.jpg");
	this.setIcon(picture);
}
public void makeVisible(){
	this.name=card.getName();
	this.description=card.getDescription();
	picture =new ImageIcon("cards\\"+card.getName()+".jpg");
	this.setIcon(picture);
}
}
