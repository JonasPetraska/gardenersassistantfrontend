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

public class WorkersFragment extends Fragment {
    View myView;
    private static WorkersListViewAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savingInstance)
    {
        myView = inflater.inflate(R.layout.fragment_workers, container, false);
        this.getActivity().setTitle(R.string.workers);

        ListView listView = myView.findViewById(R.id.workersListView);

        // Replace with call to the database
        adapter = new WorkersListViewAdapter(GetWorkers(), this.getActivity());

        listView.setAdapter(adapter);

        return myView;
    }

    private ArrayList<WorkerViewModel> GetWorkers()
    {
        ArrayList<WorkerViewModel> list = new ArrayList<>();
        ArrayList<String> firstNameDummyArray = new ArrayList<String>();
        ArrayList<String> lastNameDummyArray = new ArrayList<String>();

        //First name dummy array filling
        firstNameDummyArray.add("Jonas");
        firstNameDummyArray.add("Petras");
        firstNameDummyArray.add("Edvinas");
        firstNameDummyArray.add("Kristijonas");

        //Last name dummy array filling
        lastNameDummyArray.add("Petrauskas");
        lastNameDummyArray.add("Jonynas");
        lastNameDummyArray.add("Kairys");
        lastNameDummyArray.add("Dainys");

        for(int i = 1; i <= 10; i++)
            list.add(new WorkerViewModel(firstNameDummyArray.get(new Random().nextInt(firstNameDummyArray.size())), lastNameDummyArray.get(new Random().nextInt(firstNameDummyArray.size()))));

        return list;
    }
}
