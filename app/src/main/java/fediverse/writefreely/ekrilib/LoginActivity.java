package fediverse.writefreely.ekrilib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class LoginActivity extends AppCompatActivity {
	private int attemptCounter = 5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_login);

		LoginButton();
	}

	private void LoginButton() {
		final EditText username = (EditText) super.findViewById(R.id.editText_user);
		final EditText password = (EditText) super.findViewById(R.id.editText_password);
		final TextView attempts = (TextView) super.findViewById(R.id.textView_attempt_Count);
		final Button   loginBtn = (Button)   super.findViewById(R.id.button_login);

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
}
