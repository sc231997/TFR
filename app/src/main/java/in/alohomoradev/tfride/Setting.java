package in.alohomoradev.tfride;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Setting extends ListActivity {

    ListView listView;
    String[] titleArray={"chandan","ajay","roman"};
    int[] imageArray={R.drawable.setting_icon,R.drawable.setting_icon,R.drawable.setting_icon};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        MyAdapter myAdapter=new MyAdapter(this,titleArray,titleArray,imageArray);

        listView=getListView();
        listView.setAdapter(myAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(this,"Clicked on"+position,Toast.LENGTH_SHORT).show();
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
        ImageView myImage=(ImageView)row.findViewById(R.id.imageView);
        TextView myTitles=(TextView)row.findViewById(R.id.textView);
        TextView myDescription=(TextView)row.findViewById(R.id.textView2);

        myImage.setImageResource(arrayImages[position]);
        myTitles.setText(arrayTitles[position]);
        myDescription.setText(arrayDescription[position]);

        return row;
    }
}