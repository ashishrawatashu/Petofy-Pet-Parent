package com.cynoteck.petofyparents.activty;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cynoteck.petofyparents.R;
import com.cynoteck.petofyparents.adapter.VisitTypesAdapter;
import com.cynoteck.petofyparents.api.ApiClient;
import com.cynoteck.petofyparents.api.ApiResponse;
import com.cynoteck.petofyparents.api.ApiService;
import com.cynoteck.petofyparents.parameter.immunizationRequest.ImmunizationParams;
import com.cynoteck.petofyparents.parameter.immunizationRequest.ImmunizationRequest;
import com.cynoteck.petofyparents.response.getImmunizationReport.PetImmunizationRecordResponse;
import com.cynoteck.petofyparents.response.getPetReportsResponse.GetReportsTypeData;
import com.cynoteck.petofyparents.response.getPetReportsResponse.GetReportsTypeResponse;
import com.cynoteck.petofyparents.utils.Config;
import com.cynoteck.petofyparents.utils.Methods;
import com.cynoteck.petofyparents.utils.RegisterRecyclerViewClickListener;

import java.util.ArrayList;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

import retrofit2.Response;

public class SelectPetReportsActivity extends AppCompatActivity implements ApiResponse, View.OnClickListener, RegisterRecyclerViewClickListener {


