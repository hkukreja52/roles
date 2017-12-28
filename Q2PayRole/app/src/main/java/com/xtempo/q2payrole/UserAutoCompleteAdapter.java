package com.xtempo.q2payrole;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class UserAutoCompleteAdapter extends ArrayAdapter implements Filterable{
    private Context mContext;
    @LayoutRes
    private int layoutResId;
    private List<User> userList, finalUserList;
    public UserAutoCompleteAdapter(@NonNull Context context, int resource,List<User> list) {
        super(context, resource);
        mContext = context;
        layoutResId = resource;
        userList = list;
        finalUserList = list;
    }
    @Override
    public int getCount() {
        return finalUserList.size();
    }

    @Override
    public User getItem(int index) {
        return finalUserList.get(index);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layoutResId, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.textview1)).setText(getItem(position).getFirst_name());
        ((TextView) convertView.findViewById(R.id.textview2)).setText(getItem(position).getLast_name());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                ArrayList<User> resultList = new ArrayList<>();
                if (constraint != null) {
                    for (User user : userList) {
                        if (user.getFirst_name().toLowerCase()
                                .startsWith(constraint.toString().toLowerCase()) || user.getLast_name().toLowerCase()
                                .startsWith(constraint.toString().toLowerCase()))
                            resultList.add(user);
                    }

                    filterResults.values = resultList;
                    filterResults.count = resultList.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    finalUserList = (List<User>) results.values;
                    notifyDataSetChanged();
                }
                else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }
}
