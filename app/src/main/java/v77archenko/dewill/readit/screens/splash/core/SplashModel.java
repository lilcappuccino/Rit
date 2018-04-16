package v77archenko.dewill.readit.screens.splash.core;

import rx.Observable;
import rx.Subscription;
import v77archenko.dewill.readit.api.NewsApi;
import v77archenko.dewill.readit.models.ArticlesResponse;
import v77archenko.dewill.readit.screens.splash.SplashScreenActivity;
import v77archenko.dewill.readit.utils.PlatformUtils;

/**
 * Created by dewill on 01.04.2018.
 */

public class SplashModel {
	private NewsApi api;
	private SplashScreenActivity splshScrn;
	private final static String API_KEY = "49dc0b7e9cec4fb4a11b925568e89328";
	private final static String COUNTRY = "ua";

	public SplashModel(NewsApi api, SplashScreenActivity splshScrn) {
		this.api = api;
		this.splshScrn = splshScrn;

	}

	Observable<Boolean> isNetworkAvailable() {
		return PlatformUtils.isNetworkAvailableObservable(splshScrn);
	}

	Observable<ArticlesResponse> provideArticlesResponse() {
		return api.getArticlesResponse(API_KEY, COUNTRY);
	}

	public void showArticlesActivity() {
		splshScrn.showArticlesActivity();
	}

}
