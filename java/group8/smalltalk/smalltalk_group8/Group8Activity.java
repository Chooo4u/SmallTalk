package group8.smalltalk.smalltalk_group8;

import group8.smalltalk.smalltalk_group8.util.DBOperator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Group8Activity extends Activity implements OnClickListener{

    Button sportsBtn,entertainmentBtn,searchSportsBtn,searchEnterBtn;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group8_layout);
        sportsBtn=(Button)this.findViewById(R.id.sports_btn);
        sportsBtn.setOnClickListener(this);
        entertainmentBtn=(Button)this.findViewById(R.id.entertainment_btn);
        entertainmentBtn.setOnClickListener(this);
        searchSportsBtn=(Button)this.findViewById(R.id.search_sports_btn);
        searchSportsBtn.setOnClickListener(this);
        searchEnterBtn=(Button)this.findViewById(R.id.search_entertainment_btn);
        searchEnterBtn.setOnClickListener(this);

        //copy database file
        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void onClick(View v)
    {
        int id=v.getId();
        if (id==R.id.sports_btn){
            Intent intent = new Intent(this, SportsQueryActivity.class);
            this.startActivity(intent);
        }else if (id==R.id.entertainment_btn){
            Intent intent = new Intent(this, EntertainmentQueryActivity.class);
            this.startActivity(intent);
        }else if (id==R.id.search_sports_btn){
            Intent intent = new Intent(this, SportsSearchActivity.class);
            this.startActivity(intent);
        }else if (id==R.id.search_entertainment_btn){
            Intent intent = new Intent(this, EntertainmentSearchActivity.class);
            this.startActivity(intent);

        }
    }
}
