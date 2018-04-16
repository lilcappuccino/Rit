package v77archenko.dewill.readit.screens.articles.dagger;

import dagger.Component;
import v77archenko.dewill.readit.base.AppComponent;
import v77archenko.dewill.readit.screens.articles.ArticleActivity;

/**
 * Created by dewill on 03.04.2018.
 */

@ArticleScope
@Component(modules = { ArticleModule.class, ArticleContextModule.class }, dependencies = {
		AppComponent.class })
public interface ArticleComponent {
	void inject(ArticleActivity articleActivity);
}
