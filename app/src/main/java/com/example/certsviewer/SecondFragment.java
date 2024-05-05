package com.example.certsviewer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.certsviewer.databinding.FragmentSecondBinding;

import java.math.BigInteger;
import java.text.SimpleDateFormat;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private BigInteger serial;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        serial = new BigInteger(getArguments().getString("serial", "-1"));
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        Cert cert = CertsManager.getInstance().getCertBySerial(serial);
        binding.certName.setText("Название: " + cert.getName());
        binding.organization.setText("Организация: " + cert.getOrganization());
        binding.serial.setText("Серийный номер: " + cert.getSerial().toString());
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        binding.before.setText("Действует с " + sdf.format(cert.getStartDate()));
        binding.after.setText("Действует до " + sdf.format(cert.getEndDate()));
        binding.system.setText("Системный: " + (cert.isSystem() ? "Да" : "Нет"));

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}