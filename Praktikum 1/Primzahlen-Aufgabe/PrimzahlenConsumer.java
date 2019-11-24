public class PrimzahlenConsumer implements Runnable{

    Primzahlen meinePrimzahlen;
    int primzahl = 0;

    public PrimzahlenConsumer(Primzahlen meinePrimzahlen) {
        this.meinePrimzahlen = meinePrimzahlen;
    }

    public boolean istPrimzahl(int l) {
        return meinePrimzahlen.isPrim(l);
    }



    public void printPrimzahlen() {
        int n = meinePrimzahlen.getLength();

        for (int j = 1; j <= n; j++) {
            if (meinePrimzahlen.isPrim(j)) {
                System.out.print(j + " ");
            }
        }
    }

    @Override
    public void run() {
        if(this.istPrimzahl(primzahl))
            System.out.println(primzahl +  " ist eine Primzahl");
    }

    public int getPrimzahl() {
            return primzahl;
        }

        public void setPrimzahl(int primzahl) {
            this.primzahl = primzahl;
        }
}