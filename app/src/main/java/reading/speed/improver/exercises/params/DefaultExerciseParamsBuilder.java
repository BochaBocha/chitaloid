package reading.speed.improver.exercises.params;

public class DefaultExerciseParamsBuilder {
    private String name;
    private Float defaultTextSizeCoeff;

    public DefaultExerciseParamsBuilder() {
        name = "Упражнение без названия";
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
