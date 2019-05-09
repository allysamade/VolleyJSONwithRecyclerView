package com.allysa.volleyjsonwithrecyclerview;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

class KontakAdapter extends RecyclerView.Adapter<KontakAdapter.KontakViewHolder> {

    private ArrayList<Kontak> dataList;
    private ProgressDialog progressDialog;
    private Toast toast;
    private Context context;
    private RequestQueue rq;

    public KontakAdapter(ArrayList<Kontak> dataList) {
        this.dataList = dataList;
    }

    @Override
    public KontakViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        toast = new Toast(parent.getContext());
        context = parent.getContext();
        progressDialog = new ProgressDialog(parent.getContext());
        rq = Volley.newRequestQueue(context);
        View view = layoutInflater.inflate(R.layout.cardview, parent, false);
        return new KontakViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KontakViewHolder holder, final int position) {
        holder.txtId.setText(dataList.get(position).getId());
        holder.txtName.setText(dataList.get(position).getName());
        holder.txtEmail.setText(dataList.get(position).getEmail());
        holder.txtAddr.setText(dataList.get(position).getAddr());
        holder.txtnoHp.setText(dataList.get(position).getNoHp());

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Please Wait");
                progressDialog.setCancelable(false);
                progressDialog.show();

                String url = "http://210.210.154.65/api/kontak/delete/"+dataList.get(position).getId();
                JsonObjectRequest req = new JsonObjectRequest(Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String msg = response.getString("message");
                            toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                            Log.i("Res :",msg);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dataList.remove(position);
                        notifyItemRemoved(position);
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();

                    }
                }
                );

                rq.add(req);

            }
        });
    }


            @Override
            public int getItemCount() {
                return (dataList != null) ? dataList.size() : 0;
            }

    public class KontakViewHolder extends RecyclerView.ViewHolder{
        private TextView txtId, txtName, txtEmail, txtAddr, txtnoHp;
        private ImageButton buttonDelete;

        public KontakViewHolder(View itemView) {
            super(itemView);
            txtId = (TextView) itemView.findViewById(R.id.txtId);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtEmail = (TextView) itemView.findViewById(R.id.txtEmail);
            txtAddr = (TextView) itemView.findViewById(R.id.txtAddr);
            txtnoHp = (TextView) itemView.findViewById(R.id.txtnoHp);
            buttonDelete = (ImageButton) itemView.findViewById(R.id.buttonDelete);
        }
    }
}


