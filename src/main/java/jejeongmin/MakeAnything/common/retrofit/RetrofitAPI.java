package jejeongmin.MakeAnything.common.retrofit;

import jejeongmin.MakeAnything.common.vo.http.ResponseData;
import jejeongmin.MakeAnything.user.domain.dto.UserDto;
import jejeongmin.MakeAnything.common.vo.dodam.DodamTokenVo;
import jejeongmin.MakeAnything.common.vo.dodam.DodamUserDataVo;
import jejeongmin.MakeAnything.common.vo.dodam.DodamUserVo;
import retrofit2.Call;
import retrofit2.http.*;

public interface RetrofitAPI {

    @POST("auth/login")
    Call<ResponseData<DodamTokenVo>> dodamAuthLogin(@Body UserDto userDto, @Query("key") String dodamApiKey);

    @GET("members/my")
    Call<ResponseData<DodamUserVo<DodamUserDataVo>>> dodamGetUserInfo(@Header("x-access-token") String token);

}
