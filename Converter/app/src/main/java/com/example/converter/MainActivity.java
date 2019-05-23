package com.example.converter;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText LHS;
    private TextView RHS, history;
    private RadioButton F2C, C2F;
    double input, output;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LHS = findViewById(R.id.editText);
        RHS = findViewById(R.id.textView);
        F2C = findViewById(R.id.F2C);
        C2F = findViewById(R.id.C2F);
        button = findViewById(R.id.button);
        history = findViewById(R.id.textView3);

        history.setMovementMethod(new ScrollingMovementMethod());
    }

    public void convertClicked(View view){
        if(F2C.isChecked() || C2F.isChecked()){
            if(LHS.getText().toString().trim().length()!=0){
                input = Double.parseDouble(String.valueOf(LHS.getText()));

                String newText = LHS.getText().toString();
                String historyText = history.getText().toString();

                if (F2C.isChecked()) {
                    output = fToC(input);
                    RHS.setText(String.format("%.1f", output) + "");
                    history.setText(String.format("%s%n%s", "F to C: " + input + " ➔ " + String.format("%.1f", output), historyText));
                }
                if (C2F.isChecked()) {
                    output = cToF(input);
                    RHS.setText(String.format("%.1f", output) + "");
                    history.setText(String.format("%s%n%s", "C to F: " + input + " ➔ " + String.format("%.1f", output), historyText));
                }

            }
            else {
                Toast.makeText(this, "Please enter value to be converted", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Please select one of the option for conversion", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected  void onSaveInstanceState (Bundle outState){

        outState.putString("HISTORY",history.getText().toString());
        outState.putString("OUTPUT",RHS.getText().toString());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState (Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        String historyBundle = savedInstanceState.getString("HISTORY");
        String outputBundle = savedInstanceState.getString(("OUTPUT"));

        history.setText(historyBundle);
        RHS.setText(outputBundle);
    }

    public double fToC(double num){
        return ((num-32)/1.8);
    }
    public double cToF(double num){
        return ((num*1.8)+32);
    }

}
