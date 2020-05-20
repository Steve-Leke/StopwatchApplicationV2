package com.example.stopwatchapplication;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sccomponents.gauges.ScArcGauge;
import com.sccomponents.gauges.ScGauge;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private EditText editTextWeight;
    private EditText editTextHeight;
    private EditText editTextBMI;
    private EditText editTextAge;
    Double BMI;
    Double BFP;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account, container, false);
        final ScArcGauge gauge = (ScArcGauge) v.findViewById(R.id.BMIgauge);
        assert gauge != null;

        final ImageView indicator = (ImageView) v.findViewById(R.id.BMIindicator);
        assert indicator != null;

        final TextView counter = (TextView) v.findViewById(R.id.BMIcounter);
        assert counter != null;

        final TextView obesityLevel = (TextView) v.findViewById(R.id.BMIObesityLevel);
        assert obesityLevel != null;

        final ScArcGauge BFPgauge = (ScArcGauge) v.findViewById(R.id.BFPgauge);
        assert BFPgauge != null;

        final ImageView BFPindicator = (ImageView) v.findViewById(R.id.BFPindicator);
        assert BFPindicator != null;

        final TextView BFPcounter = (TextView) v.findViewById(R.id.BFPcounter);
        assert BFPcounter != null;

        final TextView BFPobesityLevel = (TextView) v.findViewById(R.id.BFPObesityLevel);
        assert BFPobesityLevel != null;


        editTextHeight = (EditText) v.findViewById(R.id.editTextHeight);
        editTextWeight = (EditText) v.findViewById(R.id.editTextWeight);

        editTextBMI = (EditText) v.findViewById(R.id.editTextBMI);
        editTextAge = (EditText) v.findViewById(R.id.editTextAge);

        // Set the center pivot for a right rotation
        indicator.setPivotX(30f);
        indicator.setPivotY(30f);

        BFPindicator.setPivotX(30f);
        BFPindicator.setPivotY(30f);

        // As the progress feature by default the last to be draw I must bring the notches feature
        // on top.
        gauge.bringOnTop(ScGauge.NOTCHES_IDENTIFIER);
        BFPgauge.bringOnTop(ScGauge.NOTCHES_IDENTIFIER);

        // If you set the value from the xml that not produce an event so I will change the
        // value from code.





        // Each time I will change the value I must write it inside the counter text.
        gauge.setOnEventListener(new ScGauge.OnEventListener() {
            @Override
            public void onValueChange(float lowValue, float highValue) {

                if (BMI <= 18.5) {
                    float angle = gauge.percentageToAngle(highValue-22);
                    indicator.setRotation(angle);
                    counter.setText(BMI + "");
                    obesityLevel.setText("Underweight");
                } else if (BMI > 18.5 && BMI < 25) {
                    float angle = gauge.percentageToAngle(highValue-15);
                    indicator.setRotation(angle);
                    counter.setText(BMI + "");
                    obesityLevel.setText("Normal");
                } else if (BMI > 25 && BMI < 30) {
                    float angle = gauge.percentageToAngle(highValue-10);
                    indicator.setRotation(angle);
                    counter.setText(BMI + "");
                    obesityLevel.setText("Overweight");
                } else if (BMI >= 30) {
                    float angle = gauge.percentageToAngle(highValue-5);
                    indicator.setRotation(angle);
                    counter.setText(BMI + "");
                    obesityLevel.setText("Obese");
                }

//                editTextHeight.setEnabled(true);
//                editTextWeight.setEnabled(true);


//                // Convert the percentage value in an angle
//                float angle = gauge.percentageToAngle(highValue-5);
//                indicator.setRotation(angle);
//
//                // Write the value
////                int value = (int) ScGauge.percentageToValue(highValue, 0.0f, 100.0f);
//                counter.setText(highValue + "");
            }
        });

        BFPgauge.setOnEventListener(new ScGauge.OnEventListener() {
            @Override
            public void onValueChange(float lowValue, float highValue) {

                if (BFP <= 8) {
                    float angle = BFPgauge.percentageToAngle(highValue-22);
                    BFPindicator.setRotation(angle);
                    BFPcounter.setText(BFP + "%");
                    BFPobesityLevel.setText("Underfat");
                } else if (BFP > 8 && BFP < 19) {
                    float angle = BFPgauge.percentageToAngle(highValue-15);
                    BFPindicator.setRotation(angle);
                    BFPcounter.setText(BFP + "%");
                    BFPobesityLevel.setText("Ideal");
                } else if (BFP > 20 && BFP < 25) {
                    float angle = BFPgauge.percentageToAngle(highValue-10);
                    BFPindicator.setRotation(angle);
                    BFPcounter.setText(BFP+ "%");
                    BFPobesityLevel.setText("Overfat");
                } else if (BFP >= 25) {
                    float angle = BFPgauge.percentageToAngle(highValue-5);
                    BFPindicator.setRotation(angle);
                    BFPcounter.setText(BFP + "%");
                    BFPobesityLevel.setText("Obese");
                }

//                editTextHeight.setEnabled(true);
//                editTextWeight.setEnabled(true);


//                // Convert the percentage value in an angle
//                float angle = gauge.percentageToAngle(highValue-5);
//                indicator.setRotation(angle);
//
//                // Write the value
////                int value = (int) ScGauge.percentageToValue(highValue, 0.0f, 100.0f);
//                counter.setText(highValue + "");
            }
        });




        TextWatcher HeigthWatcher = new TextWatcher() {

            public void afterTextChanged(Editable s) {
                setBmiValue();
                if (BMI < 18.5) {
                    gauge.setHighValue(25);
                } else if (BMI > 18.5 && BMI < 25) {
                    gauge.setHighValue(50);
                } else if (BMI > 25 && BMI < 30) {
                    gauge.setHighValue(75);
                } else {
                    gauge.setHighValue(100);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }
        };

        editTextHeight.addTextChangedListener(HeigthWatcher);


        TextWatcher WeightWatcher = new TextWatcher() {

            public void afterTextChanged(Editable s) {
                setBmiValue();
                if (BMI < 18.5) {
                    gauge.setHighValue(25);
                } else if (BMI > 18.5 && BMI < 25) {
                    gauge.setHighValue(50);
                } else if (BMI > 25 && BMI < 30) {
                    gauge.setHighValue(75);
                } else {
                    gauge.setHighValue(100);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }
        };

        editTextWeight.addTextChangedListener(WeightWatcher);


        TextWatcher BMIWatcher = new TextWatcher() {

            public void afterTextChanged(Editable s) {
                setBFPValue();
                if (BFP < 8) {
                    BFPgauge.setHighValue(25);
                } else if (BFP > 8 && BFP < 19) {
                    BFPgauge.setHighValue(50);
                } else if (BFP > 20 && BFP < 25) {
                    BFPgauge.setHighValue(75);
                } else {
                    BFPgauge.setHighValue(100);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }
        };

        editTextBMI.addTextChangedListener(BMIWatcher);




        //BFP Settings
//        TextWatcher BMIWatcher = new TextWatcher() {
//
//            public void afterTextChanged(Editable s) {
//                setBFPValue();
//                if (BFP < 8) {
//                    BFPgauge.setHighValue(25);
//                } else if (BFP > 8 && BFP < 19) {
//                    BFPgauge.setHighValue(50);
//                } else if (BFP > 20 && BFP < 25) {
//                    BFPgauge.setHighValue(75);
//                } else {
//                    BFPgauge.setHighValue(100);
//                }
//            }
//
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            public void onTextChanged(CharSequence s, int start, int before,
//                                      int count) {
//
//            }
//        };

//        editTextBMI.addTextChangedListener(BMIWatcher);

        TextWatcher AgeWatcher = new TextWatcher() {

            public void afterTextChanged(Editable s) {
                setBFPValue();
                if (BFP < 8) {
                    BFPgauge.setHighValue(25);
                } else if (BFP > 8 && BFP < 19) {
                    BFPgauge.setHighValue(50);
                } else if (BFP > 20 && BFP < 25) {
                    BFPgauge.setHighValue(75);
                } else {
                    BFPgauge.setHighValue(100);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }
        };

        editTextAge.addTextChangedListener(AgeWatcher);


        return v;
    }

    public void setBmiValue() {
        double Height = 0;
        double Weight = 0;
        double finalBMI = 0;
        if (!editTextHeight.getText().toString().isEmpty() && !editTextWeight.getText().toString().isEmpty() ) {
            // fetching value from edit text and type cast to integer
            Height = Integer.parseInt(editTextHeight.getText().toString().trim());
            Weight = Integer.parseInt(editTextWeight.getText().toString().trim());
            finalBMI =(703 * Weight / (Height * Height));
        } else if (editTextWeight.getText().toString() .isEmpty()) {
            // toast message to fill edit text
            Toast.makeText(getContext(), getString(R.string.message_weight), Toast.LENGTH_LONG).show();
        } else if (editTextHeight.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), getString(R.string.message_height), Toast.LENGTH_LONG).show();
        }
        BMI = finalBMI;
    }

    public void setBFPValue() {
        double Age = 0;
        double Bmi = 0;
        double finalBFP = 0;
        if (!editTextAge.getText().toString().isEmpty() && !editTextBMI.getText().toString().isEmpty() ) {
            // fetching value from edit text and type cast to integer
            Bmi = Integer.parseInt(editTextBMI.getText().toString().trim());
            Age = Integer.parseInt(editTextAge.getText().toString().trim());
            finalBFP =(((1.20 * Bmi) + (0.23 * Age)) - 16.2);
        } else if (editTextBMI.getText().toString() .isEmpty()) {
            // toast message to fill edit text
            Toast.makeText(getContext(), getString(R.string.message_BMI), Toast.LENGTH_LONG).show();
        } else if (editTextAge.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), getString(R.string.message_Age), Toast.LENGTH_LONG).show();
        }
        BFP = finalBFP;
    }
}
