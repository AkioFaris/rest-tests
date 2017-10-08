package core.mal;

public enum AnimeShows {
    // int constants for API under test
    ANIME_MAJOR(558, "Major");

    public int id;
    public String label;

    AnimeShows(int id, String label) {
        this.id = id;
        this.label = label;
    }
}
