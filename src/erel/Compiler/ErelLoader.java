/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erel.Compiler;

import erel.code.ErelCompiler;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Juan Pablo - Roverin Technologics
 */
public class ErelLoader extends ErelCompiler {

    public ErelLoader(String ruta, Robot robot) throws AWTException {
        this.ruta = ruta;
        this.robot = robot;
        this.aux = new StringBuffer();
    }

    public void readFile() throws IOException {

        //
        ArrayList<String> al = super.read(ruta);

        //
        int cont = 0;
        while (cont < al.size()) {
            switch (al.get(cont)) {
                case WAIT_ACTION:
                    switch (al.get(cont - 1)) {
                        case DO_ACTION:
                            robot.delay(super.getNumber(al.get(cont + 1)));
                            robot.delay(20);

                            //
                            aux.append(" -> Tiempo de espera de ");
                            aux.append(super.getNumber(al.get(cont + 1)));
                            aux.append("\n");

                            cont++;
                            break;
                        default:
                            break;
                    }
                    break;
                case CLICK_OBJECT:
                    switch (al.get(cont - 1)) {
                        case DO_ACTION:
                            robot.mouseMove(super.getNumber(al.get(cont + 4)), super.getNumber(al.get(cont + 5)));
                            robot.mousePress(InputEvent.getMaskForButton(super.getNumber(al.get(cont + 2))));
                            robot.delay(20);

                            //
                            aux.append(" -> Mouse presionado en ");
                            aux.append(InputEvent.getMaskForButton(super.getNumber(al.get(cont + 2))));
                            aux.append("\n");

                            cont++;
                            break;
                        case STOP_ACTION:
                            robot.mouseMove(super.getNumber(al.get(cont + 4)), super.getNumber(al.get(cont + 5)));
                            robot.mouseRelease(InputEvent.getMaskForButton(super.getNumber(al.get(cont + 2))));
                            robot.delay(20);

                            //
                            aux.append(" -> Mouse liberado en");
                            aux.append(InputEvent.getMaskForButton(super.getNumber(al.get(cont + 2))));
                            aux.append("\n");

                            cont++;
                            break;
                        default:
                            break;
                    }
                    break;
                case KEY_OBJECT:
                    int keyCode = KeyEvent.getExtendedKeyCodeForChar(super.getNumber(al.get(cont + 2)));
                    if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                        throw new RuntimeException(
                                "Key code not found for character '" + al.get(cont + 2) + "'");
                    }
                    switch (al.get(cont - 1)) {
                        case DO_ACTION:

                            robot.keyPress(keyCode);

                            robot.delay(20);

                            //
                            aux.append(" -> Tecla presionada en ");
                            aux.append(KeyEvent.getKeyText(keyCode));
                            aux.append("\n");

                            cont++;
                            break;
                        case STOP_ACTION:
                            robot.keyRelease(keyCode);
                            robot.delay(20);

                            //
                            aux.append(" -> Tecla presionada en ");
                            aux.append(KeyEvent.getKeyText(keyCode));
                            aux.append("\n");

                            cont++;
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    cont++;
                    break;
            }
        }
        aux.append(" ¡ Exito ! ");
    }

    //
    private final StringBuffer aux;
    private final String ruta;
    private final Robot robot;

}
