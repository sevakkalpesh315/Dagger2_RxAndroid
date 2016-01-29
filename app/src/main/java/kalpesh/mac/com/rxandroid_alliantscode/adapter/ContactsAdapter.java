package kalpesh.mac.com.rxandroid_alliantscode.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import kalpesh.mac.com.rxandroid_alliantscode.R;
import kalpesh.mac.com.rxandroid_alliantscode.model.Contacts_model;
import kalpesh.mac.com.rxandroid_alliantscode.utilities.ItemClickListener;

/**
 * Created by kalpesh on 23/12/2015.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder>{

    private List<Contacts_model> cakesList;
    private int rowLayout;
    private Context mContext;

    public ContactsAdapter(List<Contacts_model> cakesList, int rowLayout, Context context) {
        this.cakesList = cakesList;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Contacts_model cake = cakesList.get(i);
        viewHolder.cakeName.setText(cake.getFirstName());

        viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    Toast.makeText(mContext, "#" + position + " - " + cake.getFirstName() + " (Long click)", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "#" + position + " - " + cake.getFirstName(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return cakesList == null ? 0 : cakesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView cakeName;
        private ItemClickListener clickListener;
        public ViewHolder(View itemView) {
            super(itemView);
            cakeName = (TextView) itemView.findViewById(R.id.name);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }
        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }

    }
}