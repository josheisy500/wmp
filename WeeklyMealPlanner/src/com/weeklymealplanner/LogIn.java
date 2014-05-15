package com.weeklymealplanner;

import java.io.IOException;
import java.util.List;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.weeklymealplanner.enduserendpoint.Enduserendpoint;
import com.weeklymealplanner.enduserendpoint.model.CollectionResponseEndUser;
import com.weeklymealplanner.enduserendpoint.model.EndUser;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends Activity {
	// array of objects of type Recipe
	private List<EndUser> users = null;
	EditText userName, password;
	int count = 0;

	int times = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_log_in);
		userName = (EditText) findViewById(R.id.editText1);
		password = (EditText) findViewById(R.id.editText2);
	}

	public void loginButton(View v) {

		// to check if fields are valid
		if (!isDataValid()) {
			Toast.makeText(getBaseContext(),
					"User Name or password is incorrect", Toast.LENGTH_SHORT)
					.show();
		}
		// check users credentials against google app engine datastore
		else {
			new ListOfUsersAsyncRetriever(this).execute();
		}
	}// end loginButton

	public void registerButton(View v) {

		// launch registration layout and class
		Intent i = new Intent(LogIn.this, Registration.class);
		startActivity(i);

	}// end register button

	// function to validate data
	public boolean isDataValid() {
		// to check if password is empty - invoke function
		boolean passwordValid = !getPassword().isEmpty();

		// to check if user name is empty - invoke function
		boolean userNameValid = !getUserName().isEmpty();

		// return the two outcomes of both functions
		return passwordValid && userNameValid;

	}// end

	// function to get contents of password text field
	public String getPassword() {
		return password.getText().toString().trim();
	}// end

	public String getUserName() {
		return userName.getText().toString().trim();
	}// end

	// class to retreive all users on the google app engine database
	// retreives users in background to confirm credentials of user input
	private class ListOfUsersAsyncRetriever extends
			AsyncTask<Void, Void, CollectionResponseEndUser> {

		Context context;
		private ProgressDialog pd;

		public ListOfUsersAsyncRetriever(Context context) {
			this.context = context;
		}

		protected void onPreExecute() {
			super.onPreExecute();
			pd = new ProgressDialog(context);
			pd.setMessage("Processing Login");
			pd.show();
		}

		@Override
		protected CollectionResponseEndUser doInBackground(Void... params) {

			// calls the back end
			Enduserendpoint.Builder endpointBuilder = new Enduserendpoint.Builder(
					AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
					new HttpRequestInitializer() {
						public void initialize(HttpRequest httpRequest) {

						}

					});

			Enduserendpoint endpoint = CloudEndpointUtils.updateBuilder(
					endpointBuilder).build();

			CollectionResponseEndUser result;

			try {
				result = endpoint.listEndUser().execute();
			}// end try

			catch (IOException e) {
				e.printStackTrace();
				result = null;
			}// end catch
			return result;

		}// end protected

		protected void onPostExecute(CollectionResponseEndUser result) {

			pd.dismiss();
			// cant initialze a list must use an array
			users = result.getItems();
			for (EndUser r : users) {
				String name = r.getUserName();
				String passWord = r.getPassword();
				String n = userName.getText().toString();
				String pswrd = password.getText().toString();

				if ((n).equals(name) && (pswrd).equals(passWord)) {
					count = 1;
				}// end
			}// end

			// if succesfull user name and password launch main menu screen
			if (count == 1) {
				Intent i = new Intent(LogIn.this, MainMenu.class);
				startActivity(i);

			}
			// if incorrent credentials show error
			else {
				Toast.makeText(context,
						"Credentials Not Necognised, Please Try Again",
						Toast.LENGTH_SHORT).show();
				// increment times
				times++;

				// redirect user to registration page upon 3 attemmpts
				if (times == 3) {
					Toast.makeText(context, "Too Many Attempts",
							Toast.LENGTH_LONG).show();
					Intent intent = new Intent(LogIn.this, Registration.class);
					startActivity(intent);
				}// end if

			}// end else

		}// end on postExecute
	}// end class listOfRecipes

}// end LogIn
