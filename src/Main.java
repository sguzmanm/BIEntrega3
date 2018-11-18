import Dimension.*;
import Soporte.EscritorReportes;
import Soporte.MiniDimensiones;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.HashMap;

public class Main {

    //Ids para llevar la cuenta
    static int idEvento=1;
    static int idFecha=1;
    static int idUnidad=1;
    static int idPerfilPractica=1;
    static int idPerfilGrado=1;
    static int idPerfilPersonal=1;
    static int idEgresado=1;
    //Formato de fechas
    public static final SimpleDateFormat FORMATO_FECHA=new SimpleDateFormat("dd/MM/yyyy");

    //Hash maps de todo
    static HashMap<String,String> equiv = new HashMap<>();

    static HashMap<String,String> dic = new HashMap<>();


    static HashMap<String, DimFechaInicio> fechas=new HashMap<>();
    static HashMap<String, DimFechaInicio> fechas2=new HashMap<>();

    static HashMap<String, DimEvento> eventos=new HashMap<>();

    static HashMap<String, DimUnidadAcademica> unidadesAcademicas=new HashMap<>();

    static HashMap<String, DimEgresado> egresados=new HashMap<>();

    static HashMap<String, DimPerfilGrado> perfilesGrados=new HashMap<>();

    static HashMap<String,DimPerfilPersonal> perfilesPersonales=new HashMap<>();

    static HashMap<String,DimPerfilPracticante> perfilesPracticantese=new HashMap<>();

    static HashMap<String,HashMap<String,Fact>> theLinker= new HashMap<>();

    private static MiniDimensiones miniDimensiones= new MiniDimensiones();

    //Relaci�n con la clase de tabla agregada
    private static AggregatedTableManager agregatedTableManager=new AggregatedTableManager();
    
    //Convertir el semestre a una fecha
    public static Date convertirSemestre (String semestre, boolean inicio) throws Exception
    {
        String[] mes=new String[]{"01","06","08"};
        if(!inicio)
            mes=new String[]{"06","08","12"};
        String[] data=semestre.split("-");
        if(Integer.parseInt(data[1])>19)
            return FORMATO_FECHA.parse("01/"+mes[0]+"/" + data[0]);
        else if (Integer.parseInt(data[1])>10)
            return FORMATO_FECHA.parse("01/"+mes[1]+"/"+data[0]);
        return FORMATO_FECHA.parse("01/"+mes[2]+"/" + data[0]);
    }
    //Crear el diccionario
    public static void crearDiccionario() throws Exception{
        BufferedReader br = new  BufferedReader(
                new InputStreamReader(new FileInputStream("./data/diccionario.csv"), "UTF-8"));

        String linea=br.readLine();
        while(linea!=null)
        {
            String[] data=linea.split(";");
            dic.put(data[0],data[1]);
            linea=br.readLine();
        }
    }

