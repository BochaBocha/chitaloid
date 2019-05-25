package reading.speed.improver.exercises.emerging.text.params;

public class EmergingTextExerciseParamsBuilder {

    private String name;
    private Float defaultTextSize;

    public EmergingTextExerciseParamsBuilder() {
        name = "Появляющиеся строчки";
        defaultTextSize = 100f;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getDefaultTextSize() {
        return defaultTextSize;
    }

    public void setDefaultTextSize(Float defaultTextSize) {
        this.defaultTextSize = defaultTextSize;
    }

}
