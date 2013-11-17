package onionnpr;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.*;

/**
 *
 * @author Amdrwe
 */
public class ScrapedFeedNPR {

    private ArrayList<String> titles = new ArrayList<String>();
    Random r = new Random();

    public ScrapedFeedNPR(URL url) throws Exception {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            if (Pattern.compile(".*<title>.*").matcher(inputLine).matches()) {
                titles.add(inputLine);
            }
        }
        this.trimStrings();
        in.close();
    }

    private void trimStrings() {
        for (int i = 0; i < titles.size(); i++) {
            String toTrim = titles.get(i);
            titles.set(i, toTrim.substring(6, (toTrim.length() - 1 - 7)));
        }
        titles.remove(0);
        for (int i = 0; i < titles.size(); i++) {
            titles.set(i, titles.get(i).split("<title>")[1]);
        }
        titles.remove(0);
    }

    public void printAllTitles() {
        for (int i = 0; i < titles.size(); i++) {
            System.out.println("\"" + titles.get(i) + "\"\n");
        }
    }

    public void printRandomTitle() {
        System.out.println(titles.get(r.nextInt(titles.size())));
    }
}
