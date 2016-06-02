package com.cdrussell.boltsexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Date;

import bolts.Continuation;
import bolts.Task;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.status)
    TextView status;

    @BindView(R.id.progress_indicator)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.do_work_direct)
    public void onDoWorkDirectButtonPress() {
        progressBar.setVisibility(VISIBLE);
        new Worker().doWorkTask()
                .onSuccess((Continuation<Boolean, Void>) task -> {
                    progressBar.setVisibility(View.GONE);
                    status.setText("Finished direct task at " + new Date().toString());
                    return null;
                }, Task.UI_THREAD_EXECUTOR);
    }

    @OnClick(R.id.do_work_indirect)
    public void onDoWorkIndirectButtonPress() {
        progressBar.setVisibility(VISIBLE);
        new Worker().doWorkCallback(successful -> {
            runOnUiThread(() -> {
                progressBar.setVisibility(View.GONE);
                status.setText("Finished indirect task at " + new Date().toString());
            });
        });
    }
}
