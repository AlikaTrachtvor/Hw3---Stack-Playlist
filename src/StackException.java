public abstract class StackException extends RuntimeException{
    /***
     * This class is meant for grouping Stack related runtime exceptions
     */

    public StackException(){ };
    public StackException(String message){super(message);}
    public StackException(String message, Throwable cause){super(message, cause);}
}
