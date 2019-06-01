package reading.speed.improver.exercises.components.timer;

import android.os.Handler;
import android.os.SystemClock;

import java.util.LinkedList;
import java.util.List;


public class Timer implements ObservableTimer {
    private long millisecondTime, startTime, timeBuff, updateTime = 0L;
    private int seconds = 0;
    private int minutes = 0;
    private long delayMillis;
    Handler handler;
    private List<TimerObserver> timerObservers;

    public Timer(final long delayMillis) {
        this.delayMillis = delayMillis;
        handler = new Handler();
        timerObservers = new LinkedList<>();
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
        notifyOnTimeExceeded();
    }

    public Runnable runnable = new Runnable() {

        public void run() {

            millisecondTime = SystemClock.uptimeMillis() - startTime;

            updateTime = timeBuff + millisecondTime;

            seconds = (int) (updateTime / 1000);
            notifyOnTimeExceeded();

            handler.postDelayed(this, delayMillis);
        }
    };


    @Override
    public void registerObserver(TimerObserver timerObserver) {
        timerObservers.add(timerObserver);
    }

    @Override
    public void removeObserver(TimerObserver timerObserver) {
        timerObservers.remove(timerObserver);
    }

    @Override
    public void notifyOnTimeExceeded() {
        for (TimerObserver observer : timerObservers)
            observer.getTimeExceededNotification();
    }
}
