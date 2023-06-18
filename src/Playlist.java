import java.util.ArrayList;
import java.util.Iterator;
public class Playlist implements Cloneable {

    private ArrayList<Song> playList;

    private abstract class PlaylistIterator implements Iterable<Song> {
        private Playlist playList;
        private  int currentIndex;

        public PlaylistIterator(Playlist playlist,int currentIndex) {
           this.playList = playlist;
            this.currentIndex = currentIndex;
        }

        public boolean hasNext() {
            return this.currentIndex < this.playList.getSize();
        }

        public Song next() throws Exception {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Song nextSong = this.playList.playList.get(currentIndex+1);
            return nextSong;
        }

    }
    public Playlist(){
        playList = new ArrayList<>();
    }
    public int getSize(){
        return this.playList.size();
    }

    public void addSong(Song song) throws SongAlreadyExistsException{
        int i;
        for (i = 0; i < this.playList.size(); i++) {
            if (this.playList.get(i) == song)
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
        if (!(object instanceof Playlist otherPlayList))
            return false;
        else {
            int i, j, counter = 0;
            for (i = 0; i < this.playList.size(); i++) {
                for (j = 0; j < this.playList.size(); j++) {
                    if (this.playList.get(i).equals(otherPlayList.playList.get(j))) {
                        counter++;
                        break;
                    }
                }
            }
            return counter == this.playList.size();
        }
    }

    @Override
    public Playlist clone () {
        Playlist copyList = new Playlist();
            for (Song song : this.playList)
                copyList.playList.add(song.clone());
        return copyList;
    }

   @Override
    public String toString(){
        int i;
        String strList="";
        for(i=0;i<this.playList.size();i++)
            strList += this.playList.get(i).toString();
       return strList;
   }

}
