package ua.nure.fedorenko.kidstim.rest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.List;

import retrofit2.Response;
import rx.Completable;
import rx.Single;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import ua.nure.fedorenko.kidstim.activity.ParentMainActivity;
import ua.nure.fedorenko.kidstim.entity.ChildDTO;
import ua.nure.fedorenko.kidstim.entity.ParentDTO;
import ua.nure.fedorenko.kidstim.entity.TaskDTO;
import ua.nure.fedorenko.kidstim.entity.UserDTO;
import ua.nure.fedorenko.train2app.R;

public class APIServiceImpl {

    private Context context;

    private APIService apiService;

    public Context getContext() {
        return context;
    }

    public APIServiceImpl(Context context) {
        this.context = context;
        apiService = RetrofitClient.getClient(context).create(APIService.class);
    }

    private Single buildSingle(Single<?> single) {
        return single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public void login(UserDTO user) {
        buildSingle(apiService.login(user))
                .subscribe(new SingleSubscriber<Response<Void>>() {
                    @Override
                    public void onSuccess(Response<Void> response) {
                        String token = response.headers().get("Authorization");
                        String email = response.headers().get("User").split(";")[0];
                        String role = response.headers().get("User").split(";")[1];
                        SharedPreferences pref = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = pref.edit();
                        edit.putString("Authorization", token);
                        edit.putString("User", email);
                        edit.apply();
                        if (role.equals("parent")) {
                            context.startActivity(new Intent(context, ParentMainActivity.class));
                        }
                    }

                    @Override
                    public void onError(Throwable error) {
                        Toast.makeText(context, context.getString(R.string.error_authorization), Toast.LENGTH_LONG).show();
                    }
                });
    }


    public void register(ParentDTO parent) {
        apiService.register(parent)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<Response<ParentDTO>>() {
                    @Override
                    public void onSuccess(Response<ParentDTO> value) {
                        Toast.makeText(context, "Thank you for registration!", Toast.LENGTH_LONG).show();
                        login(parent);
                    }

                    @Override
                    public void onError(Throwable error) {
                        Toast.makeText(context, context.getString(R.string.error_registration), Toast.LENGTH_LONG).show();
                    }
                });
    }

    public Single<List<TaskDTO>> getTasksByParent(String id) {
        return apiService.getTasksByParent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ParentDTO> getParentByEmail(String email) {
        return apiService.getParentByEmail(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable updateParent(ParentDTO parent) {
        return apiService.updateParent(parent).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}



