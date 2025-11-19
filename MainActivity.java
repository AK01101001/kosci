package dev.isnow.poker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dev.isnow.poker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public int[] cubes;
    ImageView[] cubesViews;
    int[] cubesImages;
    ActivityMainBinding binding;

    int[] wins;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        cubes = new int[5];
        wins = new int[3];
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
    }
    public void rollAll()
    {
        for (int i = 0; i < cubes.length; i++)
        {
            cubes[i] = new Random().nextInt(7);
            ChangeImage(i, cubes[i]);
        }
    }
    public void Throw()
    {
        rollAll();
        ArrayList<Integer> liczby = new ArrayList<Integer>();
        ArrayList<Integer> wystopienia = new ArrayList<Integer>();
        for (int liczba : cubes) {
            if (liczby.contains(liczba))
            {
                wystopienia.add(liczby.indexOf(liczba),wystopienia.get(liczby.indexOf(liczba)+1));
            }
            else
            {
                liczby.add(liczba);
                wystopienia.add(1);
            }
        }
        for (int wystopienie : wystopienia)
        {
            if (wystopienie>1) {
                wins[wystopienie-2] = (liczby.get(wystopienia.indexOf(wystopienie))*wystopienie);
            }
        }
    }
    private void ChangeImage(int i, int v)
    {
        cubesViews[i].setImageResource(cubesImages[v]);
    }
}