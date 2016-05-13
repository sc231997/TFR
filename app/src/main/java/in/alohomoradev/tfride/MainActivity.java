package in.alohomoradev.tfride;

import android.content.Intent;
import android.content.SharedPreferences;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ImageButton setting;
    SharedPreferences sharedPreferences;
    //Only for TP purposes
    String tToSpeech;
    TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setting=(ImageButton)findViewById(R.id.setting);
        sharedPreferences = getSharedPreferences("Preferences",MODE_PRIVATE);
        tToSpeech = sharedPreferences.getString("VoiceMessage","The person you are calling is currently riding, if you still want to connect press one on your keypad");
        textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Chandan please change the speak method, it is deprecated
                    textToSpeech.speak(tToSpeech, TextToSpeech.QUEUE_FLUSH, null);
                Intent intent = new Intent(MainActivity.this,Setting.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                finish();
            }
        });

    }

}
