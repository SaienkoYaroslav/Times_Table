package com.example.timestable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvNumbers;
    private SeekBar seekBar;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> numbers;
    private int max = 20;
    private int min = 1;
    private int count = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        adapter();
        listenerSeekBar();
    }

    void init(){
        lvNumbers = findViewById(R.id.list_view_results);
        seekBar = findViewById(R.id.seek_bar);
        // встановлення максимального значення сікбару
        seekBar.setMax(max);
        // встановлення мінімального значення з перевіркою на Апі, так як на страих апі метод не підтримується
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            seekBar.setMin(min);
        }
        numbers = new ArrayList<>();
    }

    void adapter(){
        adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.item_list_view, numbers);
        lvNumbers.setAdapter(adapter);
    }

    void listenerSeekBar(){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // очищення масиву намберс, щоб значення в лістВью оновлювались, а не записувались підряд
                numbers.clear();
                for (int i = min; i <= count; i++){
                    numbers.add(progress + " * " + i + " = " + progress * i);
                }
                // оновити дані в адаптері
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        // встановлення початкового значення сікБару (для того, щоб в ліст вью при запуску апки відразу
        // відображались дані, без цього з самого початку лістВью пустий)
        seekBar.setProgress(10);
    }

}