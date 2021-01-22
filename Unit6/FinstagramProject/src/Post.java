public class Post {
    private final String imageUrl;
    private final String caption;
    private final FinstaUser user;

    public Post(final String imageUrl, final String caption, final FinstaUser user) {
        this.imageUrl = imageUrl;
        this.caption = caption;
        this.user = user;
        System.out.println("New post from " + user.getHandle() + ": " + caption + " Image: " + imageUrl);
    }
}
