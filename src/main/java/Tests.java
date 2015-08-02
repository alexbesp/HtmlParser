import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tests {
    public static void main(String[] args) throws IOException {
       // ExcelEditor.writeColumn("C:/MyJava/testExcelData.xlsx", 1, 6, 2);

/*        String charset = "UTF16";
        //Charset charset = Charset.forName("UTF-8");

//        String firstPattern ="\p{{0,20}[1-9]\\d{0,3}.{0,2}(лет|год|года|году|г).{0,20}"};
        String firstPattern = new String("заказ".getBytes("UTF8"), charset);
        String firstPatternUni = new String();

        Document doc1 = null;
        Document doc2 = null;
        String title = null;
        String url = "http://www.fotoplus.su/about/";

        try {
            doc1 = Jsoup.connect(url).get();
            doc2 = Jsoup.parse(new URL(url).openStream(), charset, url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("title is " + title);

        String bodyText1 = doc1.select("body").text();
        String bodyText11 = new String(bodyText1.getBytes("UTF-8"), charset);


        String bodyText2 = doc2.select("body").text();
        String bodyText22 = new String(bodyText2.getBytes("UTF-8"), charset);

        findMatches(bodyText1, firstPattern);
        findMatches(bodyText11, firstPattern);

        findMatches(bodyText2, firstPattern);
        findMatches(bodyText22, firstPattern);

        findMatches(bodyText1, "\u0430");
        findMatches(bodyText11, "0");

        findMatches(bodyText2, "а");
        findMatches(bodyText22, "заказ");*/

       // check("лалала", "лала");


        System.out.println("\n" + " \ndfsfds " + "\n");


//        System.out.println(plainText.getPlainText(doc));

    }

    public static void check(String text, String mask) throws UnsupportedEncodingException {
        Pattern pattern = Pattern.compile(mask);
        Matcher matcher = pattern.matcher(text);
        String result;

        System.out.println(text);

        if (matcher.find()){
//            String s = new String(matcher.group().getBytes(), "UTF8");

            result = "result: " + matcher.group();}
        else
            result = "result: " + "syka ne nashel nihera";


        System.out.println(result);
    }

}
