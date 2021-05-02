package com.example.sqlite.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite.EditData;
import com.example.sqlite.LihatData;
import com.example.sqlite.MainActivity;
import com.example.sqlite.R;
import com.example.sqlite.database.DBController;
import com.example.sqlite.database.Teman;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanViewHolder> {
    private ArrayList<Teman> listData;
    private Context context;

    public TemanAdapter(ArrayList<Teman> listData) {
        this.listData = listData;
    }

    @Override
    public TemanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInf = LayoutInflater.from(parent.getContext());
        context = parent.getContext();
        View view = layoutInf.inflate(R.layout.row_data_teman,parent,false);
        return new TemanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TemanViewHolder holder, int position) {
    String nm,tlp,id;
    nm = listData.get(position).getNama();
    tlp = listData.get(position).getTelpon();
    id = listData.get(position).getId();
    DBController controller = new DBController(context);

    holder.namatxt.setTextColor(Color.BLUE);
    holder.namatxt.setTextSize(20);
    holder.namatxt.setText(nm);
    holder.telpontxt.setText(tlp);


    holder.cardku.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(context, LihatData.class);
            i.putExtra("id",id);
            i.putExtra("nama",nm);
            i.putExtra("telpon",tlp);
            context.startActivity(i);
        }
    });
    holder.cardku.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            PopupMenu popup = new PopupMenu(context, holder.cardku);
            popup.inflate(R.menu.menu);
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.edit:
                            Intent i = new Intent(context, EditData.class);
                            i.putExtra("id",id);
                            i.putExtra("nm",nm);
                            i.putExtra("tlp",tlp);
                            context.startActivity(i);
                            break;
                        case R.id.hapus:
                            HashMap<String,String> qvalues = new HashMap<>();
                            qvalues.put("id",id);
                            controller.deleteData(qvalues);
                            Intent intent = new Intent(context, MainActivity.class);
                            context.startActivity(intent);
                            Toast.makeText(context,"Data berhasil dihapus",
                                    Toast.LENGTH_LONG).show();
                            break;
                    }
                    return true;
                }
            });
            popup.show();
            return true;
        }
    });
    }

    @Override
    public int getItemCount() {
        return (listData != null)?listData.size() : 0;
    }

    public class TemanViewHolder extends RecyclerView.ViewHolder {
        private CardView cardku;
        private TextView namatxt, telpontxt;
        public TemanViewHolder(View view) {
            super(view);
            cardku = (CardView) view.findViewById(R.id.kartuku);
            namatxt = (TextView) view.findViewById(R.id.textNama);
            telpontxt = (TextView) view.findViewById(R.id.textTelpon);

            cardku.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return true;
                }
            });

        }
    }
}
