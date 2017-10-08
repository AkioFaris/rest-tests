package core.vk.enums;

public enum QueryParamLabels {
    PARAM_SEARCH_QUERY("q", "Search query. String"),
    PARAM_COUNTRY_ID("country_id", "Country ID. Positive number"),
    PARAM_CITY_ID("city_id", "City ID. Positive number"),
    PARAM_OFFSET("offset", "Offset needed to return a specific subset of universities. Positive number"),
    PARAM_COUNT("count", "Number of universities to return. Positive number, default 100, maximum value 10000");

    public String label;
    public String meaning;

    QueryParamLabels(String label, String meaning) {
        this.label = label;
        this.meaning = meaning;
    }

}
