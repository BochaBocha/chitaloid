package reading.speed.improver.exercises.components.stopwatch;

import android.os.Handler;
import android.os.SystemClock;

import java.util.LinkedList;
import java.util.List;


public class Stopwatch implements ObservableStopwatch {
    private long millisecondTime, startTime, timeBuff, updateTime = 0L;
    private int seconds = 0;
    private int minutes = 0;
    Handler handler;
    private List<StopwatchObserver> stopwatchObservers;

    public Stopwatch() {
        handler = new Handler();
        stopwatchObservers = new LinkedList<>();
    }

    public void start() {
        startTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);
    }

    public void pause() {
        timeBuff += millisecondTime;
        handler.removeCallbacks(runnable);
    }

    public void reset() {
        millisecondTime = 0L;
        startTime = 0L;
        timeBuff = 0L;
        updateTime = 0L;
        seconds = 0;
        notifyObservers();
    }


    public Runnable runnable = new Runnable() {

        public void run() {

            millisecondTime = SystemClock.uptimeMillis() - startTime;

            updateTime = timeBuff + millisecondTime;

            seconds = (int) (updateTime / 1000);
            notifyObservers();

            handler.postDelayed(this, 0);
        }
    };

    @Override
    public void registerObserver(StopwatchObserver stopwatchObserver) {
        stopwatchObservers.add(stopwatchObserver);

    }

    @Override
    public void removeObserver(StopwatchObserver stopwatchObserver) {
        stopwatchObservers.remove(stopwatchObserver);
    }

    @Override
    public void notifyObservers() {
        for (StopwatchObserver observer : stopwatchObservers)
            observer.update(seconds);
    }
}
