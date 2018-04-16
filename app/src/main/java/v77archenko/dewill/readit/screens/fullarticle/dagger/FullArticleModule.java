package v77archenko.dewill.readit.screens.fullarticle.dagger;

import dagger.Module;
import dagger.Provides;
import v77archenko.dewill.readit.models.Article;
import v77archenko.dewill.readit.screens.fullarticle.FullArticleActivity;
import v77archenko.dewill.readit.screens.fullarticle.core.FullArticleView;

/**
 * Created by dewill on 12.04.2018.
 */

@Module
public class FullArticleModule {
	FullArticleActivity context;
	Article article;

	public FullArticleModule(FullArticleActivity context, Article article) {
		this.article = article;
		this.context = context;
	}

	@Provides
	FullArticleView provideFullArticleView() {
		return new FullArticleView(context, article);
	}
}
