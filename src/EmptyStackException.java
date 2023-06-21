public class EmptyStackException extends StackException{

    /***
     * This class describes an exception when the user tries to reach Objects in the Stack, but it's empty
     */
    public EmptyStackException(){ }
    public EmptyStackException(String message){super(message);}
    public EmptyStackException(String message, Throwable cause){super(message, cause);}
}
