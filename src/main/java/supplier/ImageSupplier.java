package supplier;

import java.awt.image.BufferedImage;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Supplier;

public class ImageSupplier implements Supplier<Future<BufferedImage>> {
    BufferedImage bufferedImage;

    public ImageSupplier(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    @Override
    public Future<BufferedImage> get() {
        CompletableFuture<BufferedImage> future = new CompletableFuture<>();
        new Thread(() -> {
            future.complete(this.bufferedImage);
        }).start();
        return future;
    }
}
