package com.example.sliderprogress;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    ProgressBar progressBar;
    TextView txtseekbar, txtStatus, txtInstall;
    Button btn1,btn2;
    int progressValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        progressBar = findViewById(R.id.progressBar);
        txtseekbar = findViewById(R.id.textView);
        txtStatus = findViewById(R.id.textView1);
        txtInstall = findViewById(R.id.textView2);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress==0){
                    txtseekbar.setText("WORST");
                }
                else if (progress==1){
                    txtseekbar.setText("BAD");
                }
                else if (progress==2){
                    txtseekbar.setText("NORMAL");
                }
                else if (progress==3){
                    txtseekbar.setText("GOOD");
                }
                else if (progress==4){
                    txtseekbar.setText("GREAT!");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                Log.e("SEEKING", "Tracking.....");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.e("SEEKING", "..... xxxxxxxxx ");
            }
        });
    }

    public void btn_Click(View v){
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Getting Status...");
        progressDialog.show();

//====================================
        new Thread(){
            public void run(){
                try {
                    sleep(2000);
                    //txtStatus.setText("Status: OK");
                    UpdateTextStatus();
                    progressDialog.dismiss();
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }.start();
//====================================

    }

    public void UpdateTextStatus(){
        //====================
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtStatus.setText("Status: OK");
            }
        });
        //====================
    }

    public void btn2_Click(View v){

        new Thread(){
            public void run(){
                try {
                    while(progressValue<100) {
                        progressValue++;
                        sleep(50);
                        UpdateTextInstall();
                    }

                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }.start();
    }

    public void UpdateTextInstall(){
        //====================
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setProgress(progressValue);
                txtInstall.setText( progressValue +"%");
            }
        });
        //====================
    }
}