package reading.speed.improver.repository.exercises;

public enum Exercises {
    SCHULTE_TABLE_SMALL ("Таблица Шульте 3х3"),
    SCHULTE_TABLE_MEDIUM ("Таблица Шульте 4х4"),
    SCHULTE_TABLE_LARGE ("Таблица Шульте 5х5"),
    SCHULTE_TABLE_EXTRA_LARGE("Таблица Шульте 6х6"),
    EMERGING_TEXT("Появляющиеся строчки"),
    FADING_TEXT("Исчезающие строчки"),
    SPACELESS_TEXT("Сплошной текст"),
    MISPLACED_SPACES_TEXT("Неправильные пробелы"),
    EMERGING_WORDS("Слова");

    private String title;

    Exercises(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}

