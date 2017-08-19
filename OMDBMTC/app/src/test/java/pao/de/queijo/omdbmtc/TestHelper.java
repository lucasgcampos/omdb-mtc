package pao.de.queijo.omdbmtc;

import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */

public class TestHelper {

    public static final Scheduler jUnitScheduler = new Scheduler() {
        @Override
        public Worker createWorker() {
            return new Worker() {
                @Override
                public Disposable schedule(Runnable run, long delay, TimeUnit unit) {
                    run.run();
                    return Observable.empty().subscribe();
                }

                @Override
                public void dispose() {

                }

                @Override
                public boolean isDisposed() {
                    return false;
                }
            };
        }
    };

}
