package core.vk;

public enum VkTextConstants {
    // useful text constants for API under test
    VK_GET_UNI_API_URI("http://api.vk.com/method/database.getUniversities"),
    PARAM_SEARCH_QUERY("q"),
    PARAM_COUNTRY_ID("country_id"),
    PARAM_CITY_ID("city_id"),
    PARAM_OFFSET("offset"),
    PARAM_COUNT("count"),
    SEARCH_QUERY("СПб");

    public String text;

    VkTextConstants(String text) {
        this.text = text;
    }
}
