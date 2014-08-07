package co.nodeath.magichealthcounter.ui;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import co.nodeath.magichealthcounter.R;
import co.nodeath.magichealthcounter.ui.event.UpdateScoreEvent;
import co.nodeath.magichealthcounter.ui.misc.BindableAdapter;

public class ScoreTrackerAdapter extends BindableAdapter<UpdateScoreEvent> {

  private List<UpdateScoreEvent> scores = new LinkedList<>();

  @Inject public ScoreTrackerAdapter(Application context) {
    super(context);
  }

  public void addScoreEvent(UpdateScoreEvent event) {
    scores.add(event);
    notifyDataSetChanged();
  }

  public void clear() {
    scores = new LinkedList<>();
    notifyDataSetChanged();
  }

  @Override public int getCount() {
    return scores.size();
  }

  @Override public UpdateScoreEvent getItem(int position) {
    return scores.get(position);
  }

  @Override public long getItemId(int position) {
    return 0;
  }

  @Override public View newView(LayoutInflater inflater, int position, ViewGroup container) {
    return inflater.inflate(R.layout.view_score_history, container, false);
  }

  @Override public void bindView(UpdateScoreEvent item, int position, View view) {
    ((ScoreItemView) view).bindTo(item.toString());
  }
}
