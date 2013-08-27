package com.xpresinfo.tipCalculator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TipCalulatorActivity extends Activity {
	
	TextView tvTip;
	EditText etTotalAmt;
	Button btnTen;
	Button btnTwenty;
	Button btnThirty;
	Button btnCalculate;
	Button btnOther;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calulator);
        tvTip=(TextView)findViewById(R.id.tvTipAmount);
        etTotalAmt = (EditText)findViewById(R.id.etTotalAmount);
        btnTen = (Button)findViewById(R.id.btnTen);
        btnTwenty = (Button)findViewById(R.id.btnTwenty);
        btnThirty = (Button)findViewById(R.id.btnThirty);
        btnOther = (Button)findViewById(R.id.btnOther);
        btnCalculate = (Button)findViewById(R.id.btnTipCalculate);
//        checkFieldsForEmptyValues();
        etTotalAmt.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

			@Override
			public void afterTextChanged(Editable s) {
				checkFieldsForEmptyValues();
			}
          });
        
    }
    
    public void checkFieldsForEmptyValues(){
        String amt = getTotalAmount();

        if(amt == null || amt.length() == 0){
        	disableButtons();
        } else {
        	enableButtons();
        }
    }
    
    public void disableButtons(){
    	btnTen.setEnabled(false);
        btnTwenty.setEnabled(false);
        btnThirty.setEnabled(false);
        btnCalculate.setEnabled(false);
        btnOther.setEnabled(false);
    }
    
    public void enableButtons(){
    	btnTen.setEnabled(true);
        btnTwenty.setEnabled(true);
        btnThirty.setEnabled(true);
        btnOther.setEnabled(true);
    }

    // Gets the total amount from Edit Text
    public String getTotalAmount(){ 
    	return etTotalAmt.getText().toString();
    }
    
    public void calculateTenPercent(View v){
    	tvTip.setText(calculateTip("10.0"));
    	etTotalAmt.setText("");
    }
    
    public void calculateTwentyPercent(View v){
    	tvTip.setText(calculateTip("20.0"));
    	etTotalAmt.setText("");
    }
    
    
    public void calculateThirtyPercent(View v){
    	tvTip.setText(calculateTip("30.0"));
    	etTotalAmt.setText("");
    	
    }
    
    public void showOther(View v){
    	EditText etTipPercent = (EditText)findViewById(R.id.etTipPercent);
    	etTipPercent.setEnabled(true);
    	btnCalculate.setEnabled(true);
    	
    }
    
    public void calculateOtherTip(View v){
    	EditText etTipPercent = (EditText)findViewById(R.id.etTipPercent);
    	String tip = etTipPercent.getText().toString();
    	tvTip.setText(calculateTip(tip));
    	etTotalAmt.setText("");
    	etTipPercent.setText("");
    	etTipPercent.setEnabled(false);
    	btnCalculate.setEnabled(false);
    }
    
    //Calculates tip. Pass total and percentage
    public String calculateTip(String tipPercent){
    	InputMethodManager imm = (InputMethodManager)getSystemService(
    		      Context.INPUT_METHOD_SERVICE);
    		imm.hideSoftInputFromWindow(etTotalAmt.getWindowToken(), 0);
    	Float total = Float.parseFloat(getTotalAmount());
    	Float percent = Float.parseFloat(tipPercent);
    	Float tip = 0.0f;
    	if (total>0 && percent>0){
    		tip = calculatePercentage(total, percent); 
    	}
    	return tip.toString();
    }
    
    //Calculates percentage.
    public Float calculatePercentage(Float value1, Float value2){
    	return (value1*value2)/100;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tip_calulator, menu);
        return true;
    }
    
}
