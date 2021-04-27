package jejeongmin.MakeAnything.common.retrofit.service;

import jejeongmin.MakeAnything.common.vo.http.ResponseData;
import jejeongmin.MakeAnything.user.domain.dto.UserDto;
import jejeongmin.MakeAnything.common.retrofit.RetrofitAPI;
import jejeongmin.MakeAnything.common.retrofit.RetrofitConfig;
import jejeongmin.MakeAnything.common.vo.dodam.DodamTokenVo;
import jejeongmin.MakeAnything.common.vo.dodam.DodamUserDataVo;
import jejeongmin.MakeAnything.common.vo.dodam.DodamUserVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

@Component
public class DodamService {

    @Value("${dodam.api-key}")
    private String dodamApiKey;

    private final RetrofitAPI api = RetrofitConfig.getRetrofit().create(RetrofitAPI.class);

    public String authLogin(UserDto userDto) throws Exception {

        Call<ResponseData<DodamTokenVo>> call = api.dodamAuthLogin(userDto, dodamApiKey);
        try {
            Response<ResponseData<DodamTokenVo>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                return response.body().getData().getToken();
            } else {
                throw new NullPointerException("도담도담에 일치하는 정보가 없습니다");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public DodamUserDataVo getUserInfo(String token) throws Exception {
        Call<ResponseData<DodamUserVo<DodamUserDataVo>>> call = api.dodamGetUserInfo(token);
        try {
            Response<ResponseData<DodamUserVo<DodamUserDataVo>>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                return response.body().getData().getMemberData();
            } else {
                throw new NullPointerException("도담도담 서버에서 값을 받아오지 못했습니다");
            }
        } catch (Exception e) {
            throw e;
        }
    }

}
