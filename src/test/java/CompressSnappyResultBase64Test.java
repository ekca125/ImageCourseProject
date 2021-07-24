import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class CompressSnappyResultBase64Test {

    @Test
    void compressAndUncompressTest() {
        try {
            String text = "abcdefg";
            String compress = CompressSnappyResultBase64.compress(text);
            String uncompress = CompressSnappyResultBase64.uncompress(compress);
            assertEquals(text, uncompress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}