package org.find.one.ui.chat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.find.one.R;
import org.find.one.utils.UserUtils;

public class FriendRecyclerViewAdapter extends RecyclerView.Adapter<FriendRecyclerViewAdapter.ViewHolder> {

    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_friend, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.root.setOnClickListener(v -> {
            System.out.println(UserUtils.getFriendUserList().get(position));
            context.startActivity(new Intent(context, ChatActivity.class).putExtra("id", UserUtils.getFriendUserList().get(position).getId()));
        });
        holder.name.setText(UserUtils.getFriendUserList().get(position).getUsername());
        holder.img.setImageDrawable(context.getDrawable(UserUtils.getFriendUserList().get(position).getImgRes()));
    }

    @Override
    public int getItemCount() {
        return UserUtils.getFriendUserList().size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        public View root;
        public ImageView img;
        public TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            root = itemView.findViewById(R.id.root);
            img = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.textView_name);
        }
    }
}
