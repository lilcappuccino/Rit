package v77archenko.dewill.readit.base;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by dewill on 01.04.2018.
 */
@Module public class AppContextModule {
	private final Context context;

	public AppContextModule(Context context) {
		this.context = context;
	}

	@Provides
	@AppScope
	Context provideAppContext(){return context;}
}
