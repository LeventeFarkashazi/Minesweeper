package minesweeper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageReader {
    private static ImageReader instance;
    private BufferedImage[] digits;
    private BufferedImage[] images;

    private ImageReader() {
        readDigits();
        readPictures();
    }

    public static ImageReader getInstance() {
        if (instance == null) {
            instance = new ImageReader();
        }
        return instance;
    }

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

    public BufferedImage[] getDigits() {
        return digits;
    }

    public BufferedImage[] getImages() {
        return images;
    }

}
