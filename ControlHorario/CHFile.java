/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlHorario;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Miguel
 */
public class CHFile {
String name;
String name2;
String fullname;
String period;
ArrayList<Employee> employees;

    CHFile(int i, ArrayList<Employee> employees){
        this.employees=employees;
        
        Calendar cal = Calendar.getInstance();
        Date date=null;
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy MM");
        try{
            date=dateformat.parse(dateformat.format(cal.getTime()));
        } catch (ParseException ex) {
                Logger.getLogger(CHFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        //if(i==0){
        //    this.name="rosi "+dateformat.format(date)+".txt";
        //    this.name2="rosi "+dateformat.format(date)+".htm";
        //    fullname="ROSA DELIA PEREZ RAMIREZ";
        //    period=dateformat.format(date);
        //}
        //else{
        //    this.name="carmen "+dateformat.format(date)+".txt";
        //    this.name2="carmen "+dateformat.format(date)+".htm";
        //    fullname="MARIA DEL CARMEN IGLESIAS ALVAREZ";
        //    period=dateformat.format(date);
        //}
        
        this.name=employees.get(i).user+" "+dateformat.format(date)+".txt";
        this.name2=employees.get(i).user+" "+dateformat.format(date)+".htm";
        fullname=employees.get(i).fullName;
        period=dateformat.format(date);
    }
    public void saveToFile(List list){
        int i;
                FileWriter fichero = null;
	        PrintWriter pw = null;
	        try
	        {
	            fichero = new FileWriter(name);
	            pw = new PrintWriter(fichero);
	            int c;
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
                    SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
                    Ficha ficha;
                    pw.write(list.fichas.size()+"\n");
                    for(i=0;i<list.fichas.size();i++){
                        ficha=list.fichas.get(i);
                        pw.write(ficha.enfermera+" "+ficha.tipo+" "+dateformat.format(ficha.date)+" "+timeformat.format(ficha.time)+"\n");
                    }
                    
                    
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	           try {
	           if (null != fichero)
	              fichero.close();
	           } catch (Exception e2) {
	              e2.printStackTrace();
	           }
	        }
    }
    
    public void loadFromFile(List list){
            try{
                Scanner s=new Scanner(new File(name));
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
                SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
                Date date;
                Date time;
                int i, k, t, n;
                String tipo, datestr, timestr;
                n=s.nextInt();
                System.out.println(n);
                for(i=0;i<n;i++){
                    tipo=s.next();
                    
                        t=s.nextInt();
                        datestr=s.next();
                        date=dateformat.parse(datestr);
                        timestr=s.next();
                        time=timeformat.parse(timestr);
                        list.fichas.add(new Ficha(1,t,date, time));
                                  
                }
                System.out.println(list.fichas.size());
            }catch(Exception e){
                e.printStackTrace();
            }
    }
    
    void esc_casilla(PrintWriter pw, String s){
		pw.print("<td width=\"10%\" height=\"40px\" bgcolor=\"#FFFFFF\"><p class=MsoNormal align=center style=\'text-align:center\'>");
		pw.print(s);
		pw.print("</td>");
    }
    void esc_casilla_long(PrintWriter pw, String s){
                pw.print("<table width=\"100%\"border=\"3\" style=\"border-style: solid;\"  cellpadding=\"0\" cellspacing=\"0\">");
		pw.print("<tr>");	
                pw.print("<td width=\"100%\" height=\"40px\" bgcolor=\"#FFFFFF\"><p class=MsoNormal align=center style=\'text-align:center\'>");
		pw.print(" CONTROL DE JORNADA LABORAL");
		pw.print("</td>");
                pw.print("</tr>");
                pw.print("<tr>");	
                pw.print("<td width=\"100%\" height=\"40px\" bgcolor=\"#FFFFFF\"><p class=MsoNormal align=center style=\'text-align:center\'>");
		pw.print(s + "   "+ period);
		pw.print("</td>");
    }
    
    void esc_cabecera_resumen(PrintWriter pw){
		
                esc_casilla_long(pw, fullname);
                
                pw.print("<table width=\"100%\"border=\"3\" style=\"border-style: solid;\" cellpadding=\"0\" cellspacing=\"0\">");

                pw.print("<tr>");
		
		//esc_casilla(pw, "Enfermera");
		esc_casilla(pw, "Fecha");
		esc_casilla(pw, "Hora llegada");
		esc_casilla(pw, "Hora salida");
		esc_casilla(pw, "Horas trabajadas");
                esc_casilla(pw, "Acumulado");
                esc_casilla(pw, "Firma");
                pw.print("</tr>");
    }
    
    void nueva_fila(PrintWriter pw){
		pw.print("<tr>");
    }
	
    void fin_fila(PrintWriter pw){
              
		pw.print("</tr>");
    }
        
    void escribir_resumen(List list){
		FileWriter fichero = null;
	        PrintWriter pw = null;
                long work;
                int hours, minutes, seconds;
                String hs, ms, ss;
                long acum=0;
	        try
	        {
	            fichero = new FileWriter(name2);
	            pw = new PrintWriter(fichero);
	            
	            
	            int c;
	            String t,a,b;
	            
	            	esc_cabecera_resumen(pw);
	                Ficha ficha, ant=null, post=null;
                        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
                        SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
	            	for (int i = 0; i < list.fichas.size(); i++){
	            		
                                ficha=list.fichas.get(i);
                                if(i>0) ant=list.fichas.get(i-1);
                                if(i<list.fichas.size()-1) post=list.fichas.get(i+1);
                                if(ficha.tipo==1){
                                    nueva_fila(pw);
                                    //esc_casilla(pw, Integer.toString(ficha.enfermera));
                                    esc_casilla(pw, dateformat.format(ficha.date));
                                    esc_casilla(pw, timeformat.format(ficha.time));
                                    if(post.tipo==1){
                                        esc_casilla(pw, "");
                                        esc_casilla(pw, "");
                                        esc_casilla(pw, "");
                                        esc_casilla(pw, "");
                                    }
                                }else{
                                    if(i==0 || ant.tipo==2){
                                        //esc_casilla(pw, dateformat.format(ficha.enfermera));
                                        esc_casilla(pw, dateformat.format(ficha.date));
                                        esc_casilla(pw, "");
                                    }
                                    esc_casilla(pw, timeformat.format(ficha.time));
                                    if(i>0 && ant.tipo==1){
                                    work=(ficha.time.getTime()-ant.time.getTime())/1000;
                                    acum+=work;
                                    hours=(int)(work/60/60);
                                    minutes=(int)((work-hours*60*60)/60);
                                    seconds=(int)(work-hours*60*60-minutes*60);
                                    hs=Integer.toString(hours);
                                    ms=Integer.toString(minutes); if(minutes<10) ms="0"+ms;
                                    ss=Integer.toString(seconds); if(seconds<10) ss="0"+ss; 
                                    esc_casilla(pw, hs+":"+ms+":"+ss);
                                    
                                    hours=(int)(acum/60/60);
                                    minutes=(int)((acum-hours*60*60)/60);
                                    seconds=(int)(acum-hours*60*60-minutes*60);
                                    hs=Integer.toString(hours);
                                    ms=Integer.toString(minutes); if(minutes<10) ms="0"+ms;
                                    ss=Integer.toString(seconds); if(seconds<10) ss="0"+ss; 
                                    esc_casilla(pw, hs+":"+ms+":"+ss);
                                    }else{
                                        esc_casilla(pw, "");
                                        esc_casilla(pw, "");
                                    }
                                    esc_casilla(pw, "");
                                    fin_fila(pw);
                                }
                                    
	            	}
	            
	            
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	           try {
	           if (null != fichero)
	              fichero.close();
	           } catch (Exception e2) {
	              e2.printStackTrace();
	           }
	        }

	}

    
}
