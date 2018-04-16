package v77archenko.dewill.readit.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;
import static v77archenko.dewill.readit.utils.PlatformUtils.isNetworkAvailable;

/**
 * Created by dewill on 04.04.2018.
 */

public class ConnectionReceiver extends BroadcastReceiver {
	private IConnectionReceiver iConnectionReceiver;

	@Override
	public void onReceive(Context context, Intent intent) {
		String actionOfIntent = intent.getAction();
		if (actionOfIntent.equals(CONNECTIVITY_ACTION)) {
			if (iConnectionReceiver != null) {
				if (isNetworkAvailable(context)) {
					iConnectionReceiver.haveConnection();
				} else {
					iConnectionReceiver.withoutConnection();
				}
			}
		}
	}
	public void setIConnectionReceiver(IConnectionReceiver iConnectionReceiver){
		this.iConnectionReceiver = iConnectionReceiver;
	}
}
