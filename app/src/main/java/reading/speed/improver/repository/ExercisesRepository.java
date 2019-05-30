package reading.speed.improver.repository;

import reading.speed.improver.db.AppDatabase;
import reading.speed.improver.exercises.text.appearence.emerging.EmergingTextExercise;
import reading.speed.improver.exercises.text.appearence.fading.FadingTextExercise;
import reading.speed.improver.exercises.text.appearence.fading.ui.FadingTextExerciseActivity;
import reading.speed.improver.exercises.text.appearence.params.EmergingTextExerciseParams;
import reading.speed.improver.exercises.text.appearence.params.EmergingTextExerciseParamsBuilder;
import reading.speed.improver.exercises.text.appearence.emerging.ui.EmergingTextExerciseActivity;
import reading.speed.improver.exercises.schulte.table.SchulteExercise;
import reading.speed.improver.exercises.schulte.table.params.SchulteTableExerciseParams;
import reading.speed.improver.exercises.schulte.table.params.SchulteTableExerciseParamsBuilder;
import reading.speed.improver.exercises.schulte.table.ui.SchulteExerciseActivity;

import java.util.HashMap;
import java.util.Map;

public class ExercisesRepository {
    private AppDatabase mAppDataBase;
    private Map<String, Exercises> exercises;
    private SchulteExercise schulteExercise;
    private EmergingTextExercise emergingTextExercise;
    private FadingTextExercise fadingTextExercise;

    public ExercisesRepository(final AppDatabase mAppDataBase) {
        this.mAppDataBase = mAppDataBase;
        exercises = new HashMap<>();
        exercises.put(Exercises.SCHULTE_TABLE_SMALL.getTitle(), Exercises.SCHULTE_TABLE_SMALL);
        exercises.put(Exercises.SCHULTE_TABLE_MEDIUM.getTitle(), Exercises.SCHULTE_TABLE_MEDIUM);
        exercises.put(Exercises.SCHULTE_TABLE_LARGE.getTitle(), Exercises.SCHULTE_TABLE_LARGE);
        exercises.put(Exercises.SCHULTE_TABLE_EXTRA_LARGE.getTitle(), Exercises.SCHULTE_TABLE_EXTRA_LARGE);
        exercises.put(Exercises.EMERGING_TEXT.getTitle(), Exercises.EMERGING_TEXT);
        exercises.put(Exercises.FADING_TEXT.getTitle(), Exercises.FADING_TEXT);
    }

    public Map<String, Exercises> getExercises() {
        return exercises;
    }

    public Class getExerciseActivity(Exercises exercise) {
        switch (exercise) {
            case SCHULTE_TABLE_SMALL:
                return SchulteExerciseActivity.class;
            case SCHULTE_TABLE_MEDIUM:
                return SchulteExerciseActivity.class;
            case SCHULTE_TABLE_LARGE:
                return SchulteExerciseActivity.class;
            case SCHULTE_TABLE_EXTRA_LARGE:
                return SchulteExerciseActivity.class;
            case EMERGING_TEXT:
                return EmergingTextExerciseActivity.class;
            case FADING_TEXT:
                return FadingTextExerciseActivity.class;
            default:
                return SchulteExerciseActivity.class;
        }
    }

    public void createExerciseModel(Exercises exercise) {
        switch (exercise) {
            case SCHULTE_TABLE_SMALL: {
                setCurrentExerciseModel(createSchulteExerciseModel(exercise.getTitle(), 3));
                break;
            }
            case SCHULTE_TABLE_MEDIUM: {
                setCurrentExerciseModel(createSchulteExerciseModel(exercise.getTitle(), 4));
                break;
            }
            case SCHULTE_TABLE_LARGE: {
                setCurrentExerciseModel(createSchulteExerciseModel(exercise.getTitle(), 5));
                break;
            }
            case SCHULTE_TABLE_EXTRA_LARGE: {
                setCurrentExerciseModel(createSchulteExerciseModel(exercise.getTitle(), 6));
                break;
            }
            case EMERGING_TEXT: {
                setCurrentExerciseModel(createEmergingTextExerciseModel(exercise.getTitle()));
                break;
            }

            case FADING_TEXT: {
                setCurrentExerciseModel(createFadingTextExerciseModel(exercise.getTitle()));
                break;
            }
            default:
                createSchulteExerciseModel(exercise.getTitle(), 3);
        }
    }

    private void setCurrentExerciseModel(SchulteExercise schulteExercise) {
        this.schulteExercise = schulteExercise;
    }

    private void setCurrentExerciseModel(EmergingTextExercise emergingTextExercise) {
        this.emergingTextExercise = emergingTextExercise;
    }
    private void setCurrentExerciseModel(FadingTextExercise fadingTextExercise) {
        this.fadingTextExercise = fadingTextExercise;
    }

    private SchulteExercise createSchulteExerciseModel(String title, int size) {
        SchulteTableExerciseParamsBuilder paramsBuilder = new SchulteTableExerciseParamsBuilder();
        paramsBuilder.setName(title);
        paramsBuilder.setTableSize(size);
        SchulteTableExerciseParams exerciseParams = new SchulteTableExerciseParams(paramsBuilder);

        return new SchulteExercise(exerciseParams);
    }

    private EmergingTextExercise createEmergingTextExerciseModel(String title) {
        EmergingTextExerciseParamsBuilder paramsBuilder = new EmergingTextExerciseParamsBuilder();
        paramsBuilder.setName(title);
        EmergingTextExerciseParams exerciseParams = new EmergingTextExerciseParams(paramsBuilder);

        return new EmergingTextExercise(exerciseParams);
    }

    private FadingTextExercise createFadingTextExerciseModel(String title) {
        EmergingTextExerciseParamsBuilder paramsBuilder = new EmergingTextExerciseParamsBuilder();
        paramsBuilder.setName(title);
        EmergingTextExerciseParams exerciseParams = new EmergingTextExerciseParams(paramsBuilder);

        return new FadingTextExercise(exerciseParams);
    }

    public void invalidateCurrentExerciseModel() {
        schulteExercise = null;
        emergingTextExercise = null;
        fadingTextExercise = null;
    }

    public SchulteExercise getSchulteExercise() {
        return schulteExercise;
    }

    public EmergingTextExercise getEmergingTextExercise() {
        return emergingTextExercise;
    }

    public FadingTextExercise getFadingTextExercise() {
        return fadingTextExercise;
    }
}
