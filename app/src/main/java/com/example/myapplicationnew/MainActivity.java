package com.example.myapplicationnew;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ebAmount)
    EditText ebAmount;
    @BindView(R.id.tipPercent)
    TextView tipPercent;
    @BindView(R.id.tip_total)
    TextView tip_total;
    @BindView(R.id.billTotalAmt)
    TextView billTotalAmt;

    float percentage = 0;
    float tipTotal = 0;
    float finalBillAmount = 0;
    float totalBillAmount = 0;

    float REGULAR_TIP_PERCENTAGE = 10;
    float DEFAULT_TIP_PERCENTAGE = 15;
    float EXCELLENT_TIP_PERCENTAGE = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setTipValues();

    }

    private void setTipValues() {

        tipPercent.setText(getString(R.string.main_msg_tipPercent, percentage));
        tip_total.setText(getString(R.string.main_msg_tipTotal, tipTotal));
        billTotalAmt.setText(getString(R.string.billTotalAmount, finalBillAmount));
    }
    @OnClick({R.id.regService, R.id.goodService, R.id.excService})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.regService:
                percentage = REGULAR_TIP_PERCENTAGE;
                break;
            case R.id.goodService:
                percentage = DEFAULT_TIP_PERCENTAGE;
                break;
            case R.id.excService:
                percentage = EXCELLENT_TIP_PERCENTAGE;
                break;
        }

        calculateFinalBill();
        setTipValues();
    }
    @OnTextChanged(R.id.ebAmount)
    public void onTextChanged() {
        calculateFinalBill();
        setTipValues();
    }

    private void calculateFinalBill() {

        if (percentage == 0)
            percentage = DEFAULT_TIP_PERCENTAGE;

        if(!ebAmount.getText().toString().equals("") && !ebAmount.getText().toString().equals("."))
            totalBillAmount = Float.valueOf(ebAmount.getText().toString());
        else
            totalBillAmount = 0;

        tipTotal = (totalBillAmount*percentage)/100;
        finalBillAmount = totalBillAmount + tipTotal;

    }
}
