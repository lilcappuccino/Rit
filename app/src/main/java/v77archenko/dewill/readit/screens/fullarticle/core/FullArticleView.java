package v77archenko.dewill.readit.screens.fullarticle.core;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.squareup.picasso.Picasso;
import io.reactivex.Observable;
import rx.Subscription;
import v77archenko.dewill.readit.R;
import v77archenko.dewill.readit.models.Article;
import v77archenko.dewill.readit.screens.fullarticle.FullArticleActivity;
import v77archenko.dewill.readit.utils.Tag;

/**
 * Created by dewill on 12.04.2018.
 */

public class FullArticleView {
	private View view;
	@BindView(R.id.activity_full_article_title) TextView title;
	@BindView(R.id.activity_full_article_author) TextView author;
	@BindView(R.id.activity_full_article_date) TextView date;
	@BindView(R.id.activity_full_article_description) TextView description;
	@BindView(R.id.activity_full_article_img) ImageView image;
	private String url;

	public FullArticleView(FullArticleActivity context, Article article) {
		FrameLayout frameLayout = new FrameLayout(context);
		frameLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));

		view = LayoutInflater.from(context).inflate(R.layout.activity_full_article, frameLayout, true);
		ButterKnife.bind(this, view);

		if (article.getUrlToImage() != null) Picasso.get().load(article.getUrlToImage()).into(image);
		title.setText(TextUtils.isEmpty(article.getTitle()) ? "no title" : article.getTitle());
		author.setText(TextUtils.isEmpty(article.getAuthor()) ? "no author" : article.getAuthor());
		date.setText(
				TextUtils.isEmpty(article.getPublishedAt()) ? "no date" : article.getPublishedAt());
		description.setText(
				TextUtils.isEmpty(article.getDescription()) ? "no description" : article.getDescription());
		url = article.getUrl();
		Log.i(Tag.ARTICLE_VIEW, url);
	}

	public View constructView() {
		return view;
	}

	@OnClick(R.id.activity_full_article_url)
	public void showWebView() {
		AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
		alert.setTitle(title.getText());

		WebView wv = new WebView(view.getContext());
		wv.getSettings().setJavaScriptEnabled(true);
		//wv.loadUrl(url);
		wv.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);

				return true;
			}
		});

		alert.setView(wv);
		alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				dialog.dismiss();
			}
		});
		alert.show();
	}
}
