package com.group15.accountingbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

    public static final int REQ_CODE_ADD = 1;
    public static final String EXTRA_DESC = "extra_desc";
    public static final String EXTRA_AMOUNT = "extra_amount";
    public static final String EXTRA_EXPENSE = "extra_expense";
    public static final String EXTRA_DATE = "extra_date";

    Button buttonOverview, buttonDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonOverview = (Button) findViewById(R.id.button_overview);
        buttonDetails = (Button) findViewById(R.id.button_details);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            OverviewFragment overviewFragment = new OverviewFragment();
            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, overviewFragment).commit();
            buttonOverview.setTextColor(getResources().getColor(R.color.indigo));
            buttonDetails.setTextColor(getResources().getColor(R.color.grey));
        }
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
                    addEntryFinished(data);
                }
                break;
        }
    }

    public void addEntry() {
        Intent intent = new Intent(this, AddActivity.class);
        startActivityForResult(intent, REQ_CODE_ADD);
    }

    public void addEntryFinished(Intent data) {
        AccountingBook.addItem(new Item(
                data.getStringExtra(EXTRA_DESC),
                ((data.getBooleanExtra(EXTRA_EXPENSE, true)) ? -1 : 1) * data.getDoubleExtra(EXTRA_AMOUNT, 0),
                data.getStringExtra(EXTRA_DATE)
        ));
        switchToDetailsTab(null);
    }

    public void switchToOverviewTab(View view) {
        OverviewFragment overviewFragment = new OverviewFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, overviewFragment).commit();
        buttonOverview.setTextColor(getResources().getColor(R.color.indigo));
        buttonDetails.setTextColor(getResources().getColor(R.color.grey));
    }

    public void switchToDetailsTab(View view) {
        ItemFragment itemFragment = new ItemFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, itemFragment).commit();
        buttonDetails.setTextColor(getResources().getColor(R.color.indigo));
        buttonOverview.setTextColor(getResources().getColor(R.color.grey));
    }
}
