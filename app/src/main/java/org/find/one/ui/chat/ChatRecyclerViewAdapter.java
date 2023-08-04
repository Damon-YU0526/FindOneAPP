package org.find.one.ui.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.find.one.R;
import org.find.one.utils.UserUtils;

import java.util.ArrayList;

public class ChatRecyclerViewAdapter extends RecyclerView.Adapter<ChatRecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> chatList = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.img.setImageDrawable(context.getDrawable(UserUtils.getMyImgRes()));
        holder.text.setText(chatList.get(position));
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public void addChat(String chat) {
        chatList.add(chat);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView img;
        public TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView);
            text = itemView.findViewById(R.id.textView_text);
        }
    }
}
