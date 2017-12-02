/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soundcapturing;

import java.io.File;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author Hassa
 */
public class newSoundRecorderClass {
    long recordTime ;
    File wavFile;
    AudioFileFormat.Type fileType;
    TargetDataLine line;
    
    public AudioFormat getAudioFormat(){
    int samplerate = 16000;
    int sampleSizeInBits =8;
    int channels =2;
    boolean singed = true;
    boolean bigEndian = true;
    AudioFormat format = new  AudioFormat(samplerate ,sampleSizeInBits ,channels,singed,bigEndian);      
    return format;
    }
    public newSoundRecorderClass(long dur , String name){
    recordTime = dur;
    wavFile= new File(name);
    fileType = AudioFileFormat.Type.WAVE;
    
    
    }
    public void start(){
    try{
    AudioFormat format = getAudioFormat();
    DataLine.Info info = new DataLine.Info(TargetDataLine.class,format);
        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("not supported");
            System.exit(0);
        }
    line = (TargetDataLine)AudioSystem.getLine(info);
    line.open(format);
    line.start();
    
        AudioInputStream ais = new AudioInputStream(line);
        try{
        AudioSystem.write(ais,fileType,wavFile);
        }catch(Exception e){
        System.out.println(e.getMessage());
        }
    }
    catch(LineUnavailableException e){
    System.out.println(e.getMessage());
    }
    
    }
    
    public void stop(){
    line.stop();
    line.close();
    System.out.println("finished");
    }
}
