package folio.jaagdeveloper.usman.a75criteria;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Criteria1 extends Fragment {

    //declare some variable outside functions so that they are accessable
    double attendancePercentage;
    int creditHours;
    View rootView;

    public Criteria1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //inflate method returns a view,
        // it is stored in variable so that we access to layout using this variable
        rootView = inflater.inflate(R.layout.fragment_criteria, container, false);
        //find the spinner view and add items to it
        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
        final String[] numbers = {"2", "3", "4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, numbers);

      //add a listener to the spinner,
        // whenever user selects a item from dropdown menu,
        // a set of instructions execuetes in result
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0: {
                        creditHours = (int)Integer.parseInt(numbers[position]);
                        break;
                    }
                    case 1: {
                        creditHours = (int)Integer.parseInt(numbers[position]);
                        break;
                    }
                    case 2: {
                        creditHours = (int)Integer.parseInt(numbers[position]);
                        break;
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                creditHours = Integer.parseInt(numbers[0]);

            }
        });
        //find reset button and add a llistener to it
        //when user press button, a method execuetes in return
        //edit text field and msg text will get empty

        Button resetButton = (Button) rootView.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText enterClassesLeft = (EditText) rootView.findViewById(R.id.edit_text);
                enterClassesLeft.setText("");
                display("");
                TextView percentageView = (TextView) rootView.findViewById(R.id.percentage);
                percentageView.setText("");
                percentageView.setBackgroundResource(0);
            }
        });
        //find calculate button and add a llistener to it
        //when user press button, a method execuetes in return
        Button calculateButton = (Button) rootView.findViewById(R.id.button);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get the classes left(input) from edittext field
                EditText enterClassesLeft = (EditText) rootView.findViewById(R.id.edit_text);
                String classesEntered = enterClassesLeft.getText().toString();
//                TextView summary = (TextView) rootView.findViewById(R.id.message);
//                summary.setBackgroundResource(R.drawable.text_rectangle);
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                String value = sharedPreferences.getString(getString(R.string.weeks_key),getString(R.string.weeks_value));
                int totalWeeks = (int)Integer.parseInt(value);
                int totalClasses = creditHours * totalWeeks ;
                if (classesEntered.matches("")) {
                    Toast.makeText(getContext(), "Invalid Input", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    int classesLeft = (int)Integer.parseInt((classesEntered));
                    if (classesLeft < 1 || classesLeft > totalClasses)
                    {
                        Toast.makeText(getContext(), "Invalid Input", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (creditHours == 2) {
                        attendancePercentage = 100.0 - (3.33 * classesLeft);
                    } else if (creditHours == 3) {
                        attendancePercentage = 100.0 - (2.22 * classesLeft);
                    } else if (creditHours == 4) {
                        attendancePercentage = 100.0 - (1.66 * classesLeft);
                    }


                    String message = "Attendance Criteria : 75%\nTotal Classes : "+totalClasses+
                            "\nWeeks/semester : "+totalWeeks;

//                    String message = " Attendance criteria = 75%\n" + "Credit hours = " + creditHours + "\nClasses Left = " + classesLeft +
//                            "\nTHANK YOU.";
                    display(message);
                    setAttendancePercentage(Math.round(attendancePercentage));
                }


            }
        });

        return rootView;
    }


    public void display(String message) {
        TextView displayMessage = (TextView) rootView.findViewById(R.id.message);
        displayMessage.setText(message);

    }
    public void setAttendancePercentage(double attendancePercentage)
    {
        TextView percentageView = (TextView) rootView.findViewById(R.id.percentage);
        String text = String.valueOf(attendancePercentage);
        percentageView.setText(text+"%");
        percentageView.setBackgroundResource(R.drawable.percentage_circle);
//        TextView percentageView = (TextView) rootView.findViewById(R.id.percentage);
//        percentageView.setText(new DecimalFormat("##.##").format(attendancePercentage)+"%");
//        percentageView.setBackgroundResource(R.drawable.percentage_circle);

    }


}