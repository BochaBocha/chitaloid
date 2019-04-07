package reading.speed.improver.repository;

public class ChitaloidRepository {
    private static final ChitaloidRepository ourInstance = new ChitaloidRepository();

    public static ChitaloidRepository getInstance() {
        return ourInstance;
    }

    private Pupil pupil;

    private ChitaloidRepository() {
    }
}
