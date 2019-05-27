package reading.speed.improver.exercises.emerging.text.params;

public class EmergingTextExerciseParamsBuilder {

    private String name;
    private Float defaultTextSizeCoeff;

    public EmergingTextExerciseParamsBuilder() {
        name = "Появляющиеся строчки";
        defaultTextSizeCoeff = 1f;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getDefaultTextSizeCoeff() {
        return defaultTextSizeCoeff;
    }

    public void setDefaultTextSizeCoeff(Float defaultTextSizeCoeff) {
        this.defaultTextSizeCoeff = defaultTextSizeCoeff;
    }

}
