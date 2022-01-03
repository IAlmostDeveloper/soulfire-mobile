package ru.ialmostdeveloper.soulfire_mobile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RegisterActivity2 extends AppCompatActivity {

    private final String[] options = {"Тревога и страх", "Беспокойный сон", "Потливость", "Учащенное сердцебиение"};
    private final ArrayList<String> selectedOptions = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        SharedPreferences sprefs = this.getSharedPreferences("ru.ialmostdeveloper.soulfire_mobile", Context.MODE_PRIVATE);

        TextView welcome_label = findViewById(R.id.welcome_label2);


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

        welcome_label.setText(sprefs.getString("userCharacterType", "Undefined kek"));

        Button btn_welcome = findViewById(R.id.welcome_btn2);
        btn_welcome.setOnClickListener(v -> {
            Set<String> optionsSet = new HashSet<String>();
            optionsSet.addAll(selectedOptions);
            sprefs.edit().putStringSet("userAutoThoughts", optionsSet).apply();
            startActivity(new Intent(this, RegisterActivity3.class));
        });
    }
}