package reading.speed.improver.exercises.text.appearence.timer;

import android.os.Handler;

import java.util.LinkedList;
import java.util.List;


public class Timer implements ObservableTimer {
    Handler handler;
    public Runnable runnable;
    private List<TimerObserver> timerObservers;

    public Timer(final long delayMillis) {
        handler = new Handler();
        timerObservers = new LinkedList<>();
        runnable= createRunnable(delayMillis);
    }

    private Runnable createRunnable(final long delayMillis){
        return new Runnable() {

            public void run() {
                notifyOnTimeExceeded();
                handler.postDelayed(this, delayMillis);
            }
        };
    }

    public void start() {
        handler.postDelayed(runnable, 0);
    }

    public void pause() {
        handler.removeCallbacks(runnable);
    }

    public void reset() {
        notifyOnTimeExceeded();
    }

    public void setDelayMillis(final long delayMillis) {
        handler.removeCallbacks(runnable);
        runnable = createRunnable(delayMillis);
    }

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
