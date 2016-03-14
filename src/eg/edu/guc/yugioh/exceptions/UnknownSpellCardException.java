package eg.edu.guc.yugioh.exceptions;

@SuppressWarnings("serial")
public class UnknownSpellCardException extends UnexpectedFormatException{
	private String unknownSpell = "";
	public UnknownSpellCardException(String sourceFile, int sourceLine,
			String unknownSpell ) {
		super(sourceFile, sourceLine);
		this.setUnknownSpell(unknownSpell);
		// TODO Auto-generated constructor stub
	}
	public UnknownSpellCardException(){
		super();
	}
	public UnknownSpellCardException(String sourceFile, int sourceLine,
			String unknownSpell,String message ) {
		super(sourceFile, sourceLine, message);
		this.setUnknownSpell(unknownSpell);
		// TODO Auto-generated constructor stub
	}
	 public String getUnknownSpell() {
		return unknownSpell;
	}
	public void setUnknownSpell(String unknownSpell) {
		this.unknownSpell = unknownSpell;
	}

}
