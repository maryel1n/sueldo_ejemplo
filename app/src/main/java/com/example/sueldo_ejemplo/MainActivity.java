package com.example.sueldo_ejemplo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] EmployeesNames ={"María Vera","Carlos Alfaro","Miguel Herrea","Patricio Flores","Claudia Olivares"};

    private EditText txtHoras, txtDias, txtMontoHoras, txtDscto, txtSB;
    private CheckBox chbxPago, chbxDcto;
    private RadioGroup rgRedondeo;
    private RadioButton rbRedondeo, rbNoRedondeo;
    private Button btnLimpiar, btnCalcular;
    private TextView lbl_pago, lbl_dcto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spin = (Spinner) findViewById(R.id.simpleSpinner);
        spin.setOnItemSelectedListener(this);


        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, EmployeesNames);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        txtHoras = (EditText)findViewById(R.id.txtMontoHoras);
        txtDias = (EditText)findViewById(R.id.txtDias);
        txtMontoHoras = (EditText)findViewById(R.id.txtMontoHoras);
        txtSB = (EditText)findViewById(R.id.txtSB);
        txtDscto = (EditText)findViewById(R.id.txtDscto);
        chbxPago = (CheckBox)findViewById(R.id.chbxPago);
        chbxDcto = (CheckBox)findViewById(R.id.chbxDcto);
        rgRedondeo = (RadioGroup)findViewById(R.id.rgRedondeo);
        rbNoRedondeo = (RadioButton)findViewById(R.id.rbNoRedondeo);
        btnLimpiar = (Button)findViewById(R.id.btnLimpiar);
        btnCalcular = (Button)findViewById(R.id.btnCalcular);
        lbl_dcto = (TextView)findViewById(R.id.lbl_dcto);
        lbl_pago = (TextView)findViewById(R.id.lbl_pago);

    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        Toast.makeText(getApplicationContext(), EmployeesNames[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    public void calcular (View view){

        int horas = Integer.parseInt(txtHoras.getText().toString());
        int dias = Integer.parseInt(txtDias.getText().toString());
        int montoHoras = Integer.parseInt(txtMontoHoras.getText().toString());
        int sueldo_base = Integer.parseInt(txtSB.getText().toString());
        double descuento = Double.parseDouble(txtDscto.getText().toString());
        descuento = descuento/100;


        int horas_mensuales = horas*dias;
        double pago = horas_mensuales*montoHoras;


        if(chbxPago.isChecked() == true){
            lbl_pago.setText(String.valueOf(pago));
        }
        if(chbxDcto.isChecked() == true && pago > sueldo_base){
            descuento = pago*descuento;
            lbl_dcto.setText(String.valueOf(descuento));
            pago = pago - descuento;
            lbl_pago.setText(String.valueOf(pago));
        }
        if (rgRedondeo.getCheckedRadioButtonId() == R.id.rbRedondeo) {
            lbl_pago.setText(String.valueOf(""));
            int pago_redondeo = (int)Math.round(pago);
            lbl_pago.setText(String.valueOf(pago_redondeo));
            int dcto_redondeo = (int)Math.round((descuento));
            lbl_dcto.setText(String.valueOf(dcto_redondeo));
        }


    }

    public void limpiar(View view){

        txtHoras.setText(String.valueOf(""));
        txtDias.setText(String.valueOf(""));
        txtMontoHoras.setText(String.valueOf(""));
        txtSB.setText(String.valueOf(""));
        txtDscto.setText(String.valueOf(""));
        chbxPago.setText(String.valueOf(""));
        chbxDcto.setText(String.valueOf(""));
        chbxDcto.setText(String.valueOf(""));
        lbl_pago.setText(String.valueOf(""));
        lbl_dcto.setText(String.valueOf(""));

        if (chbxPago.isChecked() == true){
            chbxPago.setChecked(false);
        }

        if (chbxDcto.isChecked() == true){
            chbxDcto.setChecked(false);
        }

        if (rgRedondeo.getCheckedRadioButtonId() == R.id.rbRedondeo){
            rbRedondeo.setChecked(false);
        }

        if (rgRedondeo.getCheckedRadioButtonId() == R.id.rbNoRedondeo){
            rbNoRedondeo.setChecked(false);
        }

        Toast.makeText(getApplicationContext(), "Variables Limpias", Toast.LENGTH_LONG).show();

    }
}
