package reading.speed.improver.exercises.emerging.text.params;

public class EmergingTextExerciseParams {
    private String name;
    private Float defaultTextSize;

    public EmergingTextExerciseParams(EmergingTextExerciseParamsBuilder schulteTableParamsBuilder) {
        name = schulteTableParamsBuilder.getName();
        defaultTextSize = schulteTableParamsBuilder.getDefaultTextSize();
    }

    public String getName() {
        return name;
    }

    public Float getDefaultTextSize() {
        return defaultTextSize;
    }
}
