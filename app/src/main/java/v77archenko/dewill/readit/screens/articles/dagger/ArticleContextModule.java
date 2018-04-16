package v77archenko.dewill.readit.screens.articles.dagger;

import dagger.Module;
import dagger.Provides;
import v77archenko.dewill.readit.screens.articles.ArticleActivity;

/**
 * Created by dewill on 03.04.2018.
 */
@Module
public class ArticleContextModule {
	private ArticleActivity articleActivity;

	public ArticleContextModule(ArticleActivity articleActivity) {
		this.articleActivity = articleActivity;
	}

	@ArticleScope
	@Provides
	public ArticleActivity provideArticleContext() {
		return articleActivity;
	}
}
