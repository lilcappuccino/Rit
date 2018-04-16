package v77archenko.dewill.readit.base.mvp;

import android.content.Intent;
import android.view.View;

/**
 * Created by dewill on 16.04.2018.
 */

public interface MvpPresenter<V extends MyView> {
	View constructView();

	void setArgs(Intent args);

	void setView(V view);

	void onCreate();

	void onResume();

	void onPause();

	void onDestroy();
}
