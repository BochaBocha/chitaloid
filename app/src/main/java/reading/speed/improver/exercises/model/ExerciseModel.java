package reading.speed.improver.exercises.model;

import java.util.UUID;

public class ExerciseModel {
    private UUID id;
    private String name;
    private double speed;
    private double textSize;

    public ExerciseModel(){
        id = UUID.randomUUID();
        name= "Найди слово";
        speed = 1;
        textSize = 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
