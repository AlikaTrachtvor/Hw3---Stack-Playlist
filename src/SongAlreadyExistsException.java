
public class SongAlreadyExistsException  extends Exception{
    private String exception;
    public SongAlreadyExistsException(){
        this.exception = "The song is already exists";
    }

    public String getException() {
        return this.exception;
    }
}
