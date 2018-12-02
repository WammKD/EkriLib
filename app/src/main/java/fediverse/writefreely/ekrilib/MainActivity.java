package fediverse.writefreely.ekrilib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.EditText;
import fediverse.writefreely.ekrilib.util.MarkdownUtil;
import ru.noties.markwon.Markwon;

public class MainActivity extends AppCompatActivity {
	private EditText et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et = (EditText) findViewById(R.id.plain_text_input);

		findViewById(R.id.butt).setOnClickListener(new OnClickListener() {
		                                           	@Override
		                                           	public void onClick(View view) {
		                                           		et.setText(Markwon.markdown(MainActivity.this,
		                                           		                            MarkdownUtil.conserveMarkdown(et.getText().toString())));
		                                           	}
		                                           });
	}
}
