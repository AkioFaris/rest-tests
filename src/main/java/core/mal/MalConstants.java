package core.mal;

public enum MalConstants {
    // useful text constants for API under test

    USER_LOGIN("epamUser"),
    USER_PASSWORD("nana7nana7nana"),
    VERIFY_CREDENTIALS_API_URI("https://myanimelist.net/api/account/verify_credentials.xml");

    public String text;

    MalConstants(String text) {
        this.text = text;
    }
}
