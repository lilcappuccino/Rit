package v77archenko.dewill.readit;

import android.app.Application;
import com.squareup.leakcanary.LeakCanary;
import v77archenko.dewill.readit.base.AppComponent;
import v77archenko.dewill.readit.base.AppContextModule;
import v77archenko.dewill.readit.base.DaggerAppComponent;
import v77archenko.dewill.readit.screens.splash.dagger.SplashComponent;

/**
 * Created by dewill on 01.04.2018.
 */

public class RitApp extends Application {
	private static AppComponent appComponent;

	@Override
	public void onCreate() {
		super.onCreate();
		initAppComponent();
		initLeakCanary();
	}

	private void initLeakCanary() {
		if (LeakCanary.isInAnalyzerProcess(this)) {
			return;
		}
		LeakCanary.install(this);
	}

	private void initAppComponent() {
		appComponent =
				DaggerAppComponent.builder().appContextModule(new AppContextModule(this)).build();
	}

	public static AppComponent getAppComponent() {

		return appComponent;
	}


}
