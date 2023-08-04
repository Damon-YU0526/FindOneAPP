package org.find.one.ui.find;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.find.one.data.model.User;
import org.find.one.databinding.FragmentFindBinding;
import org.find.one.utils.UserUtils;

import java.util.concurrent.atomic.AtomicReference;

public class FindFragment extends Fragment {

    private FragmentFindBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FindViewModel findViewModel = new ViewModelProvider(this).get(FindViewModel.class);
        binding = FragmentFindBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AtomicReference<User> user = new AtomicReference<>(UserUtils.getRandomUser());

        binding.imageView.setImageDrawable(getActivity().getDrawable(user.get().getImgRes()));
        binding.textViewInfo.setText(user.get().getInfo());

        binding.buttonAgree.setOnClickListener(v -> {
            UserUtils.addFriend(user.get());
            Toast.makeText(getActivity(), "complete", Toast.LENGTH_SHORT).show();

            user.set(UserUtils.getRandomUser());

            binding.imageView.setImageDrawable(getActivity().getDrawable(user.get().getImgRes()));
            binding.textViewInfo.setText(user.get().getInfo());
        });

        binding.buttonReject.setOnClickListener(v -> {
            user.set(UserUtils.getRandomUser());

            binding.imageView.setImageDrawable(getActivity().getDrawable(user.get().getImgRes()));
            binding.textViewInfo.setText(user.get().getInfo());
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}