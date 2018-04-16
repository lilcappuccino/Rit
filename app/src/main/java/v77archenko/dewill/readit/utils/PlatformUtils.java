package v77archenko.dewill.readit.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObservable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.functions.Func1;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;

/**
 * Created by dewill on 01.04.2018.
 */

public class PlatformUtils {
	public static Boolean isNetworkAvailable(Context mContext) {
		ConnectivityManager cm =
				(ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		return !(null == networkInfo || !networkInfo.isConnected());
	}

	public static Observable<Boolean> isNetworkAvailableObservable(Context mContext) {
		return Observable.just(PlatformUtils.isNetworkAvailable(mContext));
	}
}
