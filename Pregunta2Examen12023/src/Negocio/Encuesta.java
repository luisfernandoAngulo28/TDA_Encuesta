/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

/**
 *
 * @author fernando0
 */
public class Encuesta {

    VectorNBits V1;// 26bits
    VectorNBits V2CI;//27bits
    VectorNBits V3Telefono;//27bits
    VectorNBits V4Hora;//17 bits
    int cant;
    String Nombre[];

    public Encuesta(int cantidad) {
        Nombre = new String[cantidad];
        V1 = new VectorNBits(cantidad, 26);
        V2CI = new VectorNBits(cantidad, 27);
        V3Telefono = new VectorNBits(cantidad, 27);
        V4Hora = new VectorNBits(cantidad, 17);
        cant = 1;
    }

    public void insertar(String nombre, int dia, int mes, int anio, int nroHijos,
            int estadoCivil, int primaria,
            int bachiller, int tecnico, int licenciado, int postgrado,
            int ci, int telefono, int hora, int minuto, int segundo) {
        int mask = dia;//5
        mask = mask << 4;
        mask = mask | mes;//5+4=9
        //  System.out.println(Integer.toBinaryString(mask));
        mask = mask << 6;
        anio = anio - 1980;
        mask = mask | anio;
        mask = mask << 4;
        mask = mask | nroHijos;
        mask = mask << 2;
        mask = mask | estadoCivil;
        mask = mask << 1;
        mask = mask | primaria;
        mask = mask << 1;
        mask = mask | bachiller;
        mask = mask << 1;
        mask = mask | tecnico;
        mask = mask << 1;
        mask = mask | licenciado;
        mask = mask << 1;
        mask = mask | postgrado;
        // System.out.println(Integer.toBinaryString(mask));
        V1.insertar(mask, cant);
        // System.out.println(V1.toString());
        V2CI.insertar(ci, cant);
        V3Telefono.insertar(telefono, cant);
        int mask2 = hora;//5
        mask2 = mask2 << 6;
        mask2 = mask2 | minuto;
        mask2 = mask2 << 6;
        mask2 = mask2 | segundo;
        V4Hora.insertar(mask2, cant);
        // System.out.println(Integer.toBinaryString(mask2));
        Nombre[cant - 1] = nombre;
        cant++;
    }

    public int getCantidad(){
        return cant;
    }
    
    public int getDia(int pos) {
        int dato = V1.sacar(pos);
        //26-5=21
        int mask = (int) (Math.pow(2, 5) - 1);
        dato = dato >>> 21;
        dato = dato & mask;
        return dato;
    }

    public int getMes(int pos) {
        int dato = V1.sacar(pos);
        //21-4=17
        int mask = (int) (Math.pow(2, 4) - 1);
        dato = dato >>> 17;
        dato = dato & mask;
        return dato;
    }

    public int getAño(int pos) {
        int dato = V1.sacar(pos);
        //17-6=11
        int mask = (int) (Math.pow(2, 6) - 1);
        dato = dato >>> 11;
        dato = dato & mask;
        return dato + 1980;
    }

    public int getNroHijos(int pos) {
        int dato = V1.sacar(pos);
        //11-4=7
        int mask = (int) (Math.pow(2, 4) - 1);
        dato = dato >>> 7;
        dato = dato & mask;
        return dato;
    }

    public String getEstadoCivil(int pos) {
        int dato = V1.sacar(pos);
        //7-2=5;
        int mask = (int) (Math.pow(2, 2) - 1);
        dato = dato >>> 5;
        dato = dato & mask;
        String estado="";
        /*
                    case"Soltero":estadoCivil=0;break;
                    case"Divorsiado":estadoCivil=1;break;
                    case"Casado":estadoCivil=2;break;
                    case"Viudo":estadoCivil=3;break;
        */
        switch(dato){
            case 0:estado="Soltero";break;
            case 1:estado="Divorciado";break;
            case 2:estado="Casado";break;
            case 3:estado="Viudo";break;
        }
             return estado;
    }

    public int getPrimaria(int pos) {
        int dato = V1.sacar(pos);
        //5-1=4;
        int mask = (int) (Math.pow(2, 1) - 1);
        dato = dato >>> 4;
        dato = dato & mask;
        return dato;
    }

