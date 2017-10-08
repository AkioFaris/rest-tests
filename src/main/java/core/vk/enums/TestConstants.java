package core.vk.enums;

public enum TestConstants {
    VK_GET_UNI_API_URI("http://api.vk.com/method/database.getUniversities"),
    UNI_SEARCH_QUERY("СПб"),
    SEARCH_RESULTS_OFFSET(0),
    SEARCH_RESULTS_COUNT(10);
    public String text;
    public int number;

    TestConstants(int number) {
        this.number = number;
    }

    TestConstants(String text) {
        this.text = text;
    }
}
