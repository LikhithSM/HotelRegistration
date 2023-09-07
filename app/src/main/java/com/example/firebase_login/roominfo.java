package com.example.firebase_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class roominfo extends AppCompatActivity {

    public static final String PERSONS="PERSONS";
    public static final String DAYS="DAYS";

    int per,day;
    Spinner spinner1,spinner2;
    List<String> items,items1;
    String item,item1;
    TextView total,remaining;

    CheckBox chkbox;

    EditText advance;

    Button button;
    int roomcost=0,amecost=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roominfo);

        total=findViewById(R.id.total);
        advance=findViewById(R.id.advance);
        remaining=findViewById(R.id.remaining);

        chkbox=findViewById(R.id.checkbox);

        button=findViewById(R.id.calc);

        spinner1=findViewById(R.id.spinner1);
        spinner2=findViewById(R.id.spinner2);


        Intent getint= getIntent();
        int days= getint.getIntExtra("days",0);
        int persons= getint.getIntExtra("persons",0);


        items=new ArrayList<>();


        items.add("Delux");
        items.add("Suite");
        ArrayAdapter adapter =new ArrayAdapter(getApplicationContext(),R.layout.textspinner,items);
       spinner1.setAdapter(adapter);


       spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               item=spinner1.getSelectedItem().toString();

               if(item=="Delux"){
                   roomcost=2500;
               }
               else{
                   roomcost=4000;
           }
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });



        items1=new ArrayList<>();

        items1.add("AC");
        items1.add("Locker");
        items1.add("Both");
        items1.add("None");

        ArrayAdapter adapter1 =new ArrayAdapter(getApplicationContext(),R.layout.textspinner,items1);
        spinner2.setAdapter(adapter1);



        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                item1=spinner2.getSelectedItem().toString();
                if(item1=="AC"){
                    amecost=1000;
                } else if (item1=="Locker") {
                    amecost=300;
                }
                else if (item1=="Both") {
                    amecost=1300;
                }
                else {
                    amecost=110;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(TextUtils.isEmpty(advance.getText().toString())) {
                    advance.setError("Advance payment is compulsory");
                    return;
                }


                int ttlrmcst,ttlamcost, tot=0,ad=0,rem;


                ad=Integer.parseInt(advance.getText().toString());

                ttlrmcst=roomcost*days;
                ttlamcost=amecost*days;
                tot=ttlamcost+ttlrmcst;

                if(chkbox.isChecked()){
                    tot=tot+1000;
                }

                total.setText("Total:\nRoom cost:" + ttlrmcst + "\nAmenities cost:" + ttlamcost + "\nTotal:" + tot);
                rem = tot - ad;
                remaining.setText("Remaining is: " + rem);

            }
        });


    }
}