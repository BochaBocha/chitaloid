package reading.speed.improver.exercises.emerging.text.stopwatch;

public interface ObservableStopwatch {
        void registerObserver(StopwatchObserver stopwatchObserver);
        void removeObserver(StopwatchObserver stopwatchObserver);
        void notifyObservers();
}
