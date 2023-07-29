package com.example.tictactoegame;


import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private int XWinCount = 0, OWinCount = 0, drawnGameCount = 0;
    private TextView winnerTextView, winnerNumberTextView, chanceTextView, drawnGameTextView;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, restartBtn;
    String valBtn1, valBtn2, valBtn3, valBtn4, valBtn5, valBtn6, valBtn7, valBtn8, valBtn9;
    private boolean gameCompleted = false, isXChance = true;
    int flag = 0, count = 0;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        declareId();
        updateChanceText();
    }
    private void declareId() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        restartBtn = findViewById(R.id.restartGame);
        winnerTextView = findViewById(R.id.winnerTextView);
        winnerNumberTextView = findViewById(R.id.winnerNumberTextView);
        chanceTextView = findViewById(R.id.chanceTextView);
        drawnGameTextView = findViewById(R.id.DrawnTextView);
        updateDrawnGameText();
    }
    public void Implementation(View view) {
        Button currentBtn = (Button) view;
        if (currentBtn.getText().toString().equals("")) {
            count++;
            if (flag == 0) {
                currentBtn.setText("X");
                flag = 1;
            } else {
                currentBtn.setText("O");
                flag = 0;
            }
            if (count > 4) {
                valBtn1 = btn1.getText().toString();
                valBtn2 = btn2.getText().toString();
                valBtn3 = btn3.getText().toString();
                valBtn4 = btn4.getText().toString();
                valBtn5 = btn5.getText().toString();
                valBtn6 = btn6.getText().toString();
                valBtn7 = btn7.getText().toString();
                valBtn8 = btn8.getText().toString();
                valBtn9 = btn9.getText().toString();
                gameCompleted = true;
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (gameCompleted) {
                            restartGame(restartBtn);
                        }
                    }
                }, 30000);

                checkForWinner(currentBtn);
            }
            isXChance = !isXChance;
            updateChanceText();
        }
    }
    private void checkForWinner(Button currentBtn) {
        String winner = "";
        if (checkThree(currentBtn, btn1, btn2, btn3) ||
                checkThree(currentBtn, btn4, btn5, btn6) ||
                checkThree(currentBtn, btn7, btn8, btn9)) {
            winner = currentBtn.getText().toString();
        } else if (checkThree(currentBtn, btn1, btn4, btn7) ||
                checkThree(currentBtn, btn2, btn5, btn8) ||
                checkThree(currentBtn, btn3, btn6, btn9)) {
            winner = currentBtn.getText().toString();
        } else if (checkThree(currentBtn, btn1, btn5, btn9) ||
                checkThree(currentBtn, btn3, btn5, btn7)) {
            winner = currentBtn.getText().toString();
        }
        if (!winner.isEmpty()) {
            Toast.makeText(this, "Winner is: " + winner + "\uD83E\uDD47\uD83E\uDD47\uD83E\uDD47\uD83E\uDD47\uD83E\uDD47", Toast.LENGTH_SHORT).show();
            updateWinnerText(winner);
            restartGame(restartBtn);
        } else if (count == 9) {
            Toast.makeText(this, "Game is drawn \uD83E\uDD79\uD83E\uDD79\uD83E\uDD79\uD83E\uDD79\uD83E\uDD79 ", Toast.LENGTH_SHORT).show();
            drawnGameCount++;
            updateDrawnGameText();
            restartGame(restartBtn);
        }
    }
    private boolean checkThree(Button currentBtn, Button btn1, Button btn2, Button btn3) {
        String text1 = btn1.getText().toString();
        String text2 = btn2.getText().toString();
        String text3 = btn3.getText().toString();
        String currentText = currentBtn.getText().toString();
        return currentText.equals(text1) && currentText.equals(text2) && currentText.equals(text3);
    }
    public void restartGame(View view) {
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        count = 0;
        flag = 1;
        gameCompleted = false;
        Toast.makeText(this, "NEW GAME IS START \uD83E\uDD73\uD83E\uDD73\uD83E\uDD73\uD83E\uDD73\uD83E\uDD73", Toast.LENGTH_SHORT).show();
    }
    private void updateWinnerText(String winner) {
        if (winner.equalsIgnoreCase("X")) {
            XWinCount++;
        } else if (winner.equalsIgnoreCase("O")) {
            OWinCount++;
        }
        winnerTextView.setText("Winner: " + winner);
        winnerNumberTextView.setText("X: " + XWinCount + " | O: " + OWinCount);
    }
    private void updateChanceText() {
        if (isXChance) {
            chanceTextView.setText("Now X's Chance");
        } else {
            chanceTextView.setText("Now O's Chance");
        }
    }
    private void updateDrawnGameText() {
        drawnGameTextView.setText("Drawn Games: " + drawnGameCount);
    }
}