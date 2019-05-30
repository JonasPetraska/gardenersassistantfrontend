package com.psi.gardenerasistance;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class MessagesFragment extends Fragment {
    View myView;

    private static MessagesListViewAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savingInstance)
    {
        myView = inflater.inflate(R.layout.fragment_messages, container, false);
        this.getActivity().setTitle(R.string.messages);

        ListView listView = myView.findViewById(R.id.messagesListView);

        // Replace with call to the database
        adapter = new MessagesListViewAdapter(GetMessages(), this.getActivity());

        listView.setAdapter(adapter);

        return myView;
    }

    private ArrayList<MessageViewModel> GetMessages()
    {
        ArrayList<MessageViewModel> list = new ArrayList<>();
        ArrayList<String> nameDummyArray = new ArrayList<>();
        ArrayList<String> titleDummyArray = new ArrayList<>();

        //Prefill dummy arrays
        nameDummyArray.add("Jonas" + " Petrauskas");
        nameDummyArray.add("Petras" + " Jonynas");
        nameDummyArray.add("Edvinas" + " Senda");
        nameDummyArray.add("Kristijonas" + " Aleksynas");
        nameDummyArray.add("Aurelija" + " Vasiliauskaitė");
        nameDummyArray.add("Jonas" + " Petraška");

        titleDummyArray.add("Problema su Traktoriumi");
        titleDummyArray.add("Problema su Agurkais");
        titleDummyArray.add("Klausimas dėl trąšų");
        titleDummyArray.add("Atostogų prašymas");
        titleDummyArray.add("Pasiūlymas");
        titleDummyArray.add("Skundas");

        String loremIpsum = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";

        for(int i = 1; i <= 10; i++)
            list.add(new MessageViewModel(titleDummyArray.get(new Random().nextInt(titleDummyArray.size())), loremIpsum, nameDummyArray.get(new Random().nextInt(nameDummyArray.size())), nameDummyArray.get(new Random().nextInt(nameDummyArray.size())), new Date(), new Date(), MessageViewModel.TypesEnum.User));


        return list;
    }
}