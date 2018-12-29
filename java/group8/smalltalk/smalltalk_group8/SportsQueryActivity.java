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

public class SportsQueryActivity extends Activity implements OnClickListener {

    Button SbackBtn,SresultBtn;
    Spinner SquerySpinner;
    ScrollView SscrollView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sports_layout);
        //copy database file
        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }
        SbackBtn=(Button)this.findViewById(R.id.SgoBack_btn);
        SbackBtn.setOnClickListener(this);
        SresultBtn=(Button)this.findViewById(R.id.showsports_btn);
        SresultBtn.setOnClickListener(this);
        SquerySpinner=(Spinner)this.findViewById(R.id.sports_querylist_spinner);
        SscrollView=(ScrollView)this.findViewById(R.id.SscrollView);
    }

    public void onClick(View v)
    {
        String sql="";
        int id=v.getId();
        if (id==R.id.showsports_btn){
            //show query result
            int pos=SquerySpinner.getSelectedItemPosition();
            if (pos==Spinner.INVALID_POSITION){
                //User doesn't choose any query, show warning
                Toast.makeText(this.getBaseContext(), "Please choose a query!", Toast.LENGTH_SHORT).show();
                return;
            }
            SscrollView.removeAllViews();
            if (pos==0){
                sql=SQLCommand.Football_Teams;
            }else if (pos==1){
                sql=SQLCommand.Baseball_Teams;
            }else if (pos==2){
                sql=SQLCommand.Basketball_Teams;
            }else if (pos==3){
                sql=SQLCommand.Hockey_Teams;

            }
            Cursor cursor=DBOperator.getInstance().execQuery(sql);
            SscrollView.addView(new TableView(this.getBaseContext(),cursor));
        }else if (id==R.id.SgoBack_btn){
            //go back to main screen
            Intent intent = new Intent(this, Group8Activity.class);
            this.startActivity(intent);
        }
    }
}
