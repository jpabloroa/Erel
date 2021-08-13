/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erel.Compiler;

import erel.code.ErelCompiler;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Juan Pablo - Roverin Technologics
 */
public class ErelWriter extends ErelCompiler implements MouseListener, KeyListener, Runnable {

    public ErelWriter(String Directory) {
        
        //
        this.ruta = Directory;
        this.aux = new StringBuffer();
    }

    public void recordOneLine(String Command) {
        
        //
        if (isRecording) {
            addTimeLineEvent(stopTimer());
            this.aux.append(Command);
            startTimer();
        }
    }

    public void writeFile() throws IOException {

        //
        this.isRecording = false;
        super.write(ruta, aux);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
            addClickEvent(DO_ACTION, e.getButton(), e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
            addClickEvent(STOP_ACTION, e.getButton(), e.getX(), e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
            addKeyEvent(DO_ACTION, e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
            addKeyEvent(STOP_ACTION, e.getKeyChar());
    }

    /**
     *
     */
    private void startTimer() {
        this.isRecording = true;
    }

    /**
     *
     * @return
     */
    private int stopTimer() {
        this.isRecording = false;
        int a = this.timer;
        this.timer = 0;
        return a;
    }

    /**
     *
     * @param action
     * @param key
     * @param x
     * @param y
     */
    public void addClickEvent(String action, int key, int x, int y) {
        if (isRecording) {
            addTimeLineEvent(stopTimer());
            aux.append(action);
            aux.append(" ");
            aux.append(CLICK_OBJECT);
            aux.append(" ");
            aux.append(PRESS_ACTION);
            aux.append(" ");
            aux.append("-");
            aux.append(key);
            aux.append(" ");
            aux.append(AT_LOCATION);
            aux.append(" ");
            aux.append("-");
            aux.append(x);
            aux.append(" ");
            aux.append("-");
            aux.append(y);
            aux.append(";");
            startTimer();
        }
    }

    /**
     *
     * @param action
     * @param key
     */
    public void addKeyEvent(String action, int key) {
        if (isRecording) {
            addTimeLineEvent(stopTimer());
            aux.append(action);
            aux.append(" ");
            aux.append(KEY_OBJECT);
            aux.append(" ");
            aux.append(PRESS_ACTION);
            aux.append(" ");
            aux.append("-");
            aux.append(key);
            aux.append(";");
            startTimer();
        }
    }

    /**
     *
     * @param time
     */
    private void addTimeLineEvent(int time) {
        aux.append(DO_ACTION);
        aux.append(" ");
        aux.append(WAIT_ACTION);
        aux.append(" ");
        aux.append("-");
        aux.append(time);
        aux.append(";");
    }

    //
    public final StringBuffer aux;
    
    //
    private final String ruta;
    private boolean isRecording = false;
    private int timer = 0;

    //
    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                if (isRecording) {
                    timer += 100;
                }
            } catch (InterruptedException ex) {
            }
        }
    }
}
