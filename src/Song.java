public class Song implements Cloneable{
    private String songName;
    private String artistName;
    private Genre genre;
    private int duration;
    public enum Genre {
        POP,
        ROCK,
        HIP_HOP,
        COUNTRY,
        JAZZ,
        DISCO
    }

    public Song(String songName,String artistName,Genre genre,int duration){
        this.songName = songName;
        this.artistName = artistName;
        this.genre = genre;
        this.duration = duration;
    }

    public Song(){
        this.songName = null;
        this.artistName = null;
        this.duration =0;
        this.genre =null;
    }



    public void setDuration(int duration){
        this.duration = duration;
    }


    @Override
    public boolean equals(Object object){
        if(!(object instanceof Song))
        return false;
        else{
            Song otherSong = (Song)object;
            if(this.hashCode() != otherSong.hashCode())
                return false;
            else
                return true;
        }
    }

    @Override
    public int hashCode(){
        int hash = 0,i;
        for(i=0;i<this.songName.length();i++)
            hash++;
        return hash + this.duration;
    }

    @Override
    public Song clone() {
        try {
            return (Song) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public String toString(){
        String str =this.songName +", " +this.artistName +", "+this.genre+", " +this.duration/100+":"+this.duration/10;
        return str;
    }

    }



