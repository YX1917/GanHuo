package com.yx.personal.ganhuo.Utils;

import android.os.Environment;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Ted on 2015/8/30.
 */
public class PictUtil {

    public static boolean saveFile(File file) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(file);
            fos = new FileOutputStream(getNewFile(file));
            byte[] b = new byte[1024];
            int len;
            while ((len = fis.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    private static File getNewFile(File file) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "GanHuo");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = file.getName().replace(".cnt", ".png");
        File newFile = new File(appDir, fileName);
        if (newFile.exists()) {
            newFile.delete();
        }
        return newFile;
    }

}
