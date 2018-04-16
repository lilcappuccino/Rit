package v77archenko.dewill.readit.base.mvp;

import android.content.Intent;
import android.view.View;

/**
 * Created by dewill on 16.04.2018.
 */

public class BasePresenter<T extends MyView> implements MvpPresenter<T> {
	protected T view;

	@Override
	public View constructView() {
		return null;
	}

	@Override
	public void setArgs(Intent args) {

	}

	@Override
	public void setView(T view) {
		this.view = view;
	}

	@Override
	public void onCreate() {

	}

	@Override
	public void onResume() {

	}

	@Override
	public void onPause() {

	}

	@Override
	public void onDestroy() {
		view = null;
	}
}
