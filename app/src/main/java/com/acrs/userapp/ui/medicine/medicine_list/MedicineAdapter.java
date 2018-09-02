package com.acrs.userapp.ui.medicine.medicine_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acrs.userapp.R;

import java.util.List;


public class MedicineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<MedicineListModel> list_array;
    OnAdapterListener listener;
    public interface OnAdapterListener {
        void adapterItemClick(MedicineListModel item);
    }
    public MedicineAdapter(List<MedicineListModel> list_array, OnAdapterListener listener) {
        this.list_array = list_array;
        this.listener = listener;
    }

    public void setList(List<MedicineListModel> list) {
        if (list.size() > 0) {
            this.list_array = list;
            notifyDataSetChanged();

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_list_single, parent, false);
        return new ViewHolderH(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (list_array.size() > 0 && holder instanceof ViewHolderH) {
            try {
                ((ViewHolderH) holder).medicinename.setText(list_array.get(position).getMedicine());
                ((ViewHolderH) holder).medicinenote.setText(list_array.get(position).getNote());
                ((ViewHolderH) holder).medicinetime.setText(list_array.get(position).getTime());
              /*  ((ViewHolderH) holder).scheduleDate.setText(list_array.get(position).getTrainingDate());
                ((ViewHolderH) holder).schedulePlace.setText(list_array.get(position).getAddress());
                ((ViewHolderH) holder).sheduleStartTime.setText(list_array.get(position).getFromTime());
                ((ViewHolderH) holder).sheduleEndTime.setText(list_array.get(position).getToTime());*/
                ((ViewHolderH) holder).bind(list_array.get(position));


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
        private TextView medicinename,medicinenote,medicinetime;


        public ViewHolderH(View v) {
            super(v);
            medicinename=(TextView)v.findViewById(R.id.medicine_name);
            medicinenote=(TextView)v.findViewById(R.id.medicine_note);
            medicinetime=(TextView)v.findViewById(R.id.medicine_time);
           /* scheduleDate = (TextView) v.findViewById(R.id.scheduleDate);
            schedulePlace = (TextView) v.findViewById(R.id.schedulePlace);
            sheduleStartTime = (TextView) v.findViewById(R.id.sheduleStartTime);
            sheduleEndTime = (TextView) v.findViewById(R.id.sheduleEndTime);*/


        }
        public void bind(final MedicineListModel items)
        {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.adapterItemClick(items);
                }
            });
        }

    }
}
