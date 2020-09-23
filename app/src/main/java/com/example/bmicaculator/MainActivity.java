package com.example.bmicaculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bmicaculator.bmi.BmiCalculator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    Gender gender;

    @BindView(R.id.layout_btn_male)
    LinearLayout layoutBtnMale;
    @BindView(R.id.layout_btn_female)
    LinearLayout layoutBtnFemale;
    @BindView(R.id.sb_height)
    SeekBar sbHeight;
    @BindView(R.id.btn_caculate)
    Button btnCaculate;
    @BindView(R.id.txt_age)
    TextView txtAge;
    @BindView(R.id.txt_weight)
    TextView txtWeight;
    @BindView(R.id.txt_height_ft)
    TextView txtHeightFt;
    @BindView(R.id.txt_height_in)
    TextView txtHeightIn;
    @BindView(R.id.fab_weight_plus)
    FloatingActionButton fabWeightPlus;
    @BindView(R.id.fab_weight_minus)
    FloatingActionButton fabWeightMinus;
    @BindView(R.id.fab_age_plus)
    FloatingActionButton fabAgePlus;
    @BindView(R.id.fab_age_minus)
    FloatingActionButton fabAgeMinus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        layoutBtnMale.setOnClickListener(view -> {
            gender = Gender.MALE;
            changeGenderColor();
        });

        layoutBtnFemale.setOnClickListener(view -> {
            gender = Gender.FEMALE;
            changeGenderColor();
        });

        fabWeightPlus.setOnClickListener(view -> {
            txtWeight.setText(Integer.toString((convertStringToInt(txtWeight.getText().toString()) + 1)));
            fabWeightMinus.setEnabled(true);
        });

        fabWeightMinus.setOnClickListener(view -> {
            if (isLessThanOne(convertStringToInt("" + txtWeight.getText()))) {
                fabWeightMinus.setEnabled(false);
                Toast.makeText(MainActivity.this, "Weight cannot be less than zero", Toast.LENGTH_SHORT).show();
            } else {
                txtWeight.setText(Integer.toString(convertStringToInt(txtWeight.getText().toString()) - 1));
            }
        });


        fabAgePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtAge.setText(Integer.toString(convertStringToInt(txtAge.getText().toString()) + 1));
                fabAgeMinus.setEnabled(true);
            }
        });


        fabAgeMinus.setOnClickListener(view -> {
            if (isLessThanOne(convertStringToInt("" + txtAge.getText()))) {
                fabAgeMinus.setEnabled(false);
                Toast.makeText(MainActivity.this, "Age cannot be less than zero", Toast.LENGTH_SHORT).show();
            } else {
                txtAge.setText(Integer.toString(convertStringToInt(txtAge.getText().toString()) - 1));
            }
        });

        btnCaculate.setOnClickListener(view -> {
            if (inputValid()) {
                BmiResult bmiResult = BmiCalculator.calculateBmi(getHeight(), convertStringToInt(txtWeight.getText().toString()));
                double bmiNumResult = bmiResult.getBmiNumResult();
                Result bmiTextResult = bmiResult.getBmiTxtResult();
                String bmiDescription = bmiResult.getBmiDescription();
                goToResult(bmiNumResult, bmiTextResult, bmiDescription);
            } else {
                Toast.makeText(MainActivity.this, "Please fill the the fields", Toast.LENGTH_SHORT).show();
            }
        });


        sbHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txtHeightFt.setText("" + i / 12);
                txtHeightIn.setText("" + i % 12);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void goToResult(double bmi, Result bmiTextResult, String bmiDescription) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(ResultActivity.EXTRA_BMI, bmi);
        intent.putExtra(ResultActivity.EXTRA_BMI_TEXT, bmiTextResult.name());
        intent.putExtra(ResultActivity.EXTRA_BMI_DESC, bmiDescription);
        startActivity(intent);
    }

    public boolean isLessThanOne(int num) {
        return num <= 0;
    }

    public boolean isGreaterThanZero(int num) {
        return num > 0;
    }

    public boolean inputValid() {
        return (gender != null && convertStringToInt("" + txtWeight.getText()) != 0 &&
                getHeight() != 0 && convertStringToInt("" + txtAge.getText()) != 0);
    }

    public int convertStringToInt(String str) {
        return Integer.parseInt(str);
    }

    public int getHeight() {
        return (convertStringToInt("" + txtHeightFt.getText()) * 12) + convertStringToInt("" + txtHeightIn.getText());
    }

    public void changeGenderColor() {
        if (gender == Gender.MALE) {
            layoutBtnMale.setBackgroundColor(ContextCompat.getColor(this, R.color.selectedColor));
            layoutBtnFemale.setBackgroundColor(ContextCompat.getColor(this, R.color.cardViewBackground));
        } else {
            layoutBtnFemale.setBackgroundColor(ContextCompat.getColor(this, R.color.selectedColor));
            layoutBtnMale.setBackgroundColor(ContextCompat.getColor(this, R.color.cardViewBackground));
        }
    }
}
