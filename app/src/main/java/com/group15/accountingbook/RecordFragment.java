package com.group15.accountingbook;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class RecordFragment extends ListFragment {

    private ListViewAdapter mListAdapter;
    private AccountingBook accountingBook;
    private List<Record> RECORDS;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecordFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        accountingBook = AccountingBook.getInstance(getActivity());
        accountingBook.open();
        RECORDS = accountingBook.getAllRecords();

        mListAdapter = new ListViewAdapter(getActivity(), RECORDS);
        setListAdapter(mListAdapter);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView listView = getListView();
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setSelector(R.drawable.list_item_selector);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ((ListView) parent).setItemChecked(position,
                        ((ListView) parent).isItemChecked(position));
                return false;
            }
        });
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            private int nr = 0;

            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
                if (b)
                    nr++;
                else
                    nr--;
                actionMode.setTitle(String.valueOf(nr));
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                getActivity().getMenuInflater().inflate(R.menu.menu_listcab, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.cab_delete:
                        deleteSelectedItems();
                        actionMode.finish();
                        return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
                nr = 0;
            }
        });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

    }

    private void deleteSelectedItems() {
        SparseBooleanArray selectedItems = getListView().getCheckedItemPositions();
        for (int i = mListAdapter.getCount() - 1; i >= 0; i--) {
            if (selectedItems.get(i)) {
                accountingBook.deleteRecord(mListAdapter.getItem(i));
                mListAdapter.remove(mListAdapter.getItem(i));
            }
        }
    }
}
