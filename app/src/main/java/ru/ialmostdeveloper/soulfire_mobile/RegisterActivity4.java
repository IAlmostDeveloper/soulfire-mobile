package ru.ialmostdeveloper.soulfire_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RegisterActivity4 extends AppCompatActivity {

    private final String[] options = {"Я непривлекателен", "Мне не везет", "Я скучен", "Мое мнение не имеет значения"};
    private final ArrayList<String> selectedOptions = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register4);

        SharedPreferences sprefs = this.getSharedPreferences("ru.ialmostdeveloper.soulfire_mobile", Context.MODE_PRIVATE);
        LinearLayout card_layout = findViewById(R.id.card_layout);
        for (int i = 0; i < options.length; i++) {
            CardView cardView = new CardView(this);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // CardView width
                    LinearLayout.LayoutParams.WRAP_CONTENT // CardView height
            );
            final int j = i;
            layoutParams.setMargins(20, 20, 20, 20);
            cardView.setLayoutParams(layoutParams);
            cardView.setRadius(16F);

            cardView.setContentPadding(25, 25, 25, 25);
            cardView.setCardBackgroundColor(Color.LTGRAY);
            cardView.setCardElevation(8F);
            cardView.setMaxCardElevation(12F);
            cardView.setOnClickListener(v -> {
                if (!selectedOptions.contains(options[j])) {
                    cardView.setBackground(getResources().getDrawable(R.drawable.card_shape_selected));
                    Toast.makeText(
                            this.getApplicationContext(),
                            options[j],
                            Toast.LENGTH_SHORT).show();
                    selectedOptions.add(options[j]);
                } else {
                    selectedOptions.remove(options[j]);
                    cardView.setBackgroundResource(0);
                }
            });

            TextView text = new TextView(this);
            text.setText(options[j]);
            cardView.addView(text);
            card_layout.addView(cardView);
        }
        Button welcome_btn = findViewById(R.id.welcome_btn);

        welcome_btn.setOnClickListener(v -> {
            Set<String> optionsSet = new HashSet<String>(selectedOptions);
            sprefs.edit().putStringSet("userDeepThoughts", optionsSet).apply();
            startActivity(new Intent(this, RegisterActivity5.class));
        });
    }
}