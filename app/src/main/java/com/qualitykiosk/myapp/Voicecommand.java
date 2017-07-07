package com.qualitykiosk.myapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class Voicecommand extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voicecommand);
        Intent intent = getIntent();
        if (!intent.hasExtra("name"))
        {
            makeGithubSearchQuery();
        }
    }
    private void makeGithubSearchQuery() {
        URL githubSearchUrl = Networkutil.buildUrl("true","false");

        new Voicecommand.GithubQueryTask().execute(githubSearchUrl);
    }

    public class GithubQueryTask extends AsyncTask<URL, Void, String> {

        // COMPLETED (26) Override onPreExecute to set the loading indicator to visible
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String githubSearchResults = null;
            try {
                githubSearchResults = Networkutil.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return githubSearchResults;
        }

        @Override
        protected void onPostExecute(String githubSearchResults) {
            // COMPLETED (27) As soon as the loading is complete, hide the loading indicator
           // mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (githubSearchResults != null && !githubSearchResults.equals("")) {
                // COMPLETED (17) Call showJsonDataView if we have valid, non-null results
                //showJsonDataView();
                //mSearchResultsTextView.setText(githubSearchResults);




                try {
                    JSONObject loadtstartparams= new JSONObject(githubSearchResults);
                    String Loadtesststartresult=loadtstartparams.getString("LoadteststartResult");
                    Context context = getApplicationContext();
                    //CharSequence text = githubSearchResults;
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, Loadtesststartresult, duration);
                    toast.show();

                    Intent intent= new Intent(context,Stoploadtestscreen.class);
                    intent.putExtra("Loadtestscreen",Loadtesststartresult);
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                // COMPLETED (16) Call showErrorMessage if the result is null in onPostExecute
                // showErrorMessage();
            }
        }
    }
}
