package test;

import com.baizhi.ysk.App;
import com.baizhi.ysk.entity.Banner;
import com.baizhi.ysk.mapper.BannerMapper;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import io.goeasy.GoEasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class TestFastdfs {
    @Autowired
    FastFileStorageClient fastFileStorageClient;
    @Autowired
    BannerMapper bannerMapper;
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void testUpload() throws FileNotFoundException {
        File file = new File("D:/reba3.jpg");
        StorePath storePath = fastFileStorageClient.uploadFile(new FileInputStream(file), file.length(), "jpg", null);
        System.out.println(storePath);
    }


    @Test
    public void testDownload() throws IOException {
        byte[] bytes = fastFileStorageClient.downloadFile("group1", "M00/00/00/wKiph1wj3LuAeqTBAAEpQEVDqhc182.jpg", new DownloadByteArray());
        //byte[] bytes = fastFileStorageClient.downloadFile("group1", "M00/00/00/wKiph1wj7uyAd2KLAADp0r09m2E930.jpg", new DownloadByteArray());
        FileOutputStream fileOutputStream = new FileOutputStream("D:/华仔下载.jpg");
        fileOutputStream.write(bytes);
    }

    @Test
    public void testDelete() {
        fastFileStorageClient.deleteFile("group1", "M00/00/00/wKiph1wj9iqAavSdAADp0r09m2E671.jpg");
    }


    @Test
    public void test() {
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-a65c5d1a24504b399fb5c85e23aa7b9e");
        goEasy.publish("cmfz", "hahahah");
    }


    @Test
    public void test1() {
        Banner t = new Banner();
        t.setStatus("Y");
        List<Banner> select = bannerMapper.select(t);
        System.out.println(select);
    }

    @Test
    public void testRedis() {
        ValueOperations ops = redisTemplate.opsForValue();
        //redisTemplate.delete("identifyCode");
        ops.set("identifyCode", "112233", 30, TimeUnit.SECONDS);
        //Object obj = ops.get("identifyCode");
        //System.out.println(obj);
    }

}
