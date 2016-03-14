package eg.edu.guc.yugioh.exceptions;

@SuppressWarnings("serial")
public class WrongPhaseException extends RuntimeException{
	public WrongPhaseException(){
		super();
	}
	public WrongPhaseException(String message){
		super(message);
	}
}
