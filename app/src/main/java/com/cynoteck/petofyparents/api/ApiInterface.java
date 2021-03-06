package com.cynoteck.petofyparents.api;

import com.cynoteck.petofyparents.parameter.addParamRequest.AddPetRequset;
import com.cynoteck.petofyparents.parameter.appointmentParams.AppointmentsStatusRequest;
import com.cynoteck.petofyparents.parameter.appointmentParams.CreateAppointRequest;
import com.cynoteck.petofyparents.parameter.appointmentParams.UpdateAppointmentRequest;
import com.cynoteck.petofyparents.parameter.changePassRequest.ChangePassRequest;
import com.cynoteck.petofyparents.parameter.forgetPassRequest.ForgetPassRequest;
import com.cynoteck.petofyparents.parameter.getOrderDetailsParms.GetOrderRequest;
import com.cynoteck.petofyparents.parameter.getPetListRequest.GetPetListRequest;
import com.cynoteck.petofyparents.parameter.getVetListParams.GetVetListRequest;
import com.cynoteck.petofyparents.parameter.immunizationRequest.ImmunizationRequest;
import com.cynoteck.petofyparents.parameter.loginParameter.Loginparams;
import com.cynoteck.petofyparents.parameter.newPetEntryParams.NewPetRequest;
import com.cynoteck.petofyparents.parameter.otpRequest.SendOtpRequest;
import com.cynoteck.petofyparents.parameter.paymentHistoryStaus.PaymentHistoryRequest;
import com.cynoteck.petofyparents.parameter.petBreedRequest.BreedParams;
import com.cynoteck.petofyparents.parameter.petReportsRequest.PetClinicVisitDetailsRequest;
import com.cynoteck.petofyparents.parameter.petReportsRequest.PetDataRequest;
import com.cynoteck.petofyparents.parameter.petReportsRequest.VisitTypeRequest;
import com.cynoteck.petofyparents.parameter.registerRequest.Registerparams;
import com.cynoteck.petofyparents.parameter.updateRequest.updateParamRequest.UpdatePetRequest;
import com.cynoteck.petofyparents.response.addPet.addPetResponse.AddPetValueResponse;
import com.cynoteck.petofyparents.response.addPet.breedResponse.BreedCatRespose;
import com.cynoteck.petofyparents.response.addPet.imageUpload.ImageResponse;
import com.cynoteck.petofyparents.response.addPet.petAgeResponse.PetAgeValueResponse;
import com.cynoteck.petofyparents.response.addPet.petColorResponse.PetColorValueResponse;
import com.cynoteck.petofyparents.response.addPet.petSizeResponse.PetSizeValueResponse;
import com.cynoteck.petofyparents.response.addPet.uniqueIdResponse.UniqueResponse;
import com.cynoteck.petofyparents.response.appointmentResponse.AppointmentDetailsResponse;
import com.cynoteck.petofyparents.response.appointmentResponse.CreateAppointmentResponse;
import com.cynoteck.petofyparents.response.appointmentResponse.GetAppointmentResponse;
import com.cynoteck.petofyparents.response.forgetAndChangePassResponse.PasswordResponse;
import com.cynoteck.petofyparents.response.getAppointmentsStatusResponse.AppointmentStatusResponse;
import com.cynoteck.petofyparents.response.getImmunizationReport.PetImmunizationRecordResponse;
import com.cynoteck.petofyparents.response.getLabTestReportResponse.getPetLabWorkListResponse.PetLabWorkResponse;
import com.cynoteck.petofyparents.response.getOrderResponse.GetOrderResponse;
import com.cynoteck.petofyparents.response.getPetDetailsResponse.GetPetResponse;
import com.cynoteck.petofyparents.response.getPetHospitalizationResponse.getHospitalizationListResponse.GetPetHospitalizationResponse;
import com.cynoteck.petofyparents.response.getPetIdCardResponse.PetIdCardResponse;
import com.cynoteck.petofyparents.response.getPetParentResponse.GetPetParentResponse;
import com.cynoteck.petofyparents.response.getPetReportsResponse.GetReportsTypeResponse;
import com.cynoteck.petofyparents.response.getPetReportsResponse.getClinicVisitDetails.GetClinicVisitsDetailsResponse;
import com.cynoteck.petofyparents.response.getPetReportsResponse.getPetClinicVisitsListsResponse.GetPetClinicVisitListResponse;
import com.cynoteck.petofyparents.response.getPetReportsResponse.getPetListResponse.GetPetListResponse;
import com.cynoteck.petofyparents.response.getVetListResponse.GetVetListResponse;
import com.cynoteck.petofyparents.response.getXRayReports.getPetTestAndXRayResponse.GetPetTestAndXRayResponse;
import com.cynoteck.petofyparents.response.loginResponse.LoginRegisterResponse;
import com.cynoteck.petofyparents.response.newPetResponse.NewPetRegisterResponse;
import com.cynoteck.petofyparents.response.otpResponse.OtpResponse;
import com.cynoteck.petofyparents.response.paymentStatusResponse.PaymentStatusResponse;
import com.cynoteck.petofyparents.response.twofactAuthResponse.TwoFactAuthResponse;
import com.cynoteck.petofyparents.response.updateProfileResponse.PetTypeResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    //TODO================LOGIN=============================

    @POST("User/Login")
    Call<LoginRegisterResponse> loginApi(@Body Loginparams loginparams);

    //TODO=============SEND OTP TO USER======================

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("common/SendOtp")
    Call<OtpResponse> senOtp(@Header("Authorization") String auth, @Body SendOtpRequest inPetRegisterRequest);

    //TODO=============Register API============================

    @POST("User/Registration")
    Call<LoginRegisterResponse> registerApi(@Body Registerparams registerparams);

    //TODO============Forget Password API=======================

    @POST("user/forgotpassword")
    Call<PasswordResponse> getPasswordResponse(@Body ForgetPassRequest forgetPassRequest);

    //TODO===========Get Pet List API===========================

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("report/GetPetList")
    Call<GetPetListResponse> getPetList(@Header("Authorization") String auth, @Body PetDataRequest getPetDataRequest);

    //TODO===========Get Pet ID Card============================


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(" pet/GetPetIdentityCard")
    Call<PetIdCardResponse> getPetIdCard(@Header("Authorization") String auth, @Body PetClinicVisitDetailsRequest idCardRequest);

    //TODO==========Get Pet Response API========================

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pet/GetPetDetail")
    Call<GetPetResponse> getPetDetails(@Header("Authorization") String auth,
                                       @Body GetPetListRequest addPetRequset);

    //TODO==========Pet Type API===============================

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("common/GetPetTypes")
    Call<PetTypeResponse> petTypeApi(@Header("Authorization") String auth);

    //TODO=========Get Pet Unique ID===========================

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("pet/GeneratePetUniqueId")
    Call<UniqueResponse> getGeneratePetUniqueId(@Header("Authorization") String auth);

    //TODO========Get Breed API================================

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pet/GetPetBreed")
    Call<BreedCatRespose> getGetPetBreedApi(@Header("Authorization") String auth, @Body BreedParams breedParams);

    //TODO=========Get Pet Age API=============================

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pet/GetPetAge")
    Call<PetAgeValueResponse> getGetPetAgeApi(@Header("Authorization") String auth, @Body BreedParams breedParams);

    //TODO=========Get Pet Color API===========================

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pet/GetPetColor")
    Call<PetColorValueResponse> getGetPetColorApi(@Header("Authorization") String auth, @Body BreedParams breedParams);

    //TODO========Get Pet Size API=============================

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pet/GetPetSize")
    Call<PetSizeValueResponse> getGetPetSizeApi(@Header("Authorization") String auth, @Body BreedParams breedParams);

    //TODO=======Upload Images API=============================

    @Multipart
    @POST("document/UploadDocument")
    Call<ImageResponse> uploadImages(@Header("Authorization") String auth,
                                     @Part MultipartBody.Part file);

    //TODO=========Update data API==============================

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pet/UpdatePetDetails")
    Call<AddPetValueResponse> updatePetDetails(@Header("Authorization") String auth,
                                               @Body UpdatePetRequest addPetRequset);

    //TODO=========Add New Pet API=============================

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pet/AddPet")
    Call<AddPetValueResponse> addNewPet(@Header("Authorization") String auth,
                                        @Body AddPetRequset addPetRequset);

    //TODO========Get Reprts Type API===========================

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("report/GetVisitTypes")
    Call<GetReportsTypeResponse> getReportsType(@Header("Authorization") String auth);

    //TODO========Get Hospitalization API========================

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("report/GetPetHospitalization")
    Call<GetPetHospitalizationResponse> getPetHospitalization(@Header("Authorization") String auth, @Body VisitTypeRequest visitTypeRequest);

    //TODO========Get Pet Lab Work API============================

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("report/GetPetLabWork")
    Call<PetLabWorkResponse> getPetLabWorkResponse(@Header("Authorization") String auth, @Body VisitTypeRequest visitTypeRequest);

    //TODO=======Get Clinic Visit List API=======================

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("report/GetPetClinicVisits")
    Call<GetPetClinicVisitListResponse> getPetClinicVisits(@Header("Authorization") String auth, @Body VisitTypeRequest visitTypeRequest);

    //TODO======Get Pet Test Xray API============================

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("report/GetPetTestAndXRay")
    Call<GetPetTestAndXRayResponse> getPetTestAndXRay(@Header("Authorization") String auth, @Body VisitTypeRequest visitTypeRequest);

    //TODO=======Get Clinic Visit Details API====================

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/GetClinicVisit")
    Call<GetClinicVisitsDetailsResponse> getClinicVisitDetails(@Header("Authorization") String auth, @Body PetClinicVisitDetailsRequest petClinicVisitDetailsRequest);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("user/UpdateVeterinarian")
    Call<PasswordResponse> getPasswordResponse(@Header("Authorization") String auth, @Body ChangePassRequest changePassRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("user/EnableTwoStepAuthentication")
    Call<TwoFactAuthResponse> enableDisableTowFactorAuth(@Header("Authorization") String auth,
                                                         @Body GetPetListRequest addPetRequset);



    //APPOINTMENTS......................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("user/GetPetParentList")
    Call<GetPetParentResponse> getPetParentList(@Header("Authorization") String auth);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("appointment/GetAppointmentById")
    Call<AppointmentDetailsResponse> getAppointmentsDetails(@Header("Authorization") String auth,
                                                            @Body GetPetListRequest addPetRequset);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("appointment/getappointment")
    Call<GetAppointmentResponse> getAppointment(@Header("Authorization") String auth);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("appointment/GetVeterinarianList")
    Call<GetVetListResponse> getVetList(@Header("Authorization") String auth, @Body GetVetListRequest getVetListRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("appointment/UpdateAppointment")
    Call<CreateAppointmentResponse> updateAppointment(@Header("Authorization") String auth, @Body UpdateAppointmentRequest updateAppointmentRequest);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("appointment/CreateAppointment")
    Call<CreateAppointmentResponse> createAppointment(@Header("Authorization") String auth, @Body CreateAppointRequest createAppointRequest);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/AddPetToRegister")
    Call<NewPetRegisterResponse> addPetToRegister(@Header("Authorization") String auth, @Body NewPetRequest newPetRequest);



    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("paymentgateway/GetOrder")
    Call<GetOrderResponse> getOrderDetails(@Header("Authorization") String auth, @Body GetOrderRequest getOrderRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("paymentgateway/PaymentHistory")
    Call<PaymentStatusResponse> getPaymentHistory(@Header("Authorization") String auth, @Body PaymentHistoryRequest paymentHistoryRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("Appointment/CancelAppointment")
    Call<AppointmentStatusResponse> cancelAppointment(@Header("Authorization") String auth, @Body AppointmentsStatusRequest appointmentsStatusRequest);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/ViewPetVaccination")
    Call<PetImmunizationRecordResponse> viewPetVaccination(@Header("Authorization") String auth, @Body ImmunizationRequest immunizationRequest);


}


