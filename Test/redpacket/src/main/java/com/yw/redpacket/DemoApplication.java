package com.yw.redpacket;

import android.app.Application;
import android.util.Log;

import com.yunzhanghu.redpacketsdk.RPInitRedPacketCallback;
import com.yunzhanghu.redpacketsdk.RPValueCallback;
import com.yunzhanghu.redpacketsdk.RedPacket;
import com.yunzhanghu.redpacketsdk.bean.RedPacketInfo;
import com.yunzhanghu.redpacketsdk.bean.TokenData;
import com.yunzhanghu.redpacketsdk.constant.RPConstant;
import com.yw.redpacket.api.SignService;
import com.yw.redpacket.model.SignModel;
import com.yw.redpacket.utils.PreferenceUtil;
import com.yw.redpacket.utils.RedPacketUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Max on 2016/11/13.
 */

public class DemoApplication extends Application {

    private static final String TAG = "DemoApplication";
    /**
     * 当前登录用户id
     */
    public static String sCurrentUserId;
    /**
     * 当前登录用户昵称
     */
    public static String sCurrentNickname;
    /**
     * 当前用户头像url
     */
    public static String sCurrentAvatarUrl;
    /**
     * 接收者昵称
     */
    public static String sToNickname;
    /**
     * 接收者Id
     */
    public static String sToUserId;
    /**
     * 接收者头像url
     */
    public static String sToAvatarUrl;


    @Override
    public void onCreate() {
        super.onCreate();
        //初始化SharePreference,Demo中用于存储用户名
        PreferenceUtil.init(this);
        initRedPacket();
    }


    private void initRedPacket() {
        //初始化红包SDK
        RedPacket.getInstance().initRedPacket(this, RPConstant.AUTH_METHOD_SIGN, new RPInitRedPacketCallback() {
            //设置初始化TokenData的回调函数
            //说明 ：该回调函数在红包token不存在、切换用户、红包token过期、签名过期的情况下触发。
            //注意 ：以上情况不需要开发者维护，由红包SDK在请求红包相关服务时进行处理。
            @Override
            public void initTokenData(final RPValueCallback<TokenData> callback) {
                //异步向App Server获取签名参数
                //这里使用随机生成的UUID代替App中的userId,生产环境需要传入App的userId
                String token = "tempValue";
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(JacksonConverterFactory.create())
                        //Demo用URL,生产环境需要替换成APP Server提供的签名URL
                        .baseUrl("https://rpv2.yunzhanghu.com/")
                        .build();
                SignService signService = retrofit.create(SignService.class);
                Call<SignModel> call = signService.getSignInfo(sCurrentUserId, token);
                call.enqueue(new Callback<SignModel>() {
                    @Override
                    public void onResponse(Call<SignModel> call, retrofit2.Response<SignModel> response) {
                        if (response.isSuccessful()) {
                            SignModel signModel = response.body();
                            Log.d(TAG, signModel.toString());
                            //赋值返回参数给TokenData
                            TokenData tokenData = new TokenData();
                            tokenData.authPartner = signModel.partner;
                            tokenData.authSign = signModel.sign;
                            tokenData.appUserId = signModel.user_id;
                            tokenData.timestamp = signModel.timestamp;
                            //回传签名参数给红包SDK
                            callback.onSuccess(tokenData);
                        } else {
                            String statusCode = response.code() + "";
                            callback.onError(statusCode, response.errorBody().toString());
                            Log.d(TAG, "StatusCode : " + statusCode + " Message : " + response.errorBody().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<SignModel> call, Throwable t) {
                        Log.d(TAG, "onFailure :" + t.getMessage());
                        callback.onError("onFailure", t.getMessage());
                    }
                });
            }

            @Override
            public RedPacketInfo initCurrentUserSync() {
                return RedPacketUtil.getCurrentUserInfo();
            }
        });
        //开启红包相关日志输出
        RedPacket.getInstance().setDebugMode(true);
    }
}
