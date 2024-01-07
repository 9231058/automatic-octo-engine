/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageapplet;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jnlp.FileContents;
import javax.jnlp.FileOpenService;
import javax.jnlp.ServiceManager;
import javax.jnlp.UnavailableServiceException;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Parham Alvani
 */
public class ImageApplet extends JApplet {

    private ImageIcon image;
    private JPanel scaleJPanel;
    private JLabel percentJLabel;
    private JTextField scaleInputJTextField;
    private JButton scaleChangeJButton;
    private double scaleValue = 1.0;

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    @Override
    public void init() {
        // TODO start asynchronous download of heavy resources
        scaleJPanel = new JPanel();
        percentJLabel = new JLabel("scale percent");
        scaleInputJTextField = new JTextField("100");
        scaleChangeJButton = new JButton("Set scale");
        scaleJPanel.add(percentJLabel);
        scaleJPanel.add(scaleInputJTextField);
        scaleJPanel.add(scaleChangeJButton);
        add(scaleJPanel, BorderLayout.NORTH);
        scaleChangeJButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                scaleValue = Double.parseDouble(scaleInputJTextField.getText()) / 100;
                repaint();
            }
        });
        FileOpenService fileOpenService;
        try {
            fileOpenService = (FileOpenService) ServiceManager.lookup("javax.jnlp.FileOpenService");
            FileContents contents = fileOpenService.openFileDialog(null, null);
            byte[] imageData = new byte[(int) contents.getLength()];
            contents.getInputStream().read(imageData);
            image = new ImageIcon(imageData);
            add(new DrawJPanel(), BorderLayout.CENTER);
        } catch (UnavailableServiceException | IOException exception) {
            Logger.getLogger(ImageApplet.class.getName()).log(Level.SEVERE, null, exception);
        }

    }

    private class DrawJPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
            double spareWidth = getWidth() - scaleValue * image.getIconWidth();
            double spareHeight = getHeight() - scaleValue * image.getIconHeight();
            g.drawImage(image.getImage(), (int) spareWidth / 2, (int) spareHeight / 2, (int) (image.getIconWidth() * scaleValue), (int) (image.getIconHeight() * scaleValue), this);
        }

    }
    // TODO overwrite start(), stop() and destroy() methods
}
