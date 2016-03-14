package eg.edu.guc.yugioh.exceptions;

@SuppressWarnings("serial")
public class MissingFieldException extends UnexpectedFormatException {

	public MissingFieldException(){
		super();
	}
	public MissingFieldException(String sourceFile, int sourceLine) {
		super(sourceFile, sourceLine);
		// TODO Auto-generated constructor stub
	}
	public MissingFieldException(String sourceFile, int sourceLine,String message) {
		super(sourceFile, sourceLine,message);
		// TODO Auto-generated constructor stub
	}
	

}
