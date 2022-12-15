package com.meliskaradag.bmiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TextWatcher, SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener {


    protected EditText editText;
    protected TextView boy_tw, default_kilo, idealK_tw, durum_bilgisi;
    protected SeekBar seekbar;
    protected RadioGroup radioGroup;
    protected boolean man = true;
    protected double height = 0.0, bmi;
    protected int weight = 50;
    protected int idealKilo_Bay, idealKilo_Bayan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.editTextNumber);
        boy_tw=findViewById(R.id.boy_tw);
        default_kilo=findViewById(R.id.default_kilo);
        idealK_tw=findViewById(R.id.idealK_tw);
        durum_bilgisi=findViewById(R.id.durum_bilgisi);
        radioGroup=findViewById(R.id.radioGrup);
        seekbar=findViewById(R.id.seekBar);

        editText.addTextChangedListener(this);

        seekbar.setOnSeekBarChangeListener(this);

        radioGroup.setOnCheckedChangeListener(this);

        //guncelle();
    }


    protected void guncelle(){

        idealKilo_Bay = (int) (50 + 2.3*(height*100*0.4-60));
        idealKilo_Bayan = (int) (45.5 + 2.3*(height*100*0.4-60));
        bmi = weight / (height*height);

        default_kilo.setText(String.valueOf(weight) + " " + "kg");
        boy_tw.setText(String.valueOf(height) + " m");

        if(man){ //bay ise

            idealK_tw.setText(String.valueOf(idealKilo_Bay) + " kg");

            if( bmi <= 20.7){ //zayıf
                durum_bilgisi.setBackgroundResource(R.color.zayif);
                durum_bilgisi.setText(R.string.zayif);
            }else if(20.7 < bmi && bmi <= 26.4){ //ideal kilo
                durum_bilgisi.setText(R.string.ideal);
                durum_bilgisi.setBackgroundResource(R.color.ideal);
            }else if(26.4 < bmi && bmi <= 27.8){ //normal kilodan fazla
                durum_bilgisi.setText(R.string.idealden_fazla);
                durum_bilgisi.setBackgroundResource(R.color.idealden_fazla);
            }else if(27.8 < bmi && bmi <= 31.1){ //fazla kilolu
                durum_bilgisi.setText(R.string.fazla_kilolu);
                durum_bilgisi.setBackgroundResource(R.color.fazla_kilolu);
            }else if(31.1 < bmi && bmi <= 34.9){ //obez
                durum_bilgisi.setText(R.string.obez);
                durum_bilgisi.setBackgroundResource(R.color.obez);
            }else{ //doktor tedavisi
                durum_bilgisi.setText(R.string.doktora);
                durum_bilgisi.setBackgroundResource(R.color.doktora);
            }

        }else { //bayan ise

            idealK_tw.setText(String.valueOf(idealKilo_Bayan) + " kg");

            if(bmi <= 19.1){ //zayıf
                durum_bilgisi.setText(R.string.zayif);
                durum_bilgisi.setBackgroundResource(R.color.zayif);
            }else if(19.1< bmi && bmi <= 25.8){ //ideal kilo
                durum_bilgisi.setText(R.string.ideal);
                durum_bilgisi.setBackgroundResource(R.color.ideal);
            }else if(25.8 < bmi && bmi <= 27.3){ //normal kilodan fazla
                durum_bilgisi.setText(R.string.idealden_fazla);
                durum_bilgisi.setBackgroundResource(R.color.idealden_fazla);
            }else if(27.3 < bmi && bmi <= 32.3){ //fazla kilolu
                durum_bilgisi.setText(R.string.fazla_kilolu);
                durum_bilgisi.setBackgroundResource(R.color.fazla_kilolu);
            }else if(32.3 < bmi && bmi <= 34.9){ //obez
                durum_bilgisi.setText(R.string.obez);
                durum_bilgisi.setBackgroundResource(R.color.obez);
            }else{ //doktor tedavisi
                durum_bilgisi.setText(R.string.doktora);
                durum_bilgisi.setBackgroundResource(R.color.doktora);
            }

        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence s, int i, int i1, int i2) {

        try {

            height = Double.parseDouble(s.toString())/100.0;

        }catch (NumberFormatException ne) {
            height = 0.0;
        }
        guncelle();

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

        weight = 30 + progress;
        guncelle();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

        if(checkedId == R.id.bay)
            man = true;
        else if(checkedId == R.id.bayan)
            man = false;

        guncelle();
    }
}