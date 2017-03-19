package com.qualitykiosk.myapp;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageButton imagemenus;
    FloatingActionButton fabbutton;
    private int passwordNotVisible=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        TextView mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        ImageView showpasss = (ImageView) findViewById(R.id.iv_showpaasw);

        imagemenus = (ImageButton) findViewById(R.id.imagemenu);
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
                EditText paswword = (EditText) findViewById(R.id.et_password);
                if (passwordNotVisible == 1) {
                    paswword.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordNotVisible = 0;
                } else {

                    paswword.setInputType( InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordNotVisible = 1;
                }


                paswword.setSelection(paswword.length());

            }
        });
    }


}