    String pet_unique_id, pet_name,pet_sex, pet_owner_name,pet_owner_contact,pet_id,pet_encryt_id,pet_age;
    ImageView back_arrow_IV;
    TextView pet_name_TV,pet_sex_TV,pet_id_TV,pet_owner_name_TV,pet_owner_phone_no_TV;
    VisitTypesAdapter visitTypesAdapter;
    RecyclerView reports_types_RV;
    RelativeLayout reports_list_RL;
    ImageView view_xrayReport_arrow ,view_labTestReport_arrow,view_Hospitalization_arrow;
    ArrayList<GetReportsTypeData> getReportsTypeData;
    RelativeLayout xray_layout,lab_test_layout,hospitalization_layout;
    Methods methods;
    WebView webview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_pet_reports);

        methods = new Methods(this);
        init();
        setDeatils();
        getVisitTypes();

    }

    private void setDeatils() {
        pet_name_TV.setText(pet_name);
        pet_sex_TV.setText("("+pet_sex+")");
        pet_owner_name_TV.setText(pet_owner_name);
        pet_id_TV.setText(pet_unique_id);
        pet_owner_phone_no_TV.setText("("+pet_owner_contact+")");

    }

    private void init() {

        Bundle extras = getIntent().getExtras();
        pet_id=extras.getString("pet_id");
        pet_unique_id = extras.getString("pet_unique_id");
        pet_name =extras.getString("pet_name");
        pet_sex =extras.getString("pet_sex");
        pet_owner_name =extras.getString("pet_owner_name");
        pet_encryt_id = extras.getString("pet_encryt_id");
        pet_age = extras.getString("pet_age");
        reports_types_RV=findViewById(R.id.reports_types_RV);
        back_arrow_IV = findViewById(R.id.back_arrow_IV);
        pet_name_TV = findViewById(R.id.pet_name_TV);
        pet_sex_TV = findViewById(R.id.pet_sex_TV);
        pet_id_TV = findViewById(R.id.pet_id_TV);
        pet_owner_name_TV = findViewById(R.id.pet_owner_name_TV);
        pet_owner_phone_no_TV = findViewById(R.id.pet_owner_phone_no_TV);
        reports_list_RL=findViewById(R.id.reports_list_RL);
        view_xrayReport_arrow=findViewById(R.id.view_xrayReport_arrow);
        xray_layout=findViewById(R.id.xray_layout);
        view_labTestReport_arrow=findViewById(R.id.view_labTestReport_arrow);
        lab_test_layout=findViewById(R.id.lab_test_layout);
        view_Hospitalization_arrow=findViewById(R.id.view_Hospitalization_arrow);
        hospitalization_layout=findViewById(R.id.hospitalization_layout);
        webview = findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        back_arrow_IV.setOnClickListener(this);
        xray_layout.setOnClickListener(this);
        lab_test_layout.setOnClickListener(this);
        hospitalization_layout.setOnClickListener(this);

    }

    private void getVisitTypes() {
        ApiService<GetReportsTypeResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getReportsType(Config.token), "GetReportsType");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.back_arrow_IV:
                onBackPressed();
                break;

            case R.id.xray_layout:
                intentStaticReports("7.0");

                break;

            case R.id.lab_test_layout:
                intentStaticReports("8.0");

                break;

            case R.id.hospitalization_layout:
                intentStaticReports("9.0");

                break;
        }

    }


    private void intentStaticReports(String report_id) {
        Intent staticReportsIntent = new Intent(this, ReportsCommonActivity.class);
        Bundle staticReportsData = new Bundle();
        staticReportsData.putString("pet_id",pet_id);
        staticReportsData.putString("pet_name",pet_name);
        staticReportsData.putString("pet_unique_id",pet_unique_id);
        staticReportsData.putString("pet_sex",pet_sex);
        staticReportsData.putString("pet_owner_name",pet_owner_name);
        staticReportsData.putString("pet_owner_contact",pet_owner_contact);
        staticReportsData.putString("reports_id",report_id);
        staticReportsData.putString("button_type","view");

        staticReportsIntent.putExtras(staticReportsData);
        startActivity(staticReportsIntent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);

    }

    @Override
    public void onResponse(Response response, String key) {

        switch (key){
            case "GetReportsType":
                try {
                    Log.d("GetPetServiceTypes",response.body().toString());
                    GetReportsTypeResponse petServiceResponse = (GetReportsTypeResponse) response.body();
                    int responseCode = Integer.parseInt(petServiceResponse.getResponse().getResponseCode());
                    if (responseCode== 109){

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                        reports_types_RV.setLayoutManager(linearLayoutManager);
                        reports_types_RV.setNestedScrollingEnabled(false);
                        visitTypesAdapter  = new VisitTypesAdapter(SelectPetReportsActivity.this,petServiceResponse.getData(),this);
                        getReportsTypeData = petServiceResponse.getData();
                        reports_types_RV.setAdapter(visitTypesAdapter);
                        visitTypesAdapter.notifyDataSetChanged();
                        reports_list_RL.setVisibility(View.VISIBLE);
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;


            case "GetImmunization":
                try {
                    methods.customProgressDismiss();
                    Log.d("GetImmunization",response.body().toString());
                    PetImmunizationRecordResponse immunizationRecordResponse = (PetImmunizationRecordResponse) response.body();
                    int responseCode = Integer.parseInt(immunizationRecordResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        if (immunizationRecordResponse.getData().getPetImmunizationDetailModels().isEmpty()){
                            Toast.makeText(this, "No Record Found !", Toast.LENGTH_SHORT).show();
                        }else {
                            ArrayList<String> immunizationDate = new ArrayList<>();
                            ArrayList<String> vaccineClass = new ArrayList<>();
                            ArrayList<String> nextDueDate = new ArrayList<>();

                            for (int i = 0; i < immunizationRecordResponse.getData().getPetImmunizationDetailModels().size(); i++) {
                                immunizationDate.add(immunizationRecordResponse.getData().getPetImmunizationDetailModels().get(i).getImmunizationDate().substring(0, immunizationRecordResponse.getData().getPetImmunizationDetailModels().get(i).getImmunizationDate().length() - 11));
                                vaccineClass.add(immunizationRecordResponse.getData().getPetImmunizationDetailModels().get(i).getVaccine());
                                nextDueDate.add(immunizationRecordResponse.getData().getPetImmunizationDetailModels().get(i).getNextDueDate());
                            }
                            JSONArray date = new JSONArray(immunizationDate);
                            JSONArray vaccine = new JSONArray(vaccineClass);
                            JSONArray nextDate = new JSONArray(nextDueDate);
                            Log.e("aaaaaa", vaccineClass.toString());
                            Log.e("aaaaaa", vaccine.toString());
                            String immunizationSet = methods.immunizationPdfGenarator(pet_name, pet_age, pet_sex, pet_owner_name, "4564564644465", date, vaccine, nextDate);

                            webview.loadDataWithBaseURL(null, immunizationSet, "text/html", "utf-8", null);
                            new Handler().postDelayed(new Runnable() {
                                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                                @Override
                                public void run() {
                                    Context context = SelectPetReportsActivity.this;
                                    PrintManager printManager = (PrintManager) getSystemService(context.PRINT_SERVICE);
                                    PrintDocumentAdapter adapter = null;
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                        adapter = webview.createPrintDocumentAdapter();
                                    }
                                    String JobName = getString(R.string.app_name) + "Document";
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                        PrintJob printJob = printManager.print(JobName, adapter, new PrintAttributes.Builder().build());
                                    }
                                }
                            }, 3000);

                        }

                    }else if (responseCode==614){
                        Toast.makeText(this, immunizationRecordResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    @Override
    public void onError(Throwable t, String key) {

    }

    @Override
    public void onProductClick(int position) {

        if (getReportsTypeData.get(position).getId().equals("4.0")){
            methods.showCustomProgressBarDialog(this);
            ImmunizationParams immunizationParams = new ImmunizationParams();
            immunizationParams.setEncryptedId(pet_encryt_id);
//        immunizationParams.setEncryptedId(getPetListResponse.getData().getPetClinicVisitList().get(position).getEncryptedId());
            ImmunizationRequest immunizationRequest = new ImmunizationRequest();
            immunizationRequest.setData(immunizationParams);

            ApiService<PetImmunizationRecordResponse> service = new ApiService<>();
            service.get(this, ApiClient.getApiInterface().viewPetVaccination(Config.token,immunizationRequest), "GetImmunization");
            Log.d("GetImmunization",immunizationRequest.toString());
        }

        else if (getReportsTypeData.get(position).getId().equals("")){

        }else if (getReportsTypeData.get(position).getId().equals("")){

        }

        /*getReportsTypeData.get(position).getId();
        Intent selectReportsIntent = new Intent(this, ReportsCommonActivity.class);
        Bundle data = new Bundle();
        data.putString("pet_id",pet_id);
        data.putString("pet_name",pet_name);
        data.putString("pet_unique_id",pet_unique_id);
        data.putString("pet_sex",pet_sex);
        data.putString("pet_owner_name",pet_owner_name);
        data.putString("pet_owner_contact",pet_owner_contact);
        data.putString("reports_id",getReportsTypeData.get(position).getId());
        data.putString("button_type","view");
        selectReportsIntent.putExtras(data);
        startActivity(selectReportsIntent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);*/
    }
    

}
