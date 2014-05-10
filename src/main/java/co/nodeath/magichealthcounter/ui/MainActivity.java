package co.nodeath.magichealthcounter.ui;

import android.app.ActionBar;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import co.nodeath.magichealthcounter.MagicLifeCounterApp;
import co.nodeath.magichealthcounter.R;
import co.nodeath.magichealthcounter.ui.misc.BaseActivity;

import static butterknife.ButterKnife.findById;

public class MainActivity extends BaseActivity {

  @Inject AppContainer appContainer;

  @InjectView(R.id.nav_drawer_layout) DrawerLayout drawerLayout;
  @InjectView(R.id.navigation_drawer) ViewGroup navdrawerContainer;
  @InjectView(R.id.nav_drawer_about) TextView about;
  @InjectView(R.id.nav_drawer_casual) TextView casual;
  @InjectView(R.id.nav_drawer_multi_player) TextView multiplayer;
  @InjectView(R.id.nav_drawer_tournament) TextView tournament;

  private ActionBarDrawerToggle drawerToggle;
  private ViewGroup container;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    MagicLifeCounterApp app = MagicLifeCounterApp.get(this);

    container = appContainer.get(this, app);

    getLayoutInflater().inflate(R.layout.activity_main, container);

    ViewGroup drawer = findById(this, R.id.navigation_drawer);
    LayoutInflater.from(this).inflate(R.layout.drawer_layout, drawer);
    ButterKnife.inject(this);

    final ActionBar actionBar = getActionBar();
    if (actionBar == null) return; // should never happen but shuts lint up

    drawerToggle = new ActionBarDrawerToggle(
        this,
        drawerLayout,
        R.drawable.ic_drawer,
        R.string.navigation_drawer_open,
        R.string.navigation_drawer_close
    ) {

      /** Called when a drawer has settled in a completely closed state. */
      public void onDrawerClosed(View view) {
        super.onDrawerClosed(view);
        actionBar.setTitle(getString(R.string.app_name));
        invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
      }

      /** Called when a drawer has settled in a completely open state. */
      public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
        actionBar.setTitle("TEST2");
        invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
      }
    };

    drawerLayout.setDrawerListener(drawerToggle);
    drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);

    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setHomeButtonEnabled(true);
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    drawerToggle.onConfigurationChanged(newConfig);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Pass the event to ActionBarDrawerToggle, if it returns
    // true, then it has handled the app icon touch event
    if (drawerToggle.onOptionsItemSelected(item)) {
      return true;
    }
    // Handle your other action bar items...

    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    // Sync the toggle state after onRestoreInstanceState has occurred.
    drawerToggle.syncState();
  }
}
