import java.util.Scanner;

public class Obrazek {
    private int szerokosc;
    private int wysokosc;
    private boolean[][] piksele;
    private boolean[] bityParzystosciWiersze;
    private boolean[] bityParzystosciKolumny;

    public void setPiksel(int x, int y, boolean piksel) {
        piksele[x][y] = piksel;
    }

    public Obrazek(int szerokosc, int wysokosc){
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        piksele = new boolean[szerokosc][wysokosc];
        bityParzystosciKolumny = new boolean[szerokosc];
        bityParzystosciWiersze = new boolean[wysokosc];
    }

    public int getSzerokosc() {
        return szerokosc;
    }

    public int getWysokosc() {
        return wysokosc;
    }
    // 1 1 1 1
    // 1 1 0 0
    // 0 0 0 0
    // 1 0 1
    public void wczytaj(Scanner scanner){
        String wiersz = "";
        for(int y = 0; y<wysokosc; y++) {
            wiersz = scanner.next();
            for (int x = 0; x < szerokosc; x++) {
                if (wiersz.charAt(x) == '1')
                    setPiksel(x, y, true);
                else
                    setPiksel(x, y, false);
            }
            if(wiersz.charAt(szerokosc) == '1')
                bityParzystosciWiersze[y] = true;
            else
                bityParzystosciWiersze[y] = false;
        }
        wiersz = scanner.next();
        for(int x = 0; x<szerokosc; x++) {
            if (wiersz.charAt(x) == '1')
                bityParzystosciKolumny[x] = true;
            else
                bityParzystosciKolumny[x] = false;
        }
    }

    public boolean getPiksel(int x, int y) {
        return piksele[x][y];
    }

    public boolean getBitParzystosciWiersza(int y) {
        return bityParzystosciWiersze[y];
    }

    public boolean getBitParzystosciKolumny(int x) {
        return bityParzystosciKolumny[x];
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int y = 0; y<wysokosc; y++){
            for(int x = 0; x<szerokosc; x++){
                sb.append(piksele[x][y] ? '1' : '0');
            }
            sb.append(bityParzystosciWiersze[y] ? '1' : '0');
            sb.append('\n');
        }
        for(int x = 0; x<szerokosc; x++){
            sb.append(bityParzystosciKolumny[x] ? '1' : '0');
        }
        return sb.toString();
    }

    public void setBitParzystosciWiersza(int y, boolean bit) {
        this.bityParzystosciWiersze = bityParzystosciWiersze;
    }

    public void setBitParzystosciKolumny(int y, boolean bit) {
        this.bityParzystosciKolumny = bityParzystosciKolumny;
    }
    //dokonczyc
}
