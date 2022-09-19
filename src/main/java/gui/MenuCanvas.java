package gui;

import gui.input.MouseInput;

import javax.swing.*;
import java.awt.*;

/**
 * Created: 19.09.2022
 *
 * @author Tobias Frischmann
 */
public class MenuCanvas extends JPanel {
    public MenuCanvas(Window window) {
        setPreferredSize(new Dimension(window.getWidth(), window.getHeight()));

        JComboBox<Integer> playerCountList = new JComboBox<>(new Integer[]{2, 3, 4});
        add(playerCountList);

        JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(e -> {
            Object playerCount = playerCountList.getSelectedItem();

            if (playerCount != null) {
                window.startGame((int) playerCount);
            }
        });

        add(startGameButton);
    }
}
