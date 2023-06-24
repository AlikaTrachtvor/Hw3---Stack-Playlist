import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

/***
 *This class represents an object playlist
 * This object is a list of Songs and contains each song's information
 */

public class Playlist implements Cloneable, FilteredSongIterable, OrderedSongIterable {
    private ArrayList<Song> playList;
    private String artistFilter;
    private Song.Genre genreFilter;
    private Integer durationFilter;
    private ScanningOrder so;
    public Playlist(){
        this.playList = new ArrayList<>();
        this.artistFilter = null;
        this.genreFilter = null;
        this.durationFilter = null;
        this.so = null;
    }

    /***
     * adding a song to the playlist, if the song already exists then throws exception
     * @param song song that the user wants to add to the playlist
     * @throws SongAlreadyExistsException an exception for if the song is already exists in the playlist
     */
    public void addSong(Song song) throws SongAlreadyExistsException{
        int i;
        for (i = 0; i < this.playList.size(); i++) {
            if (this.playList.get(i).equals(song))
                throw new SongAlreadyExistsException();
        }
        playList.add(song);
    }

    /***
     * removes a song from the playlist
     * @param song song that the user wants to remove from the playlist
     * @return true if the remove process succeeded otherwise false
     */
    public boolean removeSong(Song song){
        int i;
        for(i=0;i<this.playList.size();i++){
            if(this.playList.get(i) == song) {
                this.playList.remove(song);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Playlist ))
            return false;
        else {
            Playlist otherList = (Playlist) object;
            int i, j, counter = 0;
            for (i = 0; i < this.playList.size(); i++) {
                for (j = 0; j < otherList.playList.size(); j++) {
                    if (this.playList.get(i).equals(otherList.playList.get(j))) {
                        counter++;
                        break;
                    }
                }
            }
            return counter == this.playList.size();
        }
    }

    @Override
    public int hashCode(){
        int hash =0;
        for(Song song: this.playList){
            hash += song.hashCode();
        }
        return hash;
    }


    @Override
    public Playlist clone () {
        try {
            Playlist copyList = (Playlist) super.clone();
            copyList.playList = new ArrayList<>();
            for (Song song : this.playList){
                copyList.playList.add(song.clone());
            }
            return copyList;
        }
        catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public String toString(){
        int i;
        String strList="[";
        for(i=0;i<this.playList.size();i++) {
            strList += "(" + this.playList.get(i).toString();
            if(i != this.playList.size() -1)
                strList += "), ";
            else strList += ")";
        }
        strList += "]";
        return strList;
    }

    @Override
    public void filterArtist(String name){this.artistFilter = name;}
    @Override
    public void filterGenre(Song.Genre genre){this.genreFilter = genre;}
    @Override
    public void filterDuration(int duration){this.durationFilter = duration;}
    @Override
    public void setScanningOrder(ScanningOrder so){this.so = so;}
    @Override
    public PlaylistIterator iterator(){return new PlaylistIterator();}
    private class PlaylistIterator implements Iterator<Song>{
        private ArrayList<Song> temp = (ArrayList<Song>) playList.clone();
        private String artist = artistFilter;
        private Song.Genre genre = genreFilter;
        private Integer duration = durationFilter;
        private ScanningOrder order = so;
        private int size = 0;
        private int currIndex = 0;
        public PlaylistIterator(){
            if(!temp.isEmpty()){
                if(this.artist != null)
                    temp.removeIf(song -> !(song.getArtistName().equals(this.artist)));
                if(this.genre != null)
                    temp.removeIf(song -> !(song.getGenre().equals(this.genre)));
                if(this.duration != null)
                    temp.removeIf(song -> song.getDuration() > this.duration);

                if(!(temp.isEmpty()) && order != null && order.equals(ScanningOrder.NAME))
                    temp.sort(Comparator.comparing((Song s) -> s.getSongName()));
                else if (!(temp.isEmpty()) && order!=null && order.equals(ScanningOrder.DURATION))
                    temp.sort(Comparator.comparing((Song s) -> s.getDuration()));
                this.size = temp.size();
            }
        }

        @Override
        public boolean hasNext(){
            if(this.currIndex < size)
                return true;
            return false;
        }

        @Override
        public Song next(){
            if(hasNext())
                return temp.get(currIndex++);
            return null;
        }
    }

}
