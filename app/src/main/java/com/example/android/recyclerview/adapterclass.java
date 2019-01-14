package com.example.android.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class adapterclass extends RecyclerView.Adapter<adapterclass.ViewHolder> {
    List<list> listItems;
    private Context context;


    public adapterclass(List<list> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final list listItem=listItems.get(i);
        viewHolder.head.setText(listItem.getHeadertext());
        viewHolder.sub.setText(listItem.getSubtext());
        Picasso.get().load(listItem.getUrl()).into(viewHolder.pic);
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You Clicked " + listItem.getHeadertext(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView head;
        private TextView sub;
        private ImageView pic;
        private LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            head=(TextView)itemView.findViewById(R.id.head);
            sub=(TextView)itemView.findViewById(R.id.sub);
            pic=itemView.findViewById(R.id.imageurl);
            linearLayout=itemView.findViewById(R.id.linearlayout);
        }
    }
}
