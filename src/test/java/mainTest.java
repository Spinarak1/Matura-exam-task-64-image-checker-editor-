import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Scanner;

public class mainTest {
    @Test
    public void test(){
        Obrazek obrazek = new Obrazek(3, 3);
        Obrazek obrazek1 = new Obrazek(3, 3);
        Scanner scanner = new Scanner("1000\n" +
                                             "0101\n" +
                                             "1000\n" +
                                             "101");
        obrazek.wczytaj(scanner);
        System.out.print(obrazek);
        Assert.assertEquals(false, obrazek.getBitParzystosciWiersza(2));
        Assert.assertEquals(true, obrazek.getBitParzystosciWiersza(1));
        Assert.assertEquals(false, obrazek.getBitParzystosciWiersza(0));
        Assert.assertEquals(true, obrazek.getBitParzystosciKolumny(0));
        Assert.assertEquals(false, obrazek.getBitParzystosciKolumny(1));
        Assert.assertEquals(true, obrazek.getBitParzystosciKolumny(2));
        Assert.assertEquals(true, obrazek.getPiksel(0, 0));
        Assert.assertEquals(false, obrazek.getPiksel(2, 2));
        Assert.assertEquals(false, obrazek.getPiksel(1, 0));
        Assert.assertEquals(false, obrazek.getPiksel(0, 1));
        Assert.assertEquals(true, obrazek.getPiksel(1, 1));
        Assert.assertEquals(false, obrazek.getPiksel(1, 2));
        Assert.assertEquals(false, obrazek.getPiksel(2, 1));
        Assert.assertEquals(false, obrazek.getPiksel(2, 0));
        Assert.assertEquals(true, obrazek.getPiksel(0, 2));
    }
    @Test
    public void testIsCorrectAllCorrect(){
        Obrazek obrazek = new Obrazek(3, 3);
        obrazek.wczytaj(new Scanner("0011\n1100\n1010\n010"));
        Assert.assertEquals(0, Main.isCorrect(obrazek, null));
    }
    @Test
    public void testIsCorrectOneRowInvalid(){
        Obrazek obrazek = new Obrazek(3, 3);
        obrazek.wczytaj(new Scanner("0010\n1100\n1010\n010"));
        Assert.assertEquals(1, Main.isCorrect(obrazek, null));
    }
    @Test
    public void testIsCorrectOneColumnInvalid(){
        Obrazek obrazek = new Obrazek(3, 3);
        obrazek.wczytaj(new Scanner("0011\n1100\n1010\n110"));
        Assert.assertEquals(1, Main.isCorrect(obrazek, null));
    }
    @Test
    public void testIsCorrectOneColumnAndRowInvalid(){
        Obrazek obrazek = new Obrazek(3, 3);
        obrazek.wczytaj(new Scanner("0011\n1101\n1010\n110"));
        Assert.assertEquals(1, Main.isCorrect(obrazek, null));
    }
    @Test
    public void testIsCorrectColumnsAndRowsInvalid(){
        Obrazek obrazek = new Obrazek(3, 3);
        obrazek.wczytaj(new Scanner("0010\n1101\n1011\n101"));
        Assert.assertEquals(6, Main.isCorrect(obrazek, null));
    }
}
