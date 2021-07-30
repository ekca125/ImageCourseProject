package pipe;

import data.RangeMap;
import dot.AddressDot;
import dot.ImageDot;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;

public class LineDotQueueToQuanzationPipe implements Function<Queue<ImageDot>, Queue<AddressDot>> {
    List<AddressDot> addressDotList;

    public LineDotQueueToQuanzationPipe(List<AddressDot> addressDotList) {
        this.addressDotList = addressDotList;
    }

    private AddressDot convertImageDotToAddressDot(ImageDot imageDot) {
        return addressDotList
                .stream()
                .reduce(addressDotList.get(0), (a, b) -> a.getCost(imageDot) < b.getCost(imageDot) ? a : b);
    }

    @Override
    public Queue<AddressDot> apply(Queue<ImageDot> imageDots) {
        Queue<AddressDot> addressDotQueue = new LinkedBlockingQueue<>();
        while (!imageDots.isEmpty()) {
            ImageDot imageDot = imageDots.poll();
            addressDotQueue.add(convertImageDotToAddressDot(imageDot));
        }
        return addressDotQueue;
    }
}
