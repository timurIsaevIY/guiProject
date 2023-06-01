package com.example.projectgui;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectgui.databinding.FragmentSecondBinding;
import com.example.projectgui.recyclerViewWeekday.DataClass;
import com.example.projectgui.recyclerViewWeekday.Place;
import com.example.projectgui.recyclerViewWeekday.PlaceAdapter;
import com.example.projectgui.recyclerViewWeekday.Underground;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SecondFragment extends Fragment {
    private ArrayList<ArrayList<Place>> arr = new ArrayList<>();

    private FragmentSecondBinding binding;
    private int position = 0;
    RecyclerView recyclerView;
    PlaceAdapter adapter;
    private Map<Integer, String> undergrounds = new HashMap<>();
    private final ArrayList<String> paths = new ArrayList<>();

    ArrayList<Place> places = new ArrayList<>();
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        undergrounds = new DataClass().getUndergrounds();
        paths.addAll(Arrays.asList("monday.txt", "tuesday.txt","wednesday.txt", "thursday.txt", "friday.txt", "saturday.txt", "sunday.txt"));

        arr.add(readFile(paths.get(0), binding.getRoot().getContext()));
        arr.add(readFile(paths.get(1), binding.getRoot().getContext()));
        arr.add(readFile(paths.get(2), binding.getRoot().getContext()));
        arr.add(readFile(paths.get(3), binding.getRoot().getContext()));
        arr.add(readFile(paths.get(4), binding.getRoot().getContext()));
        arr.add(readFile(paths.get(5), binding.getRoot().getContext()));
        arr.add(readFile(paths.get(6), binding.getRoot().getContext()));

        recyclerView = binding.recyclerView;
        adapter = new PlaceAdapter(inflater, arr.get(position));
        recyclerView.setAdapter(adapter);



        return binding.getRoot();

    }

    private ArrayList<Place> readFile(String path, Context context) {
        ArrayList<Place> ans = new ArrayList<>();
        FileInputStream fis = null;
        try {
            fis = context.openFileInput(path);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                ans.add(parsePlace(line));
            }
            if (fis != null) fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.tvWeekday.setText(R.string.fullMonday);
                position = 0;
                adapter.update(arr.get(position));
            }
        });

        binding.btnTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.tvWeekday.setText(R.string.fullTuesday);
                position = 1;
                adapter.update(arr.get(position));
            }
        });

        binding.btnWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.tvWeekday.setText(R.string.fullWednesday);
                position = 2;
                adapter.update(arr.get(position));
            }
        });
        binding.btnThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.tvWeekday.setText(R.string.fullThursday);
                position = 3;
                adapter.update(arr.get(position));
            }
        });
        binding.btnFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.tvWeekday.setText(R.string.fullFriday);
                position = 4;
                adapter.update(arr.get(position));

            }
        });
        binding.btnSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.tvWeekday.setText(R.string.fullSaturday);
                position = 5;
                adapter.update(arr.get(position));
            }
        });
        binding.btnSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.tvWeekday.setText(R.string.fullSunday);
                position = 6;
                adapter.update(arr.get(position));
            }
        });

        binding.btnAddPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.newPlaceLayout.setVisibility(View.VISIBLE);
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String underground = binding.etUnderground.getText().toString();
                String address = binding.etAddress.getText().toString();
                String ans = "";
                if (undergroundValidating(underground)) {
                    arr.get(position).add(new Place(address, new Underground(underground)));
                    adapter.update(arr.get(position));
                    ans = "Место добавлено";
                    binding.etUnderground.clearComposingText();
                    binding.etAddress.clearComposingText();
                    loadFile(underground, address);
                } else ans = "Ошибка";
                binding.newPlaceLayout.setVisibility(View.INVISIBLE);
                Toast.makeText(binding.getRoot().getContext(), ans, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean undergroundValidating(String underground) {
        return new DataClass().containsUnderground(underground);
    }

    private void loadFile(String underground, String address) {
        File file = binding.getRoot().getContext().getFileStreamPath(paths.get(position));
        if (file.exists()) {
            String res = "";
            FileInputStream fis = null;

            try {
                fis = binding.getRoot().getContext().openFileInput(paths.get(position));
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                res = sb.toString();
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            res += underground + "_" + address;

            FileOutputStream fos = null;
            try {
                fos = binding.getRoot().getContext().openFileOutput(paths.get(position), Context.MODE_PRIVATE);
                fos.write(res.getBytes());
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            String text = underground + "_" + address;
            FileOutputStream fos = null;
            try {
                fos = binding.getRoot().getContext().openFileOutput(paths.get(position), Context.MODE_PRIVATE);
                fos.write(text.getBytes());
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private Place parsePlace(String line) {
        int spaces = 0;
        String underground = "", address = "";
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '_') {
                underground = line.substring(0, i);
                address = line.substring(i + 1);
                break;
            }
        }
        return new Place(address, new Underground(underground));
    }
}