public class Main {
    public static void main(String[] args) {
        SpotifyUser user = new SpotifyUser("user");
        SpotifyUser user2 = new SpotifyUser("user2");
        Song song1 = new Song("song1", "artist1");
        Song song2 = new Song("song2", "artist2");
        user.createPlaylist("User Playlist", song1);
        //getOwnedPlaylist
        Playlist userPlaylist = user.getOwnedPlaylist("User Playlist");
        user.getOwnedPlaylist("Not a Playlist");
        //adding another song for random
        user.addToPlaylist(userPlaylist, song2);
        //random songs
        System.out.println(user.playRandomSongFrom(userPlaylist));
        System.out.println(user.playRandomSongFrom(userPlaylist));
        //removal
        user.removeFromPlaylist(userPlaylist, song2);
        user.removeFromPlaylist(userPlaylist, song2);
        //attempt follow/unfollow
        user.addToLibrary(userPlaylist);
        user.removeFromLibrary(userPlaylist);
        //deletion
        user.deletePlaylist(userPlaylist);
        user.deletePlaylist(userPlaylist);
        //second playlist for follows
        Playlist user2Playlist = user2.createPlaylist("User 2 Playlist", song1);
        //attempt unfollow, then test follow/unfollow
        user.removeFromLibrary(user2Playlist);
        user.addToLibrary(user2Playlist);
        user.removeFromLibrary(user2Playlist);
    }
}