    public static void lecturaEquivalencias() throws Exception
    {
        BufferedReader br = new BufferedReader(new FileReader(new File("./data/llaves.csv")));
        String linea=br.readLine();
        linea=br.readLine();
        while(linea!=null)
        {
            String[] data=linea.split(",");
            equiv.put(data[1],data[0]);
            linea=br.readLine();
        }
        br.close();
    }
    //llave anónima	Evento: Código Evento	Categoria
    // Nombre	Tipo evento	Fecha inicio	Asistió	Departamento
    // Facultad	Fecha fin
    public static void lecturaEventos() throws Exception {
        BufferedReader br = new  BufferedReader(
                new InputStreamReader(new FileInputStream("./data/P_eventos_total.csv"), "UTF-8"));
        String linea=br.readLine();
        linea=br.readLine();

        String[] tipos= new String[]{"ACAD","DEPO","ESPE","CULT","SOCI","OTRO"};
        while(linea!=null) {
            linea=linea.replaceAll(";;;;;;;;;;;;","");
            linea=linea.replaceAll(";;","; ;");
            String[] temp = linea.split(";");
            int indice=2;
            for(int i=3;i<temp.length;i++)
            {
                for(String t:tipos)
                {
                    if(t.equals(temp[i]))
                    {
                        indice=i;
                        break;
                    }
                }
            }
            String[] data=new String[temp.length-(indice-4)];
            data[3]=temp[3];
            for(int i=4;i<indice;i++)
            {
                data[3]+=temp[i];
            }
            data[0]=temp[0];
            data[1]=temp[1];
            data[2]=temp[2];
            for(int i=indice;i<temp.length;i++)
            {
                data[i-(indice-4)]=temp[i];
            }
            //FECHA
            DimFechaInicio fechaInicio = new DimFechaInicio();
            DimFechaInicio fechaFin = new DimFechaInicio();
            try
            {
                String[] parser=data[5].split("/");
                fechaInicio.setDiaEvento(Integer.parseInt(parser[1]));
                fechaInicio.setMesEvento(parser[0]);
                fechaInicio.setAnhoEvento(Integer.parseInt(parser[2]));
                int q = Integer.parseInt(parser[0]) / 3 + 1;
                if (q == 5)
                    q = 4;
                fechaInicio.setCuatrimestreEvento("Q" + q);
                int s = Integer.parseInt(parser[0]) / 6 + 1;
                if (s == 3)
                    s = 2;
                fechaInicio.setSemestreEvento("S" + s);

                parser=data[9].split("/");
                fechaFin.setDiaEvento(Integer.parseInt(parser[1]));
                fechaFin.setMesEvento(parser[0]);
                fechaFin.setAnhoEvento(Integer.parseInt(parser[2]));
                q = Integer.parseInt(parser[0]) / 3 + 1;
                if (q == 5)
                    q = 4;
                fechaFin.setCuatrimestreEvento("Q" + q);
                s = Integer.parseInt(parser[0]) / 6 + 1;
                if (s == 3)
                    s = 2;
                fechaFin.setSemestreEvento("S" + s);
            }
            catch(Exception e)
            {
                linea=br.readLine();
                continue;
            }
            int idFechaInicio = 0;
            if(fechas.get(fechaInicio.toString())==null)
            {
                fechaInicio.setIdFecha(idFecha);
                fechas.put(fechaInicio.toString(),fechaInicio);
                fechas2.put(fechaInicio.toString(),fechaInicio);
                idFechaInicio=idFecha++;
            }
            else
                idFechaInicio=fechas.get(fechaInicio.toString()).getIdFecha();
            int idFechaFin = 0;
            if(fechas.get(fechaFin.toString())==null)
            {
                fechaFin.setIdFecha(idFecha);
                fechas.put(fechaFin.toString(),fechaFin);
                fechas2.put(fechaFin.toString(),fechaFin);
                idFechaFin=idFecha++;
            }
            else
                idFechaFin=fechas.get(fechaFin.toString()).getIdFecha();
            //EVENTO
            DimEvento evento= new DimEvento();
            evento.setCodigoEvento(data[1]);
            evento.setCategoriaEvento(data[2]);
            evento.setTipoEvento(data[4]);
            evento.setNombreEvento(data[3]);
            int idEv=0;
            if (eventos.get(evento.toString())==null)
            {
                evento.setIdEvento(idEvento++);
                eventos.put(evento.toString(),evento);
                idEv=idEvento;
            }
            else
                idEv=eventos.get(evento.toString()).getIdEvento();
            //UNIDAD ACADEMICA
            DimUnidadAcademica unidad= new DimUnidadAcademica();
            unidad.setDepartamento(data[7]);
            unidad.setFacultad(data[8]);
            unidad.setAreaFacultad(dic.get(data[8]));
            int idUni=0;
            if (unidadesAcademicas.get(unidad.toString())==null)
            {
                unidad.setIdUnidadAcademica(idUnidad++);
                unidadesAcademicas.put(unidad.toString(),unidad);
                idUni=idUnidad;
            }
            else
                idUni=unidadesAcademicas.get(unidad.toString()).getIdUnidadAcademica();
            Fact fact = new Fact();
            fact.setAsistencia(Integer.parseInt(data[6]));
            fact.setInscripcion(1);
            fact.setIdFechaInicio(idFechaInicio);
            fact.setIdFechaFin(idFechaFin);
            fact.setIdEvento(idEv);
            fact.setIdUnidadAcademica(idUni);
            fact.setIdEgresado(-1);
            if(theLinker.get(data[0])==null)
                theLinker.put(data[0],new HashMap<String,Fact>());
            theLinker.get(data[0]).put(fact.toString(),fact);
            linea=br.readLine();
        }
        br.close();

    }

