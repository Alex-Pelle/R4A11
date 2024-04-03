package com.example.aqw.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.aqw.R;
import com.example.aqw.modele.Seance;
import com.example.aqw.ui.activity.ExercisesActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExerciseSelectionSeanceAdapter  extends ArrayAdapter<Map.Entry<String,String>> {
    private static final String TAG = ExerciseSelectionSeanceAdapter.class.getSimpleName();
    private List<Map.Entry<String,String>> exercices;
    private LayoutInflater layout;
    private Context context;

    public ExerciseSelectionSeanceAdapter(Context context, int ressource , Map<String,String> mapExercices) {
        super(context,ressource,new ArrayList<>(mapExercices.entrySet()));
        this.exercices = new ArrayList<>(mapExercices.entrySet());
        this.context=context;
        this.layout = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {final View result;

        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_item, parent, false);
        } else {
            result = convertView;
        }

        Map.Entry<String, String> item = getItem(position);

        ((TextView) result.findViewById(R.id.nomExo)).setText(item.getKey());
        ((TextView) result.findViewById(R.id.muscle)).setText(item.getValue());

        return result;
    }

    @Override
    public Map.Entry<String,String> getItem(int i) {
        return this.exercices.get(i);
    }

    static class ViewHolder {
        TextView textView;
    }
}
