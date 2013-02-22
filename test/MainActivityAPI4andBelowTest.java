import com.xtremelabs.robolectric.RobolectricTestRunner;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import co.nodeath.magichealthcounter.R;
import co.nodeath.magichealthcounter.presentation.activity.MainActivityAPI4AndBelow;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


/**
 * Tests MainActivityAPI4andBelowTest, due to issues with API 17 and RobolectricTestRunner this
 * needs to be run with API 16
 *
 * @author pieces029
 */
@RunWith(RobolectricTestRunner.class)
public class MainActivityAPI4andBelowTest {

    @Mock
    private Bundle mBundle;

    private MainActivityAPI4AndBelow mActivity;
    private TextView mTextViewPlayer1;
    private TextView mTextViewPlayer2;
    private Button mButtonPlayer1Plus1;
    private Button mButtonPlayer1Plus5;
    private Button mButtonPlayer2Plus1;
    private Button mButtonPlayer2Plus5;
    private Button mButtonPlayer1Minus1;
    private Button mButtonPlayer1Minus5;
    private Button mButtonPlayer2Minus1;
    private Button mButtonPlayer2Minus5;

    //Runs before each test
    @Before
    public void setUp() throws Exception {
        initMocks(this);

        mActivity = new MainActivityAPI4AndBelow();
        mActivity.onCreate(null);

        mTextViewPlayer1 = (TextView) mActivity
                .findViewById(R.id.main_activity_textView_player1_health);
        mTextViewPlayer2 = (TextView) mActivity
                .findViewById(R.id.main_activity_textView_player2_health);
        mButtonPlayer1Plus1 = (Button) mActivity
                .findViewById(R.id.main_activity_button_player1_plus1);
        mButtonPlayer2Plus1 = (Button) mActivity
                .findViewById(R.id.main_activity_button_player2_plus1);
        mButtonPlayer1Plus5 = (Button) mActivity
                .findViewById(R.id.main_activity_button_player1_plus5);
        mButtonPlayer2Plus5 = (Button) mActivity
                .findViewById(R.id.main_activity_button_player2_plus5);
        mButtonPlayer1Minus1 = (Button) mActivity
                .findViewById(R.id.main_activity_button_player1_minus1);
        mButtonPlayer2Minus1 = (Button) mActivity
                .findViewById(R.id.main_activity_button_player2_minus1);
        mButtonPlayer1Minus5 = (Button) mActivity
                .findViewById(R.id.main_activity_button_player1_minus5);
        mButtonPlayer2Minus5 = (Button) mActivity
                .findViewById(R.id.main_activity_button_player2_minus5);
    }

    @Test
    public void shouldStartAt20() throws Exception {
        String expected = "20";

        assertThat(mTextViewPlayer1.getText().toString(), equalTo(expected));
        assertThat(mTextViewPlayer2.getText().toString(), equalTo(expected));
    }

    @Test
    public void shouldIncreaseTextBy5() throws Exception {
        String expected = "25";

        mButtonPlayer1Plus5.performClick();
        assertThat(mTextViewPlayer1.getText().toString(), equalTo(expected));

        mButtonPlayer2Plus5.performClick();
        assertThat(mTextViewPlayer2.getText().toString(), equalTo(expected));
    }

    @Test
    public void shouldDecreaseTextBy5() throws Exception {
        String expected = "15";

        mButtonPlayer1Minus5.performClick();
        assertThat(mTextViewPlayer1.getText().toString(), equalTo(expected));

        mButtonPlayer2Minus5.performClick();
        assertThat(mTextViewPlayer2.getText().toString(), equalTo(expected));
    }

    @Test
    public void shouldIncreateTextBy1() throws Exception {
        String expected = "21";

        mButtonPlayer1Plus1.performClick();
        assertThat(mTextViewPlayer1.getText().toString(), equalTo(expected));

        mButtonPlayer2Plus1.performClick();
        assertThat(mTextViewPlayer2.getText().toString(), equalTo(expected));
    }

    @Test
    public void shouldDecreaseTextBy1() throws Exception {
        String expected = "19";

        mButtonPlayer1Minus1.performClick();
        assertThat(mTextViewPlayer1.getText().toString(), equalTo(expected));

        mButtonPlayer2Minus1.performClick();
        assertThat(mTextViewPlayer2.getText().toString(), equalTo(expected));
    }

    @Test
    public void shouldSaveTextViewTextToBundle() throws Exception {
        String expected = "25";

        mTextViewPlayer1.setText(expected);
        mTextViewPlayer2.setText(expected);

        mActivity.onSaveInstanceState(mBundle);

        verify(mBundle, atLeastOnce()).putString("player1textView", expected);
        verify(mBundle, atLeastOnce()).putString("player2textView", expected);
    }

    @Test
    public void shouldSetTextViewsToTextFromBundle() throws Exception {
        String expected = "25";

        when(mBundle.getString("player1textView")).thenReturn(expected);
        when(mBundle.getString("player2textView")).thenReturn(expected);

        MainActivityAPI4AndBelow activity = new MainActivityAPI4AndBelow();
        activity.onCreate(mBundle);

        TextView textView1 = (TextView)activity.findViewById(R.id.main_activity_textView_player1_health);
        assertThat(textView1.getText().toString(), equalTo(expected));

        TextView textView2 = (TextView)activity.findViewById(R.id.main_activity_textView_player2_health);
        assertThat(textView2.getText().toString(), equalTo(expected));
    }
}
