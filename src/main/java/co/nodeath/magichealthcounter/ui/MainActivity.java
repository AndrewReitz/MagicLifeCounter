package co.nodeath.magichealthcounter.ui;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.collect.Maps;
import com.inkapplications.preferences.BooleanPreference;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import co.nodeath.magichealthcounter.MagicLifeCounterApp;
import co.nodeath.magichealthcounter.R;
import co.nodeath.magichealthcounter.data.SeenNavDrawer;
import co.nodeath.magichealthcounter.ui.misc.BaseActivity;
import hugo.weaving.DebugLog;
import icepick.Icicle;

import static android.widget.Toast.LENGTH_LONG;
import static butterknife.ButterKnife.findById;

public class MainActivity extends BaseActivity implements View.OnTouchListener {

  @Inject AppContainer appContainer;
  @Inject @SeenNavDrawer BooleanPreference seenNavDrawer;
  @Inject Bus bus;

  @InjectView(R.id.nav_drawer_layout) DrawerLayout drawerLayout;
  @InjectView(R.id.navigation_drawer) ViewGroup navdrawerContainer;
  @InjectView(R.id.nav_drawer_about) TextView about;
  @InjectView(R.id.nav_drawer_casual) TextView casual;
  @InjectView(R.id.nav_drawer_multi_player) TextView multiplayer;
  @InjectView(R.id.nav_drawer_tournament) TextView tournament;

  @Icicle Class<? extends Fragment> currentFragment;

  private GestureDetector gestureDetector;
  private ActionBarDrawerToggle drawerToggle;
  private Map<Class<? extends Fragment>, Fragment> fragments = Maps.newHashMap();

  private String actionbarTitle = "";

  @OnClick(R.id.nav_drawer_casual) void casualClick() {
    navigateToFragment(CasualFragment.class);
    drawerLayout.closeDrawers();
  }

  @OnClick(R.id.nav_drawer_tournament) void tournamentClick() {
    navigateToFragment(TournamentFragment.class);
    drawerLayout.closeDrawers();
  }

  @OnClick(R.id.nav_drawer_about) void aboutClick() {
    navigateToFragment(CasualFragment.class);
    drawerLayout.closeDrawers();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    MagicLifeCounterApp app = MagicLifeCounterApp.get(this);
    ViewGroup container = appContainer.get(this, app);
    getLayoutInflater().inflate(R.layout.activity_main, container);

    ViewGroup drawer = findById(this, R.id.navigation_drawer);
    LayoutInflater.from(this).inflate(R.layout.drawer_layout, drawer);
    ButterKnife.inject(this);
    MagicLifeCounterApp.get(this).inject(this);

    setupNavigationDrawer();

    fragments.put(CasualFragment.class, CasualFragment.newInstance());
    fragments.put(TournamentFragment.class, TournamentFragment.newInstance());

    if (savedInstanceState == null) {
      navigateToFragment(CasualFragment.class);
    }

    gestureDetector = new GestureDetector(this, new PullDownGestureDetector());
    container.setOnTouchListener(this);
  }

  @Override protected void onResume() {
    super.onResume();
    bus.register(this);
  }

  @Override protected void onPause() {
    super.onPause();
    bus.unregister(this);
  }

  @Override public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    drawerToggle.onConfigurationChanged(newConfig);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // Pass the event to ActionBarDrawerToggle, if it returns
    // true, then it has handled the app icon touch event
    if (drawerToggle.onOptionsItemSelected(item)) {
      return true;
    }
    // Handle your other action bar items...

    return super.onOptionsItemSelected(item);
  }

  @Override protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    // Sync the toggle state after onRestoreInstanceState has occurred.
    drawerToggle.syncState();
  }

  @DebugLog
  @Override public boolean onTouch(View v, MotionEvent event) {
    gestureDetector.onTouchEvent(event);
    return false;
  }

  @Subscribe public void onActionbarTitleChange(ActionBarTitleEvent event) {
    actionbarTitle = event.title;
    //noinspection ConstantConditions
    getActionBar().setTitle(actionbarTitle);
  }

  private void navigateToFragment(Class<? extends Fragment> fragmentClass) {
    if (fragmentClass.equals(currentFragment)) return;

    currentFragment = fragmentClass;
    getFragmentManager().beginTransaction()
        .replace(R.id.content_frame, fragments.get(fragmentClass))
        .commit();
  }

  private void setupNavigationDrawer() {
    final ActionBar actionBar = getActionBar();

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
        //noinspection ConstantConditions
        actionBar.setTitle(actionbarTitle);
        invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
      }

      /** Called when a drawer has settled in a completely open state. */
      public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
        //noinspection ConstantConditions
        actionBar.setTitle(getString(R.string.app_name));
        invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
      }
    };

    drawerLayout.setDrawerListener(drawerToggle);
    drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);

    //noinspection ConstantConditions
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setHomeButtonEnabled(true);

    // Display nav drawer if it's the first time the app is opened per Google's guidelines
    if (!seenNavDrawer.get()) {
      drawerLayout.postDelayed(new Runnable() {
        @Override public void run() {
          drawerLayout.openDrawer(Gravity.START);
          Toast.makeText(MainActivity.this, R.string.drawer_intro_text, LENGTH_LONG).show();
        }
      }, TimeUnit.MILLISECONDS.toMillis(500)); /* Half a second, but there's gotta be a better way*/
      seenNavDrawer.set(true);
    }
  }

  static class PullDownGestureDetector extends GestureDetector.SimpleOnGestureListener {
    @DebugLog
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
      return super.onFling(e1, e2, velocityX, velocityY);
    }

    @DebugLog
    @Override public boolean onDown(MotionEvent e) {
      return super.onDown(e);
    }

    @DebugLog
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
      return super.onScroll(e1, e2, distanceX, distanceY);
    }
  }
}
