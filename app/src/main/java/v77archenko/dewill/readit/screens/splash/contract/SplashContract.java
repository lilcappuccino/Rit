package v77archenko.dewill.readit.screens.splash.contract;

import android.view.View;
import rx.Subscription;
import v77archenko.dewill.readit.base.mvp.BasePresenter;
import v77archenko.dewill.readit.base.mvp.MvpPresenter;
import v77archenko.dewill.readit.base.mvp.MyView;

/**
 * Created by dewill on 16.04.2018.
 */

public interface SplashContract {
	interface Presenter extends MvpPresenter<View> {
		Subscription getArticles();
	}

	interface View extends MyView {
		void showAnim();

		android.view.View constructView();
	}
}