    public static void lecturaCTP() throws Exception {

        //Cargar del CTP

        BufferedReader br = new BufferedReader(new FileReader(new File("./data/Base_Evaluaciones.csv")));
        String linea = br.readLine();
        linea = br.readLine();
        String codigoCTP = "CTP-000";
        int contCtp = 1;
        String[] eventosCtp = new String[]{"Taller Manejo Tiempo", "Taller Comunicación",
                "Taller Situaciones Difíciles", "Sesión Individual"};
        while(linea!=null)
        {
            try
            {
                String[] data=linea.split(";");
                //FECHA
                //PerfilPracticante
                //Periodo;ID Estudiante;Comunicaci�n Verbal40;
                // Autonom�a e Iniciativa44;Compromiso �tico45;
                // Disposici�n al Aprendizaje46;Taller Manejo Tiempo (Opcional);
                // Taller Comunicaci�n (Opcional);
                // Taller Situaciones Dif�ciles (Opcional);
                // Sesi�n Individual (Opcional)
                String fecha=data[0].substring(0,data[0].length()-2)+"-"+data[0].substring(data[0].length()-2);
                DimPerfilPracticante perfilPracticante= new DimPerfilPracticante();
                perfilPracticante.setAutonomiaEIniciativa(data[3]);
                perfilPracticante.setComunicacionVerbal(data[2]);
                perfilPracticante.setCompromisoEtico(data[4]);
                perfilPracticante.setDisposicionAlAprendizaje(data[5]);
                perfilPracticante.setSemestrePractica(fecha);
                int idP=0;
                if(perfilesPracticantese.get(perfilPracticante.toString())==null)
                {
                    idP=idPerfilPractica++;
                    perfilPracticante.setIdPerfilPractica(idP);
                    perfilesPracticantese.put(perfilPracticante.toString(),perfilPracticante);

                }
                String llaveVice=equiv.get(data[1]);
                miniDimensiones.getEgresadoPracticante().put(llaveVice,perfilPracticante.toString());
                //UNIDAD ACADEMICA
                DimUnidadAcademica unidad= new DimUnidadAcademica();
                unidad.setDepartamento("CTP");
                unidad.setFacultad("Centro de Trayectoria Profesional (CTP)");
                unidad.setAreaFacultad("Profesional");
                int idUni=0;
                if (unidadesAcademicas.get(unidad.toString())==null)
                {
                    unidad.setIdUnidadAcademica(idUnidad++);
                    unidadesAcademicas.put(unidad.toString(),unidad);
                    idUni=idUnidad;
                }
                else
                    idUni=unidadesAcademicas.get(unidad.toString()).getIdUnidadAcademica();
                DimFechaInicio fechaInicio = new DimFechaInicio();
                DimFechaInicio fechaFin = new DimFechaInicio();
                try
                {
                    String[] parser=FORMATO_FECHA.format(convertirSemestre(fecha,true)).split("/");
                    fechaInicio.setDiaEvento(Integer.parseInt(parser[0]));
                    fechaInicio.setMesEvento(parser[1]);
                    fechaInicio.setAnhoEvento(Integer.parseInt(parser[2]));
                    int q = Integer.parseInt(parser[1]) / 3 + 1;
                    if (q == 5)
                        q = 4;
                    fechaInicio.setCuatrimestreEvento("Q" + q);
                    int s = Integer.parseInt(parser[1]) / 6 + 1;
                    if (s == 3)
                        s = 2;
                    fechaInicio.setSemestreEvento("S" + s);

                    parser=FORMATO_FECHA.format(convertirSemestre(fecha,false)).split("/");
                    fechaFin.setDiaEvento(Integer.parseInt(parser[0]));
                    fechaFin.setMesEvento(parser[1]);
                    fechaFin.setAnhoEvento(Integer.parseInt(parser[2]));
                    q = Integer.parseInt(parser[1]) / 3 + 1;
                    if (q == 5)
                        q = 4;
                    fechaFin.setCuatrimestreEvento("Q" + q);
                    s = Integer.parseInt(parser[1]) / 6 + 1;
                    if (s == 3)
                        s = 2;
                    fechaFin.setSemestreEvento("S" + s);
                }
                catch(Exception e)
                {
                    linea=br.readLine();
                    continue;
                }
                int idFechaInicio = 0;
                if(fechas.get(fechaInicio.toString())==null)
                {
                    fechaInicio.setIdFecha(idFecha);
                    fechas.put(fechaInicio.toString(),fechaInicio);
                    fechas2.put(fechaInicio.toString(),fechaInicio);
                    idFechaInicio=idFecha++;
                }
                else
                    idFechaInicio=fechas.get(fechaInicio.toString()).getIdFecha();
                int idFechaFin = 0;
                if(fechas.get(fechaFin.toString())==null)
                {
                    fechaFin.setIdFecha(idFecha);
                    fechas.put(fechaFin.toString(),fechaFin);
                    fechas2.put(fechaFin.toString(),fechaInicio);
                    idFechaFin=idFecha++;
                }
                else
                    idFechaFin=fechas.get(fechaFin.toString()).getIdFecha();
                //EVENTO
                for(int i=6;i<data.length;i++) {
                    //EVENTO
                    DimEvento evento = new DimEvento();
                    evento.setCodigoEvento(codigoCTP + data[0]);
                    evento.setCategoriaEvento("Práctica Profesional");
                    evento.setTipoEvento("CTP");
                    evento.setNombreEvento(eventosCtp[i - 6]);
                    int idEv = 0;
                    if (eventos.get(evento.toString()) == null) {
                        evento.setIdEvento(idEvento++);
                        eventos.put(evento.toString(), evento);
                        idEv = idEvento;
                    } else
                        idEv = eventos.get(evento.toString()).getIdEvento();
                    Fact fact = new Fact();
                    fact.setIdEvento(idEv);
                    fact.setIdFechaFin(idFechaFin);
                    fact.setIdFechaInicio(idFechaInicio);
                    if (data[i].trim().equals("Asiste"))
                        fact.setAsistencia(1);
                    else
                        fact.setAsistencia(0);
                    fact.setInscripcion(1);
                    fact.setIdUnidadAcademica(idUni);
                    HashMap<String, Fact> hash = theLinker.get(llaveVice);
                    if (hash == null) {
                        hash = new HashMap<>();
                        theLinker.put(llaveVice, hash);
                    }
                    hash.put(fact.toString(), fact);
                    linea = br.readLine();
                }
            }
            catch(Exception e)
            {
                linea=br.readLine();
               continue;
            }


        }
        br.close();

    }

