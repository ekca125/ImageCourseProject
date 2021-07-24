import org.xerial.snappy.Snappy;

import java.io.IOException;
import java.util.Base64;

public class CompressSnappyResultBase64 {
    static public String compress(String input) throws IOException {
        byte[] compressBytes = Snappy.compress(input);
        String compressBase64 = new String(Base64.getUrlEncoder().encode(compressBytes));
        return compressBase64;
    }

    static public String uncompress(String input) throws IOException {
        String reformatString = input.replaceAll("[^a-zA-Z0-9]", "");
        byte[] compressBase64 = Base64.getUrlDecoder().decode(reformatString);
        byte[] uncompressBytes = Snappy.uncompress(compressBase64);
        return new String(uncompressBytes);
    }
}
