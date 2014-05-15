package com.weeklymealplanner;

import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson.JacksonFactory;
import com.weeklymealplanner.enduserendpoint.Enduserendpoint;
import com.weeklymealplanner.enduserendpoint.model.EndUser;

public class DietaryInfo extends Activity {

	String veganResult, mainGoal;

	Float goalWeight, currWeight, wklyBud;

	RadioButton yesRadio, noRadio, loseRadio, saveRadio, gainRadio;

	EditText currWeightEditTxt, goalWeightEditTxt, wklyBugEditTxt;

	protected static EndUser ep;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dietary_info);

	}

	public void registerDietaryInfo(View v) throws IOException {
		new EndpointsTask(this).execute(getApplicationContext());

	}

	public class EndpointsTask extends AsyncTask<Context, Integer, Long> {
		Context context;
		private ProgressDialog p;

		public EndpointsTask(Context context) {
			this.context = context;
		}

		protected void onPreExecute() {
			super.onPreExecute();
			p = new ProgressDialog(context);
			p.setMessage("Posting Dietary Information");
			p.show();

		}

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

				// get users current weight
				currWeightEditTxt = (EditText) findViewById(R.id.editText33);
				currWeight = Float.parseFloat(currWeightEditTxt.getText()
						.toString());

				// Get User Current Goal Weight
				goalWeightEditTxt = (EditText) findViewById(R.id.editText44);
				goalWeight = Float.parseFloat(goalWeightEditTxt.getText()
						.toString());

				// get user weekly budget
				wklyBugEditTxt = (EditText) findViewById(R.id.editText55);
				wklyBud = Float.parseFloat(wklyBugEditTxt.getText().toString());

				// get vegetarian
				veganResult = "";
				yesRadio = (RadioButton) findViewById(R.id.radio0_y);
				noRadio = (RadioButton) findViewById(R.id.radio1_n);

				if (yesRadio.isChecked()) {
					veganResult = "yes";
				} else {
					veganResult = "no";
				}

				// get main goal
				mainGoal = "";
				loseRadio = (RadioButton) findViewById(R.id.radio0_l);
				saveRadio = (RadioButton) findViewById(R.id.radio2_s);
				gainRadio = (RadioButton) findViewById(R.id.radio1_g);

				if (loseRadio.isChecked()) {
					mainGoal = "Lose Weight";
				} else if (saveRadio.isChecked()) {
					mainGoal = "Save Money";
				} else {
					mainGoal = "Gain Weight";
				}

				ep.setCurrentWeight(currWeight);
				ep.setGoalWeight(goalWeight);
				ep.setWeeklyBudget(wklyBud);
				ep.setMainGoal(mainGoal);
				ep.setVegetarian(veganResult);

				// post informatin to GAE
				endpoint.updateEndUser(ep).execute();

			} catch (IOException e) {
				e.printStackTrace();
			}

			return (long) 0;

		}

		protected void onPostExecute(Long l) {
			p.dismiss();
			Toast.makeText(getBaseContext(), "Sign In With Your New Log In",
					Toast.LENGTH_SHORT).show();

			Intent intent = new Intent(DietaryInfo.this, LogIn.class);
			startActivity(intent);
			return;
		}// end on postExecute

	}

}
