package org.find.one.ui.center;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.find.one.R;
import org.find.one.data.LoginRepository;
import org.find.one.data.model.User;
import org.find.one.databinding.ActivityEditInfoBinding;

public class EditInfoActivity extends AppCompatActivity {

    private ActivityEditInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEditInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final int[] myselfImgRes = {LoginRepository.getInstance().getUser().getImgRes()};

        binding.imageViewCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myselfImgRes[0] = R.drawable.cat;
                Toast.makeText(EditInfoActivity.this, "chose cat", Toast.LENGTH_SHORT).show();
            }
        });
        binding.imageViewDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myselfImgRes[0] = R.drawable.dog;
                Toast.makeText(EditInfoActivity.this, "chose dog", Toast.LENGTH_SHORT).show();
            }
        });

        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age;
                String info;
                User user = LoginRepository.getInstance().getUser();
                if (!binding.editTextAge.getText().toString().isEmpty()){
                    age = Integer.parseInt(binding.editTextAge.getText().toString());
                    user.setAge(age);
                }

                if (!binding.editTextInfo.getText().toString().isEmpty()) {
                    info = binding.editTextInfo.getText().toString();
                    user.setDescription(info);
                }

                user.setImgRes(myselfImgRes[0]);

                LoginRepository.getInstance().setUser(user);
                Toast.makeText(EditInfoActivity.this, "complete", Toast.LENGTH_SHORT).show();
                setResult(1);
                finish();
            }
        });
    }
}