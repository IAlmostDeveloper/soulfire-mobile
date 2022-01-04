package ru.ialmostdeveloper.soulfire_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RegisterActivity3 extends AppCompatActivity {

    private final String[] options = {"Побег от действительности", "Избегание близких отношений", "Поиск признания", "Накручивание мыслей"};
    private final int[] optionsImg = {R.drawable.reality_escape, R.drawable.relations_escape, R.drawable.acceptance_search, R.drawable.obsessional_thoughts};
    private final ArrayList<String> selectedOptions = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);
        SharedPreferences sprefs = this.getSharedPreferences("ru.ialmostdeveloper.soulfire_mobile", Context.MODE_PRIVATE);

        LinearLayout card_layout = findViewById(R.id.card_layout);
        for (int i = 0; i < options.length; i++) {
            CardView cardView = new CardView(this);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    400,400// CardView height
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

            ImageView imageView = new ImageView(this);
            imageView.setImageResource(optionsImg[j]);

            cardView.addView(imageView);

            TextView text = new TextView(this);
            text.setText(options[j]);
            cardView.addView(text);
            card_layout.addView(cardView);
        }


        Button btn_welcome = findViewById(R.id.welcome_btn);
        btn_welcome.setOnClickListener(v -> {
            Set<String> optionsSet = new HashSet<String>(selectedOptions);
            sprefs.edit().putStringSet("userMiddleThoughts", optionsSet).apply();
            startActivity(new Intent(this, RegisterActivity4.class));
        });
    }
}