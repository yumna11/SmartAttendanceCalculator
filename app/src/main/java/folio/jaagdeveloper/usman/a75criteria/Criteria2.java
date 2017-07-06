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


public class Criteria2 extends Fragment {
    //declare some variable outside functions so that they are accessable
    String message;
    int creditHours = 0;
    Spinner spinner;
    EditText editText;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //inflate method returns a view,
        // it is stored in variable so that we access to layout using this variables
        //using same layout for both fragments
        rootView = inflater.inflate(R.layout.fragment_criteria, container, false);
        //finding edittext field and set hint
        editText = (EditText) rootView.findViewById(R.id.edit_text);
        editText.setHint("Enter classes attended:");
        //find the spinner view and add items to it
        spinner = (Spinner) rootView.findViewById(R.id.spinner);
        final String[] numbers = {"2", "3", "4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, numbers);
        spinner.setAdapter(adapter);
        //add a listener to the spinner,
        // whenever user selects a item from dropdown menu,
        // a set of instructions execuetes in result
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position)
                {
                    case 0:
                    {
                        creditHours = (int)Integer.parseInt(numbers[position]);
                        break;
                    }
                    case 1:
                    {
                        creditHours = (int)Integer.parseInt(numbers[position]);
                        break;
                    }
                    case 2:
                    {
                        creditHours = (int)Integer.parseInt(numbers[position]);
                        break;
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                creditHours = (int)Integer.parseInt(numbers[0]);

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
                TextView attendanceSummmaryTextView = (TextView) rootView.findViewById(R.id.message);
                attendanceSummmaryTextView.setText("");
                TextView percentageView = (TextView) rootView.findViewById(R.id.percentage);
                percentageView.setText("");
                percentageView.setBackgroundResource(0);
            }
        });
        //find calculate button and add a listener to it
        //when user press button, a method execuetes in return
        Button calculateButton = (Button) rootView.findViewById(R.id.button);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int totalWeeks;
                int totalClasses;

                int classesAttended = 0;
                double attendancePercantage = 0;
                int requiredClasses = 0;
                int needClasses = 0;
                int bunkClasses = 0;
                // get the text(class attended) entered,
                String inputInField = editText.getText().toString();

                if (inputInField.matches(""))
                {
                    Toast.makeText(getContext(),"Invalid Input",Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    classesAttended = (int) Integer.parseInt(inputInField);
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                    String value = sharedPreferences.getString(getString(R.string.weeks_key),getString(R.string.weeks_value));
                    totalWeeks = (int)Integer.parseInt(value);
                    totalClasses = creditHours * totalWeeks ;
                    if (classesAttended<1 || classesAttended>totalClasses)
                    {
                        Toast.makeText(getContext(),"Invalid Input",Toast.LENGTH_LONG).show();
                        return;
                    }
                    //get the total weeks from settings

                attendancePercantage =  (classesAttended * 100) / totalClasses ;
                requiredClasses = totalClasses *  75 / 100  ;
                if (attendancePercantage >= 75.0 && attendancePercantage<=100.0)
                {
                    bunkClasses = classesAttended - requiredClasses ;
                    message = "Total Classes : "+totalClasses+
                            "\nAttended : "+classesAttended+
                            "\nYou have "+bunkClasses+" bunk"+
                            "\nWeeks/semester "+totalWeeks;
//            message = "Total Classes = "+totalClasses+"\nClasses Attended = "+classesAttended+
//                    "\nAttendance = "+attendancePercantage+"%\nRequired Classes for 75% Attendance is "+requiredClasses +
//                    " classes.\nYou can bunk "+bunkClasses+" classes."+"\nTotal weeks"+totalWeeks+"";
                }
                if (attendancePercantage < 75.0 && attendancePercantage>=0.0)
                {
                    needClasses = requiredClasses - classesAttended ;

                    message = "Attendance Criteria : 75%\nTotal Classes : "+totalClasses+
                            "\nAttended : "+classesAttended+
                            " \nAttend next "+needClasses+" classes."+
                            "\nWeeks/semester : "+totalWeeks+"";
//            message = "Attendance Criteria = 75%\nTotal Classes = "+totalClasses+"\nClasses Attended = "+classesAttended+
//                    "\nAttendance = "+attendancePercantage+"%\nRequired Classes for 75% Attendance is "+requiredClasses +
//                    " classes.\nYou need "+needClasses+" more classes for 75% attendance."+"\nTotal weeks"+totalWeeks+"";
                }
                    setAttendancePercentage(Math.round(attendancePercantage));
                display(message);

            }}
            /* This method displays the message in the textView created below calculate button
            * @param message*/
            public void display(String message)
            {
                TextView attendanceSummmaryTextView = (TextView) rootView.findViewById(R.id.message);
                attendanceSummmaryTextView.setText(message);

            }
        });


        return rootView;
    }
    /* This method is called when calculate button is pressed
    * @param button view*/
    public void setAttendancePercentage(double attendancePercentage)
    {
        TextView percentageView = (TextView) rootView.findViewById(R.id.percentage);
        String text = String.valueOf(attendancePercentage);
        percentageView.setText(text+"%");
        percentageView.setBackgroundResource(R.drawable.percentage_circle);
//        TextView percentageView = (TextView) rootView.findViewById(R.id.percentage);
//        percentageView.setText(new DecimalFormat("##.#").format(attendancePercentage)+"%");
//        percentageView.setBackgroundResource(R.drawable.percentage_circle);
    }



}
