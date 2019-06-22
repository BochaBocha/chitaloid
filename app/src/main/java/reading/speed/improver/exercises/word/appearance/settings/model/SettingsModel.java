package reading.speed.improver.exercises.word.appearance.settings.model;

import java.io.Serializable;

public class SettingsModel implements Serializable {
    private float textSizeCoeff;
    private int speed;
    private int amountOfLetters;

    public SettingsModel(final float textSizeCoeff, final int speed, final int amountOfLetters) {
        this.textSizeCoeff = textSizeCoeff;
        this.speed = speed;
        this.amountOfLetters = amountOfLetters;
    }

    public float getTextSizeCoeff() {
        return textSizeCoeff;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAmountOfLetters() {
        return amountOfLetters;
    }
}
