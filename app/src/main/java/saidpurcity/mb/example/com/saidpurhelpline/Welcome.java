package saidpurcity.mb.example.com.saidpurhelpline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import me.anwarshahriar.calligrapher.Calligrapher;

public class Welcome extends AppCompatActivity {

    ProgressBar progressbar;
    private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

// Set Local Font
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"fonts/Hind_Siliguri_Light-Regular.ttf", true);


        progressbar = findViewById(R.id.progressbar);

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {

                doWork();
                startApp();
            }


        });

        th.start();

    }


    private void doWork() {

        for(progress=20; progress<=100;progress=progress+20){

            try{

                Thread.sleep(1000);
                progressbar.setProgress(progress);

            } catch (InterruptedException e){

                e.printStackTrace();
            }


        }



    }

    public void startApp(){

        Intent intent = new Intent(Welcome.this,Home.class);
        startActivity(intent);
        finish();

    }
}
