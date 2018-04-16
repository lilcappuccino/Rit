package v77archenko.dewill.readit.screens.articles.dagger;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;
import v77archenko.dewill.readit.api.NewsApi;
import v77archenko.dewill.readit.screens.articles.ArticleActivity;
import v77archenko.dewill.readit.screens.articles.core.ArticleModel;
import v77archenko.dewill.readit.screens.articles.core.ArticlePresenterImpl;
import v77archenko.dewill.readit.screens.articles.core.ArticleViewImpl;
import v77archenko.dewill.readit.utils.RxSchedulers;

/**
 * Created by dewill on 03.04.2018.
 */

@Module public class ArticleModule {

	@Provides
	@ArticleScope
	public ArticleModel provideArticleModel(NewsApi api, ArticleActivity context) {
		return new ArticleModel(api, context);
	}

	@Provides
	@ArticleScope
	public ArticleViewImpl provideArticleView(ArticleActivity context) {
		return new ArticleViewImpl(context);
	}

	@Provides
	@ArticleScope
	public ArticlePresenterImpl providePresenter(ArticleViewImpl view, ArticleModel model,
			RxSchedulers rxSchedulers) {
		CompositeSubscription subscription = new CompositeSubscription();
		return new ArticlePresenterImpl(view, subscription, model, rxSchedulers);
	}
}
