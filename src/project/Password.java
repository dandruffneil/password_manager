package project;

public class Password {
    private String password;
    private String username;
    private String website;

    public Password(String password, String username, String website) {
        this.password = password;
        this.username = username;
        this.website = website;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWebsite() {
        return this.website;
    }

    public boolean isEqual(String website, String username) {
        if (website.equals(this.website) && username.equals(this.username)) {
            return true;
        } else {
            return false;
        }
    }

    public void clear() {
        password = "";
        username = "";
        website = "";
    }

    public String toString() {
        return "Website: \t" + this.website + "\nUsername: \t" + this.username + "\nPassword: \t" + this.password + "\n";
    }

}
