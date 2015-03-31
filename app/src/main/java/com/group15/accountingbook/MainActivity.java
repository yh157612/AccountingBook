package com.group15.accountingbook;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    public static final int REQ_CODE_ADD = 1;
    public static final String EXTRA_DESC = "extra_desc";
    public static final String EXTRA_AMOUNT = "extra_amount";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            addEntry();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_ADD:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(this,
                            data.getStringExtra(EXTRA_DESC) + "\n$" + data.getDoubleExtra(EXTRA_AMOUNT, 0),
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void addEntry() {
        Intent intent = new Intent(this, AddActivity.class);
        startActivityForResult(intent, REQ_CODE_ADD);
    }
}
