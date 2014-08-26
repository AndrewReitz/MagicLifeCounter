package co.nodeath.magichealthcounter.ui;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.inkapplications.preferences.StringPreference;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import co.nodeath.magichealthcounter.MagicLifeCounterApp;
import co.nodeath.magichealthcounter.R;
import co.nodeath.magichealthcounter.ui.event.ActionBarTitleEvent;

public class SettingsFragment extends PreferenceFragment {

  @Inject Bus bus;
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
    bus.post(new ActionBarTitleEvent(getString(R.string.settings)));
    findPreference(getString(R.string.pref_key_version)).setSummary(versionName.get());
    findPreference("use_dark_theme").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
      @Override public boolean onPreferenceChange(Preference preference, Object newValue) {
        getActivity().recreate();
        return true;
      }
    });
  }
}
