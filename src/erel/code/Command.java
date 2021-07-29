/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erel.code;

/**
 *
 * @author Juan Pablo - Roverin Technologics
 */
public interface Command {
    /**
     * 
     */
    public final String HEADER_MAIN_TOGGLE = "toggle action main";
    
    //
    public final String INFO_TOGGLE = "toggle"; 
    public final String MAIN_TOGGLE = "main";
    
    /**
     * 
     */
    public final String DO_ACTION = "do";
    public final String STOP_ACTION = "stop";
    public final String WAIT_ACTION = "wait";
    public final String PRESS_ACTION = "press";
    public final String AT_LOCATION = "at";
    
    /**
     * 
     */
    public final String CLICK_OBJECT = "click";
    public final int RIGHT_BUTTON = 1;
    public final int LEFT_BUTTON = 2;
    
    /**
     * 
     */
    public final String KEY_OBJECT = "key";
}
