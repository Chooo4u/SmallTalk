package group8.smalltalk.smalltalk_group8;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import group8.smalltalk.smalltalk_group8.constant.SQLCommand;
import group8.smalltalk.smalltalk_group8.util.DBOperator;
import group8.smalltalk.smalltalk_group8.view.TableView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

public class SportsSearchActivity extends Activity implements OnClickListener {

    Button searchAbtn,searchTbtn,searchSbtn,searchSbackBtn;
    EditText fnameSedit, lnameSedit, teamSedit, stateSedit;
    ScrollView searchSscrollview;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sports_search_layout);
        //copy database file
        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }
        searchAbtn=(Button)this.findViewById(R.id.search_athlete_btn);
        searchAbtn.setOnClickListener(this);
        searchTbtn=(Button)this.findViewById(R.id.search_team_btn);
        searchTbtn.setOnClickListener(this);
        searchSbtn=(Button)this.findViewById(R.id.search_state_btn);
        searchSbtn.setOnClickListener(this);
        searchSbackBtn=(Button)this.findViewById(R.id.SearchS_goBackbtn);
        searchSbackBtn.setOnClickListener(this);

        fnameSedit=(EditText)this.findViewById(R.id.sports_fname_edittext);
        lnameSedit=(EditText)this.findViewById(R.id.sports_lname_edittext);
        teamSedit=(EditText)this.findViewById(R.id.sports_team_edittext);
        stateSedit=(EditText)this.findViewById(R.id.sports_state_edittext);

        searchSscrollview=(ScrollView)this.findViewById(R.id.searchSscrollview);
    }




    public void onClick(View v)
    {
        int id=v.getId();
        if (id==R.id.search_athlete_btn){
            String sql = "Select sport.sport_name, team.team_name, athlete.* from sport inner join team on sport.sid = team.sid inner join athlete on team.tid = athlete.tid WHERE athlete.first_name = '" + fnameSedit.getText() + "' and athlete.last_name = '" + lnameSedit.getText() + "';";

            searchSscrollview.removeAllViews();
            Cursor cursor = DBOperator.getInstance().execQuery(sql);
            searchSscrollview.addView(new TableView(this.getBaseContext(), cursor));
//
        }else if (id==R.id.search_team_btn){
            ///show query result
            String sql = "Select sport.sport_name, team.team_name, team.location, team.wins, team.losses, athlete.first_name, athlete.last_name from sport inner join team on sport.sid = team.sid inner join athlete on team.tid = athlete.tid WHERE team.team_name = '" + teamSedit.getText() + "' order by athlete.last_name;";

            searchSscrollview.removeAllViews();
            Cursor cursor = DBOperator.getInstance().execQuery(sql);
            searchSscrollview.addView(new TableView(this.getBaseContext(), cursor));

        }else if (id==R.id.search_state_btn){
            ///show query result
            String sql = "Select sport.league_name, team.team_name, team.location, team.wins, team.losses from sport inner join team on sport.sid = team.sid WHERE team.location = '" + stateSedit.getText() + "';";

            searchSscrollview.removeAllViews();
            Cursor cursor = DBOperator.getInstance().execQuery(sql);
            searchSscrollview.addView(new TableView(this.getBaseContext(), cursor));

        }else if (id==R.id.SearchS_goBackbtn){
            //Go back to main screen
            Intent intent = new Intent(this, Group8Activity.class);
            this.startActivity(intent);
        }
    }


}
