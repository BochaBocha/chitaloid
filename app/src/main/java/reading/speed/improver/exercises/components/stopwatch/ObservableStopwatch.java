package reading.speed.improver.exercises.components.stopwatch;

public interface ObservableStopwatch {
        void registerObserver(StopwatchObserver stopwatchObserver);
        void removeObserver(StopwatchObserver stopwatchObserver);
        void notifyObservers();
}
