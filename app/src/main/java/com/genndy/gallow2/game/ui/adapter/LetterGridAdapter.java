package com.genndy.gallow2.game.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.genndy.gallow2.R;
import com.genndy.gallow2.game.model.Letter;

import java.util.List;

public class LetterGridAdapter extends BaseAdapter {
    List<Letter> letters;
    LayoutInflater layoutInflater;

    public LetterGridAdapter(List<Letter> letters, Context context){
        this.letters = letters;
        layoutInflater = LayoutInflater.from(context);
    }

    public void updateLetters(List<Letter> letters){
        this.letters = letters;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return letters.size();
    }

    @Override
    public Object getItem(int position) {
        return letters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LetterGridAdapter.ViewHolder holder;
        convertView = layoutInflater.inflate(R.layout.item_letter, null);
        holder = new LetterGridAdapter.ViewHolder();
        holder.itemImage = (ImageView) convertView.findViewById(R.id.item_image);
        holder.itemLetter = (TextView) convertView.findViewById(R.id.item_letter);

        holder.itemImage.setVisibility(View.INVISIBLE);
        holder.itemLetter.setText(letters.get(position).getLetter());

        convertView.setTag(holder);

        Letter letter = this.letters.get(position);
        return convertView;
    }

    class ViewHolder{
        ImageView itemImage;
        TextView itemLetter;
    }
}
