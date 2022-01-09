package ru.ialmostdeveloper.soulfire_mobile.Activities;

import static android.view.View.GONE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.ialmostdeveloper.soulfire_mobile.Adapters.DiarySlideAdapter;
import ru.ialmostdeveloper.soulfire_mobile.R;
import ru.ialmostdeveloper.soulfire_mobile.network.ApiClient;
import ru.ialmostdeveloper.soulfire_mobile.network.SessionManager;
import ru.ialmostdeveloper.soulfire_mobile.network.models.DiaryAnswer;
import ru.ialmostdeveloper.soulfire_mobile.network.models.DiaryNote;
import ru.ialmostdeveloper.soulfire_mobile.network.models.DiaryQuestion;
import ru.ialmostdeveloper.soulfire_mobile.network.models.SelfBelief;
import ru.ialmostdeveloper.soulfire_mobile.network.models.SelfBeliefProof;
import ru.ialmostdeveloper.soulfire_mobile.network.models.SelfBeliefResponse;
import ru.ialmostdeveloper.soulfire_mobile.network.models.UserAnswer;

public class AddSelfBeliefActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private ApiClient apiClient;
    private Context self;

    private ViewPager viewPager;
    private DiarySlideAdapter diarySlideAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_self_belief);

        self = this;
        sessionManager = new SessionManager(this);
        apiClient = new ApiClient();

        viewPager = findViewById(R.id.viewpager);
        diarySlideAdapter = new DiarySlideAdapter(this);
        diarySlideAdapter.questions = new DiaryQuestion[]{
                new DiaryQuestion(1, "Назови проблемную ситуацию, которая из раза в раз вызывает одни и те же реакции", false, null),
                new DiaryQuestion(1, "Какие автоматические мысли и действия типичны для этих ситуаций?", false, null),
                new DiaryQuestion(1, "Какие есть доказательства тому, что это не правда?", false, null),
//                new DiaryQuestion(3, "Какие чувства я испытал?", true, new String[]{"Тревога", "Страх", "Гнев", "Обида"}),
//                new DiaryQuestion(3, "Какие ощущения в теле почувствовал?", true, new String[]{"Тревога", "Страх", "Гнев", "Обида"}),
//                new DiaryQuestion(2, "Почему думаю, что все действительно так?", true, null),
//                new DiaryQuestion(2, "Почему может оказаться, что все не совсем так, или все совсем не так?", true, null),
//                new DiaryQuestion(2, "Что худшее может случиться и что я в таком случае сделаю?", true, null),
//                new DiaryQuestion(2, "Что самое лучшее может случиться?", true, null),
//                new DiaryQuestion(1, "Что, скорее всего, случится?", true, null),
//                new DiaryQuestion(1, "Что случится, если я буду продолжать повторять себе эту мысль?", true, null),
//                new DiaryQuestion(1, "Что может случиться, если я изменю образ мыслей?", true, null),
//                new DiaryQuestion(1, "Насколько после реалистичного взгляда на проблему я верю в изначальную мысль?", true, null),
//                new DiaryQuestion(1, "Что я теперь сделаю?", true, null)
        };
        viewPager.setAdapter(diarySlideAdapter);
        //viewPager.setOffscreenPageLimit(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                EditText inputType1 = viewPager.findViewById(R.id.input_type1);
                String content = inputType1.getText().toString();
                diarySlideAdapter.answers[position] = new DiaryAnswer(1, diarySlideAdapter.questions[position].getContent(), content, null, null);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Button nextQuestionBtn = findViewById(R.id.next_question_btn);

        AtomicInteger currentItem = new AtomicInteger(viewPager.getCurrentItem());

        nextQuestionBtn.setOnClickListener(v -> {
            if (currentItem.get() == diarySlideAdapter.questions.length - 2) {
                nextQuestionBtn.setText("Готово");
                viewPager.setCurrentItem(currentItem.incrementAndGet());
            } else if (currentItem.get() == diarySlideAdapter.questions.length - 1) {
                Toast.makeText(self, "Готово", Toast.LENGTH_SHORT).show();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String updatedDate = now.format(dtf);
                String title = diarySlideAdapter.answers[1].getStringContent();
                String content = diarySlideAdapter.answers[2].getStringContent();
                addSelfBelief(new SelfBelief(sessionManager.fetchUserId(), Objects.requireNonNull(sessionManager.fetchUserId()),
                       title , content , new SelfBeliefProof[]{}, new UserAnswer[]{}));
            } else {
                viewPager.setCurrentItem(currentItem.incrementAndGet());
            }
        });

    }

    private void addSelfBelief(SelfBelief selfBelief) {
        apiClient.getApiService().addSelfBelief("Bearer " + sessionManager.fetchAuthToken(), selfBelief)
                .enqueue(new Callback<SelfBeliefResponse>() {
                    @Override
                    public void onResponse(Call<SelfBeliefResponse> call, Response<SelfBeliefResponse> response) {
                        RequestBody body = call.request().body();
                        Toast.makeText(self, "added selfbelief", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<SelfBeliefResponse> call, Throwable t) {
                        Toast.makeText(self, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}