package ru.ialmostdeveloper.soulfire_mobile.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.ialmostdeveloper.soulfire_mobile.R;
import ru.ialmostdeveloper.soulfire_mobile.network.ApiClient;
import ru.ialmostdeveloper.soulfire_mobile.network.SessionManager;
import ru.ialmostdeveloper.soulfire_mobile.network.models.Preset;
import ru.ialmostdeveloper.soulfire_mobile.network.models.PresetResponse;

public class AddPresetActivity extends AppCompatActivity {
    private SessionManager sessionManager;
    private ApiClient apiClient;
    private Context self;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_preset);

        sessionManager = new SessionManager(this);
        apiClient = new ApiClient();
        self = this;

        EditText preset_input = findViewById(R.id.preset_input);
        Button btn_add_preset = findViewById(R.id.btn_add_preset);

        btn_add_preset.setOnClickListener(v -> {
            String content = preset_input.getText().toString();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String updatedDate = now.format(dtf);
            Preset preset = new Preset(sessionManager.fetchUserId(), sessionManager.fetchUserId(),content, "", updatedDate);
            addUserPreset(preset);
        });

    }

    private void addUserPreset(Preset preset){
        apiClient
                .getApiService()
                .addUserPreset("Bearer " + sessionManager.fetchAuthToken(), preset)
                .enqueue(new Callback<PresetResponse>() {
                    @Override
                    public void onResponse(Call<PresetResponse> call, Response<PresetResponse> response) {
                        Toast.makeText(self, "Preset added", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<PresetResponse> call, Throwable t) {
                        Toast.makeText(self, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}