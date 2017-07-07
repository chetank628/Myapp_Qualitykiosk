package com.qualitykiosk.myapp;

import android.app.MediaRouteButton;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class Stoploadtestscreen extends AppCompatActivity {
private  Button stoploadtest;
private ProgressBar mLoadingIndicator;
private TextView Tvloadtest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stoploadtestscreen);
        stoploadtest=(Button) findViewById(R.id.Stoptest);
        mLoadingIndicator=(ProgressBar) findViewById(R.id.loadstop) ;
        Tvloadtest = (TextView) findViewById(R.id.Loadtestindicator);
        stoploadtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                URL githubSearchUrl = Networkutil.buildUrl("false","true");

                new GithubQueryTask().execute(githubSearchUrl);
            }
        });

    }

    public  class  GithubQueryTask extends AsyncTask<URL, Void, String> {

        // COMPLETED (26) Override onPreExecute to set the loading indicator to visible
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mLoadingIndicator.setVisibility(View.VISIBLE);
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
            mLoadingIndicator.setVisibility(View.INVISIBLE);
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
                        Tvloadtest.setText("Load Test Stopped");
                        stoploadtest.setVisibility(View.INVISIBLE);
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
