package reading.speed.improver.exercises.text.appearance.fading.settings.model;

import java.io.Serializable;

public class SettingsModel implements Serializable {
    private float textSizeCoeff;
    private int speed;

    public SettingsModel(final float textSizeCoeff, final int speed) {
        this.textSizeCoeff = textSizeCoeff;
        this.speed = speed;
    }

    public float getTextSizeCoeff() {
        return textSizeCoeff;
    }

    public int getSpeed() {
        return speed;
    }
}
