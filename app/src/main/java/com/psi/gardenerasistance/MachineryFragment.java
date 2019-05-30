package com.psi.gardenerasistance;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class MachineryFragment extends Fragment {
    View myView;

    private static MachineryListViewAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savingInstance)
    {
        myView = inflater.inflate(R.layout.fragment_sensors, container, false);
        this.getActivity().setTitle(R.string.machinery);

        ListView listView = myView.findViewById(R.id.workersListView);

        adapter = new MachineryListViewAdapter(GetMachinery(), this.getActivity());

        listView.setAdapter(adapter);

        return myView;
    }

    private ArrayList<MachineryViewModel> GetMachinery()
    {
        ArrayList<MachineryViewModel> list = new ArrayList<>();

        for(int i = 1; i <= 10; i++)
            list.add(new MachineryViewModel( "Traktorius #" + i));

        return list;
    }
}
