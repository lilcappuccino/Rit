package v77archenko.dewill.readit.screens.articles.contract;

import rx.Observable;
import rx.Subscription;
import v77archenko.dewill.readit.base.mvp.MvpPresenter;
import v77archenko.dewill.readit.base.mvp.MyView;
import v77archenko.dewill.readit.models.ArticlesResponse;

/**
 * Created by dewill on 16.04.2018.
 */

public interface ArticleContract {
	interface Presenter extends MvpPresenter<ArticleContract.View> {
		Subscription respondToClick();

		Subscription getArticle();
	}

	interface View extends MyView {
		void registerReceiver();

		void unregisterReceiver();

		Observable<Integer> itemClick();

		void swap(ArticlesResponse articlesResponse);
	}
}
