package in.alohomoradev.tfride;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class Setting extends ListActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ListView listView;
    String voiceMessage,defaultVoiceMessage;
    String[] titleArray={"Set Voice Message","ajay","roman"};
    String[] description = {"Current : "," "," "};
    int[] imageArray={R.drawable.setting_icon,R.drawable.setting_icon,R.drawable.setting_icon};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //Sets the description of Set Voice Message setting
        defaultVoiceMessage = "The person you are calling is currently riding," +
                " if you still want to connect press one on your keypad";
        sharedPreferences = getSharedPreferences("Preferences",MODE_PRIVATE);
        //Replace the word 'person' with the name of the user, get the name by prompting the user during the first run of the app
        voiceMessage =  sharedPreferences.getString("VoiceMessage",defaultVoiceMessage);

        description[0]= "Current : " + voiceMessage;
        MyAdapter myAdapter=new MyAdapter(this,titleArray,description,imageArray);

        listView=getListView();
        listView.setAdapter(myAdapter);


    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //Toast.makeText(this,"Clicked on"+position,Toast.LENGTH_SHORT).show();
        //Do not remove the following line, The Alohomora Devs Convention prevents you, know more about it on alohomora.in(Its basically a silly non
        // working easter egg)
        Toast.makeText(this,"Kneel before the great Chandan!",Toast.LENGTH_LONG);
        //Handle the click
        switch (position)
        {
            case 0:
                final Dialog dialog = new Dialog(Setting.this);
                dialog.setContentView(R.layout.dialog_set_preferences);
                final RadioButton setDefault,setCustom;
                final EditText editText;
                Button button;
                setDefault = (RadioButton)dialog.findViewById(R.id.radioButtonVoiceMessageDefualt);
                setCustom = (RadioButton)dialog.findViewById(R.id.radioButtonVoiceMessageCustom);
                editText = (EditText)dialog.findViewById(R.id.editTextVoiceMessage);
                button = (Button)dialog.findViewById(R.id.buttonSetVoiceMessage);
                setCustom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(setCustom.isChecked())
                            editText.setVisibility(View.VISIBLE);
                        else
                            editText.setVisibility(View.INVISIBLE);
                    }
                });
                setDefault.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(setDefault.isChecked())
                            editText.setVisibility(View.INVISIBLE);
                        else

                            editText.setVisibility(View.VISIBLE);
                    }
                });

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editor = sharedPreferences.edit();
                        if(setDefault.isChecked()) {

                            editor.putString("VoiceMessage",defaultVoiceMessage);
                            editor.apply();
                        }
                        else
                        {
                            if (editText.getText().toString() != null) {
                                editor.putString("VoiceMessage",editText.getText().toString());
                                editor.apply();
                            } else {
                                Toast.makeText(Setting.this,"Please enter a valid message",Toast.LENGTH_SHORT).show();
                            }
                        }
                        Toast.makeText(Setting.this,"Message set",Toast.LENGTH_SHORT).show();
                        voiceMessage =  sharedPreferences.getString("VoiceMessage",defaultVoiceMessage);
                        description[0]= "Current : " + voiceMessage;
                        MyAdapter myAdapter=new MyAdapter(Setting.this,titleArray,description,imageArray);

                        listView=getListView();
                        listView.setAdapter(myAdapter);
                        dialog.dismiss();
                    }
                });
                if(sharedPreferences.getString("VoiceMessage",defaultVoiceMessage) != defaultVoiceMessage)
                {
                    setCustom.setChecked(true);
                    editText.setVisibility(View.VISIBLE);
                    editText.setText(sharedPreferences.getString("VoiceMessage",defaultVoiceMessage));
                }
                dialog.show();
                break;
        }
        super.onListItemClick(l, v, position, id);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        finish();
    }
}
class MyAdapter extends ArrayAdapter<String>{
    Context context;
    String[] arrayTitles;
    String[] arrayDescription;
    int[] arrayImages;
    MyAdapter(Context c,String[] titles,String[] description,int[] images){
        super(c,R.layout.activity_listview,R.id.textView,titles);
        this.context=c;
        this.arrayTitles=titles;
        this.arrayDescription=description;
        this.arrayImages=images;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.activity_listview,parent,false);
        //Try to use a Holder, this is ineffecient
        ImageView myImage=(ImageView)row.findViewById(R.id.imageView);
        TextView myTitles=(TextView)row.findViewById(R.id.textView);
        TextView myDescription=(TextView)row.findViewById(R.id.textView2);

        myImage.setImageResource(arrayImages[position]);
        myTitles.setText(arrayTitles[position]);
        myDescription.setText(arrayDescription[position]);

        return row;
    }
}