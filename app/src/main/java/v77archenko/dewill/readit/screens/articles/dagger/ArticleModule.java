package v77archenko.dewill.readit.screens.articles.dagger;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;
import v77archenko.dewill.readit.api.NewsApi;
import v77archenko.dewill.readit.models.Article;
import v77archenko.dewill.readit.screens.articles.ArticleActivity;
import v77archenko.dewill.readit.screens.articles.core.ArticleModel;
import v77archenko.dewill.readit.screens.articles.core.ArticlePresenter;
import v77archenko.dewill.readit.screens.articles.core.ArticleView;
import v77archenko.dewill.readit.screens.splash.dagger.SplashScope;
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
	public ArticleView provideArticleView(ArticleActivity context) {
		return new ArticleView(context);
	}

	@Provides
	@ArticleScope
	public ArticlePresenter providePresenter(ArticleView view, ArticleModel model,
			RxSchedulers rxSchedulers) {
		CompositeSubscription subscription = new CompositeSubscription();
		return new ArticlePresenter(view, subscription, model, rxSchedulers);
	}
}
