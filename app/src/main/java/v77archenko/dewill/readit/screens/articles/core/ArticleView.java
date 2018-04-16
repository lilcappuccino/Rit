package v77archenko.dewill.readit.screens.articles.core;

import android.content.IntentFilter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import v77archenko.dewill.readit.R;
import v77archenko.dewill.readit.models.ArticlesResponse;
import v77archenko.dewill.readit.screens.articles.ArticleActivity;
import v77archenko.dewill.readit.screens.articles.adapter.ArticlesRecycleAdapter;
import v77archenko.dewill.readit.utils.ConnectionReceiver;
import v77archenko.dewill.readit.utils.IConnectionReceiver;
import v77archenko.dewill.readit.utils.Tag;
import v77archenko.dewill.readit.utils.Toasty;

/**
 * Created by dewill on 03.04.2018.
 */

public class ArticleView {
	private View view;
	private ConnectionReceiver receiver;
	private IntentFilter intentFilter;
	final static String CONNECTIVITY_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
	@BindView(R.id.activity_main_recycle_view) RecyclerView recyclerView;
	ArticlesRecycleAdapter adapter;

	public ArticleView(ArticleActivity context) {
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

	public void registerReceiver() {
		view.getContext().registerReceiver(receiver, intentFilter);
	}

	public void unregisterReceiver() {
		view.getContext().unregisterReceiver(receiver);
	}

	private void initReceiver() {
		View internetView = view.findViewById(R.id.articles_internet_connection);
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

	public Observable<Integer> itemClick() {
		return adapter.observeClicks()
				.doOnNext(
						integer -> Toasty.showErrorMessage(constructView().getContext(), integer.toString()));
	}

	public void swap(ArticlesResponse articlesResponse) {
		Log.i(Tag.ARTICLE_VIEW, "adapter.getItemCount :" + adapter.getItemCount());
		adapter.swapAdapter(articlesResponse);
	}
}

