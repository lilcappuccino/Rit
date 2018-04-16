package v77archenko.dewill.readit.screens.fullarticle.dagger;

import dagger.Component;
import v77archenko.dewill.readit.screens.fullarticle.FullArticleActivity;

/**
 * Created by dewill on 12.04.2018.
 */

@Component(modules = {FullArticleModule.class})
public interface FullArticleComponent {
	void inject(FullArticleActivity context);
}
