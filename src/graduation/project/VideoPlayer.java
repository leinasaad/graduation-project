/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graduation.project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import org.gstreamer.Gst;
import org.gstreamer.State;
import org.gstreamer.elements.PlayBin;
import org.gstreamer.swing.VideoComponent;

/**
 *
 * @author leina
 */

public class VideoPlayer {
    public static void main(String[] args) {
       // args = Gst.init("VideoPlayer", args);
        File f=new File(".//emotions Videos/anger.mp4");
        final PlayBin playbin = new PlayBin("VideoPlayer");
        //playbin.setInputFile(new File(args[0]));
        playbin.setInputFile(f);
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                VideoComponent videoComponent = new VideoComponent();
                playbin.setVideoSink(videoComponent.getElement());

                JFrame frame = new JFrame("VideoPlayer");
                frame.getContentPane().add(videoComponent, BorderLayout.CENTER);
                frame.setPreferredSize(new Dimension(640, 480));
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                playbin.setState(State.PLAYING);
            }
        });
        Gst.main();
        playbin.setState(State.NULL);
    }
}