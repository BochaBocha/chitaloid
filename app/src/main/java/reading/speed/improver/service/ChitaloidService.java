package reading.speed.improver.service;

import reading.speed.improver.exercises.materials.sentence.Sentence;
import reading.speed.improver.exercises.materials.Text;
import reading.speed.improver.exercises.materials.Word;
import reading.speed.improver.exercises.materials.sentence.SentencesClassification;
import reading.speed.improver.exercises.schulte.table.SchulteExercise;
import reading.speed.improver.exercises.text.appearance.emerging.EmergingTextExercise;
import reading.speed.improver.exercises.text.appearance.fading.FadingTextExercise;
import reading.speed.improver.exercises.text.misplaced.spaces.MisplacedSpacesExercise;
import reading.speed.improver.exercises.text.spaceless.SpacelessTextExercise;
import reading.speed.improver.exercises.word.appearance.EmergingWordsExercise;
import reading.speed.improver.repository.ChitaloidRepository;
import reading.speed.improver.repository.exercises.Exercises;
import reading.speed.improver.statistic.Statistic;
import reading.speed.improver.user.pupil.Pupil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class ChitaloidService {

    private ChitaloidRepository chitaloidRepository;

    public ChitaloidService() {
        chitaloidRepository = ChitaloidRepository.getInstance();
    }

    public Pupil getCurrentPupil() {
        return chitaloidRepository.getCurrentPupil();
    }

    public void setCurrentPupil(Pupil pupil) {
        chitaloidRepository.setCurrentPupil(pupil);
    }

    public Pupil createPupil(String name) {
        return new Pupil(null, name);
    }

    public Pupil addPupil(Pupil pupil) {
        try {
            return chitaloidRepository.addPupil(pupil);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Pupil> getPupils() {
        try {
            return chitaloidRepository.getPupils();
        } catch (ExecutionException | InterruptedException e) {
            System.out.println((e.getStackTrace()));
            return null;
        }
    }

    public Pupil getPupilByName(String name) {
        try {
            return chitaloidRepository.getPupilByName(name);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deletePupil(String name) {
        // todo: implement it
    }

    public Text getRandomText() {
        try {
            int count = chitaloidRepository.getTextCount();
            Random random = new Random();
            int id = random.nextInt(count) + 1;
            return chitaloidRepository.getText(id);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Word getRandomWord(final int amountOfLetters) {
        try {
            return chitaloidRepository.getRandomWord(amountOfLetters);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Class getExerciseActivity() {
        return chitaloidRepository.getExerciseActivity();
    }

    public void setCurrentExercise(Exercises exercise) {
        chitaloidRepository.setCurrentExercise(exercise);
    }

    public void invalidateCurrentExercise() {
        chitaloidRepository.invalidateCurrentExercise();
    }

    public SchulteExercise createSchulteTableExercise() {
        return chitaloidRepository.createSchulteTableExercise();
    }

    public EmergingTextExercise createEmergingTextExercise() {
        return chitaloidRepository.createEmergingTextExercise();
    }

    public FadingTextExercise createFadingTextExerciseModel() {
        return chitaloidRepository.createFadingTextExercise();
    }

    public SpacelessTextExercise createSpacelessTextExercise() {
        return chitaloidRepository.createSpacelessTextExercise();
    }

    public MisplacedSpacesExercise createMisplacedSpacesExercise() {
        return chitaloidRepository.createMisplacedSpacesExercise();
    }

    public EmergingWordsExercise createEmergingWordsExercise() {
        return chitaloidRepository.createEmergingWordsExercise();
    }

    public Sentence getRandomSentence() {
        return chitaloidRepository.getRandomSentence();
    }

    public Sentence getRandomSentence(final SentencesClassification sentencesClassification) {
        return chitaloidRepository.getRandomSentence(
                sentencesClassification.getMinAmountOfLetters(),
                sentencesClassification.getMaxAmountOfLetters()
        );
    }

    public Statistic createStatistic(String exerciseName, Integer lead_time_sec) {
        return new Statistic(null, chitaloidRepository.getCurrentPupil()._id,
                chitaloidRepository.getIdByName(exerciseName)
                , new Date(), lead_time_sec, null);
    }

    public void insertStatistic(final Statistic statistic) {
        chitaloidRepository.insertStatistic(statistic);
    }

    public void deleteStatistics(Pupil pupil) {
        chitaloidRepository.deleteStatistic(pupil._id);
    }

    public String getPupilStatistic(int pupilId) {
        ArrayList<Statistic> statistics = (ArrayList<Statistic>) chitaloidRepository.getStatisticByPupilId(pupilId);
        if (statistics.size() == 0) {
            return "Статистика не найдена";
        }
        return constructStatisticsString(statistics);
    }

    private String constructStatisticsString(ArrayList<Statistic> statistics) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Statistic statistic : statistics) {
            stringBuilder.append(chitaloidRepository.getNameById(statistic.exercise_id)).append(" ");

            stringBuilder.append(new SimpleDateFormat("MM-dd-yyyy").format(statistic.date)).append(" ");
            stringBuilder.append(statistic.lead_time_sec).append(" сек").append("\n");
        }
        return stringBuilder.toString();
    }

}
