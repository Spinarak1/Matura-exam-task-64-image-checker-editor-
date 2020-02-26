import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] argc)throws Exception{
    //Obrazek[] obrazek = fromTxtToTab();
        Scanner scanner = new Scanner(new File("C://Users//Spinarak//Desktop//dane//64//dane_obrazki.txt"));
        Obrazek[] obrazki = new Obrazek[200];
        for(int i =0; i<200; i++) {
            obrazki[i] = new Obrazek(20, 20);
            obrazki[i].wczytaj(scanner);
            System.out.println(obrazki[i]);
        }
    }
    public static int howManyBlack(Obrazek obrazek) {
        int black = 0;
        for (int y = 0; y < obrazek.getWysokosc(); y++) {
            for (int x = 0; x < obrazek.getSzerokosc(); x++) {
                if (obrazek.getPiksel(x, y) == true)
                    black += 1;
            }
        }
        return black;
    }
    public static int howManyRevers(Obrazek[] obrazki){
        int revers = 0;
        for(int i = 0; i<200; i++) {
            if (howManyBlack(obrazki[i]) > (obrazki[i].getSzerokosc() * obrazki[i].getWysokosc()) / 2)
                revers += 1;
        }
        return revers;
    }
    public static boolean czyRekurencyjny(Obrazek obrazek){
        for(int y=0; y < obrazek.getWysokosc()/2; y++)
            for(int x=0; x<obrazek.getSzerokosc()/2; x++){
                if (obrazek.getPiksel(x,y)!=obrazek.getPiksel(x+obrazek.getSzerokosc()/2 ,y))
                    return false;
                if (obrazek.getPiksel(x,y)!=obrazek.getPiksel(x ,y+obrazek.getWysokosc()/2))
                    return false;
                if (obrazek.getPiksel(x,y)!=obrazek.getPiksel(x+obrazek.getSzerokosc()/2 ,y+obrazek.getWysokosc()/2))
                    return false;
            }

        return true;
    }
    public static int isCorrect(Obrazek obrazek, JakNaprawic jn){
        int ileNiepoprawnychWierszy = 0;
        int ileNiepoprawnychKolumn = 0;
        int indeksNiepoprawnegoWiersza = -1;
        int indeksNiepoprawnejKolumny = -1;
        for(int y=0;y<obrazek.getWysokosc();y++){
            int blackX = 0;
            for(int x=0;x<obrazek.getSzerokosc();x++){
                if(obrazek.getPiksel(x, y) == true)
                    blackX=blackX+1;
            }
            if(obrazek.getBitParzystosciWiersza(y) == true && (blackX % 2) != 1) {
                ileNiepoprawnychWierszy += 1;
                indeksNiepoprawnegoWiersza = y;
            }
            else if(obrazek.getBitParzystosciWiersza(y) == false && (blackX % 2) != 0){
                ileNiepoprawnychWierszy += 1;
                indeksNiepoprawnegoWiersza = y;
            }
        }
        for(int x = 0; x<obrazek.getSzerokosc(); x++){
            int blackY = 0;
            for(int y = 0; y<obrazek.getWysokosc(); y++){
                if(obrazek.getPiksel(x,y) == true)
                    blackY = blackY + 1;
            }
            if(obrazek.getBitParzystosciKolumny(x) == true && (blackY % 2) != 1) {
                ileNiepoprawnychKolumn += 1;
                indeksNiepoprawnejKolumny = x;
            }
            else if(obrazek.getBitParzystosciKolumny(x) == false && (blackY % 2) != 0){
                ileNiepoprawnychKolumn += 1;
                indeksNiepoprawnejKolumny = x;
            }
        }
        //System.out.println(ileNiepoprawnychKolumn + " " + ileNiepoprawnychWierszy);
        if (ileNiepoprawnychKolumn+ileNiepoprawnychWierszy==0)
            return 0;
        if (ileNiepoprawnychKolumn<=1 && ileNiepoprawnychWierszy<=1) {
            if(jn != null) {
                jn.wiersz = indeksNiepoprawnegoWiersza;
                jn.kolumna = indeksNiepoprawnejKolumny;
            }
            return 1;
        }
        return ileNiepoprawnychKolumn+ileNiepoprawnychWierszy;
    }
    public static void korygujObrazekJezeliMozna(Obrazek obrazek) {
        JakNaprawic jn = new JakNaprawic();
        if (isCorrect(obrazek, jn) == 1) {
            if (jn.kolumna != -1 && jn.wiersz == -1)
                obrazek.setBitParzystosciKolumny(jn.kolumna, !obrazek.getBitParzystosciKolumny(jn.kolumna));
            else if (jn.kolumna == -1 && jn.wiersz != -1)
                obrazek.setBitParzystosciWiersza(jn.wiersz, !obrazek.getBitParzystosciWiersza(jn.wiersz));
            else if(jn.kolumna != -1 && jn.wiersz != -1)
                obrazek.setPiksel(jn.kolumna, jn.wiersz, !obrazek.getPiksel(jn.kolumna, jn.wiersz));
        }
    }//dokonczyc, testy do koryguj i isCorrect(z jakNaprawic), 65 - z testami
}
