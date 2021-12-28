package bih.in.aanganwadiBenVerification.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import bih.in.aanganwadiBenVerification.R;
import io.michaelrocks.paranoid.Obfuscate;

@Obfuscate
public class SignupAuthActivity extends AppCompatActivity {

    CheckBox cb_auth;
    Button btn_get_otp,btn_submit;
    EditText et_otp;
    ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_auth);

        initialization();

        isOathChecked(false);

        iv_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        btn_get_otp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                et_otp.setVisibility(View.VISIBLE);
                btn_submit.setVisibility(View.VISIBLE);
            }
        });

        cb_auth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                isOathChecked(isChecked);
            }
        });
    }

    public void initialization()
    {
        iv_back = (ImageView)findViewById(R.id.iv_back);
        cb_auth = (CheckBox) findViewById(R.id.cb_auth);

        btn_get_otp = (Button) findViewById(R.id.btn_get_otp);
        btn_submit = (Button) findViewById(R.id.btn_submit);

        et_otp = (EditText)findViewById(R.id.et_otp);

        btn_get_otp.setVisibility(View.GONE);
        btn_submit.setVisibility(View.GONE);
        et_otp.setVisibility(View.GONE);
    }

    public void isOathChecked(boolean isChecked)
    {
        if(isChecked)
        {
            btn_get_otp.setVisibility(View.VISIBLE);
        }
        else
        {
            btn_get_otp.setVisibility(View.GONE);
            btn_submit.setVisibility(View.GONE);
            et_otp.setVisibility(View.GONE);
        }
    }

}
