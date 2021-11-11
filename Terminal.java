
import java.util.Scanner;


public class Terminal {

    Parser parser;
    //Implement each command in a method, for example:
    //public String pwd(){...}
    //public void cd(String[] args){...}
    // ...
    public Terminal(String s){
        parser = new Parser();
        parser.parse(s);

    }
    public void echo(String[] s){

        for (String i : s) {
            System.out.print(i + " ");
        }

    }
//This method will choose the suitable command method to be called

    public void chooseCommandAction(){

        String s = parser.getCommandName();

        switch (s) {
            case "echo" -> this.echo(parser.getArgs());
        }



    }

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        Terminal terminal = new Terminal(command);
       terminal.chooseCommandAction();




    }

}
