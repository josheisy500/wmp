package com.weeklymealplanner;

import java.io.IOException;
import java.util.Date;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson.JacksonFactory;
import com.weeklymealplanner.enduserendpoint.Enduserendpoint;
import com.weeklymealplanner.enduserendpoint.model.EndUser;

public class Registration extends Activity {

	// Android Widget Refrences
	EditText pswdEditTxt, userEditTxt, emailEditTxt;

	RadioButton maleGender, femaleGender;

	CheckBox maleChkBx, femaleChkBx;

	String pswrd, userName, email, confEmail, genderResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		userEditTxt = (EditText) findViewById(R.id.editText_user);
		emailEditTxt = (EditText) findViewById(R.id.editText2);
		pswdEditTxt = (EditText) findViewById(R.id.editText4);
		maleGender = (RadioButton) findViewById(R.id.radio0_m);

	}// end

	// onClick Image Button Event
	public void regPersonalInfo(View v) throws IOException {

		// to check if fields are valid
		if (!isDataValid()) {
			Toast.makeText(getBaseContext(),
					"Missing Fields or incorrect email ", Toast.LENGTH_LONG)
					.show();
		}// end if
		else {
			// invoke class to call back end api classes to commnicate with GAE
			new EndpointsTask(this).execute(getApplicationContext());
		}

	}// end

	public boolean isDataValid() {

		// to check if user Name is empty - invoke function
		boolean userName = !getUserName().isEmpty();

		// to check if password is empty - invoke function
		boolean passwordValid = !getPassword().isEmpty();

		// to check if user name is empty - invoke function
		boolean emailValid = Patterns.EMAIL_ADDRESS.matcher(getEmail())
				.matches();

		// return the two outcomes of both functions
		return userName && passwordValid && emailValid;

	}// end

	public String getUserName() {
		return userEditTxt.getText().toString().trim();
	}

	// function to get contents of password text field
	public String getPassword() {
		return pswdEditTxt.getText().toString().trim();
	}// end

	public String getEmail() {
		return emailEditTxt.getText().toString().trim();
	}

	// Class to commuunicate with the GAE to register new users from mobile
	// client
	public class EndpointsTask extends AsyncTask<Context, Integer, Long> {
		Context context;
		private ProgressDialog p;

		public EndpointsTask(Context context) {
			this.context = context;
		}// end

		protected void onPreExecute() {
			super.onPreExecute();

			p = new ProgressDialog(context);
			p.setMessage("Posting Personal Information");
			p.show();

		}// end

		@Override
		protected Long doInBackground(Context... contexts) {

			// calls the backend API classes
			Enduserendpoint.Builder endpointBuilder = new Enduserendpoint.Builder(
					AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
					new HttpRequestInitializer() {
						public void initialize(HttpRequest httpRequest) {

						}
					});

			Enduserendpoint endpoint = CloudEndpointUtils.updateBuilder(
					endpointBuilder).build();

			try {

				EndUser endUser = new EndUser();
				String EndUserID = new Date().toString();

				// get user name
				userName = userEditTxt.getText().toString();

				// get user email address
				email = emailEditTxt.getText().toString();

				// get user password
				pswrd = pswdEditTxt.getText().toString();

				// get gender
				// genderResult = "";
				femaleGender = (RadioButton) findViewById(R.id.radio1_f);

				// basic if/else to determine which radio button is selcted
				if (maleGender.isChecked()) {
					genderResult = "male";
				} else {
					genderResult = "female";
				}

				// endUser object of type EndUser
				// used to setFileds in Entity Class (EndUser)
				endUser.setEndUserID(EndUserID);
				endUser.setUserName(userName);
				endUser.setPassword(pswrd);
				endUser.setEmailAddress(email);
				endUser.setGender(genderResult);

				// result object post informatin to GAE
				EndUser result = endpoint.insertEndUser(endUser).execute();
				//
				DietaryInfo.ep = result;

			} catch (IOException e) {
				e.printStackTrace();
			}

			return (long) 0;

		}

		// On Completion of upload to GAE
		protected void onPostExecute(Long l) {
			p.dismiss();
			Intent i = new Intent(Registration.this, DietaryInfo.class);
			startActivity(i);
			return;
		}// end postExecute

	}

}// end Registration
