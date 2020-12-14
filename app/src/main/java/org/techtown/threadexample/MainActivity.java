package org.techtown.threadexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_start, btn_stop;
    Thread thread;
    TextView number;
    boolean isThread = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = findViewById(R.id.btn_start);
        btn_stop = findViewById(R.id.btn_stop);
        number = findViewById(R.id.text_num);

        //스레드 시작
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isThread = true;
                thread = new Thread() {
                    public void run(){
                          while(isThread){
                              for(int i=1; i<=5; i++){
                                  if(!isThread) break;
                                  number.setText(Integer.toString(i));
                                  try {
                                      sleep(1000);
                                  } catch (InterruptedException e) {
                                      e.printStackTrace();
                                  }
                              }
                              if(!isThread) break;
                              //핸들러?
                              handler.sendEmptyMessage(0);
                          }
                    }
                };
                thread.start();
            }
        });

        //스레드 종료
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isThread=false;
                number.setText("준비");
            }
        });

    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
        }
    };
}