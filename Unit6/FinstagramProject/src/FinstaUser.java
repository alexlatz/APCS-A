public class FinstaUser {
    private String firstName;
    private String lastName;
    private String handle;
    public FinstaUser(String firstName, String lastName, String handle) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.handle = '@' + handle;
    }

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName + "\n"
                + "Handle: " + handle + "\n";
    }
}