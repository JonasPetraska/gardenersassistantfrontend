package com.psi.gardenerasistance;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;


public class SensorsFragment extends Fragment {
    View myView;

    private static SensorsListViewAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savingInstance)
    {
        myView = inflater.inflate(R.layout.fragment_sensors, container, false);
        this.getActivity().setTitle(R.string.sensors);

        ListView listView = myView.findViewById(R.id.workersListView);

        // Replace with call to the database
        adapter = new SensorsListViewAdapter(GetSensors(), this.getActivity());

        listView.setAdapter(adapter);

        return myView;
    }

    private ArrayList<SensorViewModel> GetSensors()
    {
        ArrayList<SensorViewModel> list = new ArrayList<SensorViewModel>();

        for(int i = 0; i < 10; i++)
            list.add(new SensorViewModel( "Jutiklis #" + i, new Random().nextInt() > 0 ? 2*i + " C" : 578*i + " mm", new Random().nextInt() > 0 ? SensorViewModel.TypesEnum.Temperature : SensorViewModel.TypesEnum.Moisture));

        return list;
    }
}
