package ru.mily_v.translator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetermineActivity extends Activity {

	public static final String EXTRA_EDIT_TEXT_RESULT = "extra_edit_text_result";
	private EditText editTextForDetermine;
	private EditText editTextForTranslate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.determine);
		editTextForDetermine = (EditText) findViewById(R.id.determine_edit_text_for_determine);
		editTextForTranslate = (EditText) findViewById(R.id.determine_edit_text_for_translate);

		final Button goToTranslateActivity = (Button) findViewById(R.id.determine_button_start_translate);
		goToTranslateActivity.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(DetermineActivity.this, TranslateActivity.class);
				i.putExtra(EXTRA_EDIT_TEXT_RESULT, editTextForDetermine.getText().toString());
				startActivity(i);
			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
