package reading.speed.improver.service;

import reading.speed.improver.repository.ChitaloidRepository;
import reading.speed.improver.user.pupil.Pupil;

import java.util.List;
import java.util.UUID;
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
        return new Pupil(UUID.randomUUID().toString(), name);
    }

    public void addPupil(Pupil pupil) {
        chitaloidRepository.addPupil(pupil);
    }

    public List<Pupil> getPupils() {
        try {
            return chitaloidRepository.getPupils();
        } catch (ExecutionException |  InterruptedException e){
            System.out.println(e.getStackTrace());
            return null;

        }
    }

    public void deletePupil(String name) {
        // todo: implement it
    }
}
