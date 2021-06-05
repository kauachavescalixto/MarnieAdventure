package marnie.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


public class tempo {
    public static void main(String[] args) {
    	
    	//Podemos obter a data/hora atual utilizando a classe LocaleDateTime com o método now(). Ele retorna a data e hora no formato YYYY-MM-DD-hh-mm-ss.zzz que se parece com 2020-09-22T14:39:33.889798.
    	//Para torná-lo mais facilmente legível, utilizaremos DateTimeFormatter.ofPattern(pattern) que leva um padrão de data e hora que podemos personalizar de acordo com nossas necessidades.

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println("yyyy/MM/dd HH:mm:ss-> "+dtf.format(LocalDateTime.now()));

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");
        System.out.println("yy/MM/dd HH:mm:ss-> "+dtf2.format(LocalDateTime.now()));

        DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyy/MMMM/dd HH:mm:ss");
        System.out.println("yyyy/MMMM/dd HH:mm:ss-> "+dtf3.format(LocalDateTime.now()));

        DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        System.out.println("yyyy/MM/dd HH:mm-> "+dtf4.format(LocalDateTime.now()));

        DateTimeFormatter dtf5 = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm");
        System.out.println("yyyy/MM/dd hh:mm:ss-> "+dtf5.format(LocalDateTime.now()));
        
        System.out.println("\n");
        System.out.println("\n");
        
        //ZonedDateTime.now() para obter a data e hora atuais com fuso horário em Java
        //O fuso horário é uma parte importante da data e da hora. Podemos obter a data e a hora com o fuso horário utilizando ZonedDateTime.now().
        //Isso não é tudo, pois podemos obter a hora de cada fuso horário passando os argumentos ZoneId dentro de ZonedDateTime.now().
        
        System.out.println(ZonedDateTime.now());
        
        System.out.println("Get current timezone "+ZonedDateTime.now().getZone());
         
         System.out.println("Get time of different timezone: "+ZonedDateTime.now(ZoneId.of("America/New_York")));

         
         System.out.println("\n");
         System.out.println("\n");
         
         //Outra maneira de obter a data/hora atual é utilizar Calendar.getInstance() devolve um objeto Calendar com a data e hora atual que pode ser convertido em forma de Date/Time utilizando o método getTime().
         
         String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

         System.out.println(timeStamp);
         
         System.out.println(System.currentTimeMillis());
     }
 

    }
