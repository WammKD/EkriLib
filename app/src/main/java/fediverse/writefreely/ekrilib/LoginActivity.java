package fediverse.writefreely.ekrilib;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import fediverse.writefreely.api.WriteFreelyAPIwithUser;
import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
	private TextView                     attempts;
	private Button                       loginBtn;
	private int                    attemptCounter = 5;
	private WriteFreelyAPIwithUser             wf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_login);

		attempts = (TextView) super.findViewById(R.id.textView_attempt_Count);
		loginBtn = (Button)   super.findViewById(R.id.button_login);

		LoginButton();
	}

	private void LoginButton() {
		final EditText username = (EditText) super.findViewById(R.id.editText_user);
		final EditText password = (EditText) super.findViewById(R.id.editText_password);

		attempts.setText("" + attemptCounter);

		loginBtn.setOnClickListener(new OnClickListener() {
		                            	@Override
		                            	public void onClick(View v) {
		                            		if(username.getText().toString().equals("user") &&
		                            		   password.getText().toString().equals("pass")) {
		                            			Toast.makeText(LoginActivity.this,
		                            			               "User and Password is correct",
		                            			               Toast.LENGTH_SHORT).show();

		                            			startActivity(new Intent(LoginActivity.this,
		                            			                         MainActivity.class));
		                            		} else {
		                            			Toast.makeText(LoginActivity.this,
		                            			               "User and Password is not correct",
		                            			               Toast.LENGTH_SHORT).show();

		                            			attemptCounter--;
		                            			attempts.setText(Integer.toString(attemptCounter));

		                            			if(attemptCounter == 0) {
		                            				loginBtn.setEnabled(false);
		                            			}
		                            		}
		                            	}
		                            });
	}

	private class MyTask extends AsyncTask<Void, Void, String> {
		@Override
		protected String doInBackground(Void... params) {
			try {
				wf = new WriteFreelyAPIwithUser("https://gospel.sunbutt.faith/",
				                                "redacted",
				                                "redacted");

				return "Logged into WriteFreely!";
			} catch(final IOException e) {
				return "FUCK";
			}
		}

		@Override
		protected void onPostExecute(final String result) {
			Toast.makeText(LoginActivity.this,
			               result,
			               Toast.LENGTH_SHORT).show();
		}
	}
}
