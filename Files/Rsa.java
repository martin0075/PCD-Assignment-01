import java.util.concurrent.atomic.AtomicInteger;

/**
 * Given two primes it calculates 
 * the public and the priavte key
 * [e,n] [d,n]
 * 
 * @author mic
 */
public class Rsa {

    AtomicInteger x;
    AtomicInteger y; 

    public Rsa(int p, int q) {
        x = new AtomicInteger();
        y = new AtomicInteger();
        computeKeys(p, q);
    }

    public Rsa(int p, int q, int e) {
        x = new AtomicInteger();
        y = new AtomicInteger();
        computeKeysEGiven(p, q, e);
    }

    /**
     * Returns gcd 
     */
    private int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
            
        return gcd(b % a, a);
    }

    /**
     * Outputs K_priv e K_pub
     * @param p
     * @param q
     */
    private void computeKeys(int p, int q) {
        int d = 0;
        int e = 0;
        int n = p * q;
        int phiN = (p-1) * (q-1);
        
        // Calculate e
        for (e = 2; e < phiN; e++) {
            if (gcd(e, phiN) == 1) {
                break;
            }
        }

        System.out.printf("Public key: [%d, %d]\n", e, n);

        extended_gcd(phiN, e, this.x, this.y);
        d = phiN + this.y.get();

        System.out.printf("Private key: [%d, %d]\n", d, n);

    }

    /**
     * Outputs K_priv e K_pub
     * @param p 
     * @param q
     * @param e
     */
    private void computeKeysEGiven(int p, int q, int e) {
        int d = 0;
        int n = p * q;
        int phiN = (p-1) * (q-1);

        System.out.printf("Public key: [%d, %d]\n", e, n);

        extended_gcd(phiN, e, this.x, this.y);
        d = phiN + this.y.get();

        System.out.printf("Private key: [%d, %d]\n", d, n);

    }

    public int extended_gcd(int a, int b, AtomicInteger x, AtomicInteger y)
    {
        if (a == 0)
        {
            x.set(0);
            y.set(1);
            return b;
        }
 
        AtomicInteger _x = new AtomicInteger(), _y = new AtomicInteger();
        int gcd = extended_gcd(b % a, a, _x, _y);

        x.set(_y.get() - (b/a) * _x.get());
        y.set(_x.get());
        System.out.println("\n");
        System.out.printf("[%d, %d, %d]", a, x.get(), y.get());
        System.out.println("\n");
        
        return gcd;
    }
    
    /**
     * 
     * compile
     * javac Rsa.java
     * 
     * execute chosing p and q
     * java Rsa.java p q
     * 
     * execute with e given by ozalp 
     * java Rsa.java p q e
     */
    public static void main(String[] args) {

        Rsa rsa;

        if(args.length < 2) {
            System.out.println("Please add p and q as arguments");
        
        } else if(args.length == 2) {
            int p = Integer.parseInt(args[0]);
            int q = Integer.parseInt(args[1]);
            
            rsa = new Rsa(p, q);

        } else {
            int p = Integer.parseInt(args[0]);
            int q = Integer.parseInt(args[1]);
            int e = Integer.parseInt(args[2]);

            rsa = new Rsa(p, q, e);
        }
    }
}