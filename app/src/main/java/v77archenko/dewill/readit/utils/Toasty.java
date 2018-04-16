package v77archenko.dewill.readit.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;
import javax.inject.Inject;

/**
 * Created by dewill on 01.04.2018.
 */

public class Toasty {
	@Inject Context context;

	public void showSuccessMessage(String text) {
		if (context != null) {
			es.dmoral.toasty.Toasty.success(context, text, Toast.LENGTH_LONG, true).show();
		}
	}

	public static void showErrorMessage(@NonNull Context mContext, @NonNull String text) {
		if (mContext != null) {
			es.dmoral.toasty.Toasty.error(mContext, text, Toast.LENGTH_LONG, true).show();
		}
	}

	public static void showWarningMessage(@NonNull Context mContext, @NonNull String text) {
		if (mContext != null) {
			es.dmoral.toasty.Toasty.warning(mContext, text, Toast.LENGTH_LONG, true).show();
		}
	}
}
