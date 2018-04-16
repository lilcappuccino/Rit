package v77archenko.dewill.readit.screens.splash.dagger;

import dagger.Module;
import dagger.Provides;
import v77archenko.dewill.readit.screens.splash.SplashScreenActivity;

/**
 * Created by dewill on 01.04.2018.
 */
@Module public class SplashContextModule {
	private SplashScreenActivity splScrAct;

	public SplashContextModule(SplashScreenActivity splScrAct) {
		this.splScrAct = splScrAct;
	}

	@SplashScope
	@Provides
	public SplashScreenActivity provideSplashContext() {
		return splScrAct;
	}
}
