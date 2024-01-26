package com.example.quotaku;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuoteViewHolder extends RecyclerView.ViewHolder {
    TextView tvQuote, tvCharacter, tvAnime;

    public QuoteViewHolder(@NonNull View itemView) {
        super(itemView);
        tvQuote = itemView.findViewById(R.id.quotetv);
        tvCharacter = itemView.findViewById(R.id.charactertv);
        tvAnime = itemView.findViewById(R.id.animetv);
    }

    public void bind(Quote quote) {
        tvQuote.setText(quote.getQuote());
        tvCharacter.setText(quote.getCharacter());
        tvAnime.setText(quote.getAnime());
    }
}
