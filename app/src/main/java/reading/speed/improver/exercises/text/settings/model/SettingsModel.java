package reading.speed.improver.exercises.text.settings.model;

import java.io.Serializable;

public class SettingsModel implements Serializable {
    private float textSizeCoeff;

    public SettingsModel(final float textSizeCoeff) {
        this.textSizeCoeff = textSizeCoeff;
    }

    public float getTextSizeCoeff() {
        return textSizeCoeff;
    }
}
