package reading.speed.improver.repository;

public enum Exercises {
    SCHULTE_TABLE_SMALL ("Таблица Шульте 3х3"),
    SCHULTE_TABLE_MEDIUM ("Таблица Шульте 4х4"),
    SCHULTE_TABLE_LARGE ("Таблица Шульте 5х5"),
    SCHULTE_TABLE_EXTRA_LARGE("Таблица Шульте 6х6"),
    EMERGING_TEXT("Появляющиеся строчки");

    private String title;

    Exercises(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}

