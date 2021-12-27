package pipe;

import dot.ImageDot;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class DotListSamplingPipe implements Function<List<ImageDot>, List<ImageDot>> {
    int size;

    public DotListSamplingPipe(int size) {
        this.size = size;
    }

    @Override
    public List<ImageDot> apply(List<ImageDot> imageDots) {
        if (size < imageDots.size()) {
            Collections.shuffle(imageDots);
            return imageDots.subList(0, size);
        } else {
            return imageDots;
        }
    }
}
