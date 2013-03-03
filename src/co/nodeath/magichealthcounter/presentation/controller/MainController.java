/*
 *      Copyright (C) 2012-2013 Pieces029
 *
 *  This Program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2, or (at your option)
 *  any later version.
 *
 *  This Program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with MagicHealthCounter; see the file license.  If not, write to
 *  the Free Software Foundation, 675 Mass Ave, Cambridge, MA 02139, USA.
 *  http://www.gnu.org/copyleft/gpl.html
 *
 */

package co.nodeath.magichealthcounter.presentation.controller;

import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.ShareActionProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import co.nodeath.magichealthcounter.R;
import co.nodeath.magichealthcounter.presentation.activity.MainActivityAPI5AndAbove;
import co.nodeath.magichealthcounter.presentation.dialog.CoinFlipDialogAPI4Minus;
import co.nodeath.magichealthcounter.presentation.dialog.CoinFlipDialogAPI5Plus;
import co.nodeath.magichealthcounter.presentation.dialog.D20DialogAPI4Minus;
import co.nodeath.magichealthcounter.presentation.dialog.D20DialogAPI5Plus;

/**
 * Controller for main activity
 *
 * @author pieces029
 */
public class MainController extends AbstractActivityController {

    private static final String INSTANT_STATE_PLAYER1 = "player1textView";
    private static final String INSTANT_STATE_PLAYER2 = "player2textView";

    public MainController(FragmentActivity activity) {
        super.onCreate(activity);
    }

    public void restoreInstanceState(final Bundle savedState, final TextView player1TextView,
            final TextView player2TextView) {

        if (savedState != null) {
            player1TextView.setText(savedState.getString(INSTANT_STATE_PLAYER1));
            player2TextView.setText(savedState.getString(INSTANT_STATE_PLAYER2));
        }
    }

    public void saveInstanceState(final Bundle outState, final TextView player1TextView,
            final TextView player2TextView) {

        outState.putString(INSTANT_STATE_PLAYER1, player1TextView.getText().toString());
        outState.putString(INSTANT_STATE_PLAYER2, player2TextView.getText().toString());
    }

    public View.OnClickListener getOnClickListener(final TextView textView, final int value) {
        return getOnClickListener(textView, value, false);
    }

    public View.OnClickListener getOnClickListener(final TextView textView, final int value,
            final boolean poisonCounter) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                setTextViewValue(textView, value, poisonCounter);
            }
        };
    }

    public void connectShareActionProvider(MenuItem menuItem) {
        ShareActionProvider mShareActionProvider = (ShareActionProvider) menuItem
                .getActionProvider();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");

        shareIntent.putExtra(Intent.EXTRA_TEXT, mActivity.getString(R.string.share_text));
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, mActivity.getString(R.string.share_subject));
        mShareActionProvider.setShareIntent(shareIntent);
    }

    /**
     * Resets the text views to there default color and resets their values
     *
     * @param player1TextView player ones health text view
     * @param player2TextView player twos health text view
     * @param poison1TextView player ones poison counter text view
     * @param poison2TextView player twos poison counter text view
     */
    public void reset(final TextView player1TextView, final TextView player2TextView,
            final TextView poison1TextView, final TextView poison2TextView) {
        resetHealthCounter(player1TextView);
        resetHealthCounter(player2TextView);

        resetPoisonCounter(poison1TextView);
        resetPoisonCounter(poison2TextView);
    }

    public void displayCoinFlipDialog() {
        android.support.v4.app.FragmentManager fm = mActivity.getSupportFragmentManager();

        if (isApiGreaterThan4()) {
            CoinFlipDialogAPI5Plus cfd = new CoinFlipDialogAPI5Plus();
            cfd.show(fm);
        } else {
            CoinFlipDialogAPI4Minus cfd = new CoinFlipDialogAPI4Minus();
            cfd.show(fm, "coin_flip_dialog");
        }
    }

    public void displayD20Dialog() {
        android.support.v4.app.FragmentManager fm = mActivity.getSupportFragmentManager();

        if (isApiGreaterThan4()) {
            D20DialogAPI5Plus d2d = new D20DialogAPI5Plus();
            d2d.show(fm);
        } else {
            D20DialogAPI4Minus d2d = new D20DialogAPI4Minus();
            d2d.show(fm, "d20_dialog");
        }
    }

    public void showHidePoisonCounter(final View layout) {
        if (layout.getVisibility() == View.VISIBLE) {
            layout.setVisibility(View.GONE);
        } else {
            layout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * checks the sdk version if it's greater than 4 the activity switches to
     * MainActivityAPI5AndAbove
     */
    public void checkSdkVersion() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.DONUT) {
            Intent intent = new Intent(mActivity.getApplicationContext(),
                    MainActivityAPI5AndAbove.class);
            mActivity.startActivity(intent);
            mActivity.finish();
        }
    }

    private boolean isApiGreaterThan4() {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.DONUT;
    }

    private void resetHealthCounter(final TextView healthTextView) {
        resetTextView(healthTextView, "20");
    }

    private void resetPoisonCounter(final TextView poisonTextView) {
        resetTextView(poisonTextView, "0");
    }

    private void resetTextView(final TextView textView, final String value) {
        textView.setText(value);
        textView.setBackgroundColor(Color.TRANSPARENT);
    }

    private void setTextViewValue(final TextView textView, final int value,
            final boolean poisonCounter) {

        int currentVal = Integer.parseInt(textView.getText().toString())
                + value;

        textView.setText(String.valueOf(currentVal));

        if (poisonCounter) {
            if (currentVal >= 10) {
                textView.setBackgroundColor(Color.RED);
            } else {
                textView.setBackgroundColor(Color.TRANSPARENT);
            }
        } else {
            if (currentVal <= 0) {
                textView.setBackgroundColor(Color.RED);
            } else {
                textView.setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }
}
