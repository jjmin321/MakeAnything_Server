package jejeongmin.MakeAnything.common.lib.retrofit;

import jejeongmin.MakeAnything.common.vo.http.ResponseData;
import jejeongmin.MakeAnything.user.domain.dto.UserDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DodamAPI {
    @POST("auth/login")
    Call<retrofit2.Response<ResponseData>> dodamAuthLogin(@Body UserDto userDto);
}
