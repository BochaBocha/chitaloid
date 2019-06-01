package reading.speed.improver.exercises.params;

public class DefaultExerciseParams {
    private String name;
    private Float defaultTextSizeCoeff;

    public DefaultExerciseParams(DefaultExerciseParamsBuilder defaultExerciseParamsBuilder) {
        name = defaultExerciseParamsBuilder.getName();
        defaultTextSizeCoeff = defaultExerciseParamsBuilder.getDefaultTextSizeCoeff();
    }

    public String getName() {
        return name;
    }

    public Float getDefaultTextSizeCoeff() {
        return defaultTextSizeCoeff;
    }
}
