package eg.edu.guc.yugioh.exceptions;

@SuppressWarnings("serial")
public class UnknownCardTypeException extends UnexpectedFormatException {
private String unknownType = "";
	public UnknownCardTypeException(String sourceFile, int sourceLine,String unknownType) {
		super(sourceFile, sourceLine);
		this.unknownType=unknownType;
		// TODO Auto-generated constructor stub
	}
	public UnknownCardTypeException(){
		super();
	}
	public UnknownCardTypeException(String sourceFile, int sourceLine,String unknownType,String message) {
		super(sourceFile,sourceLine,message);
		this.unknownType=unknownType;
		// TODO Auto-generated constructor stub
	}
	public String getUnknownType() {
		return unknownType;
	}
	public void setUnknownType(String unknownType) {
		this.unknownType = unknownType;
	}

}
