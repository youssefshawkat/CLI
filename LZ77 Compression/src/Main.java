import java.util.Scanner;

public class Main{


    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);
        System.out.println("Enter Your Data:");
        String s = input.nextLine();
        LZ77_Compression compression = new LZ77_Compression(s);
        System.out.println("The Decompression is");
        compression.Decompression();
    }


}
