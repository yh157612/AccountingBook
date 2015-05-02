package com.group15.accountingbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Record> {

    public ListViewAdapter(Context context, List<Record> records) {
        super(context, R.layout.listview_item, records);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listview_item, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.itemDescription = (TextView) convertView.findViewById(R.id.item_description);
            viewHolder.itemAmount = (TextView) convertView.findViewById(R.id.item_amount);
            viewHolder.itemDate = (TextView) convertView.findViewById(R.id.item_date);
            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        Record record = getItem(position);
        viewHolder.itemDescription.setText(record.description);
        viewHolder.itemAmount.setText("$  " + ((record.amount > 0) ? "+" : "") + record.amount);
        viewHolder.itemDate.setText(record.date);

        return convertView;
    }

    private static class ViewHolder {
        TextView itemDescription;
        TextView itemAmount;
        TextView itemDate;
    }

}
