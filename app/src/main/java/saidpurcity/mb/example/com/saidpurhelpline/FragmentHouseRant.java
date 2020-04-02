package saidpurcity.mb.example.com.saidpurhelpline;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentHouseRant extends android.support.v4.app.Fragment {

    View v;

    public FragmentHouseRant() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.houserant_fragment,container,false);
        return v;
    }
}
