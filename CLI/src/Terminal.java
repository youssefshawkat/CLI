import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;



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
                String str = s[0];
                str = str.substring(0,str.lastIndexOf("/")+1);
                File f = new File("C:\\" + str);

                if(f.exists()) {


                    File x = new File("C:\\" + i);
                    if(x.exists()){

                        System.out.println("Cannot create directory " + i + ": File exists");

                    }
                    else {

                        x.mkdir();
                    }



                }
                else{

                    System.out.println(str + " :No such file or directory");
                }

            }
                else if(!i.contains("/")) {

                File f = new File("C:\\"+PATH+"/" + i);

                if(f.exists()){

                    System.out.println("Cannot create directory " + i + ": File exists");

                }
                else {


                    f.mkdir();
                }


                }
            }
        }

    public void rmdir(String[] s) {
        File [] paths;
        File f = new File("C:\\"+PATH);
        paths = f.listFiles(File::isDirectory);



        if(s[0].equals("*")){



            for(File path:paths) {

                if(path.list().length == 0){
                    path.delete();
                }
                else{
                    System.out.println("rmdir: failed to remove " + path +": Directory not empty" );
                }
            }


        }
        else if (s[0].contains("/")) {
            f = new File("C:\\" + s[0]);
            if (f.exists()) {
                paths = f.listFiles();

                if (f.list().length == 0) {
                    f.delete();
                }
                else{
                    System.out.println("rmdir: failed to remove " + s[0] +": Directory not empty" );
                }

            } else{


                System.out.println("rmdir: failed to remove "+s[0]+": No such file or directory");
            }
        }
        else if(!s[0].contains("/"))
        {

            f = new File("C:\\" +PATH + "/" +s[0]);
            if(f.exists()) {
                paths = f.listFiles();

                if (f.list().length == 0) {
                    f.delete();
                }
                else{
                    System.out.println("rmdir: failed to remove " + s[0] +": Directory not empty" );
                }

            }
        }
        else {

            System.out.println("rmdir: failed to remove "+s[0]+": No such file or directory");
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
           else {

                if (!s[0].contains("/")) {
                    File f = new File("C:\\" + PATH + "/" + s[0]);


                    if (f.exists()) {

                        PATH = PATH.concat("/").concat(s[0]);
                    }


                    if (!f.exists())
                        System.out.println(s[0] + " :No such file or directory");

                } else {



                    File f = new File("C:\\" + s[0]);


                    if (f.exists()) {

                        PATH = "/" +s[0] ;
                    }


                    if (!f.exists())
                        System.out.println(s[0] + " :No such file or directory");

                }
            }




        }

    public void ls(){
        String[] files;

        File f = new File("C:\\"+PATH);
        files = f.list();


        for (String file:files) {

            System.out.print(file + " ");

        }
        System.out.println("\r");

    }

    public void ls_r(){
        String[] files;

        File f = new File("C:\\"+PATH);
        files = f.list();


        for (int i = files.length-1 ; i >= 0 ; i--) {

            System.out.print(files[i] + " ");

        }
        System.out.println("\r");

    }

    public void touch(String[] s) throws IOException {

        if(s[0].contains("/")) {
            String str = s[0];
            str = str.substring(0,str.lastIndexOf("/")+1);
            File f = new File("C:\\" + str);

            if(f.exists()) {


                File x = new File("C:\\" + s[0]);
                if(x.exists()){

                    System.out.println("Cannot create directory " + s[0] + ": File exists");

                }
                else {

                    x.createNewFile();
                }



            }
            else{

                System.out.println(str + " :No such file or directory");
            }

        }
        else if(!s[0].contains("/")) {



            File f = new File("C:\\"+PATH+"/" + s[0] );

            if(f.exists()){

                System.out.println("Cannot create directory " + s[0] + ": File exists");

            }
            else {


                f.createNewFile();
            }


        }

    }


    public void rm(String[] s){



        File f = new File("C:\\"+PATH +"/" + s[0]);
        if(f.exists()) {

            if(f.isDirectory()){

                System.out.println("rm: cannot remove "+s[0]+": Is a directory");
            }
            else {

                f.delete();
            }



        }
        else {

            System.out.println("rm: failed to remove "+s[0]+": No such file or directory");
        }



    }

    public void cp(String[] s) throws Exception {
        File f1 = new File("C:\\" + PATH + "/" + s[0]);
        File f2 = new File("C:\\" + PATH + "/" + s[1]);

        if (!(f1.isDirectory() && f2.isDirectory())) {




            if (f1.exists() && f2.exists()) {

                Path src = Paths.get("C:\\" + PATH + "/" + s[0]);
                Path dest = Paths.get("C:\\" + PATH + "/" + s[1]);

                Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
            } else {

                if (f1.exists() && !f2.exists())
                    System.out.println("No such file  :" + s[1]);
                else if (!f1.exists() && f2.exists())
                    System.out.println("No such file  :" + s[0]);
                else
                    System.out.println("No such files or directories");

            }
        }
        else {

            System.out.println("cp: -r not specified");
        }
    }
    public void cp_r(String[] s) throws Exception{




        File f1 = new File("C:\\"+PATH+"/" +s[1]);
        File f2 = new File("C:\\"+PATH+"/" +s[2]);


        if (f1.exists() && f2.exists()) {


           FileUtils.copyDirectoryToDirectory(f1,f2);
        }

        else{

            if(f1.exists() && !f2.exists())
                System.out.println("No such file  :"+ s[2] );
            else if (!f1.exists() && f2.exists())
                System.out.println("No such file  :"+ s[1] );
            else
                System.out.println("No such files or directories");

        }
    }

    public void cat(String[] s) throws Exception{



        if(s.length == 1) {
            File f1 = new File("C:\\"+PATH+"/" +s[0]);
            if (f1.exists()) {
                if (!f1.isDirectory()) {

                    Scanner input = new Scanner((f1));

                    while (input.hasNextLine()) {
                        System.out.println(input.nextLine());
                    }
                } else {

                    System.out.println("cat : " + s[0] + ": is a  directory: ");
                }
            } else {

                System.out.println("cat : " + s[0] + ": No such file or directory: ");
            }


        }
        else if (s.length == 2){
            File f1 = new File("C:\\"+PATH+"/" +s[0]);
            File f2 = new File("C:\\"+PATH+"/" +s[1]);
            if (f1.exists() && f2.exists()) {
                if (!f1.isDirectory() && !f2.isDirectory()) {

                    String file1Str = FileUtils.readFileToString(f1);
                    String file2Str = FileUtils.readFileToString(f2);
                    Scanner input = new Scanner((f1));
                    System.out.println(file1Str);
                    System.out.println(file2Str);

                } else {

                    System.out.println("cat : one of the operands is a directory: ");
                }
            } else if(!f1.exists()){

                System.out.println("cat : " + s[0] + ": No such files or directory: ");
            }
            else if(!f2.exists()){

                System.out.println("cat : " + s[1] + ": No such files or directory: ");

            }


        }


    }









//This method will choose the suitable command method to be called

    public void chooseCommandAction() throws IOException {

        String s = parser.getCommandName();

        switch (s) {
            case "echo" -> this.echo(parser.getArgs());

            case "mkdir" -> this.mkdir(parser.getArgs());

            case "cd" -> this.cd(parser.getArgs());

            case "pwd" -> this.pwd();

            case "rmdir" -> this.rmdir(parser.getArgs());

            case "ls" -> this.ls();
            case "ls -r" -> this.ls_r();
            case "touch" ->this.touch(parser.getArgs());
            case "rm" ->this.rm(parser.getArgs());
            case "cp" -> {
                try {
                    this.cp(parser.getArgs());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case "cp -r" -> {
                try {
                    this.cp_r(parser.getArgs());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case "cat" -> {
                try {
                    cat(parser.getArgs());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }



    }

    public static void main(String[] args) throws IOException {

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
