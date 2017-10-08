package core.vk.enums;

public enum Universities {
    SPBU("СПбГУ", 1),
    VAS("ВАШ при Администрации СПб", 13),
    NIU_HSE("НИУ ВШЭ (СПб)", 17),
    SPBUTUiE("СПбУТУиЭ (бывш. САУ, СПбУУЭ, СПбАУЭ)", 23),
    SPBGMAM("СПбГАВМ (бывш. ЛВИ)", 36),
    RGISI("РГИСИ (бывш. СПбГАТИ)", 37),
    LESGAFT_NSU("НГУ им. Лесгафта (бывш. СПбГУФК)", 38),
    RIMSKY_KORSAKOV_SPBGK("СПбГК им. Римского-Корсакова", 39),
    KIROV_SPBSTU("СПбГЛТУ им. Кирова", 40),
    MECHNIKOV_SZGMU("СЗГМУ им. Мечникова (бывш. СПбГМА, СПбМАПО, ЛСГМИ) ", 41);

    public String title;
    public int id;

    Universities(String title, int id) {
        this.title = title;
        this.id = id;
    }
}
