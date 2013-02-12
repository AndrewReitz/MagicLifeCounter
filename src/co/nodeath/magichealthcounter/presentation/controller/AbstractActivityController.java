package co.nodeath.magichealthcounter.presentation.controller;

import android.support.v4.app.FragmentActivity;

/**
 * All activity controllers should extend this
 *
 * @author pieces029
 */
public abstract class AbstractActivityController {

    protected FragmentActivity mActivity;

    /**
     * Call this in order to save the activity this is here instead of in the constructor because not
     * all controllers will need to save the activity
     *
     * @param activity activity to save
     */
    public void onCreate(final FragmentActivity activity) {
        mActivity = activity;
    }
}
