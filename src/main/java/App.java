import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) throws IOException {
        // разобраться с "в" (с|в) или [св]
        // жрет строки с "с" но без года
        //удалить дубли по одному сайту

        //-Djsse.enableSNIExtension=false
        System.setProperty("jsse.enableSNIExtension", "false");

        String firstPattern = "((?iu)САЙТ НА РЕКОНСТРУКЦИИ|Сайт находится в разработке|This Domain Is For Sale|Domain contact information|"+
                "Congratulations|Раньше это был Ваш сайт|If you are the owner of this website|этот домен возможно продается|Срок регистрации домена)";
        String url = "http://www.sovnet.ru/about/history/";
        String sourseFile = "C:\\Users\\Lenovo\\Desktop\\testExcelDataDomain.xlsx";
        Document doc1 = null;
        String title = null;
        ExcelEditor excelEditor = new ExcelEditor();

        ArrayList<String> domainExpiredList = excelEditor.extractColumn(sourseFile, 1, 1, 2);
        ArrayList<String> siteList = excelEditor.extractColumn(sourseFile, 1, 4, 2);
        ArrayList<String> pageList = excelEditor.extractColumn(sourseFile, 1, 2, 2);
        ArrayList<ArrayList> sitesWithPagesList = Parser.makeSitesWithPagesList(siteList, pageList);

        Parser.printStringList(siteList);
        Parser.printStringList(pageList);

        Parser parser = new Parser();
        ArrayList<ArrayList> results = parser.parse(sitesWithPagesList, firstPattern);

        excelEditor.writeTable(sourseFile, 1, 6, 2, results);

        System.out.println();
        System.out.println("Обработано " + siteList.size() + " сайтов");
        System.out.println("Найдено " + parser.numberOfResults + " результатов с " + (siteList.size() * pageList.size() + siteList.size()) + " страниц ");
        System.out.println("Произошли следующие ошибки: ");
        for (Exception e : parser.exceptions) {
            System.out.println(e);
        }
    }
}
