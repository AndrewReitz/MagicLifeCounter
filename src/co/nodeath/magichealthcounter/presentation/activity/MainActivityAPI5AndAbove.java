package co.nodeath.magichealthcounter.presentation.activity;

import co.nodeath.magichealthcounter.R;
import co.nodeath.magichealthcounter.presentation.controller.MainController;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import org.holoeverywhere.app.Activity;
import org.holoeverywhere.widget.TextView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.RelativeLayout;

/**
 * MainActivity for phones running API 5 (2.0 Eclair) extends @{link
 * org.holoeverywhere.app.Activity} in order to use HoloEveryWhere
 *
 * @author pieces029
 */
public class MainActivityAPI5AndAbove extends Activity {

    private MainController mMainController;
    private RelativeLayout mRelativeLayoutPoisonCounter;
    private TextView mTextViewPlayer1Health;
    private TextView mTextViewPlayer1PoisonCounter;
    private TextView mTextViewPlayer2Health;
    private TextView mTextViewPlayer2PoisonCounter;

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainController = new MainController(this);

        if (mMainController.hasHardwareMenuButton()) {
            getSupportActionBar().hide();
        }

        mRelativeLayoutPoisonCounter = (RelativeLayout) findViewById(
                R.id.main_activity_relative_layout_poison_counter);

        //Text Views
        mTextViewPlayer1Health = (TextView) findViewById(
                R.id.main_activity_textView_player1_health);
        mTextViewPlayer2Health = (TextView) findViewById(
                R.id.main_activity_textView_player2_health);

        mTextViewPlayer1PoisonCounter = (TextView) findViewById(
                R.id.main_activity_textView_player1_poison);
        mTextViewPlayer2PoisonCounter = (TextView) findViewById(
                R.id.main_activity_textView_player2_poison);

        //Health Buttons
        findViewById(R.id.main_activity_button_player1_minus1).setOnClickListener(
                mMainController.getOnClickListener(mTextViewPlayer1Health, -1));
        findViewById(R.id.main_activity_button_player1_minus5).setOnClickListener(
                mMainController.getOnClickListener(mTextViewPlayer1Health, -5));

        findViewById(R.id.main_activity_button_player1_plus1).setOnClickListener(
                mMainController.getOnClickListener(mTextViewPlayer1Health, 1));
        findViewById(R.id.main_activity_button_player1_plus5)
                .setOnClickListener(mMainController.getOnClickListener(mTextViewPlayer1Health, 5));

        findViewById(R.id.main_activity_button_player2_minus1).setOnClickListener(
                mMainController.getOnClickListener(mTextViewPlayer2Health, -1));
        findViewById(R.id.main_activity_button_player2_minus5)
                .setOnClickListener(mMainController.getOnClickListener(mTextViewPlayer2Health, -5));

        findViewById(R.id.main_activity_button_player2_plus1).setOnClickListener(
                mMainController.getOnClickListener(mTextViewPlayer2Health, 1));
        findViewById(R.id.main_activity_button_player2_plus5).setOnClickListener(
                mMainController.getOnClickListener(mTextViewPlayer2Health, 5));

        //Poison Counter Buttons
        findViewById(R.id.main_activity_button_player1_poison_plus).setOnClickListener(
                mMainController.getOnClickListener(mTextViewPlayer1PoisonCounter, 1, true));
        findViewById(R.id.main_activity_button_player1_poison_minus).setOnClickListener(
                mMainController.getOnClickListener(mTextViewPlayer1PoisonCounter, -1, true));

        findViewById(R.id.main_activity_button_player2_poison_plus).setOnClickListener(
                mMainController.getOnClickListener(mTextViewPlayer2PoisonCounter, 1, true));
        findViewById(R.id.main_activity_button_player2_poison_minus).setOnClickListener(
                mMainController.getOnClickListener(mTextViewPlayer2PoisonCounter, -1, true));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_flip_coin:
                mMainController.displayCoinFlipDialog();
                return true;
            case R.id.menu_roll_d20:
                mMainController.displayD20Dialog();
                return true;
            case R.id.menu_reset_life:
                mMainController.reset(mTextViewPlayer1Health, mTextViewPlayer2Health,
                        mTextViewPlayer1PoisonCounter, mTextViewPlayer2PoisonCounter);
                return true;
            case R.id.menu_poison_counter:
                mMainController.showHidePoisonCounter(mRelativeLayoutPoisonCounter);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
