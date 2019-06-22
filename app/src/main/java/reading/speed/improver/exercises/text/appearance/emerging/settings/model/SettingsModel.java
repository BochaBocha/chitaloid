package reading.speed.improver.exercises.text.appearance.emerging.settings.model;

import java.io.Serializable;

public class SettingsModel implements Serializable {
    private float textSizeCoeff;
    private int speed;
    private boolean autoScroll;

    public SettingsModel(final float textSizeCoeff, final int speed, final boolean autoScroll) {
        this.textSizeCoeff = textSizeCoeff;
        this.speed = speed;
        this.autoScroll = autoScroll;
    }

    public float getTextSizeCoeff() {
        return textSizeCoeff;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isAutoScrollEnabled(){
        return autoScroll;
    }
}
