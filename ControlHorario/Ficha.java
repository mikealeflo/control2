/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlHorario;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author Miguel
 */
public class Ficha {
Calendar cal = Calendar.getInstance();
Date date;
Date time;
int enfermera;
int tipo;

Ficha(int enfermera, int tipo, Date date, Date time){
    this.tipo=tipo;
    this.enfermera=enfermera;
    this.date=date;
    this.time=time;
}
}
