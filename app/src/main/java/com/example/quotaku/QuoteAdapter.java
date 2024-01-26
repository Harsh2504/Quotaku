package com.example.quotaku;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteViewHolder> {

    private List<Quote> quoteList;

    public QuoteAdapter(List<Quote> quoteList) {
        this.quoteList = quoteList;
    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quote_item, parent, false);
        return new QuoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position) {
        Quote quote = quoteList.get(position);
        holder.bind(quote);
    }

    @Override
    public int getItemCount() {
        return quoteList.size();
    }
}
