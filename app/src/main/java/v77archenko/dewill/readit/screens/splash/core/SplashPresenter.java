package v77archenko.dewill.readit.screens.splash.core;

import android.util.Log;
import android.view.animation.AnimationUtils;
import java.util.concurrent.TimeUnit;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import v77archenko.dewill.readit.R;
import v77archenko.dewill.readit.utils.AppRxSchedulers;
import v77archenko.dewill.readit.utils.RxSchedulers;
import v77archenko.dewill.readit.utils.Tag;

/**
 * Created by dewill on 01.04.2018.
 */

public class SplashPresenter {
	private SplashModel model;
	private RxSchedulers appRxSchedulers;
	private CompositeSubscription subscription;
	private SplashView view;

	public SplashPresenter(SplashModel model, RxSchedulers appRxSchedulers,
			CompositeSubscription subscription, SplashView view) {
		this.model = model;
		this.appRxSchedulers = appRxSchedulers;
		this.subscription = subscription;
		this.view = view;
	}

	public void onCreate() {
		view.splashAnim();
		subscription.add(getArticles());
	}

	public Subscription getArticles() {
		return model.isNetworkAvailable()
				.doOnNext(networkAvailable -> {
					if (!networkAvailable) {
						Log.d("no conn", "no connexion");
					}
				})
				.filter(isNetworkAvailable -> true)
				.flatMap(isAvailable -> model.isNetworkAvailable())
				.subscribeOn(appRxSchedulers.internet())
				.observeOn(appRxSchedulers.androidThread())
				.delay(3, TimeUnit.SECONDS)
				.subscribe(aBoolean -> model.showArticlesActivity(),
						throwable -> Log.e(SplashPresenter.class.toString(), throwable.getMessage()));
	}

	public void onDestroy() {
		subscription.clear();
	}


}
