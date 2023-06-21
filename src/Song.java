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


    public void setDuration(int duration){
        this.duration = duration;
    }


    @Override
    public boolean equals(Object object){
        if(!(object instanceof Song))
            return false;
        else{
            Song otherSong = (Song)object;
            if(this.artistName != otherSong.artistName || this.songName != otherSong.songName)
                return false;
        }
        return true;
    }

    @Override
    public int hashCode(){
        int hash = this.songName.length() + this.artistName.length();
        return hash;
    }

    @Override
    public Song clone() {
        try {
            return (Song) super.clone();
        }
        catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public String toString(){
        String str = this.songName +", " +this.artistName +", "+this.genre+", "
                +this.duration/60 +":"+this.duration%60;
        return str;
    }

}

