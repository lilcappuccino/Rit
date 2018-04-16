package v77archenko.dewill.readit.screens.splash.dagger;

import dagger.Component;
import dagger.Subcomponent;
import v77archenko.dewill.readit.base.AppComponent;
import v77archenko.dewill.readit.screens.splash.SplashScreenActivity;
import v77archenko.dewill.readit.screens.splash.core.SplashModel;

/**
 * Created by dewill on 01.04.2018.
 */
@SplashScope
@Component(modules = { SplashModule.class, SplashContextModule.class }, dependencies = {
		AppComponent.class })
  public interface SplashComponent {
	void inject(SplashScreenActivity activity);
}
