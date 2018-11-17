package Soporte;

import Dimension.DimPerfilGrado;
import Dimension.Fact;
import Soporte.Reportable;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.io.*;
import java.util.Set;

public class EscritorReportes {

    private static HashMap<String,String> headers=new HashMap<>();

    private static HashMap<String,String> tiposAtributos=new HashMap<>();


    public EscritorReportes() throws Exception
    {
        BufferedReader br = new BufferedReader(new FileReader(new File("./data/classHeaders.csv")));
        String linea=br.readLine();
        while(linea!=null)
        {
            String[] data=linea.split(";;;");
            headers.put(data[0],data[1]);
            linea=br.readLine();
        }
        br.close();
        br = new BufferedReader(new FileReader(new File("./data/cargaDatos.csv")));
        linea=br.readLine();
        while(linea!=null)
        {
            System.out.println(linea);
            String[] data=linea.split(";;;");
            tiposAtributos.put(data[0],data[1]);
            linea=br.readLine();
        }
        br.close();

    }

    public void escribirReporteValoresCsv(String className, Collection<Reportable> list) throws Exception
    {
        String header=headers.get(className);
        File f = new File("./data/reportes/"+className+".csv");
        PrintWriter pw = new PrintWriter(f);
        pw.println(header);
        HashMap<String,Boolean> hash = new HashMap<>();
        for(Reportable r:list)
        {
        	if(!hash.containsKey(r.getClassHeaders()))
        	{
        		pw.println(r.getClassHeaders());
        		hash.put(r.getClassHeaders(), true);
        	}
        }
            
        pw.close();
    }

    public void generarScriptsSQL() throws Exception
    {
        Field[] fields=null;
        String[] dimesniones=new String[]{"DimEgresado","DimEvento","DimFechaInicio","DimFechaFin",
                                "DimPerfilGrado","DimPerfilPersonal","DimPerfilPracticante",
                                "DimUnidadAcademica"};
        for(String s:dimesniones)
        {
            PrintWriter pw= new PrintWriter("./data/sql/"+s+".sql");
            Class c=Class.forName("Dimension."+s);
            fields=c.getDeclaredFields();
            pw.println("DROP TABLE IF EXISTS "+s+";");
            pw.println("CREATE TABLE IF NOT EXISTS "+s+" (");
            String fks="";
            for(int i=0;i<fields.length;i++)
            {
                String comma=",";
                if(i==fields.length-1)
                    comma="";
                System.out.println(fields[i].getName());
                String accion=tiposAtributos.get(fields[i].getName());
                // Se asume para un FK la notacion, llave, FOREIGN KEY, Dimension de referencia
                System.out.println(fields[i].getName());
                String[] data=accion.split(";");
                if(accion.contains("FK"))
                {
                    System.out.println("ACCION "+fields[i].getName()+" "+accion);
                    accion=data[0];
                    fks+="FOREIGN KEY ("+data[0]+") References "+data[2]+comma+"\n";
                }
                else
                {
                    accion="";
                    for(String d: data)
                        accion+=d+" ";
                }
                if(!fks.equals(""))
                    comma=",";
                pw.println(fields[i].getName()+" "+accion+comma);
            }
            pw.println(fks);
            pw.println(");");
            pw.close();
        }
        String s="HechoInscribirseAEvento";
        PrintWriter pw= new PrintWriter("./data/sql/"+s+".sql");
        Class c= Fact.class;
        fields=c.getDeclaredFields();
        pw.println("CREATE TABLE IF NOT EXISTS "+s+" (");
        String fks="";
        for(int i=0;i<fields.length;i++)
        {
            String comma=",";
            if(i==fields.length-1)
                comma="";
            System.out.println(fields[i].getName());
            String accion=tiposAtributos.get(fields[i].getName());
            // Se asume para un FK la notacion, llave, FOREIGN KEY, Dimension de referencia
            System.out.println("The name is "+fields[i].getName());
            String[] data=accion.split(";");
            if(accion.contains("FK"))
            {
                System.out.println("ACCION "+fields[i].getName()+" "+accion);
                accion=data[0];
                fks+="FOREIGN KEY ("+fields[i].getName()+") References "+data[2]+comma+"\n";
            }
            else
            {
                accion="";
                for(String d: data)
                    accion+=d+" ";
            }
            if(!fks.equals(""))
                comma=",";
            pw.println(fields[i].getName()+" "+accion+comma);
        }
        pw.println(fks);
        pw.println(");");
        pw.close();
    }

    public static void main (String[] args) throws Exception
    {
        EscritorReportes er= new EscritorReportes();
        er.generarScriptsSQL();
    }



}
