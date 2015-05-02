package com.group15.accountingbook;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OverviewFragment extends Fragment {

    private AccountingBook accountingBook;

    public OverviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_overview, container, false);

        accountingBook = AccountingBook.getInstance(getActivity());
        accountingBook.open();

        TextView textBalance = (TextView) view.findViewById(R.id.text_balance);
        textBalance.setText("$" + accountingBook.getBalance());
        return view;
    }

}
