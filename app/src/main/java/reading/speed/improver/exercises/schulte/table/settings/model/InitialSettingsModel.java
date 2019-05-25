package reading.speed.improver.exercises.schulte.table.settings.model;

import java.io.Serializable;

public class InitialSettingsModel implements Serializable {
    private float initialTextSize;

    public InitialSettingsModel(final float initialTextSize) {
        this.initialTextSize = initialTextSize;
    }

    public float getInitialTextSize() {
        return initialTextSize;
    }

    public void setInitialTextSize(final float initialTextSize) {
        this.initialTextSize = initialTextSize;
    }
}
