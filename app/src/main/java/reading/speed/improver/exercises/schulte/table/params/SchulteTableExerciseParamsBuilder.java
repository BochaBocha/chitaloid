package reading.speed.improver.exercises.schulte.table.params;

public class SchulteTableExerciseParamsBuilder {

    private String name;
    private  Float defaultTextSize;
    private  int tableSize;

    public SchulteTableExerciseParamsBuilder(){
        name = "Таблица Шульте 3х3";
        defaultTextSize = 100f;
        tableSize = 3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getDefaultTextSize() {
        return defaultTextSize;
    }

    public void setDefaultTextSize(Float defaultTextSize) {
        this.defaultTextSize = defaultTextSize;
    }

    public int getTableSize() {
        return tableSize;
    }

    public void setTableSize(int tableSize) {
        this.tableSize = tableSize;
    }
}
