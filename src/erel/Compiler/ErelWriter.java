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
        this.ruta = Directory;
        this.aux = new StringBuffer();

    }

    public void recordOneLine(String Command) {
        this.aux.append(Command);
    }

    public void record() {
        this.isRecording = true;
    }

    public void writeFile() throws IOException {

        //
        this.isRecording = false;

        //
        super.write(ruta, aux);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (isRecording) {

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isRecording) {
            addTimeLineEvent(stopTimer());
            addClickEvent(DO_ACTION, e.getButton(), e.getX(), e.getY());
            startTimer();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isRecording) {
            addTimeLineEvent(stopTimer());
            addClickEvent(STOP_ACTION, e.getButton(), e.getX(), e.getY());
            startTimer();
        }
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
        if (isRecording) {
            addTimeLineEvent(stopTimer());
            addKeyEvent(DO_ACTION, e.getKeyCode());
            startTimer();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (isRecording) {
            if (isRecording) {
                addTimeLineEvent(stopTimer());
                addKeyEvent(STOP_ACTION, e.getKeyCode());
                startTimer();
            }
        }
    }

    private void startTimer() {
        this.isRecording = true;
    }

    private int stopTimer() {
        this.isRecording = false;
        int a = this.timer;
        this.timer = 0;
        return a;
    }

    private void addClickEvent(String action, int key, int x, int y) {
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
    }

    private void addKeyEvent(String action, int key) {
        aux.append(action);
        aux.append(" ");
        aux.append(KEY_OBJECT);
        aux.append(" ");
        aux.append(PRESS_ACTION);
        aux.append(" ");
        aux.append("-");
        aux.append(key);
        aux.append(";");
    }

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
    private final StringBuffer aux;
    private final String ruta;
    private boolean isRecording = false;
    private int timer = 0;
//    private int 

    //
    private PointerInfo a;
    private Point b;
    private double x, y;

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

class Timer {

}
