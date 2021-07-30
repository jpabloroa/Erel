/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erel.code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Juan Pablo - Roverin Technologics
 */
public class ErelCompiler implements Command {

    public void write(String Directory, StringBuffer Command) throws IOException {

        //
        if (Directory.substring(Directory.length() - 4).equalsIgnoreCase(".erel")) {
            lector = new FileReader(new File(Directory));
        } else {
            throw new FileNotFoundException("No se encuentra el archivo .EREL");
        }

        //
        writeBuffer = new BufferedWriter(raster);

        //
        StringBuffer respuesta = new StringBuffer();

        //
        respuesta.append(HEADER_MAIN_TOGGLE);
        respuesta.append("{");

        //
        respuesta.append(Command);

        respuesta.append("}");

        //
        writeBuffer.write(respuesta.toString());

        //
        writeBuffer.close();
        raster.close();
    }

    public ArrayList<String> read(String Directory) throws FileNotFoundException, IOException {

        //
        if (Directory.substring(Directory.length() - 4).equalsIgnoreCase(".erel")) {
            lector = new FileReader(new File(Directory));
        } else {
            throw new FileNotFoundException("No se encuentra el archivo .EREL");
        }

        //
        readBuffer = new BufferedReader(lector);

        //
        StringBuffer res = new StringBuffer();
        while (readBuffer.ready()) {
            res.append(String.valueOf((char) readBuffer.read()));
        }

        //
        respuesta = res.toString();

        //
        String aux = "";
        int cont1 = 0;
        int largo = respuesta.length();
        int step = 0;
        //
        steps = new ArrayList<>();
        steps.add("*");

        //
        while (cont1 < largo) {

            if (getCharStet(cont1, " ") || getCharStet(cont1, "\n") || getCharStet(cont1, "\r") || getCharStet(cont1, "{") || getCharStet(cont1, "}") || getCharStet(cont1, ";")) {
                if (!aux.equals("") && !aux.equals(" ") && !aux.equals("\n") && !aux.equals("\r")) {
                    steps.add(aux.strip());
                    aux = "";
                }
                if (!getCharStet(cont1, "") && !getCharStet(cont1, " ") && !getCharStet(cont1, "\n") && !getCharStet(cont1, "\r")) {
                    steps.add(getChar(cont1));
                    if (getCharStet(cont1, ";")) {
                        step++;
                    }
                }
                cont1++;
            } else {
                aux += getChar(cont1);
                cont1++;
            }
        }

        steps.set(0, String.valueOf(step));

        //
        readBuffer.close();
        lector.close();
        return steps;
    }

    //
    private String getChar(int Index) {
        return respuesta.substring(Index, Index + 1);
    }

    //
    private boolean getCharStet(int Index, String Character) {
        return respuesta.substring(Index, Index + 1).equals(Character);
    }

    //
    private StringBuffer response;
    private ArrayList<String> steps;

    //
    private String respuesta;

    //
    private FileWriter raster;
    private FileReader lector;
    private BufferedWriter writeBuffer;
    private BufferedReader readBuffer;

    public int getNumber(String string) {
        return Integer.parseInt(string.substring(1));
    }

}
