package v77archenko.dewill.readit.utils;

import rx.Scheduler;

/**
 * Created by dewill on 03.04.2018.
 */

public interface RxSchedulers {

	Scheduler runOnBackground();

	Scheduler io();

	Scheduler compute();

	Scheduler androidThread();

	Scheduler internet();
}
