package reading.speed.improver.exercises.schulte.table.params;

public class SchulteTableExerciseParamsBuilder {

    private String name;
    private  Float defaultTextSizeCoeff;
    private  int tableSize;

    public SchulteTableExerciseParamsBuilder(){
        name = "Таблица Шульте 3х3";
        defaultTextSizeCoeff = 1f; //sp
        tableSize = 3;
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

    public int getTableSize() {
        return tableSize;
    }

    public void setTableSize(int tableSize) {
        this.tableSize = tableSize;
    }
}
