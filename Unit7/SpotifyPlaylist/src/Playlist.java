import java.util.ArrayList;

public class Playlist {
    private String title;
    private SpotifyUser owner;
    private ArrayList<Song> songs;

    public Playlist(String title, Song firstSong, SpotifyUser owner) {
        this.title = title;
        songs = new ArrayList<>();
        songs.add(firstSong);
        this.owner = owner;
        System.out.println("New playlist created by " + owner.getUsername() + " called " + title + " with the song " + firstSong.toString());
    }

    public void addSong(Song song) {
        System.out.println("Added " + song.toString() + " to the playlist " + title);
        songs.add(song);
    }

    public void removeSong(Song song) {
        if (songs.contains(song)) {
            System.out.println("Removed " + song.toString() + " from the playlist " + title);
            songs.remove(song);
        } else System.out.println("The song " + song.toString() + " is not in the playlist " + title + "!");
    }

    public Song getRandomSong() {
        return songs.get((int)Math.ceil(Math.random()*songs.size())-1);
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title + " by " + owner.getUsername();
    }

}
