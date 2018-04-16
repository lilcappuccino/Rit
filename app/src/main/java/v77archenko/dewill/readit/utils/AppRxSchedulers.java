package v77archenko.dewill.readit.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dewill on 01.04.2018.
 */

public class AppRxSchedulers implements RxSchedulers {
	public static Executor backgroundExecutor = Executors.newCachedThreadPool();
	public static Scheduler BACKGROUND_SCHEDULERS = Schedulers.from(backgroundExecutor);
	public static Executor internetExecutor = Executors.newCachedThreadPool();
	public static Scheduler INTERNET_SCHEDULERS = Schedulers.from(internetExecutor);

	public Scheduler runOnBackground() {
		return BACKGROUND_SCHEDULERS;
	}

	public Scheduler io() {
		return Schedulers.io();
	}

	public Scheduler compute() {
		return Schedulers.computation();
	}

	public Scheduler androidThread() {
		return AndroidSchedulers.mainThread();
	}

	public Scheduler internet() {
		return INTERNET_SCHEDULERS;
	}
}
