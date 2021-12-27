package com.trainer.courserunner.Application.mapdb.newimpl.data;

import com.jiechic.library.android.snappy.Snappy;

import java.io.IOException;
import java.util.Base64;

public class CompressSnappyResultBase64 {
    static public String compress(String input) throws IOException {
        byte[] compressBytes = Snappy.compress(input);
        String compressBase64 = new String(Base64.getUrlEncoder().encode(compressBytes));
        return compressBase64;
    }

    static public String uncompress(String input) throws IOException {
        byte[] compressBase64 = Base64.getUrlDecoder().decode(input);
        byte[] uncompressBytes = Snappy.uncompress(compressBase64);
        return new String(uncompressBytes);
    }
}
