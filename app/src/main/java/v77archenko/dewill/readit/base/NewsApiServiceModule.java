package v77archenko.dewill.readit.base;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import v77archenko.dewill.readit.api.NewsApi;

/**
 * Created by dewill on 01.04.2018.
 */

@Module public class NewsApiServiceModule {
	private static final String BASE_URL = "https://newsapi.org/v2/";


	@AppScope
	@Provides
	NewsApi provideApiService() {
		Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.build();
		return retrofit.create(NewsApi.class);
	}
}
