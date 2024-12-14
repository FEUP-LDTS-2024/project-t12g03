package projectt12g03.model;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Sprite {
    private final TextColor.RGB[][] colorCache;
    private final int width;
    private final int height;

    public Sprite(String path) throws IOException {
        URL resource = getClass().getClassLoader().getResource(path);
        BufferedImage image = ImageIO.read(Objects.requireNonNull(resource));
        this.width = image.getWidth();
        this.height = image.getHeight();

        colorCache = new TextColor.RGB[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = image.getRGB(x, y);
                int alpha = (pixel >> 24) & 0xff;
                if (alpha != 0) {
                    int red = (pixel >> 16) & 255;
                    int green = (pixel >> 8) & 255;
                    int blue = pixel & 255;
                    colorCache[x][y] = new TextColor.RGB(red, green, blue);
                } else {
                    colorCache[x][y] = null; // Transparent pixel
                }
            }
        }
    }

    public void draw(GUI gui, double x, double y) {
        for (int dx = 0; dx < width; dx++) {
            for (int dy = 0; dy < height; dy++) {
                if (colorCache[dx][dy] != null) {
                    gui.drawPixel(x + dx, y + dy, colorCache[dx][dy]);
                }
            }
        }
    }
}
