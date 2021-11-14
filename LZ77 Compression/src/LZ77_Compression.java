import java.util.ArrayList;

public class LZ77_Compression {

    int ps = 0, ln = 0;
    char ch = 0;
    int max_ps = 0;
    int max_ln = 0;
    int NOTs;
    int psB, lnB;
    int Tag_Size;
    float CR;

    ArrayList<Tag> tags = new ArrayList<>();


    public LZ77_Compression(String s) {


        for (int i = 0; i < s.length(); i++) {


            if (s.substring(0, i).contains(s.substring(i, i + 1))) {


                for (int count = i + 1; count <= s.length(); count++) {



                    if (!(s.substring(0, i).contains(s.substring(i, count)))) {





                        ps = ((i) - s.indexOf(s.substring(i, count - 1)));


                        ln = (s.substring(i, count).length() - 1);


                        ch = (s.charAt(count - 1));


                        i = count-1 ;

                        break;
                    } else {



                        ps = (s.indexOf(s.substring(i, count - 1)));


                        ln = (s.substring(i, count).length() - 1);


                        ch = (s.charAt(count - 1));

                        if(i == s.length()-2 && ln ==1) {

                            i = i+1;
                        }


                    }

                }


                tags.add(new Tag(ps, ln, ch));
                if (max_ps < ps) {
                    max_ps = ps;
                }

                if (max_ln < ln) {
                    max_ln = ln;
                }

            } else {



                tags.add(new Tag(0, 0, s.charAt(i)));

            }


        }


        lnB = Integer.toBinaryString(max_ln).length();
        psB = Integer.toBinaryString(max_ps).length();
        NOTs = tags.size();
        Tag_Size = lnB + psB + NOTs;
        tags.forEach(System.out::println);
        System.out.println("Compressed Size = " + Tag_Size * NOTs);
        System.out.println("Original Size = " + s.length() * 8);
        CR = (float)(s.length() * 8)/(Tag_Size * NOTs);
        System.out.printf("Compression Ratio =  " + "%.2f", CR );
        System.out.println("\n");



    }

    public void Decompression()
    {
        StringBuilder decompressed = new StringBuilder();
        String temp;
        int j=0;
        int k;

        for (Tag tag : tags) {
            if (tag.position == 0) {
                j++;
                decompressed.append(tag.next_symbol);
            } else {
                k = j - tag.position;
                temp = decompressed.substring(k, k + tag.length);
                j += tag.length + 1;
                decompressed.append(temp).append(tag.next_symbol);
            }

        }

        System.out.println(decompressed);
    }
}




