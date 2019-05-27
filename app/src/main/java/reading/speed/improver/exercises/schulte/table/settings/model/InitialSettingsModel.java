package reading.speed.improver.exercises.schulte.table.settings.model;

import java.io.Serializable;

public class InitialSettingsModel implements Serializable {
    private float initialTextSizeCoeff;

    public InitialSettingsModel(final float initialTextSizeCoeff) {
        this.initialTextSizeCoeff = initialTextSizeCoeff;
    }

    public float getInitialTextSizeCoeff() {
        return initialTextSizeCoeff;
    }

    public void setInitialTextSizeCoeff(final float initialTextSizeCoeff) {
        this.initialTextSizeCoeff = initialTextSizeCoeff;
    }
}
