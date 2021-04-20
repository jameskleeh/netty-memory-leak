package temp;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.CompletedFileUpload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Controller
public class Temp {

    @Post("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public HttpResponse<String> upload(String json, CompletedFileUpload file0) {

        System.out.println("Processing multipart request: ");
        System.out.printf("Part json [%s]%n", json);
        System.out.printf("Part file0 [size: %s, type: %s]%n", file0.getSize(), file0.getContentType());

        InputStream stream = null;
        try {
            stream = file0.getInputStream();
            File targetFile = File.createTempFile("test.", ".tmp");
            Files.copy(stream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println(targetFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Request completed");
        return HttpResponse.ok();
    }
}
