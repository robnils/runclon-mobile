package com.rnils.runnerorganiser;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RunnerInfoActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runner_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);
        /*
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        Bundle bundle = getIntent().getExtras();
        String scanContent = bundle.getString("scanContent");

        TextView textViewContent = (TextView) findViewById(R.id.textViewContent_Runner);
        if ( scanContent != "" || scanContent != null)
        {
            textViewContent.setText(scanContent);
        }

        Button buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent mainActivityIntent = new Intent(RunnerInfoActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("registerSuccess", "true");
                mainActivityIntent.putExtras(bundle);
                startActivity(mainActivityIntent);
            }
        });
    }

}
