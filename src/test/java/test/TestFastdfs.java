package test;

import com.baizhi.ysk.App;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class TestFastdfs {
    @Autowired
    FastFileStorageClient fastFileStorageClient;

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
        fastFileStorageClient.deleteFile("group1", "M00/00/00/wKiph1wj3LuAeqTBAAEpQEVDqhc182.jpg");
    }


}
