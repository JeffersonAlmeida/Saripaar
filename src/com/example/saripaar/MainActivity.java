package com.example.saripaar;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.QuickRule;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;


public class MainActivity
extends Activity implements ValidationListener {
	
	@NotEmpty
	private EditText text;
	
	private Validator validator ;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        text = (EditText) findViewById(R.id.id);
        
        validator = new Validator(this);
        validator.setValidationListener(this);
        validator.put(text, r);
        
        
    }
    
    public void ok (View view){
    	validator.validate();
    }

	private QuickRule<EditText> r = new QuickRule<EditText>() {
		private static final int min = 4;
		@Override
		public String getMessage(Context arg0) {
			return "Must be bigger than " + min;
		}

		@Override
		public boolean isValid(EditText editText) {
			 String text = editText.getText().toString();
			return text.length() > min ;
		}
	};
    

	@Override
	public void onValidationFailed(List<ValidationError> errors) {
		for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
	}

	@Override
	public void onValidationSucceeded() {
		Toast.makeText(this, "Yay! we got it right!", Toast.LENGTH_SHORT).show();
	}

}
