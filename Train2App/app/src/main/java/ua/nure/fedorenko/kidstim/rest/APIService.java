package ua.nure.fedorenko.kidstim.rest;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Completable;
import rx.Observable;
import rx.Single;
import ua.nure.fedorenko.kidstim.entity.UserDTO;
import ua.nure.fedorenko.kidstim.entity.ChildDTO;
import ua.nure.fedorenko.kidstim.entity.ParentDTO;
import ua.nure.fedorenko.kidstim.entity.RewardDTO;
import ua.nure.fedorenko.kidstim.entity.TaskDTO;

public interface APIService {

    @POST("login")
    Single<Response<Void>> login(@Body UserDTO user);

    @POST("register")
    Single<Response<ParentDTO>> register(@Body ParentDTO parent);

    @PUT("updateParent")
    Completable updateParent(@Body ParentDTO parent);

    @PUT("updateChild")
    Observable<ChildDTO> updateChild(@Body ChildDTO child);

    @POST("addChild")
    Single<Response<ChildDTO>> addChild(@Body ChildDTO child);

    @DELETE("deleteChild")
    Observable<ChildDTO> deleteChild(@Body ChildDTO child);

    @GET("parent")
    Single<ParentDTO> getParentById(@Query("id") String id);

    @GET("child")
    Single<ChildDTO> getChildById(@Query("id") String id);

    @GET("parentByEmail")
    Single<ParentDTO> getParentByEmail(@Query("email") String id);

    @GET("childByEmail")
    Single<ChildDTO> getChildByEmail(@Query("email") String id);

    @GET("childrenByParent")
    Single<List<ChildDTO>> getChildrenByParent(@Query("email") String id);

    @GET("tasksByParent")
    Single<List<TaskDTO>> getTasksByParent(@Query("id") String id);

    @GET("rewardsByParent")
    Single<List<RewardDTO>> getRewardsByParent(@Query("id") String id);

    @PUT("minusPoints")
    Observable<ChildDTO> minusPoints(@Body ChildDTO child, @Query("points") int points);

    @PUT("plusPoints")
    Observable<ChildDTO> plusPoints(@Body ChildDTO child, @Query("points") int points);

    @PUT("updateReward")
    Completable updateReward(@Body RewardDTO reward);

    @Multipart
    @POST("uploadImage")
    Completable uploadImage(@Part MultipartBody.Part image, @Query("name")String name);

    @GET("getImage")
    Single<Byte[]> getImage(@Query("name") String name);

    @POST("addReward")
    Completable addReward(@Body RewardDTO reward);

    @DELETE("deleteReward")
    Completable deleteReward(@Query("id") String id);

    @GET("reward")
    Single<RewardDTO> getRewardById(@Query("id") String id);


    @PUT("updateTask")
    Completable updateTask(@Body TaskDTO task);

    @POST("addTask")
    Completable addTask(@Body TaskDTO task);

    @DELETE("deleteTask")
    Completable deleteTask(@Query("id") String id);

    @GET("task")
    Single<TaskDTO> getTaskById(@Query("id") String id);

}
