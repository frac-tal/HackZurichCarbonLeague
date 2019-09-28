package com.example.carbonleague;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CreateLeague extends AppCompatActivity {

    EditText textViewLeagueName;
    EditText editTextPhone1;
    EditText editTextPhone2;
    RadioGroup radioGroupPickPrice;
    Button buttonAddNumber;
    Button buttonDone;
    List<EditText> moreNumbers = new ArrayList<>();
    EditText lastPhone = editTextPhone2;
    LinearLayout linearLayoutNumbers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_league);

        textViewLeagueName = findViewById(R.id.edit_league_name);
        editTextPhone1 = findViewById(R.id.phone1);
        editTextPhone2 = findViewById(R.id.phone2);
        radioGroupPickPrice = findViewById(R.id.price_pick);
        buttonAddNumber = findViewById(R.id.add_number);
        buttonDone = findViewById(R.id.button_done);
        linearLayoutNumbers = findViewById(R.id.more_numbers_linear);

        buttonAddNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = new EditText(CreateLeague.this);
                Resources r = CreateLeague.this.getResources();
                int px = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        8,
                        r.getDisplayMetrics()
                );
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.gravity = Gravity.CENTER;
                params.setMargins(px, px, px, px);
                editText.setLayoutParams(params);
                editText.setHint("Enter Phone Number");
                editText.setInputType(InputType.TYPE_CLASS_PHONE);

                linearLayoutNumbers.addView(editText);
                moreNumbers.add(editText);
            }
        });

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });


    }
}
