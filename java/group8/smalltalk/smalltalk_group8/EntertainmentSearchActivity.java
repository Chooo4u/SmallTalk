package group8.smalltalk.smalltalk_group8;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import group8.smalltalk.smalltalk_group8.util.DBOperator;
import group8.smalltalk.smalltalk_group8.view.TableView;

public class EntertainmentSearchActivity extends Activity implements OnClickListener {

    Button searchCbtn,searchTtbtn,searchEbtn,searchEbackBtn;
    EditText fnameEedit, lnameEedit, titleEedit, episodeEedit;
    ScrollView searchEscrollview;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entertainment_search_layout);
        //copy database file
        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }
        searchCbtn=(Button)this.findViewById(R.id.search_crew_btn);
        searchCbtn.setOnClickListener(this);
        searchTtbtn=(Button)this.findViewById(R.id.search_title_btn);
        searchTtbtn.setOnClickListener(this);
        searchEbtn=(Button)this.findViewById(R.id.search_episode_btn);
        searchEbtn.setOnClickListener(this);
        searchEbackBtn=(Button)this.findViewById(R.id.SearchE_goBackbtn);
        searchEbackBtn.setOnClickListener(this);

        fnameEedit=(EditText)this.findViewById(R.id.enter_fname_edittext);
        lnameEedit=(EditText)this.findViewById(R.id.enter_lname_edittext);
        titleEedit=(EditText)this.findViewById(R.id.enter_title_edittext);
        episodeEedit=(EditText)this.findViewById(R.id.enter_episode_edittext);

        searchEscrollview=(ScrollView)this.findViewById(R.id.searchEscrollview);
    }




    public void onClick(View v)
    {
        int id=v.getId();
        if (id==R.id.search_crew_btn){
            String sql = "Select Title.vtitle, Profession.profession, Profession.character, Title.year_start, Title.year_end, Title.vtype, Title.genres, Title.vrating from Profession inner join Title on Profession.vid = Title.vid inner join Crew on Crew.cid = Profession.cid WHERE Crew.first_name = '" + fnameEedit.getText() + "' and Crew.last_name = '" + lnameEedit.getText() + "' order by year_start asc;";
            searchEscrollview.removeAllViews();
            Cursor cursor = DBOperator.getInstance().execQuery(sql);
            searchEscrollview.addView(new TableView(this.getBaseContext(), cursor));

        }else if (id==R.id.search_title_btn){
            ///show query result
            String sql = "Select Title.vtitle, Title.vtype, Title.year_start, Title.genres, Title.vrating, Crew.first_name, Crew.last_name, Profession.profession, Profession.character from Crew inner join Profession on Crew.cid = Profession.cid inner join Title on Profession.vid = Title.vid WHERE Title.vtype = 'movie' and Title.vtitle = '" + titleEedit.getText() + "' order by year_start asc;";
            searchEscrollview.removeAllViews();
            Cursor cursor = DBOperator.getInstance().execQuery(sql);
            searchEscrollview.addView(new TableView(this.getBaseContext(), cursor));

        }else if (id==R.id.search_episode_btn){
            ///show query result
            String sql = "Select Title.vtype, Episode.vtitle, Episode.num_season,Episode.num_episode,Episode.etitle, Title.year_start, Title.year_end, Episode.rating from Episode inner join Title on Episode.vid = Title.vid WHERE Episode.vtitle = '" + episodeEedit.getText() + "' order by year_start asc;";

            searchEscrollview.removeAllViews();
            Cursor cursor = DBOperator.getInstance().execQuery(sql);
            searchEscrollview.addView(new TableView(this.getBaseContext(), cursor));

        }else if (id==R.id.SearchE_goBackbtn){
            //Go back to main screen
            Intent intent = new Intent(this, Group8Activity.class);
            this.startActivity(intent);
        }
    }



}
