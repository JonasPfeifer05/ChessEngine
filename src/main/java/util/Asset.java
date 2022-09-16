package util;

import functional.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created: 08.09.2022
 *
 * @author Tobias Frischmann
 */
public class Asset {

    public static final String RUN_PATH = "src/main/resources/";
    public static final String JAR_PATH = "../resources/main/";

    public static final int PIECE_COUNT = 6;
    public static final int SPRITE_SIZE = 45;


    private static final BufferedImage[][] spritesPlayer = new BufferedImage[4][PIECE_COUNT];
    private static final BufferedImage[][] spritesPlayerDead = new BufferedImage[4][PIECE_COUNT];

    public static File getFile(String name) {
        File file;

        file = new File(RUN_PATH + name);
        if (file.exists()) return file;

        file = new File(JAR_PATH + name);

        return file;
    }

    public static void setUp() {
        BufferedImage img;

        try {
            img = ImageIO.read(getFile("Chess_Pieces_Sprite.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < PIECE_COUNT; i++) {
            for (int j = 0; j < spritesPlayer.length; j++) {
                spritesPlayer[j][i] = img.getSubimage(i * SPRITE_SIZE, 0, SPRITE_SIZE, SPRITE_SIZE);
                spritesPlayerDead[j][i] = img.getSubimage(i * SPRITE_SIZE, 0, SPRITE_SIZE, SPRITE_SIZE);
            }
        }

        updateColor();
    }

    public static BufferedImage getSprite(Player player, int id, Game game) {


        switch (player) {
            case PLAYER1 -> {
                if (!game.isAlive(player)) {
                    return spritesPlayerDead[0][id];
                } else {
                    return spritesPlayer[0][id];
                }
            }
            case PLAYER2 -> {
                if (!game.isAlive(player)) {
                    return spritesPlayerDead[1][id];
                } else {
                    return spritesPlayer[1][id];
                }
            }
            case PLAYER3 -> {
                if (!game.isAlive(player)) {
                    return spritesPlayerDead[2][id];
                } else {
                    return spritesPlayer[2][id];
                }
            }
            case PLAYER4 -> {
                if (!game.isAlive(player)) {
                    return spritesPlayerDead[3][id];
                } else {
                    return spritesPlayer[3][id];
                }
            }
        }

        return null;
    }

    public static void updateColor() {

        for (int i = 0; i < spritesPlayer[0].length; i++) {
            spritesPlayer[0][i] = updateColor(spritesPlayer[0][i], Player.PLAYER1.color);
            spritesPlayer[1][i] = updateColor(spritesPlayer[1][i], Player.PLAYER2.color);
            spritesPlayer[2][i] = updateColor(spritesPlayer[2][i], Player.PLAYER3.color);
            spritesPlayer[3][i] = updateColor(spritesPlayer[3][i], Player.PLAYER4.color);
            spritesPlayerDead[0][i] = updateColor(spritesPlayerDead[0][i], Player.PLAYER1.deadColor);
            spritesPlayerDead[1][i] = updateColor(spritesPlayerDead[1][i], Player.PLAYER2.deadColor);
            spritesPlayerDead[2][i] = updateColor(spritesPlayerDead[2][i], Player.PLAYER3.deadColor);
            spritesPlayerDead[3][i] = updateColor(spritesPlayerDead[3][i], Player.PLAYER4.deadColor);
        }
    }

    private static BufferedImage updateColor(BufferedImage img, Color color) {
        BufferedImage tintedSprite = new BufferedImage(img.getWidth(), img.
                getHeight(), BufferedImage.TRANSLUCENT);
        Graphics2D graphics = tintedSprite.createGraphics();
        graphics.drawImage(img, 0, 0, null);
        graphics.dispose();

        for (int i = 0; i < tintedSprite.getWidth(); i++) {
            for (int j = 0; j < tintedSprite.getHeight(); j++) {
                int ax = tintedSprite.getColorModel().getAlpha(tintedSprite.getRaster().
                        getDataElements(i, j, null));
                int rx = tintedSprite.getColorModel().getRed(tintedSprite.getRaster().
                        getDataElements(i, j, null));
                int gx = tintedSprite.getColorModel().getGreen(tintedSprite.getRaster().
                        getDataElements(i, j, null));
                int bx = tintedSprite.getColorModel().getBlue(tintedSprite.getRaster().
                        getDataElements(i, j, null));

                rx *= color.getRed() / 255f;
                gx *= color.getGreen() / 255f;
                bx *= color.getBlue() / 255f;
                ax *= 1;

                tintedSprite.setRGB(i, j, (ax << 24) | (rx << 16) | (gx << 8) | (bx));
            }
        }
        return tintedSprite;
    }
}
