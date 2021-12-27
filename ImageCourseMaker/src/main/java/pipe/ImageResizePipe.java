package pipe;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.function.Function;

public class ImageResizePipe implements Function<BufferedImage, BufferedImage> {
    int targetWidth;
    int targetHeight;

    public ImageResizePipe(int targetWidth, int targetHeight) {
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
    }

    @Override
    public BufferedImage apply(BufferedImage bufferedImage) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            Thumbnails.of(bufferedImage)
                    .size(targetWidth, targetHeight)
                    .outputFormat("JPEG")
                    .outputQuality(1)
                    .toOutputStream(outputStream);
            byte[] data = outputStream.toByteArray();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
            return ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return bufferedImage;
        }
    }
}
