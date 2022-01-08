package ru.ialmostdeveloper.soulfire_mobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.ialmostdeveloper.soulfire_mobile.network.ApiClient;
import ru.ialmostdeveloper.soulfire_mobile.network.SessionManager;
import ru.ialmostdeveloper.soulfire_mobile.network.models.Preset;
import ru.ialmostdeveloper.soulfire_mobile.network.models.PresetResponse;
import ru.ialmostdeveloper.soulfire_mobile.network.models.PresetsResponse;

public class PresetsSavedFragment extends Fragment {
    private SessionManager sessionManager;
    private ApiClient apiClient;
    private Context self;
    private View view;
    private ViewGroup container;
    private LayoutInflater inflater;

    public PresetsSavedFragment() {
        // Required empty public constructor
    }

    public static PresetsSavedFragment newInstance() {
        PresetsSavedFragment fragment = new PresetsSavedFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        self = this.requireActivity();
        apiClient = new ApiClient();
        sessionManager = new SessionManager(self);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.container = container;
        this.inflater = inflater;
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_presets_saved, container, false);

        Button addPresetBtn = view.findViewById(R.id.btn_add_preset);
        addPresetBtn.setOnClickListener(v -> {
            startActivity(new Intent(self, AddPresetActivity.class));
        });

        getUserPresets();

        return view;
    }

    private Preset[] getUserPresets() {
        final Preset[][] presets = {{}};
        apiClient
                .getApiService()
                .getUserPresets("Bearer " + sessionManager.fetchAuthToken(), sessionManager.fetchUserId())
                .enqueue(new Callback<PresetsResponse>() {
                    @Override
                    public void onResponse(Call<PresetsResponse> call, Response<PresetsResponse> response) {
                        Toast.makeText(self, "Got presets", Toast.LENGTH_SHORT).show();
                        if (response.isSuccessful()) {
                            presets[0] = response.body().getContent();

                            LinearLayout presetsLayout = view.findViewById(R.id.preset_layout);
                            presetsLayout.removeAllViews();
                            for (int i = 0; i < presets[0].length; i++) {
                                View presetView = inflater.inflate(R.layout.preset_card, container, false);
                                TextView presetLabel = presetView.findViewById(R.id.preset_label);
                                presetLabel.setText(presets[0][i].getTitle());
                                Button btn_addPreset = presetView.findViewById(R.id.btn_add_preset);
                                int finalI = i;
                                btn_addPreset.setText("удалить");
                                btn_addPreset.setOnClickListener(v -> {
                                    deleteUserPreset(presets[0][finalI].getId());
                                });
                                presetsLayout.addView(presetView);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<PresetsResponse> call, Throwable t) {
                        Toast.makeText(self, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        return presets[0];
    }

    private void deleteUserPreset(String presetId) {
        apiClient.getApiService().deleteUserPreset("Bearer " + sessionManager.fetchAuthToken(), presetId)
                .enqueue(new Callback<PresetResponse>() {
                    @Override
                    public void onResponse(Call<PresetResponse> call, Response<PresetResponse> response) {
                        getUserPresets();
                    }

                    @Override
                    public void onFailure(Call<PresetResponse> call, Throwable t) {

                    }
                });
    }
}