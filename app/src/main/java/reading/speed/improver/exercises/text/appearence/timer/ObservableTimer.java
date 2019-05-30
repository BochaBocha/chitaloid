package reading.speed.improver.exercises.text.appearence.timer;

public interface ObservableTimer {
        void registerObserver(TimerObserver timerObserver);
        void removeObserver(TimerObserver timerObserver);
        void notifyOnTimeExceeded();
}
