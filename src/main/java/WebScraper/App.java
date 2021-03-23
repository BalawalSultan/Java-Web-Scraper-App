package WebScraper;

public class App {

    public static void main(String[] args) {       
        if(args.length < 1){
            System.out.println("You forgot to enter the name of the html file!");
            System.out.println("Closing App");
            return;
        }

        Menu menu = new Menu(args[0]);
        menu.startMenu();
    }

}
