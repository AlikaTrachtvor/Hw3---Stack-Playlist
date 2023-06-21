
public class SongAlreadyExistsException  extends Exception{
    private String exception;
    public SongAlreadyExistsException(){
        this.exception = "Cannot add the song!";
    }


}
