package com.example.ayodejifabusuyi.tictactoe;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    GridView gridView;
    int userXscore = 0;
    int userOscore = 0;
    TextView scoreX,scoreO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view);

        scoreX = findViewById(R.id.scoreX);
        scoreO = findViewById(R.id.scoreO);
        gridView = findViewById(R.id.gridView);

        final ArrayList<Tic> tics = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            tics.add(new Tic());
        }

        TicAdapter adapter = new TicAdapter(this, tics);
        gridView.setAdapter(adapter);

        final ArrayList<Integer> playedPositions = new ArrayList<>();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            LinearLayout gridItem;
            TextView ticTextView;
            int status = 1;

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                playedPositions.add(position);
                gridItem = (LinearLayout) gridView.getChildAt(position);
                ticTextView = (TextView) gridItem.getChildAt(0);
                if (ticTextView.getText().toString().isEmpty()) {
                    if (status % 2 != 0) {
                        ticTextView.setText("X");
                    } else {
                        ticTextView.setText("O");
                    }
                    status += 1;
                } else {
                    Toast.makeText(GameActivity.this, "You can't play here", Toast.LENGTH_SHORT).show();
                }
                scoreGame();
            }

            public TextView getIndividual(int position) {
                gridItem = (LinearLayout) gridView.getChildAt(position);
                ticTextView = (TextView) gridItem.getChildAt(0);
                return ticTextView;
            }

            public void scoreGame() {
                ArrayList<String> markables = getMarkables();
                int status;
                for (String s : markables) {
                    status = 0;
                    String[] arrStr = s.split("");
                    int[] arr = new int[arrStr.length - 1];
                    int[] positionArr = new int[arr.length];
                    for (int i = 1; i < arrStr.length; i++) {
                        arr[i - 1] = Integer.parseInt(arrStr[i]);
                    }
                    for (int i = 0; i < arr.length; i++) {
                        positionArr[i] = arr[i] - 1;
                    }
                    for (int i = 0; i < positionArr.length; i++) {
                        TextView aView = getIndividual(positionArr[i]);
                        TextView bView = null;
                        if (i + 1 < arr.length) {
                            bView = getIndividual(positionArr[i + 1]);
                            if ((!aView.getText().toString().isEmpty()) && (!bView.getText().toString().isEmpty())) {
                                if (aView.getText().toString().equals(bView.getText().toString())) {
                                    status += 1;
                                }
                            }
                        }

                    }
                    if (status == 2) {
                        gridView.setClickable(false);
                        TextView toShade = new TextView(GameActivity.this);
                        for (int i : positionArr) {
                            toShade = getIndividual(i);
                            toShade.setBackgroundResource(R.drawable.winbackground);
                        }
                        String winningUser = toShade.getText().toString();
                        Toast.makeText(GameActivity.this, "User " + winningUser + " wins", Toast.LENGTH_SHORT).show();
                        increaseScore(winningUser);
                        new Handler().postDelayed(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        clearViews();
//                                        gridView.setFocusable(true);
                                        gridView.setClickable(true);
                                    }
                                }
                                , 1000
                        );

                    }

                }
            }
        });
    }


    public void clearViews() {
        LinearLayout gridItem;
        TextView ticTextView;
        for (int i = 0; i < gridView.getChildCount(); i++) {
            gridItem = (LinearLayout) gridView.getChildAt(i);
            ticTextView = (TextView) gridItem.getChildAt(0);
            ticTextView.setText("");
            ticTextView.setBackgroundResource(R.drawable.strokes);
        }
    }

    public void resetGame(View view){
        clearViews();
        userOscore = 0;
        userXscore = 0;
        scoreO.setText("0");
        scoreX.setText("0");
    }

    public ArrayList<String> getMarkables() {
        int[][] ticIndexes = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        //replace 3 in the method below with the size of the grid
        ArrayList<String> markableLines = new ArrayList<>();
        String row = "", column = "", leftToR = "", rightToL = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                row += Integer.toString(ticIndexes[i][j]);
                column += Integer.toString(ticIndexes[j][i]);
            }
            markableLines.add(row);
            markableLines.add(column);
            row = "";
            column = "";
        }

        for (int i = 0; i < 3; i++) {
            int j = ((3) - 1) - i;
            leftToR += Integer.toString(ticIndexes[i][i]);
            rightToL += Integer.toString(ticIndexes[i][j]);
        }
        markableLines.add(leftToR);
        markableLines.add(rightToL);
        return markableLines;
    }

    public void increaseScore(String user){
        if(user.equals("X")){
            userXscore+=1;
            scoreX.setText(String.valueOf(userXscore));
        }else if(user.equals("O")){
            userOscore+=1;
            scoreO.setText(String.valueOf(userOscore));
        }
    }

}
