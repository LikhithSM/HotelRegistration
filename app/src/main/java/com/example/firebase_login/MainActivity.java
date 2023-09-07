package com.example.firebase_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    TextView txtview;
    FirebaseAuth auth;
    FirebaseUser user;
    ImageButton button,nextbtn;

    EditText days,persons,name,date;

    int day,per;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth=FirebaseAuth.getInstance();
        button=findViewById(R.id.logout);
        nextbtn=findViewById(R.id.nextbtn);

        name=findViewById(R.id.name);
        date=findViewById(R.id.date);
        days=findViewById(R.id.days);
        persons=findViewById(R.id.persons);

        user= auth.getCurrentUser();

        if(user==null)
        {
            Intent intent=new Intent(getApplicationContext(),login.class);
            startActivity(intent);
            finish();
        }
        else{

        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getApplicationContext(),login.class);
                startActivity(intent);
                finish();
            }
        });



    nextbtn.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            if(TextUtils.isEmpty(name.getText().toString())){
                name.setError("Name is compulsory");
                return;
            } else if (TextUtils.isEmpty(date.getText().toString())) {
                date.setError("Date is compulsory");
                return;
            }else if (TextUtils.isEmpty(days.getText().toString())) {
                days.setError("Days is compulsory");
                return;
            }else if (TextUtils.isEmpty(persons.getText().toString())) {
                persons.setError("Persons number is compulsory");
                return;
            }

            day=Integer.parseInt(days.getText().toString());
            per=Integer.parseInt(persons.getText().toString());

            Intent intent=new Intent(getApplicationContext(),roominfo.class);

                intent.putExtra("days", day);
                intent.putExtra("persons", per);
                startActivity(intent);
        }
    });
    }
}