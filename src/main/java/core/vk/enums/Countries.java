package core.vk.enums;

public enum Countries {
    RUS("Russia", 1),
    UKR("Ukraine", 2),
    BEL("Belarus", 3),
    KAZ("Kazakhstan", 4),
    AZE("Azerbaijan", 6);

    public String title;
    public int id;

    Countries(String title, int id) {
        this.title = title;
        this.id = id;
    }
}
