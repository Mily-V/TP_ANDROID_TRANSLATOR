package ru.mily_v.translator;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.concurrent.TimeUnit;

public class TranslateActivity extends Activity {

	public static final String EXTRA_EDIT_TEXT = "extra_edit_text_result";
	private ProgressBar progressBar;
	private EditText editTextTranslateResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.translate);

		final Button translate = (Button) findViewById(R.id.translate_button_translate);
		progressBar = (ProgressBar) findViewById(R.id.translate_progress_bar_wait);
		translate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				progressBar.setVisibility(View.VISIBLE);
				new AsyncTaskExample().execute();
			}
		});

		editTextTranslateResult = (EditText) findViewById(R.id.translate_edit_text_origin);
		editTextTranslateResult.setText(getIntent() != null ? getIntent().getStringExtra
				(TranslateActivity.EXTRA_EDIT_TEXT) : "getIntent() == null");

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

	public class AsyncTaskExample extends AsyncTask<Integer, Void, Void> {

		@Override
		protected Void doInBackground(Integer... params) {
			for (int i = 0; i < 2; i++) {
				try {
					TimeUnit.SECONDS.sleep(1);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void aVoid) {
			editTextTranslateResult = (EditText) findViewById(R.id.translate_edit_text_dest);
			editTextTranslateResult.setText("Типа переведенный текст");
			progressBar.setVisibility(View.INVISIBLE);
		}

	}
}
