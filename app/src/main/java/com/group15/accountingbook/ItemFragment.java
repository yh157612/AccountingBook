package com.group15.accountingbook;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ItemFragment extends ListFragment {

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, AccountingBook.ITEMS));
    }

}
