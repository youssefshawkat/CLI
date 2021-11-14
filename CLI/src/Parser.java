public class Parser {

    String commandName;
    String[] args;
    String[] str;
    String[] commands = {"echo","mkdir","cd","pwd","rmdir","ls","ls -r","touch","rm","cp","cp -r","cat"};

    //This method will divide the input into commandName and args
//where "input" is the string command entered by the user
    public boolean parse(String input){

        str = input.split(" ");
        args = new String[str.length-1];

        for (int i = 0; i < commands.length; i++) {
            if(commands[i].equals(str[0])){
                commandName = str[0];

                System.arraycopy(str, 1, args, 0, args.length);

                break;
            }
            else if (i == commands.length-1){

                System.out.println("Invalid Command");
                return false;

            }

        }


        if(str[0].equals("mkdir") && args.length ==0){


            System.out.println("mkdir: missing operand");
            return false;
        }
        if(str[0].equals("cd") && args.length > 1){


            System.out.println("cd: too many arguments");

            return false;
        }

        if(str[0].equals("rmdir") && (args.length > 1) ){


            System.out.println("rmdir: too many arguments");

            return false;
        }

        if(str[0].equals("rmdir") && (args.length == 0) ){


            System.out.println("rmdir: missing operands");

            return false;
        }

        if(str[0].equals("ls") && str.length > 1 && str[1].equals("-r")){



            commandName ="ls -r";

            if( args.length > 1 ){


                System.out.println("ls -r: too many arguments");

                return false;
            }


            return true;

        }


        if(str[0].equals("ls") && args.length > 0){


            System.out.println("ls: too many arguments");

            return false;
        }




        if(commandName.equals("touch") && args.length > 1){


            System.out.println("touch: too many arguments");

            return false;
        }

        if(commandName.equals("touch") && args.length == 0){


            System.out.println("touch: missing operands");

            return false;
        }

        if(commandName.equals("rm") && args.length == 0){


            System.out.println("rm: missing operands");

            return false;
        }

        if(commandName.equals("rm") && args.length > 1){


            System.out.println("rm: too many arguments");

            return false;
        }

        if(str[0].equals("cp") && str[1].equals("-r")){



            commandName ="cp -r";


            if( args.length > 3 ){


                System.out.println("cp -r: too many arguments");

                return false;
            }

            if( args.length < 3){


                System.out.println("cp -r: missing operands");

                return false;
            }


            return true;

        }



        if(commandName.equals("cp") && args.length > 2){


            System.out.println("cp: too many arguments");

            return false;
        }

        if(commandName.equals("cp") && args.length < 2){


            System.out.println("cp: missing operands");

            return false;
        }

        if(commandName.equals("cat") && args.length > 2){


            System.out.println("cat: too many arguments ");

            return false;
        }

        if(commandName.equals("cat") && args.length == 0){


            System.out.println("cat: missing operands");

            return false;
        }










        return true;



    }

    public String getCommandName(){

        return commandName;
    }
    public String[] getArgs(){

        return args;

    }

}
