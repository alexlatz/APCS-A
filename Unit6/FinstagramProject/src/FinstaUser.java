// Alex Latz
public class FinstaUser {
    private final String firstName;
    private final String lastName;
    private final String handle;
    private final ResizingArray following;
    private final ResizingArray followers;

    public FinstaUser(final String firstName, final String lastName, final String handle) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.handle = '@' + handle;
        this.following = new ResizingArray();
        this.followers = new ResizingArray();
    }

    public void follow(final FinstaUser user) {
        if (this == user) {
            System.out.println(this.handle + " can't follow themself!");
        } else if (this.following.contains(user)) {
            System.out.println(this.handle + " already follows " + user.getHandle() + "!");
        } else {
            this.following.add(user);
            user.followers.add(this);
            System.out.println(this.handle + " followed " + user.getHandle() + "!");
        }
    }

    public void unfollow(final FinstaUser user) {
        if (this == user) {
            System.out.println(this.handle + " can't follow or unfollow themself!");
        } else if (!this.following.contains(user)) {
            System.out.println(this.handle + " can't unfollow " + user.getHandle() + " without following them first!");
        } else {
            this.following.remove(user);
            user.followers.remove(this);
            System.out.println(this.handle + " unfollowed " + user.getHandle() + "!");
        }
    }

    public Post post(final String imageUrl, final String caption) {
        return new Post(imageUrl, caption, this);
    }

    public String getHandle() {
        return this.handle;
    }

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName + "\n"
                + "Handle: " + handle + "\n"
                + "Follows: " + following.toString() + "\n"
                + "Followers: " + followers.toString();
    }
}