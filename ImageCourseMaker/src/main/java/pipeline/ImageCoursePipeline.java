package pipeline;

import data.RangeMap;
import data.RangeMapInfo;
import dot.AddressDot;
import dot.AddressDotBuilder;
import pipe.*;
import supplier.ImageSupplier;
import supplier.RangeMapAddressSupplier;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImageCoursePipeline {
    //data
    BufferedImage bufferedImage;

    //pipes
    ImageResizePipe imageResizePipe;
    ImageToDotListPipe imageToDotListPipe;
    DotListSamplingPipe dotListSamplingPipe;
    DotListToLineDotQueuePipe dotListToLineDotQueuePipe;
    LineDotQueueToQuanzationPipe lineDotQueueToQuanzationPipe;

    public List<AddressDot> startPipe() {
        Queue<AddressDot> addressDotQueue = Stream.of(bufferedImage)
                .map(imageResizePipe)
                .map(imageToDotListPipe)
                .map(dotListSamplingPipe)
                .map(dotListToLineDotQueuePipe)
                .map(lineDotQueueToQuanzationPipe)
                .collect(Collectors.toList())
                .get(0);
        return addressDotQueue
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }
    public static class PipelineBuilder {
        //default values
        private static final int IMAGE_WIDTH = 1000;
        private static final int IMAGE_HEIGHT = 1000;
        private static final int BLACK_LIMIT = 10;
        private static final int BLUE_LIMIT = 10;
        private static final int GREEN_LIMIT = 10;
        private static final int DOT_SAMPLING = 100000;
        //data
        RangeMapInfo rangeMapInfo;
        //supplier
        ImageSupplier imageSupplier;
        Future<BufferedImage> bufferedImageFuture;
        RangeMapAddressSupplier rangeMapAddressSupplier;
        Future<RangeMap> rangeMapFuture;

        public PipelineBuilder() {
            imageSupplier=null;
            rangeMapAddressSupplier=null;
        }

        public void setImageSupplier(ImageSupplier imageSupplier) {
            this.imageSupplier = imageSupplier;
            this.bufferedImageFuture=imageSupplier.get();
        }

        public void setRangeMapAddressSupplier(RangeMapAddressSupplier rangeMapAddressSupplier) {
            this.rangeMapAddressSupplier = rangeMapAddressSupplier;
            this.rangeMapFuture=rangeMapAddressSupplier.get();
        }

        public void setRangeMapInfo(RangeMapInfo rangeMapInfo) {
            this.rangeMapInfo = rangeMapInfo;
        }

        private List<AddressDot> convertRangeMapToAddressList(RangeMap rangeMap){
            List<db.AddressVO> addressVOList = rangeMap.getMapVoList();
            AddressDotBuilder addressDotBuilder = new AddressDotBuilder(rangeMapInfo);
            return addressVOList.stream()
                    .map(addressVO -> addressDotBuilder.build(addressVO.getLatitude(),addressVO.getLongitude()))
                    .collect(Collectors.toList());
        }

        public ImageCoursePipeline build(){
            ImageCoursePipeline imageCoursePipeline = new ImageCoursePipeline();
            try {
                imageCoursePipeline.bufferedImage=bufferedImageFuture.get();
                imageCoursePipeline.imageResizePipe = new ImageResizePipe(IMAGE_WIDTH,IMAGE_HEIGHT);
                imageCoursePipeline.imageToDotListPipe = new ImageToDotListPipe(BLACK_LIMIT,BLUE_LIMIT,GREEN_LIMIT);
                imageCoursePipeline.dotListSamplingPipe = new DotListSamplingPipe(DOT_SAMPLING);
                imageCoursePipeline.dotListToLineDotQueuePipe = new DotListToLineDotQueuePipe();
                imageCoursePipeline.lineDotQueueToQuanzationPipe = new LineDotQueueToQuanzationPipe(convertRangeMapToAddressList(rangeMapFuture.get()));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return imageCoursePipeline;
        }
    }
}
