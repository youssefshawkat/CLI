public class Tag {

    int position;
    int length;
    char next_symbol;
    public Tag(int p, int l , char n){
        position = p;
        length = l;
        next_symbol = n;

    }

    @Override
    public String toString() {
        return "position("+position+") + length ("+length+") + Next Symbol ("+next_symbol+") " ;
    }
}
