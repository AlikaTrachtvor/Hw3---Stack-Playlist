import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

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
    public int getSize(){
        return this.playList.size();
    }
    public void addSong(Song song) throws SongAlreadyExistsException{
        int i;
        for (i = 0; i < this.playList.size(); i++) {
            if (this.playList.get(i).equals(song))
                throw new SongAlreadyExistsException();
        }
        playList.add(song);
    }
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
                for (j = 0; j < this.playList.size(); j++) {
                    if (this.playList.get(i).equals(otherList.playList.get(j))) {
                        counter++;
                        break;
                    }
                }
            }
            return counter == this.playList.size();
        }
    }

    //needs to be replaced with super
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

                if(temp != null && order.equals(ScanningOrder.NAME))
                    temp.sort(Comparator.comparing((Song s) -> s.getArtistName()));
                else if (order.equals(ScanningOrder.DURATION))
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
