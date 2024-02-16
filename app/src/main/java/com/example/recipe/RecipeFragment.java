package com.example.recipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private LinearLayout progressBarsLayout;
    private ProgressBar progressBarProtein, progressBarFiber, progressBarFat, progressBarCarbs, progressBarSodium;

    private EditText edtServings;
    private TextView tvDescription,tvDescription1;
    private ImageView ivDropDown;
    private int servingsValue = 5;

    public RecipeFragment() {
        // Required empty public constructor
    }


    public static RecipeFragment newInstance(String param1, String param2) {
        RecipeFragment fragment = new RecipeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_recipe, container, false);
        Spinner spinner = rootView.findViewById(R.id.spinner);
        TextView tvHeading = rootView.findViewById(R.id.tvHeading);
        tvDescription = rootView.findViewById(R.id.tvDescription);
        tvDescription1 = rootView.findViewById(R.id.tvDescription2);
        ivDropDown = rootView.findViewById(R.id.ivDropDown);
        progressBarsLayout = rootView.findViewById(R.id.progressBarsLayout);
        progressBarProtein = rootView.findViewById(R.id.progressBarProtein);
        progressBarFiber = rootView.findViewById(R.id.progressBarFiber);
        progressBarFat = rootView.findViewById(R.id.progressBarFat);
        progressBarCarbs = rootView.findViewById(R.id.progressBarCarbs);
        progressBarSodium = rootView.findViewById(R.id.progressBarSodium);
        ivDropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDescriptionVisibility();
            }
        });
        // Create a list of items for the spinner
        List<Item> items = new ArrayList<>();
        items.add(new Item("Ingredients ", false));
        items.add(new Item("Eggs", false));
        items.add(new Item("Milk", false));
        items.add(new Item("Chocochips", false));
        items.add(new Item("Butter", false));
        items.add(new Item("Backing Soda", false));
        items.add(new Item("Salt", false));
        items.add(new Item("Sugar", false));
        items.add(new Item("Flour", false));


        // Create a custom adapter
        CustomAdapter adapter = new CustomAdapter(items);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // Set a listener to handle item selections
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Display a toast message with the selected item
                Item selected = items.get(position);
                Toast.makeText(requireContext(), "Selected: " + selected.getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        Button btnMinus = rootView.findViewById(R.id.btnMinus);
        Button btnPlus = rootView.findViewById(R.id.btnPlus);
        edtServings = rootView.findViewById(R.id.edtServings);

        // Set default servings value
        edtServings.setText(String.valueOf(servingsValue));

        // Set click listeners for + and - buttons
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementServings();
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementServings();
            }
        });

        return rootView;
    }

    private void toggleDescriptionVisibility() {
        int newVisibility = (tvDescription.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE;
        tvDescription.setVisibility(newVisibility);
        int newVisibility1 = (tvDescription1.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE;
        tvDescription1.setVisibility(newVisibility1);
    }
    private static class Item {
        private String name;
        private boolean checked;

        public Item(String name, boolean checked) {
            this.name = name;
            this.checked = checked;
        }

        public String getName() {
            return name;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }
    }private class CustomAdapter extends ArrayAdapter<Item> {

        public CustomAdapter(List<Item> items) {
            super(requireContext(), 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Item item = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item_layout, parent, false);
            }

            // Lookup view for data population
            CheckBox checkBox = convertView.findViewById(R.id.checkBox);
            TextView textView = convertView.findViewById(R.id.textView);
            checkBox.setVisibility(position == 0 ? View.GONE : View.VISIBLE);
            // Populate the data into the template view using the data object
            checkBox.setChecked(item.isChecked());
            textView.setText(item.getName());

            // Return the completed view to render on screen
            return convertView;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getView(position, convertView, parent);
        }
    }
    private void incrementServings() {
        servingsValue++;
        updateServingsText();
    }

    private void decrementServings() {
        if (servingsValue > 1) {
            servingsValue--;
            updateServingsText();
        }
    }

    private void updateServingsText() {
        edtServings.setText(String.valueOf(servingsValue));
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.top_nav,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}