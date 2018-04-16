package v77archenko.dewill.readit.base;

import dagger.Component;
import v77archenko.dewill.readit.api.NewsApi;
import v77archenko.dewill.readit.screens.fullarticle.FullArticleActivity;
import v77archenko.dewill.readit.utils.RxSchedulers;

/**
 * Created by dewill on 01.04.2018.
 */
@AppScope
@Component(modules = { AppContextModule.class, NewsApiServiceModule.class, RxModule.class})
public interface AppComponent {
	//void inject(FullArticleActivity context);

	RxSchedulers rxSchedulers();
	NewsApi apiService();

}
