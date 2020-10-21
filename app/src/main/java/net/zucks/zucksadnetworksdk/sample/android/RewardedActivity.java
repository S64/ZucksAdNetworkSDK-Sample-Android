package net.zucks.zucksadnetworksdk.sample.android;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import net.zucks.sdk.rewardedad.ZucksRewardedAd;
import net.zucks.sdk.rewardedad.ZucksRewardedAdException;

public class RewardedActivity extends AppCompatActivity {

    private static final String FRAME_ID = "rewarded_video_web";

    private ZucksRewardedAd rewardedAd;

    private final ZucksRewardedAd.Listener listener = new ZucksRewardedAd.Listener() {

        @Override
        public void onLoaded(@NonNull ZucksRewardedAd zucksRewardedAd) {
            refreshUi();
            Toast.makeText(RewardedActivity.this, "onLoaded", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onLoadFailed(@NonNull ZucksRewardedAd zucksRewardedAd, @NonNull ZucksRewardedAdException e) {
            rewardedAd = null;
            refreshUi();
            Toast.makeText(RewardedActivity.this, "onLoadFailed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPlaybackFailed(@NonNull ZucksRewardedAd zucksRewardedAd, @NonNull ZucksRewardedAdException e) {
            rewardedAd = null;
            refreshUi();
            Toast.makeText(RewardedActivity.this, "onPlaybackFailed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onOpened(@NonNull ZucksRewardedAd zucksRewardedAd) {
            Toast.makeText(RewardedActivity.this, "onOpened", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStarted(@NonNull ZucksRewardedAd zucksRewardedAd) {
            Toast.makeText(RewardedActivity.this, "onStarted", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onClicked(@NonNull ZucksRewardedAd zucksRewardedAd) {
            Toast.makeText(RewardedActivity.this, "onClicked", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onClosed(@NonNull ZucksRewardedAd zucksRewardedAd) {
            Toast.makeText(RewardedActivity.this, "onClosed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onShouldReward(@NonNull ZucksRewardedAd zucksRewardedAd) {
            rewardedAd = null;
            refreshUi();
            Toast.makeText(RewardedActivity.this, "onShouldReward", Toast.LENGTH_SHORT).show();
        }

    };

    private Button loadButton;
    private Button showButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rewarded_activity);

        loadButton = findViewById(R.id.loadButton);
        showButton = findViewById(R.id.showButton);

        rewardedAd = new ZucksRewardedAd(FRAME_ID, listener);

        loadButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                rewardedAd.load(RewardedActivity.this);
            }

        });

        showButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                rewardedAd.show(RewardedActivity.this);
            }

        });

        refreshUi();
    }

    private void refreshUi() {
        loadButton.setEnabled(rewardedAd != null && !rewardedAd.isAvailable());
        showButton.setEnabled(rewardedAd != null && rewardedAd.isAvailable());
    }

}
