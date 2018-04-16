package v77archenko.dewill.readit.screens.articles.core;

import android.content.Context;
import android.util.Log;
import rx.Observable;
import v77archenko.dewill.readit.api.NewsApi;
import v77archenko.dewill.readit.models.Article;
import v77archenko.dewill.readit.models.ArticlesResponse;
import v77archenko.dewill.readit.screens.articles.ArticleActivity;
import v77archenko.dewill.readit.utils.PlatformUtils;
import v77archenko.dewill.readit.utils.Tag;

/**
 * Created by dewill on 03.04.2018.
 */

public class ArticleModel {
	NewsApi api;
	ArticleActivity context;
	private final static String API_KEY = "49dc0b7e9cec4fb4a11b925568e89328";
	private final static String COUNTRY = "ua";

	public ArticleModel(NewsApi api, ArticleActivity context) {
		this.api = api;
		this.context = context;
	}

	Observable<Boolean> isNetworkAvailable() {
		return PlatformUtils.isNetworkAvailableObservable(context);
	}

	Observable<ArticlesResponse> provideNewsList() {
		Log.i(Tag.ARTICLE_MODEL, "provideList(), country: " + COUNTRY + "key + " + API_KEY);
		return api.getArticlesResponse(API_KEY, COUNTRY);
	}

	public void goToFullArticle(Article article) {
		context.goToFullArticle(article);
	}
}
