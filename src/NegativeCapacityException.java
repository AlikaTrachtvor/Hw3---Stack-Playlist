public class NegativeCapacityException extends StackException{
    /***
     * This class describes an exception when a user tries to build a Stack with a negative max capacity
     */
    public NegativeCapacityException(){ }
    public NegativeCapacityException(String message){super(message);}
    public NegativeCapacityException(String message, Throwable cause){super(message, cause);}
}
