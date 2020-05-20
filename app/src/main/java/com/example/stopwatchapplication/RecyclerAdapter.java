package com.example.stopwatchapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.florent37.expansionpanel.ExpansionLayout;
import com.github.florent37.expansionpanel.viewgroup.ExpansionLayoutCollection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//public final class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {
//
//    private final List<Object> list = new ArrayList<>();
//
//    private final ExpansionLayoutCollection expansionsCollection = new ExpansionLayoutCollection();
//
//    public RecyclerAdapter() {
////        expansionsCollection.openOnlyOne(true);
//    }
//
//    @Override
//    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return RecyclerHolder.buildFor(parent);
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerHolder holder, int position) {
////        holder.bind(list.get(position));
////
////        expansionsCollection.add(holder.getExpansionLayout());
//    }
//
//    @Override
//    public int getItemCount() {
////        return list.size();
//        return 0;
//    }
//
//    public void setItems(List<Object> items) {
//        this.list.addAll(items);
//        notifyDataSetChanged();
//    }
//
//    public final static class RecyclerHolder extends RecyclerView.ViewHolder {
//
//        private static final int LAYOUT = R.layout.music_options_recycler_cell;
//
//        ExpansionLayout expansionLayout;
//
//        public static RecyclerHolder buildFor(ViewGroup viewGroup){
//            View view = LayoutInflater.from(viewGroup.getContext()).inflate(LAYOUT, viewGroup, false);
//            return new RecyclerHolder(view);
//        }
//
//
//
//        public RecyclerHolder(View itemView) {
//            super(itemView);
//            expansionLayout = itemView.findViewById(R.id.expansionLayout);
//        }
//
//        public void bindView(int position){
//
//        }
//
//        public void bind(Object object){
//            expansionLayout.collapse(false);
//        }
//
//        public ExpansionLayout getExpansionLayout() {
//            return expansionLayout;
//        }
//    }
//}

public final class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {

    Context context;
    ArrayList<Object> arrayList ;

    public final static class RecyclerHolder extends RecyclerView.ViewHolder {

        //      RecyclerView recyclerView;
        ImageButton playBtn;
        TextView songTitle;
        TextView remaingTimeLabel;

        public RecyclerHolder(View itemView) {
            super(itemView);
//          recyclerView = itemView.findViewById(R.id.recyclerView);
            playBtn = itemView.findViewById(R.id.playBtn);
            songTitle = itemView.findViewById(R.id.songTitle);
            remaingTimeLabel = itemView.findViewById(R.id.remainingTimeLabel);
        }


    }




    public RecyclerAdapter(ArrayList<Object> list) {
        this.arrayList = list;
        // this.context = context;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.music_options_recycler_cell, parent,  false);

        //arrayList = new ArrayList<Object>();



        return new RecyclerHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {

        holder.songTitle.setText("----- Title --- ");
        holder.remaingTimeLabel.setText("00:00");

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }



}
