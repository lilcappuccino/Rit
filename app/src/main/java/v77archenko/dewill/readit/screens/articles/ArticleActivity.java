package v77archenko.dewill.readit.screens.articles;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import java.io.Serializable;
import javax.inject.Inject;
import v77archenko.dewill.readit.RitApp;

import v77archenko.dewill.readit.models.Article;
import v77archenko.dewill.readit.screens.articles.core.ArticlePresenter;
import v77archenko.dewill.readit.screens.articles.core.ArticleView;
import v77archenko.dewill.readit.screens.articles.dagger.ArticleContextModule;

import v77archenko.dewill.readit.screens.articles.dagger.DaggerArticleComponent;
import v77archenko.dewill.readit.screens.fullarticle.FullArticleActivity;

/**
 * Created by dewill on 01.04.2018.
 */

public class ArticleActivity extends AppCompatActivity {
	@Inject ArticleView view;
	@Inject ArticlePresenter presenter;

	@Override
	protected void onDestroy() {
		super.onDestroy();
		presenter.onDestroy();
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DaggerArticleComponent.builder()
				.appComponent(RitApp.getAppComponent())
				.articleContextModule(new ArticleContextModule(this))
				.build()
				.inject(this);
		setContentView(view.constructView());
		presenter.onCreate();
	}

	@Override
	protected void onResume() {
		super.onResume();
		view.registerReceiver();
	}

	@Override
	protected void onPause() {
		super.onPause();
		view.unregisterReceiver();
	}

	public void goToFullArticle(Article article) {
		Intent intent = new Intent(this, FullArticleActivity.class);
		intent.putExtra("article", (Serializable) article);
		startActivity(intent);
	}
}
