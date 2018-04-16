package v77archenko.dewill.readit.screens.articles.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Observable;
import rx.subjects.PublishSubject;
import v77archenko.dewill.readit.R;
import v77archenko.dewill.readit.models.Article;
import v77archenko.dewill.readit.models.ArticlesResponse;
import v77archenko.dewill.readit.utils.Tag;

/**
 * Created by dewill on 04.04.2018.
 */

public class ArticlesRecycleAdapter
		extends RecyclerView.Adapter<ArticlesRecycleAdapter.ViewHolder> {
	private ArticlesResponse response = new ArticlesResponse();
	private final PublishSubject<Integer> itemClicks = PublishSubject.create();

	public void swapAdapter(ArticlesResponse articles) {
		if (response.getArticles() != null) {
			response.getArticles().clear();
		}
		response.setArticles(articles.getArticles());
		notifyDataSetChanged();
	}

	public Observable<Integer> observeClicks() {
		return itemClicks;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_news, parent, false);
		return new ViewHolder(view, itemClicks);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		Article article = response.getArticles().get(position);
		holder.bind(article);
	}

	@Override
	public int getItemCount() {
		if (response.getArticles() != null && response.getArticles().size() > 0) {
			Log.i(Tag.ARTICLE_RECYCLEADAPTER, "articles size " + response.getArticles().size());
			return response.getArticles().size();
		}
		return 0;
	}

	protected class ViewHolder extends RecyclerView.ViewHolder {
		View view;
		String description;
		String date;
		String author;
		String stringUrl;
		String stringImgUrl;
		@BindView(R.id.list_news_title) TextView title;
		@BindView(R.id.list_news_source) TextView source;
		@BindView(R.id.list_news_image) CircleImageView imageView;

		public ViewHolder(View itemView, PublishSubject<Integer> clickSubject) {
			super(itemView);
			view = itemView;
			ButterKnife.bind(this, view);
			view.setOnClickListener(position -> clickSubject.onNext(getAdapterPosition()));
		}

		public void bind(Article article) {
			description = article.getDescription();
			date = article.getPublishedAt();
			author = article.getAuthor();
			stringUrl = article.getUrl();
			stringImgUrl = article.getUrlToImage();
			title.setText(TextUtils.isEmpty(article.getTitle()) ? "missing title" : article.getTitle());
			source.setText(
					TextUtils.isEmpty(article.getSource().getName()) ? "" : article.getSource().getName());
			if (stringImgUrl != null) Picasso.get().load(stringImgUrl).into(imageView);
		}
	}
}
