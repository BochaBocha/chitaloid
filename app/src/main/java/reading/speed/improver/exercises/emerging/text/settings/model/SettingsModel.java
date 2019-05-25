package reading.speed.improver.exercises.emerging.text.settings.model;

import java.io.Serializable;

public class SettingsModel implements Serializable {
    private float textSize;
    private float speed;
    private boolean autoScroll;

    public SettingsModel(final float textSize, final float speed, final boolean autoScroll) {
        this.textSize = textSize;
        this.speed = speed;
        this.autoScroll = autoScroll;
    }

    public float getTextSize() {
        return textSize;
    }

    public float getSpeed() {
        return speed;
    }

    public boolean isAutoScrollEnabled(){
        return autoScroll;
    }
}
