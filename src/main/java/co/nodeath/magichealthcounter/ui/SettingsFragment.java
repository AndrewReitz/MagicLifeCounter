package co.nodeath.magichealthcounter.ui;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.inkapplications.preferences.StringPreference;

import javax.inject.Inject;

import co.nodeath.magichealthcounter.MagicLifeCounterApp;
import co.nodeath.magichealthcounter.R;

public class SettingsFragment extends PreferenceFragment {

  @Inject @VersionName StringPreference versionName;

  public static SettingsFragment newInstance() {
    return new SettingsFragment();
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    MagicLifeCounterApp.get(getActivity()).inject(this);
    addPreferencesFromResource(R.xml.preferences);
  }

  @Override public void onResume() {
    super.onResume();

    findPreference(getString(R.string.pref_key_version)).setSummary(versionName.get());
  }
}
