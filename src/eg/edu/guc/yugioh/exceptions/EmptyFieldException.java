package eg.edu.guc.yugioh.exceptions;

@SuppressWarnings("serial")
public class EmptyFieldException extends UnexpectedFormatException{
	private int sourceField = 0;
	public EmptyFieldException(String sourceFile, int sourceLine ,int sourceField) {
		super(sourceFile, sourceLine);
		this.setSourceField(sourceField);
		// TODO Auto-generated constructor stub
	}
	public EmptyFieldException(){
		super();
	}
	public EmptyFieldException(String sourceFile, int sourceLine ,int sourceField,String message) {
		super(sourceFile, sourceLine,message);
		this.setSourceField(sourceField);
		// TODO Auto-generated constructor stub
	}
	public int getSourceField() {
		return sourceField;
	}
	public void setSourceField(int sourceField) {
		this.sourceField = sourceField;
	}

}
