package co.nodeath.magichealthcounter.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import co.nodeath.magichealthcounter.R;
import co.nodeath.magichealthcounter.ui.event.UpdateScoreEvent;

public class ScoreItemView extends FrameLayout {
  @InjectView(R.id.score_item_view_who) TextView who;
  @InjectView(R.id.score_item_view_value) TextView value;
  @InjectView(R.id.score_item_view_time) TextView time;

  public ScoreItemView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.inject(this);
  }

  public void bindTo(UpdateScoreEvent scoreEvent) {
    who.setText(scoreEvent.who.toString());
    value.setText(scoreEvent.value.toString());
    time.setText(scoreEvent.getFormattedTime());
  }
}
