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

public class TranslateActivity extends Activity implements TranslateTask.TranslateTaskListener {

	public final String EXTRA_EDIT_TEXT = "extra_edit_text_result";
	private final int DICT_SELECT_CODE = 17;

	private String dict = "en-ru";
	private Button buttonTranslate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.translate);

		final Button dictionary_select =
				(Button) findViewById(R.id.translate_button_select_dictionary);
		dictionary_select.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(TranslateActivity.this, DictionarySelectActivity.class);
				startActivityForResult(i, DICT_SELECT_CODE);
			}
		});

		buttonTranslate = (Button) findViewById(R.id.translate_button_translate);
		buttonTranslate.setText(getString(R.string.translate_button_translate) + " " + dict);
		buttonTranslate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String origin = ((TextView) findViewById(R.id.translate_edit_text_origin))
						.getText().toString();
				new TranslateTask(TranslateActivity.this, "translate")
						.execute(origin, dict);
			}
		});

		EditText editTextTranslateResult =
				(EditText) findViewById(R.id.translate_edit_text_origin);
		editTextTranslateResult.setText(getIntent() != null
				? getIntent().getStringExtra(EXTRA_EDIT_TEXT) : "getIntent() == null");
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK && requestCode == DICT_SELECT_CODE) {
			dict = data.getStringExtra(DictionarySelectActivity.EXTRA_DICT);
			buttonTranslate.setText(getString(R.string.translate_button_translate) + " " + dict);
		}
	}

	@Override
	public void onPostExecute(JSONObject json) {
		findViewById(R.id.translate_progress_bar_wait).setVisibility(View.GONE);
		String s = "";
		try {
			s = json.getJSONArray("text").getString(0);
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		((TextView) findViewById(R.id.translate_edit_text_dest)).setText(s);
	}

	@Override
	public void onPreExecute() {
		findViewById(R.id.translate_progress_bar_wait).setVisibility(View.VISIBLE);
	}
}
