import java.util.Comparator;

public class Sort_Prop implements Comparator<Symbol> {

    @Override
    public int compare(Symbol a1, Symbol a2){

        return -Float.compare(a1.getProbability(),(a2.getProbability()));


    }

}
