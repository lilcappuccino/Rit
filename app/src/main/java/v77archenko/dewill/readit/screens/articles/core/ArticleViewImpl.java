package v77archenko.dewill.readit.screens.articles.core;

import android.content.IntentFilter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import v77archenko.dewill.readit.R;
import v77archenko.dewill.readit.models.ArticlesResponse;
import v77archenko.dewill.readit.screens.articles.ArticleActivity;
import v77archenko.dewill.readit.screens.articles.adapter.ArticlesRecycleAdapter;
import v77archenko.dewill.readit.screens.articles.contract.ArticleContract;
import v77archenko.dewill.readit.utils.ConnectionReceiver;
import v77archenko.dewill.readit.utils.IConnectionReceiver;
import v77archenko.dewill.readit.utils.Tag;
import v77archenko.dewill.readit.utils.Toasty;

/**
 * Created by dewill on 03.04.2018.
 */

public class ArticleViewImpl implements ArticleContract.View {
	private View view;
	private ConnectionReceiver receiver;
	private IntentFilter intentFilter;
	private ArticlesRecycleAdapter adapter;
	final static String CONNECTIVITY_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
	@BindView(R.id.activity_article_recycle_view) RecyclerView recyclerView;
	@BindView(R.id.activity_articles_internet_connection) LinearLayout internetView;

	public ArticleViewImpl(ArticleActivity context) {
		FrameLayout parent = new FrameLayout(context);
		parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		view = LayoutInflater.from(context).inflate(R.layout.activity_articles, parent, true);
		ButterKnife.bind(this, view);
		initReceiver();
		adapter = new ArticlesRecycleAdapter();
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(context));
	}

	public View constructView() {
		return view;
	}

	@Override
	public void registerReceiver() {
		view.getContext().registerReceiver(receiver, intentFilter);
	}

	@Override
	public void unregisterReceiver() {
		view.getContext().unregisterReceiver(receiver);
	}

	private void initReceiver() {
		IConnectionReceiver iConnectionReceiver = new IConnectionReceiver() {
			@Override
			public void haveConnection() {
				internetView.setVisibility(View.GONE);
			}

			@Override
			public void withoutConnection() {
				internetView.setVisibility(View.VISIBLE);
			}
		};
		receiver = new ConnectionReceiver();
		receiver.setIConnectionReceiver(iConnectionReceiver);
		intentFilter = new IntentFilter();
		intentFilter.addAction(CONNECTIVITY_ACTION);
	}

	@Override
	public Observable<Integer> itemClick() {
		return adapter.observeClicks();
	}

	@Override
	public void swap(ArticlesResponse articlesResponse) {
		Log.i(Tag.ARTICLE_VIEW, "adapter.getItemCount :" + adapter.getItemCount());
		adapter.swapAdapter(articlesResponse);
	}
}

