package org.find.one.ui.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.find.one.R;
import org.find.one.data.model.User;
import org.find.one.utils.UserUtils;

public class ChatActivity extends AppCompatActivity {

    private User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        long id = getIntent().getLongExtra("id", -1L);
        user = UserUtils.getUserById(id);

        if (user == null) {
            Toast.makeText(this, "该用户不存在", Toast.LENGTH_SHORT).show();
            finish();
        }

        getSupportActionBar().setTitle(user.getUsername());

        RecyclerView recyclerView = findViewById(R.id.recycler);
        Button sendButton = findViewById(R.id.button_send);
        EditText editText = findViewById(R.id.editText);

        ChatRecyclerViewAdapter adapter = new ChatRecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        sendButton.setOnClickListener(v -> {
            String chat = editText.getText().toString();
            adapter.addChat(chat);
            adapter.notifyDataSetChanged();
        });
    }
}