    public static void lecturaGradosOtorgados() throws Exception
    {
        BufferedReader br = new  BufferedReader(
                new InputStreamReader(new FileInputStream("./data/P_grados_total.csv"), "UTF-8"));
        String linea=br.readLine();

        while(linea!=null)
        {
            String[] data=linea.split(";");
            DimPerfilGrado perfilGrado= new DimPerfilGrado();
            perfilGrado.setGradoEgresado(data[1]);
            perfilGrado.setNivelGrado(data[2]);
            perfilGrado.setSemestreGrado(data[4]);
            int idGrado=0;
            //Cuidado porque no se tiene el 0 o el 1 de Becado
            if(perfilesGrados.get(perfilGrado.toString())==null)
            {
                idGrado=idPerfilGrado++;
                perfilGrado.setIdPerfilGrado(idGrado);
                perfilesGrados.put(perfilGrado.toString(),perfilGrado);
                miniDimensiones.getEgresadoGrado().put(data[0],perfilGrado.toString());
            }
            linea=br.readLine();
        }
    }

    public static void lecturaHistorico() throws Exception
    {

        BufferedReader br = new  BufferedReader(
                new InputStreamReader(new FileInputStream("./data/P_historico_total.csv"), "UTF-8"));
        String linea=br.readLine();
        linea=br.readLine();
        ArrayList<String> llaves=new ArrayList<>();
        while(linea!=null)
        {
            linea=toTitleCase(linea);
            linea=linea.replaceAll(";","; ");
            String[] data=linea.split(";");
            DimPerfilPersonal perfilPersonal= new DimPerfilPersonal();
            int idPersonal=0;
            //PERIODO;llave an�nima;FACULTAD;EMPRESA
            // CARGO ;UNIVERSIDAD O INSTITUCI�N:;
            // CIUDAD DE RESIDENCIA;
            // ESTADO/DEPARTAMENTO:;
            // PA�S DE RESIDENCIA:
            perfilPersonal.setCargoActual(data[4]);
            perfilPersonal.setCiudadResidenciaActual(data[6]);
            perfilPersonal.setEmpresaActual(data[3]);
            perfilPersonal.setEstadoResidenciaActual(data[7]);
            perfilPersonal.setFechaActualizacion(FORMATO_FECHA.format(convertirSemestre(data[0],true)));
            perfilPersonal.setPaisResidenciaActual(data[8]);
            //Cuidado porque no se tiene el 0 o el 1 de Becado
            if(perfilesPersonales.get(perfilPersonal.toString())==null)
            {
                idPersonal=idPerfilPersonal++;
                perfilPersonal.setIdPerfilPersonal(idPersonal);
                perfilesPersonales.put(perfilPersonal.toString(),perfilPersonal);
                PriorityQueue<DimPerfilPersonal> queue=miniDimensiones.getEgresadoPersonal().get(data[1]);
                if (queue==null)
                {
                    queue=new PriorityQueue<>();
                    miniDimensiones.getEgresadoPersonal().put(data[1],queue);
                }
                llaves.add(data[1]);
                queue.add(perfilPersonal);
            }
            linea=br.readLine();
        }

    }

