package eg.edu.guc.yugioh.exceptions;

@SuppressWarnings("serial")
public class MultipleMonsterAdditionException extends RuntimeException{
	public MultipleMonsterAdditionException(){
		super();
	}
	public MultipleMonsterAdditionException(String message){
		super(message);
	}
}
