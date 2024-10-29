package com.example.kosci;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final ImageView[] ivDices = new ImageView[5];
    private TextView tvScore, tvTotal;
    private int total = 0;

    private final int[] diceImages = {R.drawable.pytajnik, R.drawable.kosc1, R.drawable.kosc2, R.drawable.kosc3, R.drawable.kosc4, R.drawable.kosc5, R.drawable.kosc6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnDice = findViewById(R.id.btnDice);
        Button btnReset = findViewById(R.id.btnReset);
        tvScore = findViewById(R.id.tvScore);
        tvTotal = findViewById(R.id.tvTotal);
        ivDices[0] = findViewById(R.id.ivDice1);
        ivDices[1] = findViewById(R.id.ivDice2);
        ivDices[2] = findViewById(R.id.ivDice3);
        ivDices[3] = findViewById(R.id.ivDice4);
        ivDices[4] = findViewById(R.id.ivDice5);

        btnDice.setOnClickListener(this::handleDice);
        btnReset.setOnClickListener(this::handleReset);
    }

    private int sumUpPoints(int[] dices){
        int[] points = {0, 0, 0, 0, 0, 0};
        for (int dice : dices) points[dice - 1]++;
        int result = 0;
        for(int i = 0; i < points.length; i++)
            if(points[i] > 1)
                result += (i+1) * points[i];
        return result;
    }

    private int[] getDices(){
        int[] dices = {0, 0, 0, 0, 0};
        Random random = new Random();
        for(int i = 0; i < 5; i++)
            dices[i]  = 1 + random.nextInt(6);
        return dices;
    }

    public void handleDice(View view){
        int[] dices = getDices();
        for(int i = 0; i < ivDices.length; i++)
            ivDices[i].setImageResource(diceImages[dices[i]]);
        int score = sumUpPoints(dices);
        total += score;
        tvTotal.setText("Wynik gry: " + total);
        tvScore.setText("Wynik tego losowania: " + score);
    }

    public void handleReset(View view){
        total = 0;
        tvTotal.setText("Wynik gry: 0");
        tvScore.setText("Wynik tego losowania: 0");
        for (ImageView ivDice : ivDices) ivDice.setImageResource(R.drawable.pytajnik);
    }
}