package v77archenko.dewill.readit.screens.articles.core;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import v77archenko.dewill.readit.models.ArticlesResponse;
import v77archenko.dewill.readit.utils.RxSchedulers;
import v77archenko.dewill.readit.utils.Tag;

/**
 * Created by dewill on 03.04.2018.
 */

public class ArticlePresenter {

	private ArticleModel model;
	private CompositeSubscription subscription;
	private ArticleView view;
	private RxSchedulers rxSchedulers;
	private Boolean isNetAvailable;
	private ArticlesResponse articlesResponse;

	public ArticlePresenter(ArticleView view, CompositeSubscription subscription, ArticleModel model,
			RxSchedulers rxSchedulers) {
		this.model = model;
		this.view = view;
		this.subscription = subscription;
		this.rxSchedulers = rxSchedulers;
	}

	public void onDestroy() {
		subscription.clear();
	}

	public void onCreate() {
		subscription.add(getArticle());
		subscription.add(respondToClick());
		articlesResponse = new ArticlesResponse();
	}

	public Subscription respondToClick() {
		return view.itemClick().subscribe(integer -> {
			model.goToFullArticle(articlesResponse.getArticles().get(integer));
		});
	}

	public Subscription getArticle() {
		Log.i(Tag.ARTICLE_PRESENTER, "getArticle pressed");
		return model.provideNewsList()
				.subscribeOn(rxSchedulers.internet())
				.observeOn(rxSchedulers.androidThread())
				.subscribe(response -> {
					Log.i(Tag.ARTICLE_PRESENTER, "articles " + response.getArticles().size());
					articlesResponse = response;
					view.swap(articlesResponse);
				}, throwable -> {
					Log.e(Tag.ARTICLE_PRESENTER, "error " + throwable.getMessage());
				});
	}
}





