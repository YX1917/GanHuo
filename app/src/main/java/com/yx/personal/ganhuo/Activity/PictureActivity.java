package com.yx.personal.ganhuo.activity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yx.personal.ganhuo.R;
import com.yx.personal.ganhuo.utils.AppManager;
import com.yx.personal.ganhuo.utils.PictUtil;

import java.io.File;

import butterknife.BindView;

public class PictureActivity extends BaseActivity {
    private String url;

    @BindView(R.id.draweeview_picture_show)
    SimpleDraweeView draweeviewPictureShow;


    @Override
    protected int getContentView() {
        return R.layout.activity_picture;
    }

    @Override
    protected void setToolbar() {
        setTitle("Balala");
        setIsShowBack(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_picture_toolbar, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        AppManager.getAppManager().addActivity(this);

    }

    private void initView() {
        draweeviewPictureShow = (SimpleDraweeView) findViewById(R.id.draweeview_picture_show);
        url = getIntent().getStringExtra("url");
        draweeviewPictureShow.setImageURI(Uri.parse(url));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_download:
                Log.e("TAG", "action_download");
                saveImg();
                break;
            case R.id.action_share:
                Log.e("TAG", "action_share");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 保存图片到本地
     */
    private void saveImg() {
        FileBinaryResource resource = (FileBinaryResource) Fresco.getImagePipelineFactory().getMainDiskStorageCache().getResource(new SimpleCacheKey(url.toString()));
        File file = resource.getFile();

        if (PictUtil.saveFile(file)) {
            Toast.makeText(PictureActivity.this, "图片已保存", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(PictureActivity.this, "图片保存失败，请重试", Toast.LENGTH_SHORT).show();
        }
    }
}