package com.example.myfirstapp; //package name field, often package named after company_name.com in the form of com.company

import android.os.Bundle; //Bundle is a utility class that lets you store a set of name-value pairs. You will always find this import along with the import for Activity class because both onCreate() and onFreeze() methods take Bundle as a parameter.
import android.util.Log;
import android.view.LayoutInflater; //The LayoutInflater class is used to instantiate the contents of layout XML files into their corresponding View objects.
import android.view.View; // View is a simple rectangle, the building block of applications, as the documentation refers to it. Every Widget (TextViews, Buttons, and every other UI component) extends this class.
import android.view.ViewGroup; //The user interface (UI) for an Android app is built as a hierarchy of layouts and widgets. The layouts are ViewGroup objects, containers that control how their child views are positioned on the screen.
import android.widget.TextView;
import android.widget.Toast; //A toast is a view containing a quick little message for the user. The toast class helps you create and show those.

import androidx.annotation.NonNull; //@NonNull – The compiler can determine cases where a code path might receive a null value, without ever having to debug a NullPointerException.
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.myfirstapp.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {
    TextView showCountTextView;


    private void countMe(View view) {
        Log.d("info", "running countMe");
        // Get the value of the text view
        String countString = showCountTextView.getText().toString();
        System.out.println(countString);
        Log.d("info", "countstring defined"+countString);

        // Convert value to a number and increment it
        Integer count = Integer.parseInt(countString);
        count++;
        // Display the new value in the text view.
        showCountTextView.setText(count.toString());
    }

    private FragmentFirstBinding binding; // Extends androidx.view.Viewbinding

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
      binding = FragmentFirstBinding.inflate(inflater, container, false);
      showCountTextView = binding.textviewFirst;
      return binding.getRoot();
    }

    // @NonNull – The compiler can determine cases where a code path might receive a null value, without ever having to debug a NullPointerException.
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        binding.randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentCount = Integer.parseInt(showCountTextView.getText().toString()); // count clicks
                FirstFragmentDirections.ActionFirstFragmentToSecondFragment action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(currentCount); //create a subclass object ActionFirstFragmentToSecondFragment in the navigation class FirstFragmentDirections and input the currentcount as a variable, and call the subclass object action
                NavHostFragment.findNavController(FirstFragment.this).navigate(action); // call on navigate function (?) and input the subclass in previous line
            }
        });

        binding.toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast myToast = Toast.makeText(getActivity(), "Hello toast!", Toast.LENGTH_SHORT);
                myToast.show();
            }
        });

        binding.countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("info", "you clicked the button");
                countMe(view);
            }
        });
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}