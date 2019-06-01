package reading.speed.improver.exercises.components.timer;

public interface ObservableTimer {
        void registerObserver(TimerObserver timerObserver);
        void removeObserver(TimerObserver timerObserver);
        void notifyOnTimeExceeded();
}
