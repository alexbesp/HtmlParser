import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public int numberOfResults = 0;
    public ArrayList<Exception> exceptions = new ArrayList<Exception>();

    public static void printStringList(List<String> list) {
        for (String line : list) {
            System.out.println(line);
        }
    }

    public ArrayList<ArrayList> parse(ArrayList<ArrayList> sitesWithPagesList, String mask) throws IOException {
        LinkedHashMap<String, ArrayList<String>> result = new LinkedHashMap<String, ArrayList<String>>();
        Document doc1 = null;
        ArrayList<String> matches = null;

        for (ArrayList<String> site : sitesWithPagesList) {
            for (String page : site) {
                try {
                    doc1 = Jsoup.connect(page).timeout(5000).get();
//                } catch (HttpStatusException | SocketTimeoutException | UnsupportedMimeTypeException | UnknownHostException e){
                } catch (Exception e){
                    exceptions.add(e);
                    continue;
                }
                System.out.println("SITE: " + page );                           //log
                String bodyText1 = doc1.select("body").text();
//                System.out.print("\n" + bodyText1 + "\n");
                matches = new ArrayList<String>(findMatches(bodyText1, mask));
                if (matches != null){
                    result.add(matches);
                }
            }
        }
        return result;
    }

/*    public void removeEquals(ArrayList<ArrayList> lists) {
        for (ArrayList<String> list : lists) {
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                String value = iterator.next();
            }
        }
    }*/

    public LinkedHashSet<String> findMatches(String text, String mask) {
        Pattern pattern = Pattern.compile(mask,Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(text);
        LinkedHashSet<String> matches = new LinkedHashSet<String>();
//        System.out.println(text);
        while (matcher.find()) {
            numberOfResults++;
            String match = matcher.group();
            matches.add(match);
            String result = "result: " + match;

            System.out.println(result);                                         //log
        }
        return matches;
    }

    public static ArrayList<ArrayList> makeSitesWithPagesList(ArrayList<String> siteList, ArrayList<String> pageList) {
        ArrayList<ArrayList> sitesWithPagesList = new ArrayList<ArrayList>();
        for (String site : siteList) {
            ArrayList<String> siteWithPage = new ArrayList<String>();
            siteWithPage.add(site);
            for (String page : pageList) {
                siteWithPage.add(site + page);
            }
            sitesWithPagesList.add(siteWithPage);
        }
        return sitesWithPagesList;
    }
}
