package com.qualitykiosk.myapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.AsyncTask;
import android.provider.AlarmClock;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageButton imagemenus;
    FloatingActionButton fabbutton;
    private int passwordNotVisible=1;
    private ProgressBar mLoadingIndicator;
    private Button Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        TextView mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        final ImageView showpasss = (ImageView) findViewById(R.id.iv_showpaasw);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        Login=(Button) findViewById(R.id.Login) ;


        imagemenus = (ImageButton) findViewById(R.id.imagemenu);
        Intent intent = getIntent();



        imagemenus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, imagemenus);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.show();


            }

        });

        showpasss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setActivated(!view.isActivated());
                EditText paswword = (EditText) findViewById(R.id.et_password);
                if (passwordNotVisible == 1) {
                    paswword.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordNotVisible = 0;
                    AnimatedVectorDrawable showpwd= (AnimatedVectorDrawable) getDrawable(R.drawable.avd_show_to_disable);
                                       showpasss.setImageDrawable(showpwd);


                } else {

                    paswword.setInputType( InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordNotVisible = 1;
                    AnimatedVectorDrawable showpwd= (AnimatedVectorDrawable) getDrawable(R.drawable.avd_trimclip_eye_visible_to_masked);
                    showpasss.setImageDrawable(showpwd);

                }


                paswword.setSelection(paswword.length());



            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeGithubSearchQuery();

            }
        });


    }
    private void makeGithubSearchQuery() {
        URL githubSearchUrl = Networkutil.buildUrl("true","false");

        new GithubQueryTask().execute(githubSearchUrl);
    }
    public  class  GithubQueryTask extends AsyncTask<URL, Void, String> {

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
