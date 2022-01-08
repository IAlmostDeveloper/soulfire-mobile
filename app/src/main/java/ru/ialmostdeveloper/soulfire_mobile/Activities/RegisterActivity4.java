package ru.ialmostdeveloper.soulfire_mobile.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import ru.ialmostdeveloper.soulfire_mobile.R;

public class RegisterActivity4 extends AppCompatActivity {

    private final String[] options = {"Я непривлекателен", "Мне не везет", "Я скучен", "Мое мнение не имеет значения"};
    private final int[] optionsImg = {R.drawable.forever_alone, R.drawable.weak_voice, R.drawable.nobody_cares, R.drawable.tremor};
    private final ArrayList<String> selectedOptions = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register4);

        SharedPreferences sprefs = this.getSharedPreferences("ru.ialmostdeveloper.soulfire_mobile", Context.MODE_PRIVATE);
        TextView welcome_label = findViewById(R.id.welcome_label);
        LinearLayout card_layout = findViewById(R.id.card_layout);
        for (int i = 0; i < options.length-1; i+=2) {
            LinearLayout twoCardsLayout = new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    300
            );
            layoutParams.setMargins(20, 20, 20, 20);
            twoCardsLayout.addView(makeCardView(i));
            twoCardsLayout.addView(makeCardView(i+1));

            card_layout.addView(twoCardsLayout);
        }

        welcome_label.setText(String.format("Привет, %s!\n Выбери, что тебя беспокоит:", sprefs.getString("userCharacterType", "Undefined kek")));
        Button welcome_btn = findViewById(R.id.welcome_btn);

        welcome_btn.setOnClickListener(v -> {
            Set<String> optionsSet = new HashSet<String>(selectedOptions);
            sprefs.edit().putStringSet("userDeepThoughts", optionsSet).apply();
            startActivity(new Intent(this, RegisterActivity5.class));
        });
    }

    private CardView makeCardView(int index){
        CardView cardView = new CardView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                300, 300 // CardView height
        );
        layoutParams.setMargins(20, 20, 20, 20);
        cardView.setLayoutParams(layoutParams);
        cardView.setRadius(16F);

        cardView.setContentPadding(25, 25, 25, 25);
        cardView.setCardBackgroundColor(Color.LTGRAY);
        cardView.setCardElevation(8F);
        cardView.setMaxCardElevation(12F);
        cardView.setOnClickListener(v -> {
            if (!selectedOptions.contains(options[index])) {
                cardView.setBackground(getResources().getDrawable(R.drawable.card_shape_selected));
                Toast.makeText(
                        this.getApplicationContext(),
                        options[index],
                        Toast.LENGTH_SHORT).show();
                selectedOptions.add(options[index]);
            } else {
                selectedOptions.remove(options[index]);
                cardView.setBackgroundResource(0);
            }
        });
        layoutParams = new LinearLayout.LayoutParams(
                200, 200 // CardView height
        );
        ImageView imageView = new ImageView(this);

        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(optionsImg[index]);

        cardView.addView(imageView);

        TextView text = new TextView(this);
        text.setText(options[index]);
        cardView.addView(text);

        return cardView;
    }

}