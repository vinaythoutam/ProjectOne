package com.pillreminder.pillreminder;

import android.app.Activity;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.MyOptionsPickerView;
import com.pillreminder.pillreminder.helper.ReusableCode;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import top.defaults.view.PickerView;

public class NewMedications extends AppCompatActivity {

    TextView formTextView,dosageCount,dosageForm,withMeals,withoutMeals,NewMedNext;
    final ArrayList<String>  count = new ArrayList<>();
    final ArrayList<String>  name = new ArrayList<>();

    ImageView formIcon;
    String isWithOrNot="true";

    EditText medName;
    String selectedImageTag="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_medications);

        getSupportActionBar().hide();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView toolImageView = (ImageView) findViewById(R.id.toolImage);
        dosageCount=(TextView)findViewById(R.id.dosageCount);
        dosageForm=(TextView)findViewById(R.id.dosageForm);
        formIcon=(ImageView)findViewById(R.id.formIconIV);
        withMeals=(TextView)findViewById(R.id.withMealsText);
        withoutMeals=(TextView)findViewById(R.id.withoutMealsText);
        withMeals.setTextColor(getResources().getColor(R.color.colorPrimary));
        withoutMeals.setTextColor(getResources().getColor(R.color.gray));
        NewMedNext=(TextView)findViewById(R.id.newMedNextBtn);
        medName=(EditText)findViewById(R.id.medNameTV);


        toolImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

//                startActivity(new Intent(NewMedications.this,NewMedications.class));

            }
        });

        formTextView=(TextView)findViewById(R.id.formTV);
        formTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(NewMedications.this,FormActivity.class);
//                startActivityForResult(intent,001);
                startActivityForResult(new Intent(NewMedications.this,FormActivity.class),001);
            }
        });

        PickerView pickerView = (PickerView)findViewById(R.id.pickerView);
        PickerView pickerView1= (PickerView)findViewById(R.id.pickerView2);

        count.add("0.25");
        count.add("0.5");
        count.add("0.75");
        count.add("1");
        count.add("1.25");
        count.add("1.5");
        count.add("1.75");
        count.add("2");

        name.add("Pill");
        name.add("Capsules");
        name.add("Units");
        name.add("ml");
        name.add("mg");

        dosageCount.setText(count.get(0));
        dosageForm.setText(name.get(0));
        PickerView.Adapter adapterCount = new PickerView.Adapter() {

            @Override
            public int getItemCount() {
                return count.size();
            }

            @Override
            public PickerView.PickerItem getItem(int index) {
                return null;
            }

            @Override
            public String getText(int index) {
                return count.get(index) ;
            }

        };

        PickerView.Adapter adapterNames = new PickerView.Adapter() {

            @Override
            public int getItemCount() {
                return name.size();
            }

            @Override
            public PickerView.PickerItem getItem(int index) {
                return null;
            }

            @Override
            public String getText(int index) {
                return name.get(index);
            }

        };

        pickerView.setAdapter(adapterCount);
        pickerView1.setAdapter(adapterNames);

        pickerView.setOnSelectedItemChangedListener(new PickerView.OnSelectedItemChangedListener() {
            @Override
            public void onSelectedItemChanged(PickerView pickerView, int previousPosition, int selectedItemPosition) {

                dosageCount.setText(count.get(selectedItemPosition));
            }
        });
        pickerView1.setOnSelectedItemChangedListener(new PickerView.OnSelectedItemChangedListener() {
            @Override
            public void onSelectedItemChanged(PickerView pickerView, int previousPosition, int selectedItemPosition) {
                dosageForm.setText(name.get(selectedItemPosition));
            }
        });


        withMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                withMeals.setTextColor(getResources().getColor(R.color.colorPrimary));
                withoutMeals.setTextColor(getResources().getColor(R.color.gray));
                isWithOrNot="true";
            }
        });

        withoutMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                withoutMeals.setTextColor(getResources().getColor(R.color.colorPrimary));
                withMeals.setTextColor(getResources().getColor(R.color.gray));
                isWithOrNot="false";
            }
        });

        NewMedNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(medName.getText().toString().equals("")){
                    ReusableCode.showValidationDialog("Please enter medicine name",NewMedications.this);
                }else if(formTextView.getText().toString().equalsIgnoreCase("Form")){
                    ReusableCode.showValidationDialog("Please select medicine form",NewMedications.this);
                }else {
                    Intent intent = new Intent(NewMedications.this,ScheduleActivity.class);
                    intent.putExtra("MedName",medName.getText().toString());
                    intent.putExtra("FormName",formTextView.getText().toString());
                    intent.putExtra("FormID",selectedImageTag);
                    intent.putExtra("DosageCount",dosageCount.getText().toString());
                    intent.putExtra("DosageForm",dosageForm.getText().toString());
                    intent.putExtra("IsWithMeal",isWithOrNot);
//                    Log.e("imageID",selectedImageTag);
                    startActivity(intent);

                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 001) {
            if(resultCode == Activity.RESULT_OK){
                String name=data.getStringExtra("name");
                int imageID=data.getIntExtra("image",0);
                formIcon.setImageResource(imageID);
                formTextView.setText(name);
                selectedImageTag=data.getStringExtra("Tag");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult
}
