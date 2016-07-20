package com.yx.personal.ganhuo.NetWork;

import com.yx.personal.ganhuo.Bean.AndroidInfoBean;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by YX on 16/7/14.
 */
public class RetrofitManger {

    private String BASE_NIUPAI_URL = "";

    private static ApiCallBiz mApiCallBiz;

    private static OkHttpClient mOkHttpClient;
    //短缓存有效期为1秒钟
    public static final int CACHE_STALE_SHORT = 1;
    //长缓存有效期为7天
    public static final int CACHE_STALE_LONG = 60 * 60 * 24 * 7;
//
//    // 云端响应头拦截器，用来配置缓存策略
//    private Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Request request = chain.request();
//            if (!NetUtil.isNetworkConnected()) {
//                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
//            }
//            Response originalResponse = chain.proceed(request);
//            if (NetUtil.isNetworkConnected()) {
//                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
//                String cacheControl = request.cacheControl().toString();
//                return originalResponse.newBuilder().header("Cache-Control", cacheControl).removeHeader("Pragma").build();
//            } else {
//                return originalResponse.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_LONG).removeHeader("Pragma").build();
//            }
//        }
//    };


//    private static OkHttpClient getInstance(){
//        if (mOkHttpClient==null){
//            initOkHttpClient();
//        }
//    }

    public static RetrofitManger builder(){
        return new RetrofitManger();
    };

    public RetrofitManger() {
        initOkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(new MyCustomFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiCallBiz = retrofit.create(ApiCallBiz.class);


    }

    private void initOkHttpClient() {
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null) {
            synchronized (RetrofitManger.class) {
                if (mOkHttpClient == null) {


                    mOkHttpClient = new OkHttpClient.Builder()
//                            .addInterceptor(mRewriteCacheControlInterceptor)
//                            .addNetworkInterceptor(mRewriteCacheControlInterceptor)
//                            .addInterceptor(interceptor)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }
    public Observable<AndroidInfoBean> getAndroidInfo(int num, int page){
      return mApiCallBiz.getAndroidInfo(num,page);
    }
}