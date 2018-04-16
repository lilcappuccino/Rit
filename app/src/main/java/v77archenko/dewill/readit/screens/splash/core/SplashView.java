package v77archenko.dewill.readit.screens.splash.core;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import v77archenko.dewill.readit.R;
import v77archenko.dewill.readit.screens.splash.SplashScreenActivity;

/**
 * Created by dewill on 01.04.2018.
 */

public class SplashView {
	private View view;
	@BindView(R.id.splash_screen_circle) View circle;

	public SplashView(SplashScreenActivity context) {
		FrameLayout parent = new FrameLayout(context);
		parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		view = LayoutInflater.from(context).inflate(R.layout.activity_splash_screen, parent, true);
		ButterKnife.bind(view, context);
	}

	public View constructView() {
		return view;
	}

	public void splashAnim() {
		circle.startAnimation(new AnimationUtils().loadAnimation(constructView().getContext(),
				R.anim.anim_splash_screen));
	}
}
