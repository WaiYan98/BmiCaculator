package com.example.bmicaculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity {

    public static final String EXTRA_BMI = "EXTRA_BMI";
    public static final String EXTRA_BMI_TEXT = "EXTRA_BMI_TEXT";
    public static final String EXTRA_BMI_DESC = "EXTRA_BMI_DESC";

    @BindView(R.id.btn_re_caculate)
    Button btnReCaculate;
    @BindView(R.id.txt_result_bmi_num)
    TextView txtResultBmiNum;
    @BindView(R.id.txt_result_text)
    TextView txtResultText;
    @BindView(R.id.txt_result_description)
    TextView txtResultDescription;
    private double bmi;
    private String bmiDes;
    private Result bmiText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        if (intent != null) {
            bmi = intent.getDoubleExtra(EXTRA_BMI, 0);
            bmiText = Result.valueOf(intent.getStringExtra(EXTRA_BMI_TEXT));
            bmiDes = intent.getStringExtra(EXTRA_BMI_DESC);
        }

        Log.d("google", "bmi result" + bmi);

        btnReCaculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMain();
            }
        });

        txtResultBmiNum.setText(Double.toString(bmi));
        txtResultText.setText(bmiText.name());
        txtResultDescription.setText(bmiDes);
    }

    public void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
