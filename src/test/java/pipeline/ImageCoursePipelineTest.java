package pipeline;

import data.RangeMap;
import data.RangeMapInfo;
import dot.AddressDot;
import org.junit.jupiter.api.Test;
import supplier.ImageSupplier;
import supplier.RangeMapAddressSupplier;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ImageCoursePipelineTest {

    @Test
    void startPipe() {
        Path path = Paths.get("C:\\userdata\\1.bmp");
        BufferedImage bufferedImage = null;
        try {
            byte[] fileArray = Files.readAllBytes(path);
            InputStream imageByteInputStream = new ByteArrayInputStream(fileArray);

            try {
                bufferedImage = ImageIO.read(imageByteInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        RangeMapInfo.Builder builder = new RangeMapInfo.Builder();
        builder.setStartLatitude(37.240826);
        builder.setEndLatitude(37.249826);
        builder.setStartLongitude(126.980255);
        builder.setEndLongitude(126.981255);
        RangeMapInfo rangeMapInfo = builder.build();

        ImageCoursePipeline.PipelineBuilder pipelineBuilder = new ImageCoursePipeline.PipelineBuilder();
        pipelineBuilder.setRangeMapInfo(rangeMapInfo);
        pipelineBuilder.setImageSupplier(new ImageSupplier(bufferedImage));
        pipelineBuilder.setRangeMapAddressSupplier(new RangeMapAddressSupplier(rangeMapInfo));
        List<AddressDot> addressDotList = pipelineBuilder.build().startPipe();
        addressDotList.forEach(System.out::println);
    }

    @Test
    void startPipe2(){
        Path path = Paths.get("C:\\userdata\\2.bmp");
        BufferedImage bufferedImage = null;
        try {
            byte[] fileArray = Files.readAllBytes(path);
            InputStream imageByteInputStream = new ByteArrayInputStream(fileArray);

            try {
                bufferedImage = ImageIO.read(imageByteInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        RangeMapInfo.Builder builder = new RangeMapInfo.Builder();
        builder.setStartLatitude(37.240826);
        builder.setEndLatitude(37.249826);
        builder.setStartLongitude(126.980255);
        builder.setEndLongitude(126.981255);
        RangeMapInfo rangeMapInfo = builder.build();

        ImageCoursePipeline.PipelineBuilder pipelineBuilder = new ImageCoursePipeline.PipelineBuilder();
        pipelineBuilder.setRangeMapInfo(rangeMapInfo);
        pipelineBuilder.setImageSupplier(new ImageSupplier(bufferedImage));
        pipelineBuilder.setRangeMapAddressSupplier(new RangeMapAddressSupplier(rangeMapInfo));
        List<AddressDot> addressDotList = pipelineBuilder.build().startPipe();
        addressDotList.stream().reduce(addressDotList.get(0)
                ,(a,b)->{
                    if(a.getLongitude()>b.getLongitude()){
                        assertTrue(true);
                    }
                    else{
                        return b;
                    }
                    return a;
                });
    }
}