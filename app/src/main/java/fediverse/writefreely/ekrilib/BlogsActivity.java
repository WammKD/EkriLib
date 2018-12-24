package fediverse.writefreely.ekrilib;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import fediverse.writefreely.api.model.Collection;
import java.io.IOException;

public class BlogsActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_blogs);

		new MyTask().execute();
	}

	private class MyTask extends AsyncTask<Void, Void, Collection[]> {
		@Override
		protected Collection[] doInBackground(Void... params) {
			try {
				return LoginActivity.wf.retrieveUserCollections().getData();
			} catch(final IOException e) {
				return new Collection[] {};
			}
		}

		@Override
		protected void onPostExecute(final Collection[] dataSet) {
			if(dataSet.length == 0) {
				Toast.makeText(BlogsActivity.this,
				               "Don' messed up!",
				               Toast.LENGTH_SHORT).show();
			}

			((ListView) findViewById(R.id.blogs_listView)).setAdapter(new CustomAdapter(dataSet,
			                                                                            getApplicationContext()));
		}
	}

	private static class CustomAdapter extends ArrayAdapter<Collection> implements OnClickListener {

		// View lookup cache
		private static class ViewHolder {
			TextView txtName;
			TextView txtDescription;
		}

		public CustomAdapter(final Collection[] data, final Context context) {
			super(context, R.layout.row_item_blogs, data);
		}

		@Override
		public void onClick(final View v) {
			Collection dataModel = super.getItem((Integer) v.getTag());

			Toast.makeText(super.getContext(), "I clicked", Toast.LENGTH_SHORT);
		}

		@Override
		public View getView(final int       position,
		                          View      convertView,
		                    final ViewGroup parent) {
			final Collection  dataModel = super.getItem(position);
			      ViewHolder viewHolder = new ViewHolder();

			if(convertView == null) {
				convertView = LayoutInflater.from(super.getContext())
				                            .inflate(R.layout.row_item_blogs,
				                                     parent,
				                                     false);

				viewHolder.txtName        = convertView.findViewById(R.id.name);
				viewHolder.txtDescription = convertView.findViewById(R.id.desc);

				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}

			viewHolder.txtName.setText(dataModel.getTitle());
			viewHolder.txtDescription.setText(dataModel.getDescription());

			return convertView;
		}
	}
}
