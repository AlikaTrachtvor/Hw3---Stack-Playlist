public class StackOverflowException extends StackException{

    /***
     * This class describes an exception when the Stack is full and the user tries to push more Objects into it
     */
    public StackOverflowException(){ }
    public StackOverflowException(String message){super(message);}
    public StackOverflowException(String message, Throwable cause){super(message, cause);}
}
