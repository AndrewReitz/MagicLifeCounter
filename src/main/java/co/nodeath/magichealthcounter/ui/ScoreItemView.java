package co.nodeath.magichealthcounter.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import co.nodeath.magichealthcounter.R;

public class ScoreItemView extends FrameLayout {
  @InjectView(R.id.score_item_view) TextView textView;

  public ScoreItemView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.inject(this);
  }

  public void bindTo(String text) {
    //textView.setText(text);
  }
}
