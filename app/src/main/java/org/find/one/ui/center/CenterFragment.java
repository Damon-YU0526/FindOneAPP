package org.find.one.ui.center;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.RequestCallback;

import org.find.one.R;
import org.find.one.data.LoginRepository;
import org.find.one.data.model.Result;
import org.find.one.data.model.User;
import org.find.one.databinding.FragmentCenterBinding;
import org.find.one.databinding.FragmentChatBinding;
import org.find.one.ui.chat.ChatViewModel;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CenterFragment extends Fragment {

    private FragmentCenterBinding binding;
    private File imgFile;
    private Uri imgUri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CenterViewModel chatViewModel = new ViewModelProvider(this).get(CenterViewModel.class);
        binding = FragmentCenterBinding.inflate(inflater, container, false);

        imgFile = new File(getContext().getExternalCacheDir(), "img.jpg");

        return binding.getRoot();
    }

    private void startCamareActivity(Uri imgUri) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
        startActivityForResult(intent, 2);
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,3);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        User myself = LoginRepository.getInstance().getUser();

        if (imgFile.exists()) {
            imgUri = FileProvider.getUriForFile(getContext(), "org.find.one", imgFile);
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(imgUri));
                binding.imageViewImg.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            binding.imageViewImg.setImageDrawable(getContext().getDrawable(myself.getImgRes()));
        }

        binding.textViewName.setText(myself.getUsername());
        binding.textViewInfo.setText(myself.getInfo());

        binding.buttonEdit.setOnClickListener(v -> {
            startActivityForResult(new Intent(getContext(), EditInfoActivity.class), 1);
        });
        binding.imageViewImg.setOnClickListener(v -> {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
            builder.setTitle("Please Choose");
            builder.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    PermissionX.init(CenterFragment.this).permissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE).request(new RequestCallback() {
                        @Override
                        public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                            if (allGranted) {
                                if (imgFile.exists()) {
                                    imgFile.delete();
                                }
                                try {
                                    imgFile.createNewFile();
                                    imgUri = FileProvider.getUriForFile(getContext(), "org.find.one", imgFile);
                                    startCamareActivity(imgUri);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Toast.makeText(getContext(), "No camera or file read/write access", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
            builder.setNegativeButton("Album", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    openAlbum();
                }
            });
            builder.create().show();
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == 1) {
            User myself = LoginRepository.getInstance().getUser();

            binding.imageViewImg.setImageDrawable(getContext().getDrawable(myself.getImgRes()));
            binding.textViewName.setText(myself.getUsername());
            binding.textViewInfo.setText(myself.getInfo());
        }
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                //  show picture
                try {
                    //  decodeStream() parse output_image.jpg to a Bitmap.
                    Bitmap bitmap = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(imgUri));
                    binding.imageViewImg.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        if (requestCode == 3) {
            if (resultCode == Activity.RESULT_OK) {
                Uri fromUri = data.getData();
                BufferedInputStream bis = null;

                BufferedOutputStream bos = null;

                try {

                    bis = new BufferedInputStream(getContext().getContentResolver().openInputStream(fromUri));

                    bos = new BufferedOutputStream(new FileOutputStream(imgFile.getPath(), false));

                    byte[] buf = new byte[1024];

                    bis.read(buf);

                    do {

                        bos.write(buf);

                    } while(bis.read(buf) != -1);

                } catch (IOException e) {

                    e.printStackTrace();

                } finally {

                    try {

                        if (bis != null) bis.close();

                        if (bos != null) bos.close();

                    } catch (IOException e) {

                        e.printStackTrace();

                    }
                }
                binding.imageViewImg.setImageURI(data.getData());
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}