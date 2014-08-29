package co.nodeath.magichealthcounter.ui.misc;

import android.app.Fragment;
import android.os.Bundle;

import butterknife.ButterKnife;
import hugo.weaving.DebugLog;
import icepick.Icepick;

public abstract class BaseFragment extends Fragment {

  @DebugLog
  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    // Restore objects saved with Icepick
    Icepick.restoreInstanceState(this, savedInstanceState);
  }

  @DebugLog
  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    // Save out objects annotated with @Icicle
    Icepick.saveInstanceState(this, outState);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();

    // Release the views injected by butterknife
    ButterKnife.reset(this);
  }
}
