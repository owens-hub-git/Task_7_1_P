package com.example.task_7_1_p;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task_7_1_p.data.DatabaseHelper;
import com.example.task_7_1_p.model.Item;


public class ItemFragment extends Fragment {
    private static final String ARG_ITEM = "item";

    public static ItemFragment newInstance(Item item) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);

        TextView issueView = view.findViewById(R.id.issueTextView);
        TextView postTypeView = view.findViewById(R.id.postTypeTextView);
        TextView nameView = view.findViewById(R.id.nameTextView);
        TextView phoneView = view.findViewById(R.id.phoneTextView);
        TextView descView = view.findViewById(R.id.descTextView);
        TextView dateView = view.findViewById(R.id.dateTextView);
        TextView locationView = view.findViewById(R.id.locationTextView);


        if (getArguments() != null) {
            Item item = (Item) getArguments().getSerializable(ARG_ITEM);

            issueView.setText("Issue: " + item.getIssue());
            postTypeView.setText("Post Type: " + item.getPostType());
            nameView.setText("Name: " + item.getName());
            phoneView.setText("Phone: " + item.getPhone());
            descView.setText("Description: " + item.getDescription());
            dateView.setText("Date: " + item.getDate());
            locationView.setText("Location: " + item.getLocation());
        }

        Button deleteButton = view.findViewById(R.id.deleteButton);

        deleteButton.setOnClickListener(v -> {
            if (getArguments() != null) {
                Item item = (Item) getArguments().getSerializable(ARG_ITEM);
                if (item != null) {
                    DatabaseHelper db = new DatabaseHelper(requireContext());
                    db.deleteItemById(item.getItem_id());

                    Toast.makeText(requireContext(), "Item deleted", Toast.LENGTH_SHORT).show();

                    // Go back to previous screen
                    requireActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });

        return view;
    }
}