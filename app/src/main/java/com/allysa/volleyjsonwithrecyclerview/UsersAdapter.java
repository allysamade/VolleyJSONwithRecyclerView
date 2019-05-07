package com.allysa.volleyjsonwithrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {

    private ArrayList<Users> dataList;

    public UsersAdapter(ArrayList<Users> dataList) {
        this.dataList = dataList;
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, int position) {
        holder.txtId.setText(dataList.get(position).getId());
        holder.txtName.setText(dataList.get(position).getName());
        holder.txtuserName.setText(dataList.get(position).getUserName());
        holder.txtEmail.setText(dataList.get(position).getEmail());
        holder.txtAddr.setText(dataList.get(position).getAddr());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder{
        private TextView txtId, txtName, txtuserName ,txtEmail, txtAddr;

        public UsersViewHolder(View itemView) {
            super(itemView);
            txtId = (TextView) itemView.findViewById(R.id.txtId);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtuserName = (TextView) itemView.findViewById(R.id.txtuserName);
            txtEmail = (TextView) itemView.findViewById(R.id.txtEmail);
            txtAddr = (TextView) itemView.findViewById(R.id.txtAddr);
        }
    }
}
