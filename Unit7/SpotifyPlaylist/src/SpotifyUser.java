import java.util.ArrayList;

public class SpotifyUser {
    private String username;
    private ArrayList<Playlist> ownedPlaylists;
    private ArrayList<Playlist> followedPlaylists;

    public SpotifyUser(String username) {
        this.username = username;
        ownedPlaylists = new ArrayList<>();
        followedPlaylists = new ArrayList<>();
    }

    public Playlist createPlaylist(String title, Song firstSong) {
        Playlist playlist = new Playlist(title, firstSong, this);
        ownedPlaylists.add(playlist);
        System.out.println("Created playlist " + title + " with the song " + firstSong.toString());
        return playlist;
    }

    public Playlist getOwnedPlaylist(String playlistTitle) {
        for (Playlist playlist : ownedPlaylists) {
            if (playlist.getTitle().equals(playlistTitle)) {
                System.out.println("Owned playlist " + playlistTitle + " found!");
                return playlist;
            }
        }
        System.out.println("The owned playlist " + playlistTitle + " was not found!");
        return null;
    }

    public void deletePlaylist(Playlist playlist) {
        if (ownedPlaylists.contains(playlist)) {
            System.out.println("Deleted " + playlist.getTitle());
            ownedPlaylists.remove(playlist);
        } else System.out.println("The owned playlist " + playlist.getTitle() + " was not found!");
    }

    public String getUsername() {
        return username;
    }

}
