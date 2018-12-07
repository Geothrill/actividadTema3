package com.example.pepe_.myapplication;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView txtName = findViewById(R.id.txtName);
        final TextView txtSurname = findViewById(R.id.txtSurname);
        final TextView txtNumber = findViewById(R.id.txtNumber);
        final RadioGroup rgGender = findViewById(R.id.rgGender);
        final RadioButton rbMale = findViewById(R.id.rbMale);
        final RadioButton rbFemale = findViewById(R.id.rbFemale);
        final Spinner mcStatus = findViewById(R.id.mcStatus);
        final Switch hasChildren = findViewById(R.id.hasChildren);
        final Button reset = findViewById(R.id.reset);
        final Button send = findViewById(R.id.send);
        final TextView txtResult = findViewById(R.id.txtResult);

        txtName.getText().toString();
        txtSurname.getText().toString();
        txtNumber.getText().toString();

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rbMale.isChecked()) {
                    rbFemale.setChecked(false);
                }
                if (rbFemale.isChecked()) {
                    rbMale.setChecked(false);
                }
            }
        });
        Resources arraylist = getResources();

        final String[] status = arraylist.getStringArray(R.array.valores);;
        ArrayAdapter<String> statusSelect = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, status);
        mcStatus.setAdapter(statusSelect);


        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                txtName.setText("");
                txtSurname.setText("");
                txtNumber.setText("");
                rbFemale.setChecked(false);
                rbMale.setChecked(false);
                hasChildren.setChecked(false);
                txtResult.setText("");
                mcStatus.setSelection(0);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checkAge;
                boolean gender = !rbMale.isChecked();
                boolean child = hasChildren.isChecked();
                if (txtSurname.getText().toString().matches("")){
                    txtResult.setTextColor(getResources().getColor(R.color.red));
                    txtResult.setText( R.string.error1);
                }
                else{
                    if (txtName.getText().toString().matches("")){
                        txtResult.setTextColor(getResources().getColor(R.color.red));
                        txtResult.setText(R.string.error2);
                    }
                    else {
                        if (txtNumber.getText().toString().matches("")){
                            txtResult.setTextColor(getResources().getColor(R.color.red));
                            txtResult.setText(R.string.error3);
                        }
                        else {
                            if (!rbMale.isChecked() && !rbFemale.isChecked()){
                                txtResult.setTextColor(getResources().getColor(R.color.red));
                                txtResult.setText(R.string.error4);
                            }
                            else {
                                checkAge = parseInt(txtNumber.getText().toString()) >=18;

                                String select =  mcStatus.getSelectedItem().toString();

                                String txtFinal = txtSurname.getText().toString() + " , " + txtName.getText().toString() + " , ";

                                if(checkAge){
                                    txtFinal += getResources().getString(R.string.over) + " , ";
                                }
                                else {
                                    txtFinal += getResources().getString(R.string.under) + " , ";
                                }
                                if (gender){
                                    txtFinal += getResources().getString(R.string.rbFemale) ;
                                }
                                else{
                                    txtFinal += getResources().getString(R.string.rbMale) ;
                                }
                                txtFinal += " " + select;
                                if (child){
                                    txtFinal += getResources().getString(R.string.child) + ".";
                                }
                                else {
                                    txtFinal +=  ".";
                                }
                                txtResult.setText(txtFinal);
                                txtResult.setTextColor(getResources().getColor(R.color.black));
                            }
                        }
                    }
                }


            }
        });

    }
}

