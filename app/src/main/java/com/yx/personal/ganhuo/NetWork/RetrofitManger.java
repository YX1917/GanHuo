package com.yx.personal.ganhuo.netWork;

import android.os.Environment;
import android.util.Log;

import com.yx.personal.ganhuo.bean.DataInfoBean;
import com.yx.personal.ganhuo.utils.NetUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;
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

    private static RetrofitManger mRetrofitManger;
    //短缓存有效期为1秒钟
    public static final int CACHE_STALE_SHORT = 1;
    //长缓存有效期为7天
    public static final int CACHE_STALE_LONG = 120;

    public  static RetrofitManger getInstance(){
        if(mRetrofitManger==null){
            mRetrofitManger = new RetrofitManger();
        }
        return mRetrofitManger;
    }

    public RetrofitManger() {
        initOkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//Rxjava
                .addConverterFactory(GsonConverterFactory.create())//gson解析
                .build();
        mApiCallBiz = retrofit.create(ApiCallBiz.class);


    }

    private void initOkHttpClient() {
        LoggingInterceptor loggingInterceptor = new LoggingInterceptor();
        File cacheFile = new File(Environment.getExternalStorageDirectory(), "huancun");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        if (mOkHttpClient == null) {
            synchronized (RetrofitManger.class) {
                if (mOkHttpClient == null) {
                    mOkHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(mRewriteCacheControlInterceptor)
                            .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(loggingInterceptor)//日志拦截器
                            .retryOnConnectionFailure(true)//失败重连
                            .connectTimeout(15, TimeUnit.SECONDS)//设置连接超时时间
                            .cache(cache)
                            .build();
                }
            }
        }
    }
    public Observable<DataInfoBean> getAndroidInfo(int num, int page){
        return mApiCallBiz.getAndroidInfo(num,page);

    }

    public Observable<DataInfoBean> getIosInfo(int num, int page){
        return mApiCallBiz.getIosInfo(num,page);
    }

    public Observable<DataInfoBean> getPictureInfo(int num, int page){
        return mApiCallBiz.getPictureInfo(num,page);
    }

    // 云端响应头拦截器，用来配置缓存策略
    private static final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtil.isNetworkConnected()) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetUtil.isNetworkConnected()) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder().header("Cache-Control", cacheControl).removeHeader("Pragma").build();
            } else {
                return originalResponse.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_LONG).removeHeader("Pragma").build();
            }
        }
    };

    private static class LoggingInterceptor implements Interceptor {
        private static final Charset UTF8 = Charset.forName("UTF-8");

        @Override
        public Response intercept(Chain chain) throws IOException {

            long t1 = System.nanoTime();
            Request request = chain.request();

            Log.e("TAG",String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();

            Log.e("TAG",String.format("Received response for %s in %.1fms%n%s",
                    request.url(), (t2 - t1) / 1e6d, response.headers(),response.body().toString()));

            BufferedSource source = response.body().source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            String logStr = "\n--------------------"
                    .concat("  begin--------------------\n")
                    .concat("\nnetwork code->").concat(response.code() + "")
                    .concat("\nurl->").concat(request.url() + "")
                    .concat("\nrequest headers->").concat(request.headers() + "")
                    .concat("\nbody->").concat(buffer.clone().readString(UTF8));
            Log.i("TAG", logStr);


            return response;
        }
    }


}