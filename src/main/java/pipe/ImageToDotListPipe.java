package pipe;

import dot.ImageDot;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ImageToDotListPipe implements Function<BufferedImage, List<ImageDot>> {
    int redLimit;
    int blueLimit;
    int greenLimit;

    public ImageToDotListPipe(int redLimit, int blueLimit, int greenLimit) {
        this.redLimit = redLimit;
        this.blueLimit = blueLimit;
        this.greenLimit = greenLimit;
    }

    private boolean filter(Color color) {
        return color.getRed() <= redLimit
                && color.getBlue() <= blueLimit
                && color.getGreen() <= greenLimit;
    }

    @Override
    public List<ImageDot> apply(BufferedImage bufferedImage) {
        List<ImageDot> imageDotList = new ArrayList<>();
        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                Color color = new Color(bufferedImage.getRGB(x, y), false);
                if (filter(color)) {
                    imageDotList.add(new ImageDot(x, y, bufferedImage.getWidth(), bufferedImage.getHeight(), color));
                }
            }
        }
        return imageDotList;
    }
}
