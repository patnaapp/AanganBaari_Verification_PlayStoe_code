package bih.in.aanganwadiBenVerification.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import bih.in.aanganwadiBenVerification.R;

public class VerifyActivity extends AppCompatActivity {
    String ben_type[] = {"-All-","आंगनवाड़ी केंद्र के बच्चे","स्तनपान कराने वाली महिला","गर्भवती महिला"};
    Spinner spn_labharthi;
    String Ben_Id="",Ben_Name;
    ArrayAdapter benarray_array;
    ListView list_student;
    Button btnSave;
    TextView tv_husband,tv_wife,tv_child,tv_gender,tv_age,tv_category,tv_aadhar_no,tv_account_no;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);


        initialization();
        benarray_array = new ArrayAdapter(this, R.layout.spinner_textview, ben_type);
    }
    public void initialization()
    {
        spn_labharthi=(Spinner)findViewById(R.id.spn_labharthi);
        list_student=(ListView)findViewById(R.id.list_student);
        btnSave= findViewById(R.id.btnsave);
        tv_husband = (TextView) findViewById(R.id.tv_husband);
        tv_wife = (TextView) findViewById(R.id.tv_wife);
        tv_child = (TextView) findViewById(R.id.tv_child);
        tv_gender = (TextView) findViewById(R.id.tv_gender);
        tv_age = (TextView) findViewById(R.id.tv_age);
        tv_category = (TextView) findViewById(R.id.tv_category);
        tv_aadhar_no = (TextView) findViewById(R.id.tv_aadhar_no);
        tv_account_no = (TextView) findViewById(R.id.tv_account_no);



        spn_labharthi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("arg2",""+position);
                if (position > 0) {
                    Ben_Name = ben_type[position].toString();

                    if (Ben_Name.equals("आंगनवाड़ी केंद्र के बच्चे")) {

                        Ben_Id = "1";
                    } else if (Ben_Name.equals("स्तनपान कराने वाली महिला")) {

                        Ben_Id = "2";

                    }
                    else if (Ben_Name.equals("गर्भवती महिला")) {

                        Ben_Id = "3";

                    }

                }
            }
        });
        list_student.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);

                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);

                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });




    }
}
