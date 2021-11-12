public class Parser {

    String commandName;
    String[] args;
    String[] str;
    String[] commands = {"echo","mkdir","cd","pwd","rmdir","ls","ls -r"};

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

        if(str[0].equals("rmdir") && args.length > 1){


            System.out.println("rmdir: too many arguments");

            return false;
        }

        if(str[0].equals("ls") && str.length > 1){


            str[0] = "ls -r";
            commandName =str[0];

        }

        if(str[0].equals("ls") && args.length > 0){


            System.out.println("ls: too many arguments");

            return false;
        }


        if(commandName.equals("ls -r") && args.length > 1){


            System.out.println("ls -r: too many arguments");

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
