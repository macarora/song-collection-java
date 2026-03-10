import java.util.Scanner;

public class Song {
    private String name;     // name of the song
    private String artist;   // name of the artist
    private int duration;    // duration of the song in seconds
    private String genre;    // genre of the song

    private static final String[] fixedGenre = {"rock", "pop", "hip-hop", "bossa nova"}; // fixed genre list; it can be any of these four only

    public Song() {
        name = "";
        artist = "";
        duration = 0;
        genre = "";
    }

    private boolean checkGenre(String genre) {
        for (String validGenre : fixedGenre) {
            if (genre.equalsIgnoreCase(validGenre)) {
                return true;
            }
        }
        System.out.println("Enter a valid genre (rock, pop, hip-hop, bossa nova)");
        return false;
    }

    public void userInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of the song");
        name = scanner.nextLine();

        System.out.println("Enter the name of the artist");
        artist = scanner.nextLine();

        System.out.println("Enter the duration of the song in seconds");
        duration = scanner.nextInt();

        // Consume the newline character
        scanner.nextLine();

        do {
            System.out.println("Enter the genre of the song (rock, pop, hip-hop, bossa nova)");
            genre = scanner.nextLine();
        } while (!checkGenre(genre));
    }

    public Song(String name, String artist, int duration, String genre) {
        this.name = name;
        this.artist = artist;
        this.duration = duration;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public int getDuration() {
        return duration;
    }

    public String getGenre() {
        return genre;
    }
}
