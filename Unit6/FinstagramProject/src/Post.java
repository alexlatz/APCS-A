public class Post {
    private String imageUrl;
    private String caption;
    private FinstaUser user;
    public Post(String imageUrl, String caption, FinstaUser user) {
        this.imageUrl = imageUrl;
        this.caption = caption;
        this.user = user;
    }
}
