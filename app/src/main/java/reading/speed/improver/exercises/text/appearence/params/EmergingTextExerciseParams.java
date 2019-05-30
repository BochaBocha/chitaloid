package reading.speed.improver.exercises.text.appearence.params;

public class EmergingTextExerciseParams {
    private String name;
    private Float defaultTextSizeCoeff;

    public EmergingTextExerciseParams(EmergingTextExerciseParamsBuilder emergingTextParamsBuilder) {
        name = emergingTextParamsBuilder.getName();
        defaultTextSizeCoeff = emergingTextParamsBuilder.getDefaultTextSizeCoeff();
    }

    public String getName() {
        return name;
    }

    public Float getDefaultTextSizeCoeff() {
        return defaultTextSizeCoeff;
    }
}
