public class Song {
    private String artist;
    private String title;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return this.title + " by " + this.artist;
    }
}
