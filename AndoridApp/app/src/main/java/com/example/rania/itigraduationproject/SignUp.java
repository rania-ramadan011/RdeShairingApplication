package com.example.rania.itigraduationproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rania.itigraduationproject.Interfaces.Service;
import com.example.rania.itigraduationproject.PureClasses.User;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUp extends AppCompatActivity {
    EditText date;
    DatePickerDialog datePickerDialog;
    private RadioGroup radio_user_DriverGroup;
    private RadioButton radio_user_DriverGroupButton;
    private RadioGroup radioGenderType;
    private RadioButton radioGenderButton;
    private static Retrofit retrofit = null;
    TextView email;
    TextView password;
    TextView mobile;
    TextView  national_id;
    TextView login_link;
    TextView name;
    Button signBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        login_link = (TextView) findViewById(R.id.link_login);
        signBtn=(Button)findViewById(R.id.btn_signup);
        password=(TextView)findViewById(R.id.input_password);
        email=(TextView) findViewById(R.id.input_email);
        mobile=(TextView)findViewById(R.id.phone);
        national_id=(TextView)findViewById(R.id.naional_id);
        name=(TextView) findViewById(R.id.input_name);
        date = (EditText) findViewById(R.id.date);



        retrofit = new Retrofit.Builder()
                .baseUrl(Service.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final Service service = retrofit.create(Service.class);

        // Radio User or Driver
        radio_user_DriverGroup = (RadioGroup) findViewById(R.id.radioUserType);
        int typeid= radio_user_DriverGroup.getCheckedRadioButtonId();
        radio_user_DriverGroupButton =(RadioButton)findViewById(typeid);
        radio_user_DriverGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                radio_user_DriverGroupButton = (RadioButton) findViewById(checkedId);

                Toast.makeText(getApplicationContext(), radio_user_DriverGroupButton.getText(), Toast.LENGTH_SHORT).show();
            }
        });




        //----------------------------GetGender
        radioGenderType = (RadioGroup) findViewById(R.id.radioSex);
        //radioGenderButton =(RadioButton)findViewById;
        int genid= radioGenderType.getCheckedRadioButtonId();
        radioGenderButton =(RadioButton)findViewById(genid);
        radioGenderType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                radioGenderButton =(RadioButton)findViewById(checkedId);


                Toast.makeText(getApplicationContext(), radioGenderButton.getText(), Toast.LENGTH_SHORT).show();
            }
        });


        // initiate the date picker and a button
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(SignUp.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        //Check If it is User Or Driver

           signBtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   User user = new User();
                   user.setUserName(name.getText().toString());
                   user.setPassword(password.getText().toString());
                   user.setEmail(email.getText().toString());
                   user.setGender(radioGenderButton.getText().toString());
                   user.setMobile(mobile.getText().toString());
                   user.setNationalid(national_id.getText().toString());
                   //user.setBirthDate(date.getText().toString());


                   if (radio_user_DriverGroupButton.getText().toString().equals("User")) {
                       Toast.makeText(SignUp.this, "aaaaaaaaaaaaaaaaaa", Toast.LENGTH_SHORT).show();

                       service.sendUser(user).enqueue(new Callback<User>() {
                           @Override
                           public void onResponse(Call<User> call, Response<User> response) {
                               Toast.makeText(SignUp.this, "SignUp Sucessfully", Toast.LENGTH_SHORT).show();
                               Intent intent=new Intent(getApplicationContext(),Login.class);
                               startActivity(intent);

                           }

                           @Override
                           public void onFailure(Call<User> call, Throwable t) {
                               Toast.makeText(SignUp.this, "SignUp Faild", Toast.LENGTH_SHORT).show();

                           }
                       });

                   } else if (radio_user_DriverGroupButton.getText().toString().equals("Driver")) {
                       Toast.makeText(SignUp.this, "DriverActivity", Toast.LENGTH_SHORT).show();

                       Intent intent = new Intent(getApplicationContext(),DriverRegister.class);

                       intent.putExtra("userObj",user);
                       startActivity(intent);


                   }
               }});







        login_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });


    }

}
