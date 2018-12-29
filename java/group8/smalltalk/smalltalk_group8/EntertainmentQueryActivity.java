package group8.smalltalk.smalltalk_group8;

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
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

public class EntertainmentQueryActivity extends Activity implements OnClickListener {

    Button EbackBtn,EresultBtn;
    Spinner EquerySpinner;
    ScrollView EscrollView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entertainment_layout);
        //copy database file
        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }
        EbackBtn=(Button)this.findViewById(R.id.EgoBack_btn);
        EbackBtn.setOnClickListener(this);
        EresultBtn=(Button)this.findViewById(R.id.showentertainment_btn);
        EresultBtn.setOnClickListener(this);
        EquerySpinner=(Spinner)this.findViewById(R.id.enter_querylist_spinner);
        EscrollView=(ScrollView)this.findViewById(R.id.EscrollView);
    }

    public void onClick(View v)
    {
        String sql="";
        int id=v.getId();
        if (id==R.id.showentertainment_btn){
            //show query result
            int pos=EquerySpinner.getSelectedItemPosition();
            if (pos==Spinner.INVALID_POSITION){
                //User doesn't choose any query, show warning
                Toast.makeText(this.getBaseContext(), "Please choose a query!", Toast.LENGTH_SHORT).show();
                return;
            }
            EscrollView.removeAllViews();
            if (pos==0){
                //show all books
                sql=SQLCommand.Best_Movies_2018;
            }else if (pos==1){
                //list the call numbers of books with the title ‘Database Management’
                sql=SQLCommand.Best_TV_2018;

            }
            Cursor cursor=DBOperator.getInstance().execQuery(sql);
            EscrollView.addView(new TableView(this.getBaseContext(),cursor));
        }else if (id==R.id.EgoBack_btn){
            //go back to main screen
            Intent intent = new Intent(this, Group8Activity.class);
            this.startActivity(intent);
        }
    }
}
