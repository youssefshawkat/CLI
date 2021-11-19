import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Standard_Huffman {


    private String b;
    private final int org_size;
    private final ArrayList<Symbol> symbols = new ArrayList<>();

    public Standard_Huffman(String text) {

        String s = text;
        org_size = text.length() * 8;
        b = s;
        int count;
        float prob;
        int i = 0;
        String c;
        int length = s.length();
        String a;
        float pr;
        boolean T = false;


        while (!s.isEmpty()) {

            c = String.valueOf(s.charAt(i));
            count = s.length() - s.replaceAll(c, "").length();

            prob = (float) count / length;

            Symbol sym = new Symbol(c, prob, count);

            symbols.add(sym);

            s = s.replaceAll(String.valueOf(s.charAt(i)), "");


        }

        symbols.sort(new Sort_Prop());

        if (symbols.size() > 1) {


            T = (symbols.get(0).getProbability() + symbols.get(1).getProbability() < 1);
        }

        int k = symbols.size() - 1;

        while (T && k >= 1) {

            a = symbols.get(k).getChar() + symbols.get(k - 1).getChar();
            pr = symbols.get(k).getProbability() + symbols.get(k - 1).getProbability();
            Symbol sym = new Symbol(a, pr, symbols.get(k), symbols.get(k - 1));
            symbols.add(sym);
            symbols.sort(new Sort_Prop());
            T = (symbols.get(0).getProbability() + symbols.get(1).getProbability() < 1);
            k = k - 1;


        }


    }

 

    public void Compression() {

        String dictionary = "";
        int compressed_size = 0;
        int compression_ratio;


        String c1 = "0";
        String c2 = "1";



        symbols.get(0).setCode(c1);

        if (symbols.size() > 1) {

            symbols.get(1).setCode(c2);
        }

        for (int j = 0; j < symbols.size() - 1; j = j + 2) {


            if (symbols.get(j).left != null) {

                c1 = symbols.get(j).getCode().concat("0");
                symbols.get(j).left.setCode(c1);

                c1 = symbols.get(j).getCode().substring(0, c1.length() - 1).concat("1");
                symbols.get(j).right.setCode(c1);


            }


            if (symbols.get(j + 1).left != null) {

                c2 = symbols.get(j + 1).getCode().concat("0");
                symbols.get(j + 1).left.setCode(c2);

                c2 = symbols.get(j + 1).getCode().concat("1");
                symbols.get(j + 1).right.setCode(c2);


            }


        }

        for (Symbol symbol : symbols) {

            if (symbol.getChar().length() == 1)
                b = b.replaceAll(symbol.getChar(), symbol.getCode());

        }

        try (PrintWriter out = new PrintWriter("Binary_String.txt")) {
            out.println(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Symbol symbol : symbols) {

            if(symbol.getChar().length() == 1) {

                dictionary = dictionary.concat(symbol.getChar() + " " + symbol.getCode() + ",");
            }
        }



       try (PrintWriter out = new PrintWriter("Dictionary.txt")) {
            out.println(dictionary.substring(0,dictionary.length()-1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        for (Symbol symbol : symbols) {


            if (symbol.getChar().length() == 1)

                System.out.println(symbol);

            compressed_size = compressed_size + (symbol.getFrequency() * symbol.getCode().length());


        }

        compression_ratio = org_size / compressed_size;
        System.out.println("Compressed Size = " + compressed_size);
        System.out.println("Compression Ratio = " + compression_ratio);


    }





public void decompression() {

    String data ="";

    try {
        File myObj = new File("Binary_String.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            data = myReader.nextLine();

        }
        myReader.close();
    } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }

    Symbol root = new Symbol(null, 0, null, symbols.get(0));

    if(symbols.size() > 1) {
       root = new Symbol(null, 0, symbols.get(1), symbols.get(0));
    }

    Symbol pointer = root;
    String decompressed ="";

    int q = 0;


    for (int j = 0; j <  data.length() ; j++) {



        if(data.charAt(j) == '0' && pointer.left != null){

            pointer = pointer.left;

        }


        else if (data.charAt(j) == '1' && pointer.right != null){

            pointer = pointer.right;

        }

        if(pointer.getChar().length() == 1){

            decompressed = decompressed.concat(b.substring(q,j+1).replaceAll(pointer.getCode(), pointer.getChar()));
            q = j+1;
            pointer = root;


        }



    }

    System.out.println(decompressed);

}
}




