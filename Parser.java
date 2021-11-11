public class Parser {

    String commandName;
    String[] args;
    String[] str;
    String[] commands = {"echo"};

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

        return true;



    }

    public String getCommandName(){

        return commandName;
    }
    public String[] getArgs(){

        return args;

    }

}
