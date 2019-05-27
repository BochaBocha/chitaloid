package reading.speed.improver.exercises.schulte.table.params;

public class SchulteTableExerciseParams {
    private String name;
    private  Float defaultTextSizeCoeff;
    private  int tableSize;

    public SchulteTableExerciseParams( SchulteTableExerciseParamsBuilder schulteTableParamsBuilder){
        name = schulteTableParamsBuilder.getName();
        defaultTextSizeCoeff = schulteTableParamsBuilder.getDefaultTextSizeCoeff();
        tableSize = schulteTableParamsBuilder.getTableSize();
    }

    public String getName() {
        return name;
    }

    public Float getDefaultTextSizeCoeff() {
        return defaultTextSizeCoeff;
    }

    public int getTableSize() {
        return tableSize;
    }
}
