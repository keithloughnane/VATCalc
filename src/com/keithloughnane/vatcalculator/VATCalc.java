package com.keithloughnane.vatcalculator;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class VATCalc extends Activity {

	private static final String IncVAT = "TOTAL";
	private static final String VAT = "VAT";
	private static final String ExVAT = "Inc VAT";
	
	private double dIncVAT;
	private double dVAT;
	private double dExVAT;
	
	EditText ExVatET;
	EditText VatET;
	EditText IncVatET;
	
	SeekBar exVatBar;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vatcalc);
		
		if(savedInstanceState == null) //App starting, not resuming from a saved/paused state
		{
			dIncVAT = 0.0;
			dVAT = 0.0;
			dExVAT = 0.0;
		}
		else //Restored from saved/paused state
		{
			dIncVAT = savedInstanceState.getDouble(IncVAT);
			dVAT = savedInstanceState.getDouble(VAT);
			dExVAT = savedInstanceState.getDouble(ExVAT);
		}
		Log.d("TempDebug","Got to Here");
		ExVatET = (EditText)findViewById(R.id.PreVATEditText);
		Log.d("TempDebug","1");
		VatET = (EditText)findViewById(R.id.VAT_text);
		Log.d("TempDebug","2");
		IncVatET = (EditText)findViewById(R.id.AfterVATText);
		Log.d("TempDebug","3");
		
		exVatBar = (SeekBar)findViewById(R.id.changeAmount);
		
		exVatBar.setOnSeekBarChangeListener(changeVatSeek);
		
		ExVatET.addTextChangedListener(exVATListener);
	}

	private OnSeekBarChangeListener changeVatSeek = new OnSeekBarChangeListener(){

		@Override
		public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
			
			dExVAT = exVatBar.getProgress()*100;
			ExVatET.setText(String.format("%.02f", dExVAT));
			updateVATAndTotal();
			
		}

		@Override
		public void onStartTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			
		}
		
		
		
	};
	
	private TextWatcher exVATListener = new TextWatcher(){

		@Override
		public void afterTextChanged(Editable arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence s, int arg1, int arg2,
				int arg3) {
			
			try{
				dExVAT = Double.parseDouble(s.toString());
			}
			
			catch(NumberFormatException e)
			{
				dExVAT = 0.0;
			}
			
			updateVATAndTotal();
		}
		
	};
	
	private void updateVATAndTotal()
	{
		//double iVAT = Double.parseDouble(VatET.getText().toString());
		
		double iVAT = dExVAT * .21;
		double iTotal = dExVAT + iVAT;
		
		VatET.setText(String.format("%.02f", iVAT));
		IncVatET.setText(String.format("%.02f", iTotal));
	}
	
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		
		/*private static final String IncVAT = "TOTAL";
		private static final String VAT = "VAT";
		private static final String ExVAT = "Inc VAT";
		
		private double dIncVAT;
		private double dVAT;
		private double dExVAT;
		*/
		
		outState.putDouble(IncVAT, dIncVAT);
		outState.putDouble(VAT, dVAT);
		outState.putDouble(ExVAT, dExVAT);
		
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vatcalc, menu);
		return true;
	}

}
