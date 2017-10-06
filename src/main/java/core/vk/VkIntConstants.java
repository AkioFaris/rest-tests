package core.vk;

public enum VkIntConstants {
    // int constants for API under test
     COUNTRY_ID(1),
     CITY_ID(2),
     OFFSET(0),
     COUNT(10);

    public int number;

    VkIntConstants(int number) {
        this.number = number;
    }
}
