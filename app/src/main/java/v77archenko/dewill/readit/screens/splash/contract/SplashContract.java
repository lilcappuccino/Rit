package v77archenko.dewill.readit.screens.splash.contract;

import v77archenko.dewill.readit.base.mvp.MyView;
import v77archenko.dewill.readit.base.mvp.Presenter;

/**
 * Created by dewill on 16.04.2018.
 */

public interface SplashContract {
	interface Presenter extends v77archenko.dewill.readit.base.mvp.Presenter<SplashContract.View> {
	}

	interface View extends MyView {
		public void showAnim();
	}
}
