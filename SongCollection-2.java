import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SongCollection {
    private Album[] albums;

    public SongCollection() {
        albums = new Album[4];
        albums[0] = new Album();
        albums[1] = new Album();
        albums[2] = new Album();
        albums[3] = new Album();
    }

    public void run() {
        int menuOption = 0;
        Scanner input = new Scanner(System.in);
        do {
            displayMenuOptions();
            System.out.print("\nEnter your choice: ");
            menuOption = input.nextInt();

            switch (menuOption) {
                case 1:
                    createNewAlbum();
                    break;
                case 2:
                    addSong();
                    break;
                case 3:
                    listAllSongs();
                    break;
                case 4:
                    listAllAlbums();
                    break;
                case 5:
                    listSongsUnderMaxDuration();
                    break;
                case 6:
                    listAllSongsByGenre();
                    break;
                case 7:
                    deleteAlbum();
                    break;
                case 8:
                    deleteSong();
                    break;
                case 9:
                    readFile();
                    break;
                case 10:
                    System.out.println("Thank you for using the Song Collection. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        } while (menuOption != 10);
    }

    private void displayMenuOptions() {
        System.out.println("Welcome to the Song Collection. Take your pick from the Menu options\n");
        System.out.println("1. Create a new Album");
        System.out.println("2. Add a new Song to the Album");
        System.out.println("3. List all Songs with details in an Album");
        System.out.println("4. List all Albums with Songs");
        System.out.println("5. Search songs under a particular Duration");
        System.out.println("6. Search song from a particular Genre");
        System.out.println("7. Delete Album");
        System.out.println("8. Delete Song from an Album");
        System.out.println("9. Read from file");
        System.out.println("10. Exit");
    }

    private void createNewAlbum() {
        System.out.println("Enter the name of the album: ");
        Scanner input = new Scanner(System.in);
        String albumName = input.nextLine();
        boolean albumExists = false;

        for (int i = 0; i < albums.length; i++) {
            if (albums[i].getName().equalsIgnoreCase(albumName)) {
                albumExists = true;
                break;
            }
        }

        if (albumExists) {
            System.out.println("Album already exists");
        } else {
            for (int i = 0; i < albums.length; i++) {
                if (albums[i].getName().equalsIgnoreCase("")) {
                    albums[i].setName(albumName);
                    System.out.println("Album created successfully");
                    break;
                } else if (i == albums.length - 1) {
                    System.out.println("Album collection is full");
                }
            }
        }
    }

    private void addSong() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the album: ");
        String albumName = input.nextLine();
        boolean albumExists = false;
        Album selectedAlbum = null;

        for (int i = 0; i < albums.length; i++) {
            if (albums[i].getName().equalsIgnoreCase(albumName)) {
                albumExists = true;
                selectedAlbum = albums[i];
                break;
            }
        }

        if (albumExists) {
            if (selectedAlbum.isFull()) {
                System.out.println("Album is full");
            } else {
                System.out.println("Enter the name of the song: ");
                String songName = input.nextLine();
                System.out.println("Enter the name of the artist: ");
                String artist = input.nextLine();
                System.out.println("Enter the duration of the song in seconds: ");
                int duration = input.nextInt();
                input.nextLine(); // Consume the newline character
                System.out.println("Enter the genre of the song: ");
                String genre = input.nextLine();

                Song song = new Song(songName, artist, duration, genre);
                selectedAlbum.addSong(song);
                System.out.println("Song added successfully");
            }
        } else {
            System.out.println("Album does not exist");
        }
    }

    private void listAllSongs() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the album: ");
        String albumName = input.nextLine();
        boolean albumExists = false;
        Album selectedAlbum = null;

        for (int i = 0; i < albums.length; i++) {
            if (albums[i].getName().equalsIgnoreCase(albumName)) {
                albumExists = true;
                selectedAlbum = albums[i];
                break;
            }
        }

        if (albumExists) {
            selectedAlbum.listAllSongs();
        } else {
            System.out.println("Album does not exist");
        }
    }

    private void listAllAlbums() {
        for (int i = 0; i < albums.length; i++) {
            if (!albums[i].getName().equalsIgnoreCase("")) {
                albums[i].listAllAlbumDetails();
            }
        }
    }

    private void listSongsUnderMaxDuration() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the Duration: ");
        int maxDurationInMinutes = input.nextInt();
        int maxDurationInSeconds = maxDurationInMinutes * 60;
        boolean songExists = false;

        System.out.println("Songs under max duration:");
        for (Album album : albums) {
            album.listAllSongsUnderDuration(maxDurationInSeconds);
            songExists = true;
        }

        if (!songExists) {
            System.out.println("No songs under max duration");
        }
    }

    private void listAllSongsByGenre() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the Genre: ");
        String genre = input.nextLine();
        boolean songExists = false;

        System.out.println("Songs under Genre " + genre + ":");
        for (Album album : albums) {
            songExists = album.listAllSongsByGenre(genre);
        }

        if (!songExists) {
            System.out.println("No songs under Genre " + genre);
        }
    }

    private void deleteAlbum() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the album: ");
        String albumName = input.nextLine();
        boolean albumExists = false;
        int index = -1;

        for (int i = 0; i < albums.length; i++) {
            if (albums[i].getName().equalsIgnoreCase(albumName)) {
                albumExists = true;
                index = i;
                break;
            }
        }

        if (albumExists) {
            albums[index] = new Album();
            System.out.println("Album deleted successfully");
        } else {
            System.out.println("Album does not exist");
        }
    }

    private void deleteSong() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the Song: ");
        String songName = input.nextLine();
        boolean songDeleted = false;

        for (Album album : albums) {
            if (album.deleteSong(songName)) {
                songDeleted = true;
                break;
            }
        }

        if (songDeleted) {
            System.out.println("Song deleted successfully");
        } else {
            System.out.println("Song does not exist");
        }
    }

    private void readFile() {
        String fileName = "ReginaCollection.txt";
        System.out.println("Reading file: " + fileName);
    
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
    
            String albumName = null;
            boolean albumExists = false;
            Album selectedAlbum = null;
    
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
    
                if (!line.isEmpty()) {
                    if (line.startsWith("Album")) {
                        albumName = line.substring(line.indexOf(" ") + 1);
                        albumExists = false;
    
                        for (Album album : albums) {
                            if (album.getName().equalsIgnoreCase(albumName)) {
                                albumExists = true;
                                selectedAlbum = album;
                                break;
                            }
                        }
    
                        if (!albumExists) {
                            System.out.println("Creating new album: " + albumName);
                            selectedAlbum = createNewAlbum(albumName);
                            if (selectedAlbum != null) {
                                albumExists = true;
                            }
                        }
                    } else if (albumExists && selectedAlbum != null) {
                        if (line.startsWith("Name")) {
                            String songName = line.substring(line.indexOf(" ") + 1);
    
                            if (scanner.hasNextLine()) {
                                String artistLine = scanner.nextLine().trim();
                                String artist = artistLine.substring(artistLine.indexOf(" ") + 1);
    
                                if (scanner.hasNextLine()) {
                                    String durationLine = scanner.nextLine().trim();
                                    int duration = Integer.parseInt(durationLine.substring(durationLine.indexOf(" ") + 1));
    
                                    if (scanner.hasNextLine()) {
                                        String genreLine = scanner.nextLine().trim();
                                        String genre = genreLine.substring(genreLine.indexOf(" ") + 1);
    
                                        Song song = new Song(songName, artist, duration, genre);
                                        selectedAlbum.addSong(song);
                                        System.out.println("Song added successfully to album: " + albumName);
                                    }
                                }
                            }
                        }
                    }
                }
            }
    
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    
    private Album createNewAlbum(String albumName) {
        boolean albumExists = false;
    
        for (int i = 0; i < albums.length; i++) {
            if (albums[i].getName().equalsIgnoreCase(albumName)) {
                albumExists = true;
                break;
            }
        }
    
        if (albumExists) {
            System.out.println("Album already exists");
        } else {
            for (int i = 0; i < albums.length; i++) {
                if (albums[i].getName().equalsIgnoreCase("")) {
                    albums[i].setName(albumName);
                    System.out.println("Album created successfully");
                    return albums[i];
                } else if (i == albums.length - 1) {
                    System.out.println("Album collection is full");
                }
            }
        }
    
        return null;
    }
    
    
    




    // main method
    public static void main(String[] args) {
        SongCollection sg = new SongCollection();
        sg.run();
    }
}
