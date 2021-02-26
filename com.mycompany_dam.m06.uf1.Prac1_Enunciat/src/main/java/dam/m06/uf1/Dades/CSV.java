/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.m06.uf1.Dades;

import dam.m06.uf1.Aplicacio.AplicacioException;
import dam.m06.uf1.Aplicacio.DriverMySql;
import dam.m06.uf1.Aplicacio.Model.Equips;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manel
 */
public class CSV {

    private static PrintWriter pw;
    private static StringBuilder sb;
    private static Connection conexion;
    private static ResultSet rs;
    private static DriverMySql con;
    private static PreparedStatement ps;

    /**
     * Exporta tots els equips a un fitxer CSV
     *
     * @param fitx
     * @param dades
     * @throws DadesException
     */
    public static void exportaEquipsACSV(File fitx, Equips dades) throws DadesException {
        try {

            pw = new PrintWriter(fitx);
            sb = new StringBuilder();

            con = new DriverMySql();
            conexion = con.getConnection();
            String query = "SELECT * FROM EQUIPS";
            ps = conexion.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                sb.append(rs.getInt("idEquipo"));
                sb.append(",");
                sb.append(rs.getString("nombre"));
                sb.append(",");
                sb.append(rs.getString("estadio"));
                sb.append(",");
                sb.append(rs.getString("poblacion"));
                sb.append(",");
                sb.append(rs.getString("provincia"));
                sb.append(",");
                sb.append(rs.getString("cod_postal"));
                sb.append("\n");

            }
            pw.write(sb.toString());
            pw.close();
            System.out.println("Escrito correctamente");

        } catch (AplicacioException aE) {
            throw new DadesException("Ha habido un problema en el método 'exportaEquipsACSV'");
        } catch (SQLException | FileNotFoundException ex) {
            Logger.getLogger(CSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Exporta tots els jugadors a un fitxer CSV
     *
     * @param fitx
     * @param dades
     * @throws DadesException
     */
    public static void exportaJugadorsACSV(File fitx, Equips dades) throws DadesException {
        try {

            pw = new PrintWriter(fitx);
            sb = new StringBuilder();

            con = new DriverMySql();
            conexion = con.getConnection();
            String query = "SELECT * FROM Jugadors";
            ps = conexion.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                sb.append(rs.getInt("idJugador"));
                sb.append(",");
                sb.append(rs.getInt("idEquipo"));
                sb.append(",");
                sb.append(rs.getString("nombre"));
                sb.append(",");
                sb.append(rs.getInt("dorsal"));
                sb.append(",");
                sb.append(rs.getInt("edad"));
                sb.append(",");
                sb.append(rs.getString("cp_jugador"));
                sb.append("\n");

            }
            pw.write(sb.toString());
            pw.close();
            System.out.println("Escrito correctamente");

        } catch (AplicacioException aE) {
            throw new DadesException("Ha habido un problema en el método 'exportaEquipsACSV'");
        } catch (SQLException | FileNotFoundException ex) {
            Logger.getLogger(CSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
