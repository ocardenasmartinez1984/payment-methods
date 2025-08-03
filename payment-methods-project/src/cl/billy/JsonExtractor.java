package cl.billy;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.zip.GZIPInputStream;

public class JsonExtractor {
    public String extract(String input) throws IOException {
        var stringBuilder = new StringBuilder();
        var compressed = Base64.getDecoder().decode(input);
        var gis = new GZIPInputStream(new ByteArrayInputStream(compressed));
        var br = new BufferedReader(new InputStreamReader(gis));
        String line;
        while ((line = br.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }
}