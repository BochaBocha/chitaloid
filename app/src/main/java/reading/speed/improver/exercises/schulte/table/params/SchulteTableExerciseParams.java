package reading.speed.improver.exercises.schulte.table.params;

public class SchulteTableExerciseParams {
    private String name;
    private  Float defaultTextSize;
    private  int tableSize;

    public SchulteTableExerciseParams( SchulteTableExerciseParamsBuilder schulteTableParamsBuilder){
        name = schulteTableParamsBuilder.getName();
        defaultTextSize = schulteTableParamsBuilder.getDefaultTextSize();
        tableSize = schulteTableParamsBuilder.getTableSize();
    }

    public String getName() {
        return name;
    }

    public Float getDefaultTextSize() {
        return defaultTextSize;
    }

    public int getTableSize() {
        return tableSize;
    }
}
