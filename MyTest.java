
import data_structures.UnorderedVector;
import java.util.Iterator;


/**
 *
 * @author mert
 */
public class MyTest {
    public static void main(String[] args) {
        UnorderedVector<Integer> r = new UnorderedVector<>();
        for (int i = 0; i < 109; i++) {
            r.addFirst(i);
        }
        Iterator<Integer> it = r.iterator();
        while (it.hasNext()) {            
            System.out.println("MyTest.main()" + it.next());
        }
    }
}
