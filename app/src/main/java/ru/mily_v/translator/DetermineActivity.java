package ru.mily_v.translator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class DetermineActivity extends Activity implements TranslateTask.TranslateTaskListener {

	public static final String EXTRA_EDIT_TEXT_RESULT = "extra_edit_text_result";
	private EditText editTextForDetermine;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.determine);
		editTextForDetermine = (EditText) findViewById(R.id.determine_edit_text_src);

		findViewById(R.id.determine_button_determine)
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						String origin = ((TextView) findViewById(R.id.determine_edit_text_src))
								.getText().toString();
						new TranslateTask(DetermineActivity.this, "detect")
								.execute(origin);

					}
				});

		final Button goToTranslateActivity = (Button) findViewById(R.id
				.determine_button_to_translate);
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
	public void onPostExecute(JSONObject json) {
		findViewById(R.id.determine_progressbar).setVisibility(View.INVISIBLE);

		String s = null;
		try {
			s = json.getString("lang");
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		((EditText) findViewById(R.id.determine_edit_text_dest)).setText(s);
	}

	@Override
	public void onPreExecute() {
		findViewById(R.id.determine_progressbar).setVisibility(View.VISIBLE);
	}
}
