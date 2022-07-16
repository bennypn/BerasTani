package com.example.berastani;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.type.DateTime;

// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
public class HistoryAdapter extends FirebaseRecyclerAdapter<History,HistoryAdapter.HistorysViewholder> {

    Long epochh;
    protected static String date, buyPera,buyPulen,sisaPera,sisaPulen;
    public HistoryAdapter(
            @NonNull FirebaseRecyclerOptions<History> options)
    {
        super(options);
    }

    @Override
    protected void
    onBindViewHolder(@NonNull HistorysViewholder holder,
                     int position, @NonNull History model)
    {
        epochh = model.getEpoch();
        date = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date (epochh*1000));

        buyPera = Integer.toString(model.getBuyPera());
        buyPulen = Integer.toString(model.getBuyPulen());
        sisaPera = Integer.toString(model.getBuyPulen());
        sisaPulen = Integer.toString(model.getBuyPulen());

        holder.epoch.setText(date);
        holder.buyPeraTV.setText(buyPera);
        holder.buyPulenTV.setText(buyPulen);
        holder.sisaPeraTV.setText(sisaPera);
        holder.sisaPulenTV.setText(sisaPulen);


    }

    // Function to tell the class about the Card view (here
    // "History.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public HistorysViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new HistoryAdapter.HistorysViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "History.xml")
    class HistorysViewholder
            extends RecyclerView.ViewHolder {
        TextView epoch, buyPeraTV,buyPulenTV,sisaPeraTV,sisaPulenTV;
        public HistorysViewholder(@NonNull View itemView)
        {
            super(itemView);

            epoch = itemView.findViewById(R.id.epoch_tv);
            buyPeraTV = itemView.findViewById(R.id.buyPera_tv);
            buyPulenTV = itemView.findViewById(R.id.buyPulen_tv);
            sisaPeraTV = itemView.findViewById(R.id.sisaPera_tv);
            sisaPulenTV = itemView.findViewById(R.id.sisaPulen_tv);
        }
    }


}

