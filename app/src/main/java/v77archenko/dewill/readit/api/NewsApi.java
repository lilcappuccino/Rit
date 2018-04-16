package v77archenko.dewill.readit.api;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import v77archenko.dewill.readit.models.ArticlesResponse;

/**
 * Created by dewill on 01.04.2018.
 */

public interface NewsApi {
	@GET("top-headlines")
	Observable<ArticlesResponse> getArticlesResponse(@Query("apiKey") String key,
			@Query("country") String country);
}
