package WebScraper;

import java.util.Scanner;

public class Menu {
    Scraper scraper;

    public Menu(String fileName){
        this.scraper = new Scraper(fileName);
    }

    private void scrapeFile(){
        int scrapingRresult = 1;
        do{
            Scanner scan = new Scanner(System.in);
            System.out.printf("Insert the name of the file you want to scrape: ");
            String fileName = scan.nextLine();
            scrapingRresult = scraper.scrapeFile(fileName);
        }while(scrapingRresult == 1);
    }

    private void printInterface(){
        System.out.println("\n[1] Scrape another file");
        System.out.println("[2] Get the URL of an internal hyperlink");
        System.out.println("[3] Get the URL of an external hyperlink");
        System.out.println("[4] Get the URL of an image");
        System.out.println("[5] Exit");
    }

    private int getChoice(){
        Scanner scan = new Scanner(System.in);
        int choice = 0;

        System.out.printf("\nPlease select an option: ");
        choice = scan.nextInt();

        while (choice > 5 || choice < 1){
            System.out.println("Invalid option!");
            System.out.printf("\nPlease select an option: ");
            choice = scan.nextInt();
        }

        return choice;
    }

    private void pressKeyToContinue(){ 
        System.out.println("\nPress any key to continue...");
        try{ System.in.read(); }
        catch(Exception e){}  
    }

    private int executeChoice(){
        int choice = getChoice();

        switch(choice){
            case 1: scrapeFile();
                    pressKeyToContinue();
                break;

            case 2: scraper.showInteralHyperLink();
                    pressKeyToContinue();
                break;

            case 3: scraper.showExteralHyperLink();
                    pressKeyToContinue();
                break;

            case 4: scraper.showImageLink();
                    pressKeyToContinue();
                break;
        
            case 5: System.out.println("\nClosing application");
                break;

            default:
                break;
        }

        return choice;
    }

    public void startMenu(){
        int choice = 0;
        do{
            printInterface();
            choice = executeChoice();
        }while(choice != 5);
    }
}
