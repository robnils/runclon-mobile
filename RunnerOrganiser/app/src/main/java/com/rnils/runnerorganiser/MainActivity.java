package com.rnils.runnerorganiser;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

// For scanning
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/*
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
*/


public class MainActivity extends AppCompatActivity
{
    private boolean scanning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            String registerSuccess = bundle.getString("registerSuccess");
            if(registerSuccess.equals("true"))
            {
                bundle.putString("registerSuccess", "notset");
                Toast.makeText(MainActivity.this, "Runner registered", Toast.LENGTH_LONG).show();
            }
        }


        /*
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
*/

         /*
            LayoutInflater myInflater=LayoutInflater.from(this);
            View view=myInflater.inflate(R.layout.your_custom_layout,null);
            Toast mytoast=new Toast(this);
            mytoast.setView(view);
            mytoast.setDuration(Toast.LENGTH_LONG);
            mytoast.show();
            */


        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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

        // Move text fields to a struct?
        final CharSequence buttonClickTextDefault = "Scan";

        // Buttons
        final Button buttonScan = (Button) findViewById(R.id.buttonScan);

        // Initialise
        buttonScan.setText(buttonClickTextDefault);

        buttonScan.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Start scanning if not initialised
                if (!scanning)
                {
                    scanning = true;
                    buttonScan.setEnabled(false);

                    boolean result = ScanQrCode();
                    if (result)
                    {
                        scanning = false;
                        buttonScan.setEnabled(true);
                    }
                }
            }
        });

        // Searchview
        /*
        final SearchView searchView = (SearchView) findViewById(R.id.searchView);
        String suggestWord = "Search names";
        searchView.setQuery(suggestWord, false);
        searchView.clearFocus();
        */
        //searchView.setQuery("Search name", false);
    }

    public boolean ScanQrCode()
    {
        boolean success = false;

        // Do stuff
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.initiateScan();
        /*
        while (true)
        {
            if (!scanning)
            {
                break;
            }

        }
        */
        success = true;
        return success;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        String scanContent = "";
        if (scanningResult != null)
        {
            scanContent = scanningResult.getContents();
        }
        else
        {
            /*
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
            */
        }
        Intent runnerInfoIntent = new Intent(this, RunnerInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("scanContent", scanContent);
        runnerInfoIntent.putExtras(bundle);
        startActivity(runnerInfoIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
