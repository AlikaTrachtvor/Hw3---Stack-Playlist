
public class SongAlreadyExistsException  extends Exception{
    /***
     * This class describes an exception when a user tries to add a song that is already exist in the playlist
     */
    public SongAlreadyExistsException(){ }
    public SongAlreadyExistsException(String message){super(message);}
    public SongAlreadyExistsException(String message, Throwable cause){
        super(message, cause);}


}
