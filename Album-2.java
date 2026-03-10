public class Album {
    private String name;                    // name of the album
    private Song[] songs;                   // array of songs restricted to 5
    private int totalTime;                  // total time of the album in seconds
    private int numberOfSongs;              // number of songs in the album
    private int MAX_SONGS = 5;              // maximum number of songs in the album
    private static final int MAX_TIME = 1200;   // 20 minutes

    public Album() {
        name = "";
        totalTime = 0;
        numberOfSongs = 0;
        songs = new Song[MAX_SONGS];
    }

    public void setName(String albumName) {
        name = albumName;
    }

    public String getName() {
        return name;
    }

    public boolean isFull() {
        if (numberOfSongs == MAX_SONGS) {
            System.out.println("Album is full");
            System.out.println("You cannot add more songs. Delete songs in this Album to add more songs");
            return true;
        } else {
            return false;
        }
    }

    public void addSong(Song song) {
        if (!isFull()) {
            songs[numberOfSongs] = song;
            totalTime += song.getDuration();
            numberOfSongs++;
        }
    }

    public void listAllSongs() {
        if (numberOfSongs == 0) {
            System.out.println("No songs in the album");
        } else {
            System.out.println("Name\tArtist\tDuration\tGenre");
            for (int i = 0; i < numberOfSongs; i++) {
                System.out.println(songs[i].getName() + "\t" + songs[i].getArtist() + "\t" + songs[i].getDuration()
                        + "\t" + songs[i].getGenre());
            }
        }
    }

    public void listAllAlbumDetails() {
        System.out.println("Album Name: " + name);
        System.out.println("Total Time: " + totalTime);
        System.out.println("Number of Songs: " + numberOfSongs);
        listAllSongs();
    }

    public void listAllAlbums(Album[] albums) {
        System.out.println("All Albums");
        for (Album album : albums) {
            System.out.println("Album: " + album.getName());
            System.out.println("Total Time: " + album.getTotalTime() + " seconds");
            album.listAllSongs();
        }
    }

    public void listAllSongsUnderDuration(int duration) {
        if (duration > 0 && duration <= MAX_TIME) {
            System.out.println("Songs under " + duration + " seconds");
            System.out.println("Name\tArtist\tDuration\tGenre");
            for (int i = 0; i < numberOfSongs; i++) {
                if (songs[i].getDuration() <= duration) {
                    System.out.println(songs[i].getName() + "\t" + songs[i].getArtist() + "\t" + songs[i].getDuration()
                            + "\t" + songs[i].getGenre());
                }
            }
        } else {
            System.out.println("Invalid duration");
        }
    }

    public boolean listAllSongsByGenre(String genre) {
        System.out.println("Songs with Genre: " + genre);
        System.out.println("Name\tArtist\tDuration\tGenre");
        boolean found = false;
        for (int i = 0; i < numberOfSongs; i++) {
            if (songs[i].getGenre().equalsIgnoreCase(genre)) {
                System.out.println(songs[i].getName() + "\t" + songs[i].getArtist() + "\t" + songs[i].getDuration()
                        + "\t" + songs[i].getGenre());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No songs with genre " + genre);
        }
        return found;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public boolean deleteSong(String songName) {
        for (int i = 0; i < numberOfSongs; i++) {
            if (songs[i].getName().equalsIgnoreCase(songName)) {
                totalTime -= songs[i].getDuration();
                numberOfSongs--;
    
                // Move the last song in the array to the deleted song's position
                songs[i] = songs[numberOfSongs];
                songs[numberOfSongs] = null;
    
                System.out.println("Song deleted: " + songName);
                return true;
            }
        }
    
        System.out.println("Song not found: " + songName);
        return false;
    }
    

}
