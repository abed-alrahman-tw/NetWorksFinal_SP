package com.example.finalproject_sp.activites;

import static com.android.volley.Request.Method.POST;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.finalproject_sp.R;
import com.example.finalproject_sp.databinding.ActivitySignUpBinding;
import com.example.finalproject_sp.interfaces.Constants;

import org.json.JSONObject;

public class SignUp extends AppCompatActivity {
    ActivitySignUpBinding binding;
    //    boolean b;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    RequestQueue requestQueue;
    EditText fullNameEt, emailEt, phoneNum, passwordEt;
    Button signupBtn;
    ImageView backBtn;
    TextView signInTv;
    CheckBox privacyCh;
    Spinner spinner;
    String jobId;
    Intent intent;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeVars_Views();

        jobId = getJobWorkId();
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullName = fullNameEt.getText().toString();
                String password = passwordEt.getText().toString();
                String email = emailEt.getText().toString();
                String phone = phoneNum.getText().toString();

                postData(email, password, phone, fullName, jobId);

            }
        });
    }


    void postData(String email, String password, String phone, String fullName, String workId) {

        showAnimation();
        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("email", email);
            jsonObject.put("password", password);
            jsonObject.put("phone", phone);
            jsonObject.put("name", fullName);
            jsonObject.put("work_id", workId);

        } catch (Exception e) {

            Log.e("e", "postData: " + e.getMessage());
        }
        JsonObjectRequest request = new JsonObjectRequest(POST, "https://studentucas.awamr.com/api/auth/register/delivery",
                jsonObject, new Response.Listener<JSONObject>() {

            JSONObject object;
            String token = "Bearer ";

            @Override
            public void onResponse(JSONObject response) {

                try {

                    if (response.getBoolean("success")) {
                        object = response.getJSONObject("data");

                        Toast.makeText(SignUp.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                        token += object.getString("token");
                        editor.putString("token", token);
                        editor.putString(Constants.WORK_ID,workId);
                        editor.commit();
                        intent.putExtra("email", email);
                        intent.putExtra("password", password);
                        startActivity(intent);

                    } else {

                        hideAnimation();
                        Toast.makeText(SignUp.this, "" + response.get("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    hideAnimation();
                    Log.e("Exception1", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideAnimation();
                Toast.makeText(SignUp.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(request);

    }

    void initializeVars_Views() {
        binding.animationView.setVisibility(View.INVISIBLE);
        fullNameEt = binding.registerProviderEtFullName;
        emailEt = binding.registerProviderEtEmail;
        phoneNum = binding.registerProviderEtPhoneNumber;
        passwordEt = binding.registerProviderEtPassword;
        backBtn = binding.registerProviderImgBack;
        intent = new Intent(SignUp.this, LogIn.class);

        signupBtn = binding.registerProviderBtnRegister;
        signInTv = binding.registerProviderTvSignIn;
        privacyCh = binding.registerProviderCbAccept;
        requestQueue = Volley.newRequestQueue(this);
        preferences = getSharedPreferences("shared", 0);
        editor = preferences.edit();
        spinner = binding.registerProviderSpinner;
        adapter = ArrayAdapter.createFromResource(this, R.array.services, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    String getJobWorkId() {


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                jobId = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return jobId;
    }

    void showAnimation() {
        binding.ccp.setVisibility(View.INVISIBLE);
        binding.registerProviderBtnRegister.setVisibility(View.INVISIBLE);
        binding.registerProviderEtFullNameLayout.setVisibility(View.INVISIBLE);
        binding.registerProviderEtEmailLayout.setVisibility(View.INVISIBLE);
        binding.registerProviderEtPasswordLayout.setVisibility(View.INVISIBLE);
        binding.registerProviderTvSignIn.setVisibility(View.INVISIBLE);
        binding.registerProviderSpinner.setVisibility(View.INVISIBLE);
        binding.registerProviderTvHaveAccount.setVisibility(View.INVISIBLE);
        binding.registerProviderEtPhoneNumber.setVisibility(View.INVISIBLE);
        binding.registerProviderLinear2.setVisibility(View.INVISIBLE);
        binding.registerProviderLine3.setVisibility(View.INVISIBLE);
        binding.registerProviderLinearNumber.setVisibility(View.INVISIBLE);
        binding.animationView.setVisibility(View.VISIBLE);
    }

    void hideAnimation() {
        binding.ccp.setVisibility(View.VISIBLE);
        binding.registerProviderBtnRegister.setVisibility(View.VISIBLE);
        binding.registerProviderCbAccept.setVisibility(View.VISIBLE);
        binding.registerProviderEtFullNameLayout.setVisibility(View.VISIBLE);
        binding.registerProviderEtEmailLayout.setVisibility(View.VISIBLE);
        binding.registerProviderEtPasswordLayout.setVisibility(View.VISIBLE);
        binding.registerProviderTvSignIn.setVisibility(View.VISIBLE);
        binding.registerProviderSpinner.setVisibility(View.VISIBLE);
        binding.registerProviderEtPhoneNumber.setVisibility(View.VISIBLE);
        binding.registerProviderLinear2.setVisibility(View.VISIBLE);
        binding.registerProviderLine3.setVisibility(View.VISIBLE);
        binding.registerProviderLinearNumber.setVisibility(View.VISIBLE);
        binding.animationView.setVisibility(View.INVISIBLE);
    }
}