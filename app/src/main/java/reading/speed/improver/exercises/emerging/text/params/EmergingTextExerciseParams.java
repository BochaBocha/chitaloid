package reading.speed.improver.exercises.emerging.text.params;

public class EmergingTextExerciseParams {
    private String name;
    private Float defaultTextSizeCoeff;

    public EmergingTextExerciseParams(EmergingTextExerciseParamsBuilder schulteTableParamsBuilder) {
        name = schulteTableParamsBuilder.getName();
        defaultTextSizeCoeff = schulteTableParamsBuilder.getDefaultTextSizeCoeff();
    }

    public String getName() {
        return name;
    }

    public Float getDefaultTextSizeCoeff() {
        return defaultTextSizeCoeff;
    }
}
