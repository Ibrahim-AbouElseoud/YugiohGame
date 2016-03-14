package eg.edu.guc.yugioh.exceptions;

@SuppressWarnings("serial")
public class UnexpectedFormatException extends Exception{
	private String sourceFile = "";
	private int sourceLine = 1;
	String message = "";
	public String getSourceFile() {
		return sourceFile;
	}
	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
		
	}
	public int getSourceLine() {
		return sourceLine;
	}
	public void setSourceLine(int sourceLine) {
		this.sourceLine = sourceLine;
		}
	public UnexpectedFormatException(){
		super();
	}
	public UnexpectedFormatException(String sourceFile,int sourceLine){
	super();
	this.sourceFile=sourceFile;
	this.sourceLine=sourceLine;
	}
	public UnexpectedFormatException(String sourceFile,int sourceLine,String message){
	super(message);
	this.sourceFile=sourceFile;
	this.sourceLine=sourceLine;
	}


	
}