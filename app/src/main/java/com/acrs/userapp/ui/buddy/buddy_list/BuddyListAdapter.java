package com.acrs.userapp.ui.buddy.buddy_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acrs.userapp.R;

import java.util.List;

public class BuddyListAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<BuddyListModel> list_array;
    OnAdapterListener listener;
    boolean type;
    public interface OnAdapterListener {
        void adapterItemClick(BuddyListModel item);
    }
    public BuddyListAdapter(List<BuddyListModel> list_array, OnAdapterListener listener,boolean type)
    {
        this.type=type;
        this.list_array = list_array;
        this.listener = listener;
    }

    public void setList(List<BuddyListModel> list) {
        if (list.size() > 0) {
            this.list_array = list;
            notifyDataSetChanged();

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.buddy_list_single, parent, false);
        return new BuddyListAdapter.ViewHolderH(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (list_array.size() > 0 && holder instanceof BuddyListAdapter.ViewHolderH) {
            try {
                ((ViewHolderH) holder).name.setText(list_array.get(position).getBuddy_name());
                ((ViewHolderH) holder).phone.setText(list_array.get(position).getPhone_no());

                if(type)
                {
                    ((ViewHolderH) holder).hideview.setVisibility(View.VISIBLE);
                    ((ViewHolderH) holder).accept.setText("accept");
                    ((ViewHolderH) holder).accept.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listener.adapterItemClick(list_array.get(position));
                        }
                    });

                }else{
                    ((ViewHolderH) holder).hideview.setVisibility(View.GONE);
                    ((ViewHolderH) holder).hideview.setVisibility(View.VISIBLE);
                    ((ViewHolderH) holder).accept.setText("remove");

                    ((ViewHolderH) holder).accept.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listener.adapterItemClick(list_array.get(position));
                        }
                    });

                }

            /*    ((ViewHolderH) holder).sheduleStartTime.setText(list_array.get(position).getFromTime());
                ((ViewHolderH) holder).sheduleEndTime.setText(list_array.get(position).getToTime());
                ((BuddyListAdapter.ViewHolderH) holder).bind(list_array.get(position));*/


            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    @Override
    public int getItemCount() {
        return list_array.size();
    }

    private class ViewHolderH extends RecyclerView.ViewHolder {
        private TextView name,phone;
        private LinearLayout hideview;
        private Button accept;

        public ViewHolderH(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.buddy_name);
            phone = (TextView) v.findViewById(R.id.buddy_phone);
            hideview = (LinearLayout) v.findViewById(R.id.hideview);
            accept = (Button) v.findViewById(R.id.accept);



        }


    }

}
