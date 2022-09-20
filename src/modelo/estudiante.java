/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jorge
 */
public class estudiante extends persona {
   private int id;
    private String carnet;
        Conexion cn;
        
    public estudiante(){ }
        public estudiante(int id,String carnet, String nombres, String apellidos, String direccion, String telefono, String genero, String email,String fecha_nacimiento) {
        super( nombres, apellidos, direccion, telefono, genero, email, fecha_nacimiento);
        this.carnet = carnet;
        this.id = id; 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }
    public DefaultTableModel leer (){
        DefaultTableModel tabla = new DefaultTableModel();
    try {
        cn= new Conexion ();
        cn.abrir_conexion();
        System.out.println("Conexion Exitosa " );
        String query;
        query = "select id_estudiantes as id,carnet,nombres,apellidos,direccion,telefono,genero,email,fecha_nacimiento from clientes;";
        ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
        
        String encabezado[] = {"id","carnet","Nombres","Apellidos","Direccion","telefono","genero","email","fecha_nacimiento"};
        tabla.setColumnIdentifiers(encabezado);
        
        String datos[]=new String[8];
        
        while(consulta.next()){
            datos[0] = consulta.getString("id");
            datos[1] = consulta.getString("carnet");
            datos[2] = consulta.getString("Nombres");
            datos[3] = consulta.getString("apellidos");
            datos[4] = consulta.getString("direccion");
            datos[5] = consulta.getString("telefono");
            datos[6] = consulta.getString("genero");
            datos[7] = consulta.getString("email");
            datos[8] = consulta.getString("fecha_nacimiento");
            tabla.addRow(datos);
            }
        cn.cerrar_conexion();
        
    } catch(SQLException ex){
        cn.cerrar_conexion();
        System.out.println("Error2: " + ex.getMessage() );
        
    }
    return tabla;
    }
    @Override
    public void agregar(){
      try{
         PreparedStatement parametro;
         String query ="INSERT INTO estudiantes(carnet,nombres,apellidos,direccion,telefono,genero,email,fecha_nacimiento) VALUES(?,?,?,?,?,?,?);";
    cn = new Conexion();
    cn.abrir_conexion();
    parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
    parametro.setString(1, getcarnet());
    parametro.setString(2, getNombres());
    parametro.setString(3, getApellidos());
    parametro.setString(4, getDireccion());
    parametro.setString(5, getTelefono());
    parametro.setString(6, getgenero());
    parametro.setString(7, getemail());
    parametro.setString(8, getFecha_nacimiento());
    
    int executar = parametro.executeUpdate();
    cn.cerrar_conexion();
    JOptionPane.showMessageDialog(null,Integer.toString(executar)+ " Registro Ingresado","Agregar",JOptionPane.INFORMATION_MESSAGE);

            }catch(HeadlessException | SQLException Ex){
          System.out.println("Error..."+ Ex.getMessage());
      }  
    }
    @Override
    public void actualizar(){
        try{
         PreparedStatement parametro;
         cn = new Conexion();
         cn.abrir_conexion();
         String query;
         query = "update estudiantes set carnet = ?,nombres= ?,apellidos= ?,direccion= ?,telefono= ?,genero = ?,email = ?,fecha_nacimiento= ? "+
                 "where id_clientes = ?";    
    parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
    parametro.setString(1, getcarnet());
    parametro.setString(2, getNombres());
    parametro.setString(3, getApellidos());
    parametro.setString(4, getDireccion());
    parametro.setString(5, getTelefono());
    parametro.setString(6, getgenero());
    parametro.setString(7, getemail());
    parametro.setString(8, getFecha_nacimiento());
    parametro.setInt(8, getId());
    
    int executar = parametro.executeUpdate();
    cn.cerrar_conexion();
    JOptionPane.showMessageDialog(null,Integer.toString(executar)+ " Registro Actualizado","Agregar",JOptionPane.INFORMATION_MESSAGE);

            }catch(HeadlessException | SQLException Ex){
          System.out.println("Error..."+ Ex.getMessage());
      } 
    }
    @Override
    public void eliminar(){
     try{
         PreparedStatement parametro;
         cn = new Conexion();
         cn.abrir_conexion();
         String query;
         query = "delete from estudiantes where id_estudiantes = ?";  
    parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
    parametro.setInt(1, getId());
    
    int executar = parametro.executeUpdate();
    cn.cerrar_conexion();
    JOptionPane.showMessageDialog(null,Integer.toString(executar)+ " Registro Eliminado","Agregar",JOptionPane.INFORMATION_MESSAGE);

            }catch(HeadlessException | SQLException Ex){
          System.out.println("Error..."+ Ex.getMessage());
      } 
    
    }

    private String getcarnet() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private String getgenero() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private String getemail() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
}    