    public int getBachiller(int pos) {
        int dato = V1.sacar(pos);
        //4-1=3;
        int mask = (int) (Math.pow(2, 1) - 1);
        dato = dato >>> 3;
        dato = dato & mask;
        return dato;
    }

    public int getTecnico(int pos) {
        int dato = V1.sacar(pos);
        //3-1=2;
        int mask = (int) (Math.pow(2, 1) - 1);
        dato = dato >>> 2;
        dato = dato & mask;
        return dato;
    }

    public int getLicenciado(int pos) {
        int dato = V1.sacar(pos);
        //2-1=1;
        int mask = (int) (Math.pow(2, 1) - 1);
        dato = dato >>> 1;
        dato = dato & mask;
        return dato;
    }

    public int getPostgrado(int pos) {
        int dato = V1.sacar(pos);
        //1-1=0;
        int mask = (int) (Math.pow(2, 1) - 1);
        //dato = dato >>> 0;
        dato = dato & mask;
        return dato;
    }

    //-----------------------------------------------------
    public int getci(int pos) {
        int dato = V2CI.sacar(pos);
        //27-27=0;
        int mask = (int) (Math.pow(2, 27) - 1);
        //dato = dato >>> 0;
        dato = dato & mask;
        // return dato;
        return dato & (int) (Math.pow(2, 27) - 1);
    }

    public int gettelefono(int pos) {
        int dato = V3Telefono.sacar(pos);
        //27-27=0;
        int mask = (int) (Math.pow(2, 27) - 1);
        //dato = dato >>> 0;
        dato = dato & mask;
        return dato;
        // return dato&(int) (Math.pow(2, 27) - 1);
    }
    //--------------------------------------Hora

    public int getHora(int pos) {
        int dato = V4Hora.sacar(pos);
        //17-5=12
        int mask = (int) (Math.pow(2, 5) - 1);
        dato = dato >>> 12;
        dato = dato & mask;
        return dato;
    }

    public int getMinuto(int pos) {
        int dato = V4Hora.sacar(pos);
        //12-6=6
        int mask = (int) (Math.pow(2, 6) - 1);
        dato = dato >>> 6;
        dato = dato & mask;
        return dato;
    }
    public int getsegundo(int pos) {
        int dato = V4Hora.sacar(pos);
        //6-6=0
        int mask = (int) (Math.pow(2, 6) - 1);
        //dato = dato >>> 6;
        dato = dato & mask;
        return dato;
    }
    
    public String getNombre(int pos){
        return Nombre[pos-1];
    }

   public String mostrar(int pos) {
        String s = "";
        s = s + "\nNro: " + pos
                + "\nNombre: " + getNombre(pos)
                + "\nFecha Nacimiento: " + getDia(pos) + "/"+ getMes(pos)+ "/" + getAño(pos)
                + "\nNumHijos: " + getNroHijos(pos)
                + "\nEstado Civil: " + getEstadoCivil(pos)
                + "\nPrimaria: " + (getPrimaria(pos) == 1 ? "si" : "no")
                + "\nSecundaria: " + (getBachiller(pos) == 1 ? "si" : "no") 
                + "\nTecnico: " + (getTecnico(pos) == 1 ? "si" : "no") 
                + "\nLicenciatura: " + (getLicenciado(pos) == 1 ? "si" : "no") 
                + "\nPost Grado: " + (getPostgrado(pos) == 1 ? "si" : "no") 
                + "\nCI: " + getci(pos)
                + "\nTelefono: " + gettelefono(pos)
                + "\nHora: " + getHora(pos)+ ":" + getMinuto(pos)+ ":" + getsegundo(pos);
       return s;
   }
    
    
    
    public static void main(String[] args) {
        Encuesta A = new Encuesta(10);
        A.insertar("Mario", 31, 12, 2023, 10, 1, 1, 1, 1, 1, 1, 12315321, 70265765, 22, 59, 59);
       /* System.out.println(A.getHora(1));
        System.out.println(A.getMinuto(1));
        System.out.println(A.getsegundo(1));*/
        System.out.println(A.mostrar(1));
    }
}
