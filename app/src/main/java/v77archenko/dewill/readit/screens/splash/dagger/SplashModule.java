package v77archenko.dewill.readit.screens.splash.dagger;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;
import v77archenko.dewill.readit.api.NewsApi;
import v77archenko.dewill.readit.screens.splash.SplashScreenActivity;
import v77archenko.dewill.readit.screens.splash.core.SplashModel;
import v77archenko.dewill.readit.screens.splash.core.SplashPresenter;
import v77archenko.dewill.readit.screens.splash.core.SplashView;
import v77archenko.dewill.readit.utils.AppRxSchedulers;
import v77archenko.dewill.readit.utils.RxSchedulers;

/**
 * Created by dewill on 01.04.2018.
 */

@Module public class SplashModule {

	@SplashScope
	@Provides
	public SplashModel provideSplashModel(NewsApi api, SplashScreenActivity splshScrn) {
		return new SplashModel(api, splshScrn);
	}

	@SplashScope
	@Provides
	public SplashView provideSplashView(SplashScreenActivity context) {
		return new SplashView(context);
	}

	@SplashScope
	@Provides
	public SplashPresenter provideSplashPresenter(SplashModel model, RxSchedulers rxSchedulers,
			SplashView view) {
		CompositeSubscription subscription = new CompositeSubscription();
		return new SplashPresenter(model, rxSchedulers, subscription, view);
	}
}
