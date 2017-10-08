package core.vk.enums;

public enum Cities {
    MSK("Москва", 1),
    SPB("Санкт-Петербург", 2);

    public String title;
    public int id;

    Cities(String title, int id) {
        this.title = title;
        this.id = id;
    }
}
