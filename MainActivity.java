package dev.isnow.poker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dev.isnow.poker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public int[] cubes;
    ImageView[] cubesViews;
    int[] cubesImages;
    ActivityMainBinding binding;
    ArrayAdapter<Integer> adapter;

    ArrayList<Integer> wins;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        cubes = new int[5];
        wins = new ArrayList<Integer>();
        binding.throwB.setOnClickListener(view1 -> Throw());
        cubesViews=new ImageView[5];
        cubesImages=new int[6];

        cubesViews[0] = binding.imageView2;
        cubesViews[1] = binding.imageView3;
        cubesViews[2] = binding.imageView4;
        cubesViews[3] = binding.imageView5;
        cubesViews[4] = binding.imageView6;

        cubesImages[0] = R.drawable.k1;
        cubesImages[1] = R.drawable.k2;
        cubesImages[2] = R.drawable.k3;
        cubesImages[3] = R.drawable.k4;
        cubesImages[4] = R.drawable.k5;
        cubesImages[5] = R.drawable.k6;
        adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1,wins);
        binding.list.setAdapter(adapter);
    }
    public void rollAll()
    {
        for (int i = 0; i < 5; i++)
        {
            cubes[i] = new Random().nextInt(6)+1;
            ChangeImage(i, cubes[i]);
        }
    }
    public void Throw()
    {
        ArrayList<Integer> liczby = new ArrayList<Integer>();
        ArrayList<Integer> wystopienia = new ArrayList<Integer>();
        rollAll();

        wins= new ArrayList<>();
        //street

        for (int liczba : cubes) {
            if (liczby.contains(liczba))
            {
                wystopienia.set(liczby.indexOf(liczba),wystopienia.get(liczby.indexOf(liczba))+1);

            }
            else
            {
                liczby.add(liczba);
                wystopienia.add(1);
            }
        }
        for (int i = 0; i < wystopienia.size(); i++) {
            if (wystopienia.get(i)>1) {
                wins.add(liczby.get(i)*wystopienia.get(i));
            }
        }


        boolean streetcheck=true;
        if (liczby.size()==5)
        {
            for (int i = 0; i < 5; i++) {
                if (liczby.contains(i+2))
                {

                }
                else
                {
                    streetcheck=false;
                    break;
                }
            }

        }
        else {streetcheck=false;}

        if (streetcheck)
        {
            wins.add(60);

            adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1,wins);
            binding.list.setAdapter(adapter);
            return;
        }
        streetcheck=true;
        if (liczby.size()==5)
        {
            for (int i = 0; i < 5; i++) {
                if (liczby.contains(i+1))
                {

                }
                else
                {
                    streetcheck=false;
                    break;
                }
            }

        }
        else {streetcheck=false;}

        if (streetcheck)
        {
            wins.add(60);

            adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1,wins);
            binding.list.setAdapter(adapter);

            return;
        }
        adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1,wins);
        adapter.notifyDataSetChanged();
        binding.list.setAdapter(adapter);
    }
    private void ChangeImage(int i, int v)
    {
        cubesViews[i].
                setImageResource(cubesImages[v-1]);
    }
}