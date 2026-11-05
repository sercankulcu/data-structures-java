import java.util.BitSet;

public class BitVektorKume {

    // int maskesini {..} biciminde yazdirir (evren 0..31)
    static String yaz(int maske) {
        StringBuilder sb = new StringBuilder("{");
        for (int x = 0; x < 32; x++) {
            if ((maske >> x & 1) == 1) {
                if (sb.length() > 1) sb.append(", ");
                sb.append(x);
            }
        }
        return sb.append("}").toString();
    }

    public static void main(String[] args) {
        // 1) int maskesi: i. bit 1 <=> i kumede (U <= 32)
        int a = 0, b = 0;
        for (int x : new int[]{1, 3, 4})
            a |= 1 << x;          // x. biti yak
        for (int x : new int[]{3, 5})
            b |= 1 << x;
        int bir = a | b;          // birlesim
        int kes = a & b;          // kesisim
        int fark = a & ~b;        // fark
        boolean var3 = (a >> 3 & 1) == 1; // uyelik testi, O(1)

        System.out.println("a = " + a + " -> " + yaz(a));            // 26 -> {1, 3, 4}
        System.out.println("b = " + b + " -> " + yaz(b));            // 40 -> {3, 5}
        System.out.println("a | b  = " + bir + " -> " + yaz(bir));   // 58 -> {1, 3, 4, 5}
        System.out.println("a & b  = " + kes + " -> " + yaz(kes));   // {3}
        System.out.println("a & ~b = " + fark + " -> " + yaz(fark)); // {1, 4}
        System.out.println("3 a'da mi? " + var3);
        System.out.println("|a| = " + Integer.bitCount(a));          // 3

        // 2) Ayni islemler java.util.BitSet ile (evren 32'den buyuk olabilir)
        BitSet ba = new BitSet();
        BitSet bb = new BitSet();
        ba.set(1); ba.set(3); ba.set(4);
        bb.set(3); bb.set(5);

        BitSet bBir = (BitSet) ba.clone();  // or/and/andNot yerinde degistirir: once kopya
        bBir.or(bb);
        BitSet bKes = (BitSet) ba.clone();
        bKes.and(bb);
        BitSet bFark = (BitSet) ba.clone();
        bFark.andNot(bb);

        System.out.println("\nBitSet birlesim : " + bBir);  // {1, 3, 4, 5}
        System.out.println("BitSet kesisim  : " + bKes);    // {3}
        System.out.println("BitSet fark     : " + bFark);   // {1, 4}
        System.out.println("get(3) = " + ba.get(3) + ", cardinality() = " + ba.cardinality());
    }
}
