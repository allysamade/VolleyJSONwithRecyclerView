package com.allysa.volleyjsonwithrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class KontakAdapter extends RecyclerView.Adapter<KontakAdapter.KontakViewHolder> {

    private ArrayList<Kontak> dataList;

    public KontakAdapter(ArrayList<Kontak> dataList) {
        this.dataList = dataList;
    }

    @Override
    public KontakViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview, parent, false);
        return new KontakViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KontakViewHolder holder, int position) {
        holder.txtId.setText(dataList.get(position).getId());
        holder.txtName.setText(dataList.get(position).getName());
        holder.txtEmail.setText(dataList.get(position).getEmail());
        holder.txtAddr.setText(dataList.get(position).getAddr());
        holder.txtnoHp.setText(dataList.get(position).getNoHp());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class KontakViewHolder extends RecyclerView.ViewHolder{
        private TextView txtId, txtName, txtEmail, txtAddr, txtnoHp;

        public KontakViewHolder(View itemView) {
            super(itemView);
            txtId = (TextView) itemView.findViewById(R.id.txtId);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtEmail = (TextView) itemView.findViewById(R.id.txtEmail);
            txtAddr = (TextView) itemView.findViewById(R.id.txtAddr);
            txtnoHp = (TextView) itemView.findViewById(R.id.txtnoHp);
        }
    }
}

