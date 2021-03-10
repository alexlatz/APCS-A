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
            if (playlist.getTitle().equals(playlistTitle)) return playlist;
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

    public Song playRandomSongFrom(Playlist playlist) {
        return playlist.getRandomSong();
    }

    public void addToPlaylist(Playlist playlist, Song song) {
        if (ownedPlaylists.contains(playlist)) playlist.addSong(song);
        else System.out.println("You are not the owner of the playlist " + playlist.getTitle() + "!");
    }

    public void removeFromPlaylist(Playlist playlist, Song song) {
        if (ownedPlaylists.contains(playlist)) playlist.removeSong(song);
        else System.out.println("You are not the owner of the playlist " + playlist.getTitle() + "!");
    }

    public void addToLibrary(Playlist playlist) {
        if (!ownedPlaylists.contains(playlist)) {
            if (!followedPlaylists.contains(playlist)) {
                System.out.println("Followed playlist " + playlist.toString());
                followedPlaylists.add(playlist);
            } else System.out.println("You have already followed the playlist " + playlist.toString());
        } else System.out.println("You own the playlist " + playlist.getTitle() + " and cannot follow it!");
    }

    public void removeFromLibrary(Playlist playlist) {
        if (followedPlaylists.contains(playlist)) {
            System.out.println("Unfollowed playlist " + playlist.toString());
            followedPlaylists.remove(playlist);
        } else if (ownedPlaylists.contains(playlist)) System.out.println("You can't follow or unfollow your own playlist!");
        else System.out.println("You can't unfollow the playlist " + playlist.toString() + " without following it first!");
    }

    public String getUsername() {
        return username;
    }

}
