package ru.ialmostdeveloper.soulfire_mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.google.android.material.slider.Slider;

import java.util.concurrent.atomic.AtomicInteger;

import ru.ialmostdeveloper.soulfire_mobile.network.models.DiaryQuestion;

public class DiarySlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;

    public DiaryQuestion[] questions = {
            new DiaryQuestion(1, "Что случилось? Какое событие или поток мыслей привели к неприятным эмоциям?", false, null),
            new DiaryQuestion(1, "О чем я подумал, и что представил?", false, null),
            new DiaryQuestion(2, "Итог мыслей?", true, null),
            new DiaryQuestion(3, "Какие чувства я испытал?", true, new String[]{"Тревога", "Страх", "Гнев", "Обида"}),
            new DiaryQuestion(3, "Какие ощущения в теле почувствовал?", true, new String[]{"Тревога", "Страх", "Гнев", "Обида"}),
            new DiaryQuestion(2, "Почему думаю, что все действительно так?", true, null),
            new DiaryQuestion(2, "Почему может оказаться, что все не совсем так, или все совсем не так?", true, null),
            new DiaryQuestion(2, "Что худшее может случиться и что я в таком случае сделаю?", true, null),
            new DiaryQuestion(2, "Что самое лучшее может случиться?", true, null),
            new DiaryQuestion(1, "Что, скорее всего, случится?", true, null),
            new DiaryQuestion(1, "Что случится, если я буду продолжать повторять себе эту мысль?", true, null),
            new DiaryQuestion(1, "Что может случиться, если я изменю образ мыслей?", true, null),
            new DiaryQuestion(1, "Насколько после реалистичного взгляда на проблему я верю в изначальную мысль?", true, null),
            new DiaryQuestion(1, "Что я теперь сделаю?", true, null)
    };

    public DiarySlideAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.diary_slide, container, false);

        TextView questionText = view.findViewById(R.id.diary_question_label);
        questionText.setText(questions[position].getContent());

        EditText inputType1 = view.findViewById(R.id.input_type1);
        Slider inputType2 = view.findViewById(R.id.input_type2);
        LinearLayout inputType3 = view.findViewById(R.id.input_type3);

        Button skipQuestionBtn = view.findViewById(R.id.skip_answer_btn);
        Button nextQuestionBtn = view.findViewById(R.id.next_question_btn);

        switch (questions[position].getType()) {
            case 1:
                inputType1.setVisibility(View.VISIBLE);
                inputType2.setVisibility(View.GONE);
                inputType3.setVisibility(View.GONE);
                break;
            case 2:
                inputType1.setVisibility(View.VISIBLE);
                inputType2.setVisibility(View.VISIBLE);
                inputType3.setVisibility(View.GONE);
                break;
            case 3:
                inputType1.setVisibility(View.GONE);
                inputType2.setVisibility(View.GONE);
                inputType3.setVisibility(View.VISIBLE);
                break;
        }

        AtomicInteger _position = new AtomicInteger(position);
        if (questions[position].getCanSkip()) {
            skipQuestionBtn.setVisibility(View.VISIBLE);
            skipQuestionBtn.setOnClickListener(v -> {
                instantiateItem(container, _position.getAndIncrement());
            });
        }

        nextQuestionBtn.setOnClickListener(v -> {
            // тут сохранить ответ
            instantiateItem(container, _position.getAndIncrement());
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }

    @Override
    public int getCount() {
        return questions.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (LinearLayout) object);
    }
}
