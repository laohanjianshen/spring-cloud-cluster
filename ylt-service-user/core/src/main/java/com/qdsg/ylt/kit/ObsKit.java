package com.qdsg.ylt.kit;

import com.google.gson.Gson;
import com.qdsg.ylt.obs.ObsEnv;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @ Author     ：yangrb
 * @ Date       ：Created in 11:09 2018/6/12
 * @ Description：obs工具类
 * @ Modified By：
 * @Version: $
 */
public class ObsKit {

    /**
     * 生成临时上传token
     * */
    public static String getUptoken(){
        Auth auth = Auth.create(ObsEnv.accessKey,ObsEnv.secretKey);

        StringMap putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");

        String upToken = auth.uploadToken(ObsEnv.bucket, null, ObsEnv.expireSeconds, putPolicy);
        return upToken;
    }

    /**
     * 根据文件key生成临时下载url
     * */
    public static String privateDownloadUrl(String key) throws UnsupportedEncodingException {
        Auth auth = Auth.create(ObsEnv.accessKey,ObsEnv.secretKey);

        String encodedFileName = URLEncoder.encode(key,"utf-8");
        String publicUrl = String.format("%s/%s", ObsEnv.domainOfBucket, encodedFileName);
        String finalUrl = auth.privateDownloadUrl(publicUrl,ObsEnv.expireSeconds);
        return finalUrl;
    }

    /**
     * 删除空间中的文件
     * */
    public static Map deleteFile(String key) throws UnsupportedEncodingException {
        Map result = new HashMap();
        result.put("ret",0);
        Auth auth = Auth.create(ObsEnv.accessKey,ObsEnv.secretKey);
        Configuration cfg = new Configuration(Zone.zone0());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(ObsEnv.bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
            return result;
        }
        result.put("ret",1);
        return result;
    }
    /**
     * 服务端上传图片
     * */
    public static Map putFile(String filePath){
        Auth auth = Auth.create(ObsEnv.accessKey,ObsEnv.secretKey);

        Map result = new HashMap();
        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);
        String uploadToken = getUptoken();

        result.put("ret",0);
        try {
            Response response = uploadManager.put(filePath,null,uploadToken);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(),DefaultPutRet.class);
            result.put("ret",1);
            result.put("key",putRet.key);
            result.put("hash",putRet.hash);
            result.put("bucket",ObsEnv.bucket);
        }catch (QiniuException qe){
            Response r = qe.response;
            System.err.println(r.toString());
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(putFile("C:\\Users\\Administrator\\Desktop\\test.jpg"));

        //System.out.println(privateDownloadUrl("Fq69vPn-DQ7Sdw-KYvT3F3O0mPjf"));
    }
}
