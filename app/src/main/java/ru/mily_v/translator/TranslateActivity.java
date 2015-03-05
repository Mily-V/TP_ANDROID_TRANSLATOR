package ru.mily_v.translator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TranslateActivity extends Activity implements TranslateTask.TranslateTaskListener {

	public static final String EXTRA_EDIT_TEXT = "extra_edit_text_result";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.translate);

		final Button translate = (Button) findViewById(R.id.translate_button_translate);
		translate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String origin = ((TextView) findViewById(R.id.translate_edit_text_origin)).getText().toString();
				new TranslateTask(TranslateActivity.this, "translate").execute(origin, "en-ru");
			}
		});

		EditText editTextTranslateResult = (EditText) findViewById(R.id.translate_edit_text_origin);
		editTextTranslateResult.setText(getIntent() != null ? getIntent().getStringExtra
				(TranslateActivity.EXTRA_EDIT_TEXT) : "getIntent() == null");

	}

	@Override
	public void onPostExecute(String s) {
		findViewById(R.id.translate_progress_bar_wait).setVisibility(View.INVISIBLE);
		((TextView) findViewById(R.id.translate_edit_text_dest)).setText(s);
	}

	@Override
	public void onPreExecute() {
		findViewById(R.id.translate_progress_bar_wait).setVisibility(View.VISIBLE);
	}
}
