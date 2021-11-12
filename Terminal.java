
import java.io.*;
import java.util.Scanner;


public class Terminal {

    Parser parser;
    String PATH = "/";


    //Implement each command in a method, for example:
    //public String pwd(){...}
    //public void cd(String[] args){...}
    // ...
    public Terminal(){
        parser = new Parser();
    }
    public void pwd(){

            System.out.println(PATH);
    }

    public void echo(String[] s){

        for (String i : s) {
            System.out.print(i + " ");
        }
        System.out.println("\r");

    }
    public void mkdir(String[] s) {

        for (String i : s) {

            if(i.contains("/")) {
                File f = new File("C:\\" + i);
                f.mkdir();
            }
                else if(!i.contains("/")) {

                    File f = new File("C:\\"+PATH+"/" + i);
                    f.mkdir();
                }
            }
        }

    public void rmdir(String[] s) {
        File [] paths;


        if(s[0].equals("*")){

            File f = new File("C:\\"+PATH);
            paths = f.listFiles();

            for(File path:paths) {

                if(path.list().length == 0){
                    path.delete();
                }
            }


        }
        else{
            File f = new File("C:\\"+s[0]);
            paths = f.listFiles();

            for(File path:paths) {

                if(path.list().length == 0){
                    path.delete();
                }
            }

        }


    }


    public void cd(String[] s){


            if(s.length == 0) {
                PATH = "/";
            }

           else if(s[0].equals("..") ){
                if(!PATH.equals("/"))
                    PATH = PATH.substring(0,PATH.lastIndexOf("/"));
        }
           else{



                File f = new File("C:\\"+PATH+"/" +s[0]);


                    if(f.exists()){

                        PATH = PATH.concat("/").concat(s[0]);
                    }


                if(!f.exists())
                    System.out.println(PATH + " :No such file or directory");

            }



        }

    public void ls(){
        String[] paths;

        File f = new File("C:\\"+PATH);
        paths = f.list();


        for (String path:paths) {

            System.out.print(path + " ");

        }
        System.out.println("\r");

    }

    public void ls_r(){
        String[] paths;

        File f = new File("C:\\"+PATH);
        paths = f.list();


        for (int i = paths.length-1 ; i >= 0 ; i--) {

            System.out.print(paths[i] + " ");

        }
        System.out.println("\r");

    }





//This method will choose the suitable command method to be called

    public void chooseCommandAction(){

        String s = parser.getCommandName();

        switch (s) {
            case "echo" -> this.echo(parser.getArgs());

            case "mkdir" -> this.mkdir(parser.getArgs());

            case "cd" -> this.cd(parser.getArgs());

            case "pwd" -> this.pwd();

            case "rmdir" -> this.rmdir(parser.getArgs());

            case "ls" -> this.ls();
            case "ls -r" -> this.ls_r();


        }



    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        Terminal terminal = new Terminal();
        while (!command.equals("exit")) {

            if (terminal.parser.parse(command)) {


                terminal.chooseCommandAction();
            }

            command = input.nextLine();



        }
    }
}
