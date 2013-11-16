package onionnpr;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.*;

/**
 *
 * @author andy
 */
public class ScrapedPageOnion {

    private ArrayList<String> titles = new ArrayList<String>();
    Random r = new Random();

    public ScrapedPageOnion(URL url) throws Exception {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            if (Pattern.compile(".*title=\".*\"").matcher(inputLine).matches()) {
                String[] titlesarray = inputLine.split("title=");
                for (int i = 0; i < titlesarray.length; i++) {
                    titles.add(titlesarray[i].split(">")[0]);
                }
            }
        }
        this.filterArray();
        in.close();
    }

    private void filterArray() {
        ArrayList<Integer> filternumbers = new ArrayList<Integer>();
        for (int i = 0; i < titles.size(); i++) {
            String toFilter = titles.get(i);

            if (Pattern.compile("\".*\"").matcher(toFilter).matches() == false) {
                filternumbers.add(i);
            } else if (toFilter.charAt(0) != '"') {
                filternumbers.add(i);
            }
        }
        for (int i = 0; i < filternumbers.size(); i++) {
            titles.set(filternumbers.get(i), null);
        }
        while (titles.contains(null)) {
            titles.remove(null);
        }
        titles.remove(0);
        for (int i = 0; i < titles.size(); i++) {
            titles.set(i, titles.get(i).split("\"")[1]);
        }
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