    public static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }

    //llave an�nima;Edad;G�nero;Email principal;Celular Principal;
    // Ciudad de correo;Estado o provincia de correo;
    // Pa�s de correo;Fecha Actualizaci�n datos Contacto;
    // Cargo;Estrato;Nivel Vinculaci�n;
    // Es donante;Profesor;Beneficiario QE;Indice Relacionamiento;

    public static void lecturaEgresados() throws Exception
    {
        BufferedReader br = new  BufferedReader(
                new InputStreamReader(new FileInputStream("./data/P_egresados.csv"), "UTF-8"));
        String linea=br.readLine();
        linea=br.readLine();
        double maxInd=0;
        while(linea!=null) {
            linea = toTitleCase(linea);
            linea = linea.replaceAll(";;", "; ;");
            String[] data = linea.split(";");
            String beneficiarioQE = data[14];
            String profesor = data[13];
            String donante = data[12];
            //llave an�nima;Edad;G�nero;Email principal;Celular Principal;
            // Ciudad de correo;Estado o provincia de correo;
            // Pa�s de correo;Fecha Actualizaci�n datos Contacto;
            // Cargo;Estrato;Nivel Vinculaci�n;
            // Es donante;Profesor;Beneficiario QE;Indice Relacionamiento;
            DimPerfilPersonal perfilPersonal = new DimPerfilPersonal();
            perfilPersonal.setCargoActual(data[9]);
            perfilPersonal.setPaisResidenciaActual(data[7]);
            perfilPersonal.setFechaActualizacion(data[8]);
            perfilPersonal.setEstadoResidenciaActual(data[6]);
            perfilPersonal.setCiudadResidenciaActual(data[5]);
            int idPersonal = 0;
            //Cuidado porque no se tiene el 0 o el 1 de profesor y donante
            if (perfilesPersonales.get(perfilPersonal.toString()) == null) {
                perfilPersonal.setIdPerfilPersonal(idPerfilPersonal++);
                perfilesPersonales.put(perfilPersonal.toString(), perfilPersonal);
                PriorityQueue<DimPerfilPersonal> queue = miniDimensiones.getEgresadoPersonal().get(data[1]);
                if (queue == null) {
                    queue = new PriorityQueue<>();
                    miniDimensiones.getEgresadoPersonal().put(data[0], queue);
                }
                queue.add(perfilPersonal);
            }
            //TODO: Incluir todos los perfiles
            //ID DEL PERSONAL
            idPersonal = 0;
            perfilPersonal = null;
            String id = null;
            PriorityQueue<DimPerfilPersonal> q = null;
            try {
                q = miniDimensiones.getEgresadoPersonal().get(data[0]);
                perfilPersonal = miniDimensiones.getEgresadoPersonal().get(data[0]).poll();
                perfilPersonal.setFechaExpiracion("01/01/2999");
            } catch (Exception e) {
                idPersonal = -1;
            }

            if (perfilPersonal != null) {
                perfilesPersonales.remove(perfilPersonal.toString());
                perfilPersonal.setDonante(donante);
                perfilPersonal.setProfesor(profesor);
                perfilesPersonales.put(perfilPersonal.toString(), perfilPersonal);
                idPersonal = perfilPersonal.getIdPerfilPersonal();
                //Asignar fechas de expiración a las demás opciones
                String expAnterior=perfilPersonal.getFechaActualizacion();
                Iterator<DimPerfilPersonal> it=miniDimensiones.getEgresadoPersonal().get(data[0]).iterator();
                while(it.hasNext())
                {
                    it.next().setFechaExpiracion(expAnterior);
                    expAnterior=it.next().getFechaActualizacion();
                }
            }
            //Perfil Practicante
            int idPractica = 0;
            try {
                id = miniDimensiones.getEgresadoPracticante().get(data[0]);
                idPractica = perfilesPracticantese.get(id).getIdPerfilPractica();
            } catch (Exception e) {
                idPractica = -1;
            }
            //PErfil Grado
            int idGrado = 0;
            DimPerfilGrado perfilGrado = null;
            try {
                id = miniDimensiones.getEgresadoGrado().get(data[0]);
                perfilGrado = perfilesGrados.get(id);
                idGrado = perfilGrado.getIdPerfilGrado();
            } catch (Exception e) {
                idGrado = -1;
            }
            if (perfilGrado != null) {
                perfilesGrados.remove(perfilGrado.toString());
                perfilGrado.setBeneficiarioQE(beneficiarioQE);
                perfilesGrados.put(perfilGrado.toString(), perfilGrado);
            }
            DimEgresado perfilEgresado = new DimEgresado();
            perfilEgresado.setEdadEgresado(data[1]);
            if (data[2].trim().equals("Masculino"))
                perfilEgresado.setGeneroEgresado("M");
            else if (data[2].trim().equals("Femenino"))
                perfilEgresado.setGeneroEgresado("F");
            else
                perfilEgresado.setGeneroEgresado("D");
            perfilEgresado.setIndiceRelacionamiento(Double.parseDouble(data[15].replaceAll(",", ".")));
            perfilEgresado.setNivelVinculacionEgresado(data[11]);
            if(perfilEgresado.getIndiceRelacionamiento()>maxInd)
        	{
            	maxInd=perfilEgresado.getIndiceRelacionamiento();
            	System.out.println("NEW IND "+maxInd);
        	}
            if (data[4].trim().equals(""))
                perfilEgresado.setCelularPrincipalEgresado("0");
            else
                perfilEgresado.setCelularPrincipalEgresado("1");
            if (data[3].trim().equals(""))
                perfilEgresado.setEmailEgresado("0");
            else
                perfilEgresado.setEmailEgresado("1");

            //Vinculación relaciones
            perfilEgresado.setIdPerfilGrado(idGrado);
            perfilEgresado.setIdPerfilPersonal(idPersonal);
            perfilEgresado.setIdPerfilPractica(idPractica);
            int idE=-1;
            if(egresados.get(perfilEgresado.toString())==null)
            {
                idE=idEgresado++;
                perfilEgresado.setIdEgresado(idE);
                egresados.put(perfilEgresado.toString(),perfilEgresado);
            }
            else
                idE=egresados.get(perfilEgresado.toString()).getIdEgresado();
            //Renae facts
            try
            {
                Collection<Fact> facts = theLinker.get(data[0]).values();
                HashMap<String,Fact> newFacts=new HashMap<>();
                for(Fact f:facts)
                {
                    f.setIdEgresado(idE);
                    f.setIdPerfilGrado(idGrado);
                    f.setIdPerfilPracticante(idPractica);
                    f.setIdPerfilProfesional(idPersonal);
                    newFacts.put(f.toString(),f);
                }
                theLinker.put(data[0],newFacts);

            }
            catch(Exception e)
            {

            }
            linea=br.readLine();

        }
        br.close();
        //Colocar el �ndice estandarizado
        System.out.println("MAX IND "+maxInd);
        if(maxInd==0)
        	maxInd=1;
        for (String s: egresados.keySet())
        {
        	DimEgresado egresado=egresados.get(s);
        	egresado.setIndiceRelacionamientoEstandarizado(egresado.getIndiceRelacionamiento()/maxInd);
        }
    }

    public static void generarBaseDiccionario() throws Exception{
        BufferedReader br = new  BufferedReader(
                new InputStreamReader(new FileInputStream("./data/P_eventos_total.csv"), "UTF-8"));
        String linea=br.readLine();
        String[] temp = linea.split(";");
        int indice=2;
        String[] tipos= new String[]{"ACAD","DEPO","ESPE","CULT","SOCI","OTRO"};

        for(int i=3;i<temp.length;i++)
        {
            for(String t:tipos)
            {
                if(t.equals(temp[i]))
                {
                    indice=i;
                    break;
                }
            }
        }
        String[] data=new String[temp.length-(indice-4)];
        data[3]=temp[3];
        for(int i=4;i<indice;i++)
        {
            data[3]+=temp[i];
        }
        data[0]=temp[0];
        data[1]=temp[1];
        data[2]=temp[2];
        for(int i=indice;i<temp.length;i++)
        {
            data[i-(indice-4)]=temp[i];
        }
        linea=br.readLine();
        linea=linea.replaceAll(";","; ");
        HashMap<String,String> hash = new HashMap<>();
        while(linea!=null)
        {
            linea=linea.replaceAll(";","; ");
            data=linea.split(";");
            if(hash.get(data[8].trim())==null)
            {
                hash.put(data[8].trim(),data[7]);
            }
            linea=br.readLine();

        }
        PrintWriter pw= new PrintWriter("./data/diccionario.csv");
        for(String key:hash.keySet())
            pw.println(key+";"+hash.get(key));
        br.close();
        pw.close();
    }
    
    public static void crearTablaAgregada()
    {
    	//TODO Completar m�todo como se guste para crear la tabla agregada
    }

    public static void main (String[] args) throws Exception
    {
    	System.out.println(0.2/0.5);
    	double a=0.2;
    	a/=0.5;
    	System.out.println(a);
    	crearDiccionario();
        lecturaEquivalencias();
        lecturaEventos();
        lecturaCTP();
        lecturaGradosOtorgados();
        lecturaHistorico();
        lecturaEgresados();
        EscritorReportes er= new EscritorReportes();
        er.escribirReporteValoresCsv("DimPerfilPersonal",(Collection)perfilesPersonales.values());
        er.escribirReporteValoresCsv("DimFechaInicio",(Collection)fechas.values());
        er.escribirReporteValoresCsv("DimFechaFin",(Collection)fechas2.values());
        er.escribirReporteValoresCsv("DimEvento",(Collection)eventos.values());
        er.escribirReporteValoresCsv("DimEgresado",(Collection)egresados.values());
        er.escribirReporteValoresCsv("DimPerfilGrado",(Collection)perfilesGrados.values());
        er.escribirReporteValoresCsv("DimPerfilPracticante",(Collection)perfilesPracticantese.values());
        er.escribirReporteValoresCsv("DimUnidadAcademica",(Collection)unidadesAcademicas.values());

        ArrayList<Fact> totalFacts=new ArrayList<>();
        for(String id:theLinker.keySet())
            for(Fact f:theLinker.get(id).values())
                totalFacts.add(f);
        er.escribirReporteValoresCsv("HechoInscribirseAEvento",(Collection)totalFacts);

        //Escribir scripts sql
        er.generarScriptsSQL();
    }
}
