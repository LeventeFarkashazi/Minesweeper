package minesweeper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageReader {
    private static ImageReader instance;
    BufferedImage[] digits;

    private ImageReader(){
        readDigits();
    }

    public static ImageReader getInstance(){
        if(instance == null){
            instance = new ImageReader();
        }
        return instance;
    }

    public void readDigits(){
        digits = new BufferedImage[11];
        for (int i = 0; i < 11; i++) {
            try {
                digits[i] = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\digits\\" + i + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public BufferedImage[] getDigits() {
        return digits;
    }
}
