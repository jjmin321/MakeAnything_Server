package jejeongmin.MakeAnything.user.retrofit;

import jejeongmin.MakeAnything.common.vo.http.ResponseData;
import jejeongmin.MakeAnything.user.domain.dto.UserDto;
import jejeongmin.MakeAnything.user.retrofit.vo.DodamTokenVo;
import jejeongmin.MakeAnything.user.retrofit.vo.DodamUserDataVo;
import jejeongmin.MakeAnything.user.retrofit.vo.DodamUserVo;
import retrofit2.Call;
import retrofit2.http.*;

public interface RetrofitAPI {

    @POST("auth/login")
    Call<ResponseData<DodamTokenVo>> dodamAuthLogin(@Body UserDto userDto, @Query("key") String dodamApiKey);

    @GET("members/my")
    Call<ResponseData<DodamUserVo<DodamUserDataVo>>> dodamGetUserInfo(@Header("x-access-token") String token);

}
