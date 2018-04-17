package com.example.ayodejifabusuyi.tictactoe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Ayodeji Fabusuyi on 27/12/2017.
 */

public class TicAdapter extends ArrayAdapter<Tic> {


    public TicAdapter(Context context, ArrayList<Tic> tics) {
        super(context, 0, tics);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View gridItemView = convertView;
        if (gridItemView == null) {
            gridItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.grid_item, parent, false);
        }
        return gridItemView;
    }
}
