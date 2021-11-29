package minesweeper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A játék kirajzolásához használt képeket beolvasásáért és tárolásáért felelős osztály.
 * Csak egy példánya lehet, azaz singleton.
 */
public class ImageReader {
    private static ImageReader instance;
    private BufferedImage[] digits;
    private BufferedImage[] images;

    /**
     * Konstruktor.
     * Példányosítja az osztályt és meghívja a képeket beolvasó függvényeket.
     */
    private ImageReader() {
        readDigits();
        readPictures();
    }

    /**
     * Visszadja az osztály egyetlen létező példányát.
     * Ha az osztálynak még nem létezik példánya, létrehoz egyet.
     *
     * @return az osztály példánya
     */
    public static ImageReader getInstance() {
        if (instance == null) {
            instance = new ImageReader();
        }
        return instance;
    }

    /**
     * Beolvassa a számlálókhoz használt számjegyek képeit.
     */
    public void readDigits() {
        digits = new BufferedImage[11];
        for (int i = 0; i < 11; i++) {
            try {
                digits[i] = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\digits\\" + i + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Beolvassa az aknamező mezőjinek kirajzolásához használt képeket.
     */
    public void readPictures() {
        images = new BufferedImage[13];
        for (int i = 0; i < 13; i++) {
            try {
                images[i] = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\images\\" + i + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Visszatér a számlálókhoz használt számjegyek képeit tartalmazó tömbbel.
     *
     * @return a számjegyek képei
     */
    public BufferedImage[] getDigits() {
        return digits;
    }

    /**
     * Visszatér az aknamező mezőinek kirajzolásához használt képeket tartalmazó tömbbel.
     *
     * @return a mezők képei
     */
    public BufferedImage[] getImages() {
        return images;
    }

}
