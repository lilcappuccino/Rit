package v77archenko.dewill.readit.screens.splash.core;

import android.util.Log;
import java.util.concurrent.TimeUnit;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import v77archenko.dewill.readit.base.mvp.BasePresenter;
import v77archenko.dewill.readit.screens.splash.contract.SplashContract;
import v77archenko.dewill.readit.utils.RxSchedulers;

/**
 * Created by dewill on 01.04.2018.
 */

public class SplashPresenterImpl extends BasePresenter<SplashContract.View>
		implements SplashContract.Presenter {
	private SplashModel model;
	private RxSchedulers appRxSchedulers;
	private CompositeSubscription subscription;
	private SplashViewImpl view;

	public SplashPresenterImpl(SplashModel model, RxSchedulers appRxSchedulers,
			CompositeSubscription subscription, SplashViewImpl view) {
		this.model = model;
		this.appRxSchedulers = appRxSchedulers;
		this.subscription = subscription;
		this.view = view;
	}

	@Override
	public void onCreate() {
		view.showAnim();
		subscription.add(getArticles());
	}

	@Override
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
						throwable -> Log.e(SplashPresenterImpl.class.toString(), throwable.getMessage()));
	}

	@Override
	public void onDestroy() {
		subscription.clear();
	}
}
