package reading.speed.improver.exercises.emerging.text.settings.model;

import java.io.Serializable;

public class SettingsModel implements Serializable {
    private float textSizeCoeff;
    private float speed;
    private boolean autoScroll;

    public SettingsModel(final float textSizeCoeff, final float speed, final boolean autoScroll) {
        this.textSizeCoeff = textSizeCoeff;
        this.speed = speed;
        this.autoScroll = autoScroll;
    }

    public float getTextSizeCoeff() {
        return textSizeCoeff;
    }

    public float getSpeed() {
        return speed;
    }

    public boolean isAutoScrollEnabled(){
        return autoScroll;
    }
}
