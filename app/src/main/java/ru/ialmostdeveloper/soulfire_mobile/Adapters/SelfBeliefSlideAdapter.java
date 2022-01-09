package ru.ialmostdeveloper.soulfire_mobile.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import ru.ialmostdeveloper.soulfire_mobile.network.models.DiaryAnswer;
import ru.ialmostdeveloper.soulfire_mobile.network.models.DiaryQuestion;

public class SelfBeliefSlideAdapter extends PagerAdapter {
    private final Context context;

    public SelfBeliefSlideAdapter(Context context) {
        this.context = context;
    }
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

    public DiaryAnswer[] answers = new DiaryAnswer[questions.length];

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}
