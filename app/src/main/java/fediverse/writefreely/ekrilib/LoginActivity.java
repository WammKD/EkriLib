package fediverse.writefreely.ekrilib;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import fediverse.writefreely.api.WriteFreelyAPIwithUser;
import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
	protected static WriteFreelyAPIwithUser       wf;
	private          Button                 loginBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_login);

		loginBtn = (Button)   super.findViewById(R.id.button_login);



		String          orig      = super.getString(R.string.example_text);
		int             secondAt  = orig.indexOf('@', 1);

		SpannableString formatted = new SpannableString(orig);
		formatted.setSpan(new StyleSpan(Typeface.BOLD),
		                  1,
		                  secondAt,
		                  Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		((TextView) super.findViewById(R.id.textView_username_example)).setText(formatted);

		formatted = new SpannableString(orig);
		formatted.setSpan(new StyleSpan(Typeface.BOLD),
		                  secondAt + 1,
		                  orig.length(),
		                  Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		((TextView) super.findViewById(R.id.textView_instance_example)).setText(formatted);



		loginBtn.setOnClickListener((final View v) -> new MyTask().execute(((EditText) super.findViewById(R.id.editText_instance)).getText().toString(),
		                                                                   ((EditText) super.findViewById(R.id.editText_user)).getText().toString(),
		                                                                   ((EditText) super.findViewById(R.id.editText_password)).getText().toString()));
	}

	private class MyTask extends AsyncTask<String, Void, Boolean> {
		@Override
		protected void onPreExecute() {
			loginBtn.setEnabled(false);

			Toast.makeText(LoginActivity.this,
			               "Attempting login…",
			               Toast.LENGTH_SHORT);
		}

		@Override
		protected Boolean doInBackground(String... params) {
			try {
				LoginActivity.wf = new WriteFreelyAPIwithUser("https://" + params[0] + "/",
				                                              params[1],
				                                              params[2]);

				return true;
			} catch(final Exception e) {
				return false;
			}
		}

		@Override
		protected void onPostExecute(final Boolean wasSuccessful) {
			loginBtn.setEnabled(true);

			if(wasSuccessful) {
				Toast.makeText(LoginActivity.this,
				               "User and Password is correct",
				               Toast.LENGTH_SHORT).show();

				startActivity(new Intent(LoginActivity.this,
				                         MainActivity.class));
				finish();
			} else {
				Toast.makeText(LoginActivity.this,
				               "User and Password is not correct",
				               Toast.LENGTH_SHORT).show();
			}
		}
	}
}
