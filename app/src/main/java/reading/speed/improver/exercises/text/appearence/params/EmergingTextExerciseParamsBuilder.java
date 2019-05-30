package reading.speed.improver.exercises.text.appearence.params;

public class EmergingTextExerciseParamsBuilder {

    private String name;
    private Float defaultTextSizeCoeff;

    public EmergingTextExerciseParamsBuilder() {
        name = "Успей за строчкой";
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
