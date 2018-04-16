package v77archenko.dewill.readit.screens.fullarticle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import javax.inject.Inject;
import v77archenko.dewill.readit.models.Article;
import v77archenko.dewill.readit.screens.articles.ArticleActivity;
import v77archenko.dewill.readit.screens.fullarticle.core.FullArticleView;
import v77archenko.dewill.readit.screens.fullarticle.dagger.DaggerFullArticleComponent;
import v77archenko.dewill.readit.screens.fullarticle.dagger.FullArticleModule;

/**
 * Created by dewill on 12.04.2018.
 */

public class FullArticleActivity extends AppCompatActivity {
	@Inject FullArticleView view;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Article article = (Article) getIntent().getExtras().get("article");
		DaggerFullArticleComponent.builder()
				.fullArticleModule(new FullArticleModule(this, article))
				.build()
				.inject(this);

		setContentView(view.constructView());
	}

	public void onClickBackBtn() {
		startActivity(new Intent(this, ArticleActivity.class));
	}
}
