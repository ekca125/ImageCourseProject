import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CompressSnappyResultBase64Test {

    @Test
    void compress() {
    }

    @Test
    void uncompress() {
        try {
            String compress =
                    "xRWweyJtYXBWb0xpc3QiOlt7ImxhdGl0dWRlIjozNy41NDkwNjY1MTUsImxvbmdpCRk8MTI2Ljg2NjAyMzM2ODN9LEo1ABg4ODA3NjIsTjYAFDQzODYyOVI2ABQxMjE0NDZONgAYMTEzNjAwOFI2ABQzMzcwNThSNgAQMzQwOTVSogAYMTE4MDA1M1Y2ABA3ODI5NU5sABQxMDAzNzRS2AAYMTYzNTc4NEo2ABw4OTU3NzczN05sABgyMDM4Mzk2SjYAGDkxODYzMTdSegEQMjExNjFabAAMNzkzM1YOARQyMjQzMzJW2AAMNjg2OFawARgyMzczNjcxTqIAFDI1NDYyOVaiABQ4NTQwNzJKNgAYODkwMzQwM1LmARQyOTQwNzZSDgEYMzE2NDU5OU5EARQzMDU2MTJWogAQMzY5OThS5gEUMzExMTU2TjYAGDg5MjQ5NjRSUgIUMzE0NTcxTjYAGDkzMjU4MzJSHAIMMzM3NFYqAxg4ODQ5MDI5VjYAEDg0MzA3TrABBDg4QfJSDQEYNDA0ODA0M055ARg3OTQ2NTM0TkMBFDQ3NDU0MFZrABAxNjIxNFLlARQ0OTUwMzVWDQEMNTQ2M1Y2ABg1MjQ3MTE3TqIAFDg3MjAyN1KiABQ1MzIyOTNWNgAMNTUyMlbLAxQ1NTg5MDJOUQIYODc0MDI4MlKHAhQ1NjQ3NzJWRAFheFbLAxQ1ODUyNjZO2QQAOGGTBDMzUlECFDU5MzU3NVLZBBQyOTk5MjBS5gEQNjE0NjFSKQMUODkxNjcyUmwEFDYyMDE4MFavAQwyMTQwVqEAFDYzNTI0MFJDARQ2ODU5MTFSXgMQNjU1MDFSUQIYOTAwODA1OFJRAhQ2NzAyODFODQEUODcwNzQ3Vr0CFDY3NTUwM1Z6BRA4MzM2OVLlARQ2ODQxMzJOogQYOTI0NTIwNlJ5ARQ3MDQ1MjdOogAYOTI2NjY5MFJ6ARQ3MjUxNTBObAAQODk0NDhebAAQNDI3MDRSNgAUNjMxNTQxTsoDFDc0NTI0Nk6hABQ4NjUzMTBSagQUNzY1NzQxVkIBEDYzNzE2Uq4BFDc2Njk3OU5rABQ5MTkwNDlWJwMUNzk0NDM3Tl0DFDkyMTE5N1ZsABQ4MTUwNjFONgAYODg4MDY1MVINARA4MjE1NFIACBA4NTc3MVKsARQ4MzU0NzhSdgEUODk5MDYyVmoADDQ2NDFWNgAQNTk4NzNaoAAQNTU5NzJWQgEOTghO4QEQODg0MzRaPwUQMTM3NjlSrAEUOTAyNTk4Tp0EGDkxNTcyNjJSbAAhxgQ2NVZ1BQwzNzE5VvwDFDkxOTU2OVKsAQw1MjI3VsUHEDkyNTcwUnUJDDg1NDRWLwgkOTQ2MjA0MX1dfQ==";
            String uncompress = CompressSnappyResultBase64.uncompress(compress);
            System.out.println(compress);
            System.out.println(uncompress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void fulltest(){

        try {
            String a= "abcd";
            String compress = CompressSnappyResultBase64.compress(a);
            String uncompress = CompressSnappyResultBase64.uncompress(compress);
            System.out.println(a);
            System.out.println(compress);
            System.out.println(uncompress);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}