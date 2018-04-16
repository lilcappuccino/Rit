package v77archenko.dewill.readit.base;

import dagger.Module;
import dagger.Provides;
import v77archenko.dewill.readit.utils.AppRxSchedulers;
import v77archenko.dewill.readit.utils.RxSchedulers;

/**
 * Created by dewill on 01.04.2018.
 */

@Module public class RxModule {

	@Provides
	RxSchedulers provideRxSchedulers() {
		return new AppRxSchedulers() {
		};
	}
}

