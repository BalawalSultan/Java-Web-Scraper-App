package WebScraper;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraper {
    ArrayList<String> imageLinks, internalHyperLinks, externalHyperLinks;
    String title, domain;

    public Scraper(String fileName){
        int scrapingRresult = scrapeFile(fileName);

        while(scrapingRresult == 1){
            Scanner scan  = new Scanner(System.in);
            System.out.printf("Insert the name of the file you want to scrape: ");
            fileName = scan.nextLine();
            scrapingRresult = scrapeFile(fileName);
        }
    }

    public int scrapeFile(String fileName){
        File html = new File(fileName);

        if(html.exists() == false){
            System.out.println("\nThere is no file named \""+ fileName +"\" in the current directory");
            System.out.println("The current directory is : " + System.getProperty("user.dir"));
            return 1;
        }

        domain = fileName.replace(".html", "");
        externalHyperLinks = new ArrayList<String>();
        internalHyperLinks = new ArrayList<String>();
        imageLinks = new ArrayList<String>();

        try {
            Document doc = Jsoup.parse(html, "UTF-8", ("https://" + domain));
            Elements imgTags = doc.select("img");
            Elements ancorTags = doc.select("a");
            title = doc.title();

            for(Element tag: imgTags)
                imageLinks.add(tag.attr("src"));

            for(Element tag: ancorTags){
                String link = tag.attr("href");

                if(isLinkInternal(link))
                    internalHyperLinks.add(link);
                else
                    externalHyperLinks.add(link);
            }

        } catch (Exception e) {
            System.err.println(e.getStackTrace());
        }

        showReport();

        return 0;
    }

    private boolean isLinkInternal(String link){
        if(link.contains(domain))
            return true;

        if(link.contains("https://") || link.contains("http://"))
            return false;

        return true;
    }

    private String getListRange(ArrayList<String> list){
        return "[1-" + list.size() + "] :";
    }

    private int getIndex(ArrayList<String> list){
        Scanner scan = new Scanner(System.in);
        int index = 0;

        index = scan.nextInt();

        while (index > list.size() || index < 1){
            System.out.println("\nInvalid option!");
            System.out.printf("\nPlease select a valid index " + getListRange(list));
            index = scan.nextInt();
        }

        return index - 1;
    }

    public void showInteralHyperLink(){
        System.out.printf("\nPlease enter the index of the internal hyperlink you want to know the URL of " + getListRange(internalHyperLinks));
        int index = getIndex(internalHyperLinks);
        System.out.println("The internal hyperlink number " + (index+1) +" is: " + internalHyperLinks.get(index));
    }

    public void showExteralHyperLink(){
        System.out.printf("\nPlease enter the index of the external hyperlink you want to know the URL of " + getListRange(externalHyperLinks));
        int index = getIndex(externalHyperLinks);
        System.out.println("The external hyperlink number " + (index+1) +" is: " + externalHyperLinks.get(index));
    }

    public void showImageLink(){
        System.out.printf("\nPlease enter the index of the image you want to know the URL of " + getListRange(imageLinks));
        int index = getIndex(imageLinks);
        System.out.println("The image number " + (index+1) +" is: " + imageLinks.get(index));
    }
    
    public void showReport(){
        System.out.println("The title is: " + title);
        System.out.println("There are " + internalHyperLinks.size() + " internal links.");
        System.out.println("There are " + externalHyperLinks.size() + " external links.");
        System.out.println("There are " + imageLinks.size() + " images.");
    }
}