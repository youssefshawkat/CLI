public class Symbol {

    private final String Char;
    private float probability;
    private String code;
    public Symbol right;
    public  Symbol left;
    public int frequency;


    public Symbol(String c ,float p,int f){

        Char = c;
        probability = p;
        frequency = f;



    }

    public Symbol(String c ,float p,Symbol r , Symbol l){

        Char = c;
        probability = p;
        right = r;
        left = l;


    }

    public Symbol(String character, String code) {
        this.code = code;
        Char = character;

    }


    public String getChar() {
        return Char;
    }

    public float getProbability() {

        return probability;
    }

    public  String getCode (){
        return code;

    }

    public int getFrequency() {
        return frequency;
    }

    public void setCode(String c){

        code = c;
    }

    public String toString() {
        return "Char: " + Char + " -> " + frequency ;
    }


}
