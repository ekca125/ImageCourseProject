package pipe;

import dot.ImageDot;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;

public class DotListToLineDotQueuePipe implements Function<List<ImageDot>, Queue<ImageDot>> {
    private ImageDot getMininumCostImageDot(List<ImageDot> imageDotList, ImageDot imageDot) {
        return imageDotList
                .stream()
                .reduce(imageDotList.get(0), (a, b) -> {
                    if (a.getCost(imageDot) > b.getCost(imageDot)) {
                        return b;
                    } else {
                        return a;
                    }
                });
    }

    @Override
    public Queue<ImageDot> apply(List<ImageDot> imageDotList) {
        if (imageDotList.isEmpty()) {
            return null;
        }
        //ready
        Queue<ImageDot> imageDotQueue = new LinkedBlockingQueue<>();
        //start 1
        ImageDot currentImageDot = imageDotList.get(0);
        //
        imageDotQueue.add(currentImageDot);
        imageDotList.remove(currentImageDot);
        //during
        while (!imageDotList.isEmpty()) {
            ImageDot prviousImageDot = currentImageDot;
            currentImageDot = this.getMininumCostImageDot(imageDotList, prviousImageDot);
            //
            imageDotQueue.add(currentImageDot);
            imageDotList.remove(currentImageDot);
        }
        return imageDotQueue;
    }
}
