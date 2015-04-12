package com.group15.accountingbook;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Calendar;


public class AddActivity extends ActionBarActivity {

    private static EditText editDesc, editAmount;
    private static Button editDate;
    private static RadioButton radioExpense;
    private static int editYear, editMonth, editDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editDesc = (EditText) findViewById(R.id.edit_description);
        editDate = (Button) findViewById(R.id.edit_date);
        editAmount = (EditText) findViewById(R.id.edit_amount);
        radioExpense = (RadioButton) findViewById(R.id.radio_expense);

        final Calendar c = Calendar.getInstance();
        editYear = c.get(Calendar.YEAR);
        editMonth = c.get(Calendar.MONTH);
        editDay = c.get(Calendar.DAY_OF_MONTH);
        editDate.setText(editYear + "/" + (editMonth + 1) + "/" + editDay);

        radioExpense.setChecked(true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            formFinished();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, editYear, editMonth, editDay);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            editYear = year;
            editMonth = month;
            editDay = day;
            editDate.setText(year + "/" + (month + 1) + "/" + day);
        }
    }

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void formFinished() {
        if (editDesc.getText().toString().isEmpty() ||
                editAmount.getText().toString().isEmpty() ||
                editDate.getText().toString().isEmpty())
            return;

        // Return to main activity with some information
        Intent intent = new Intent();
        intent.putExtra(MainActivity.EXTRA_DESC, editDesc.getText().toString());
        intent.putExtra(MainActivity.EXTRA_AMOUNT,
                Double.parseDouble(editAmount.getText().toString()));
        intent.putExtra(MainActivity.EXTRA_EXPENSE, radioExpense.isChecked());
        intent.putExtra(MainActivity.EXTRA_DATE, editDate.getText());

        setResult(RESULT_OK, intent);
        finish();
    }

}
