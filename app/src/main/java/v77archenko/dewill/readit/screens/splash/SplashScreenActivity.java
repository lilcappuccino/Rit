package v77archenko.dewill.readit.screens.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import javax.inject.Inject;
import v77archenko.dewill.readit.RitApp;
import v77archenko.dewill.readit.screens.articles.ArticleActivity;
import v77archenko.dewill.readit.screens.splash.core.SplashModel;
import v77archenko.dewill.readit.screens.splash.core.SplashPresenter;
import v77archenko.dewill.readit.screens.splash.core.SplashView;
import v77archenko.dewill.readit.screens.splash.dagger.DaggerSplashComponent;
import v77archenko.dewill.readit.screens.splash.dagger.SplashContextModule;

/**
 * Created by dewill on 01.04.2018.
 */

public class SplashScreenActivity extends AppCompatActivity {
	@Inject SplashView view;
	@Inject SplashPresenter presenter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DaggerSplashComponent.builder()
				.appComponent(RitApp.getAppComponent())
				.splashContextModule(new SplashContextModule(this))
				.build()
				.inject(this);
		setContentView(view.constructView());
		presenter.onCreate();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		presenter.onDestroy();
	}

	public void showArticlesActivity() {
		Intent i = new Intent(SplashScreenActivity.this, ArticleActivity.class);
		startActivity(i);
	}
}
