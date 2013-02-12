package co.nodeath.magichealthcounter.presentation.controller;

import android.support.v4.app.Fragment;

/**
 * All fragment controllers should extend this class
 *
 * @author pieces029
 */
public class AbstractFragmentController {

    protected Fragment mFragment;

    /**
     * Call this in order to save the fragment this is here instead of in the constructor because
     * not all controllers will need to save the fragment
     *
     * @param fragment fragment to save
     */
    public void onCreate(final Fragment fragment) {
        mFragment = fragment;
    }
}
