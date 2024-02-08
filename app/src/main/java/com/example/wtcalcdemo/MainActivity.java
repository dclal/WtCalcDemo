package com.example.wtcalcdemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    TextView txtViewResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.mipmap.ic_launcher_wt_round);
        actionBar.setTitle(R.string.txtTitle);

        txtViewResults = findViewById(R.id.txtViewResults);
        EditText editTextInputWt = findViewById(R.id.editTextInputWt);
        RadioGroup radGroupConv = findViewById(R.id.radGroupConv);
        RadioButton radBtnLbsToKgs = findViewById(R.id.radBtnLbsToKgs);
        RadioButton radBtnKgsToLbs = findViewById(R.id.radBtnKgsToLbs);
        Button btnConvertWt = findViewById(R.id.btnConvertWt);
        //added another line from local on Feb8
        radGroupConv.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radBtnLbsToKgs){
                    Toast.makeText(MainActivity.this,
                            "Let us convert Pounds to Kilos",
                            Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.radBtnKgsToLbs){
                    Toast.makeText(MainActivity.this,
                            "Let us convert kilos to pounds",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

       // radGroupConv.getCheckedRadioButtonId() --> -1 if nothing is onContextItemSelected()
        // --> R.id.radBtnLbsToKgs if that radio button is checked
        // --> R.id.radBtnKgsToLbs if the other radio button is checked

        //First, set up a button click listener for btnConvertWt
        btnConvertWt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat df = new DecimalFormat("#.##");
                String outputTxt;
                int selectedRadBtn = radGroupConv.getCheckedRadioButtonId();
                double convertedWeight =0;
                if(editTextInputWt.getText().toString().isEmpty()){
                    //Display an error message if editTextInputWt is empty
                    Toast.makeText(MainActivity.this, "Please input a weight to convert", Toast.LENGTH_SHORT).show();
                } else {
                    double weightInp = Double.parseDouble(editTextInputWt.getText().toString());
                    if(radGroupConv.getCheckedRadioButtonId() == -1){
                        //In that listener, do the following:
                        //Display an error message if no radio button is checked (id is -1)
                        Toast.makeText(MainActivity.this, "Please select a conversion", Toast.LENGTH_SHORT).show();
                    } else {
                        if(radBtnLbsToKgs.isChecked()){
                            if(weightInp > 1000){
                                //If Pounds to Kilos is checked and parsed inputWt is > 1000,
                                // display an error message saying input weight in pounds must be <= 1000
                                Toast.makeText(MainActivity.this, "Input weight in pounds must be <= 1000", Toast.LENGTH_SHORT).show();
                            } else {
                                //Otherwise compute outputWtInKilos = inputWt/2.2
                                convertedWeight = weightInp / 2.2;
                                outputTxt = df.format(convertedWeight);
                                txtViewResults.setText("Converted weight = "+outputTxt+" kg");
                            }
                        } else if (radBtnKgsToLbs.isChecked()){
                            if(weightInp > 500){
                                //If Kilos to Pounds is checked and parsed inputWt is > 500,
                                // display an error message saying input weight in pounds must be <= 500
                                Toast.makeText(MainActivity.this, "Input weight in pounds must be <= 500", Toast.LENGTH_SHORT).show();
                            } else {
                                //Otherwise compute outputWtInPounds = inputWt*2.2
                                convertedWeight = weightInp * 2.2;
                                outputTxt = df.format((convertedWeight));
                                txtViewResults.setText("Converted weight = "+outputTxt+" lbs");
                            }
                        }
                    }
                }
            }
        });


        //Display the output weight in txtViewResults using correct
        //unit as Kgs (if converting from pounds to kilos) and
        //Lbs (if converting kilos to pounds)

    }
}