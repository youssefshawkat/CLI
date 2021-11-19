import java.io.*;
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
        float compression_ratio;


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

        try (PrintWriter out = new PrintWriter("Compressed_BString.txt")) {
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
        System.out.println("===============================");
        compression_ratio = (float) org_size / compressed_size;
        System.out.println("Original Size = " + org_size + " bits");
        System.out.println("Compressed Size = " + compressed_size + " bits");
        System.out.println("Compression Ratio = " + compression_ratio);


    }





public void decompression() {

    String data = "";
    String[] line = null;
    String Character;
    String Code;
    String decompressed = "";
    ArrayList<Symbol> symbols1 = new ArrayList<>();


    try {
        File myObj = new File("Compressed_BString.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            data = myReader.nextLine();

        }
        myReader.close();
    } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }

    try {
        File myObj = new File("Dictionary.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            line = myReader.nextLine().split(",");

        }
        myReader.close();
    } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }


    assert line != null;

    for (String i : line) {


        Character = String.valueOf(i.charAt(0));
        Code = i.substring(2);
        Symbol sym = new Symbol(Character, Code);
        symbols1.add(sym);


    }
    int q = 0;

    for (int i = 0; i <= data.length(); i++) {


        for (Symbol symbol : symbols1) {


            if (data.substring(q, i).equals(symbol.getCode())) {

                decompressed = decompressed.concat(data.substring(q, i).replaceAll(symbol.getCode(), symbol.getChar()));

                q = i;
                break;

            }

        }


    }


    try (PrintWriter out = new PrintWriter("Decompression.txt")) {
        out.println(decompressed);
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }





}

}




