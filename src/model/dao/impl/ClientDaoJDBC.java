
package model.dao.impl;

import db.DB;
import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.dao.ClientDao;
import model.entites.Client;

public class ClientDaoJDBC implements ClientDao  {
    
    private Connection conn;
    
    public ClientDaoJDBC(Connection conn){
        this.conn = conn;
    }
    
    @Override
    public void insert(Client obj){
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO client "
                    + "(cpf, name, endereco, telefone, email) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, obj.getCpf());
            st.setString(2, obj.getName());
            st.setString(3, obj.getAddress());
            st.setInt(4, obj.getTelephone());
            st.setString(5, obj.getEmail());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
             throw new DbException("ERRO! No rows affected!");
               
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }
    
    @Override
    public void update(Client obj){
       PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE seller "
                    + "SET name = ?, endereco = ?, telefone = ?, email = ? "
                    + "WHERE cpf = ?");

            st.setString(1, obj.getName());
            st.setString(2, obj.getAddress());
            st.setInt(3, obj.getTelephone());
            st.setString(4, obj.getEmail());
            st.setInt(5, obj.getCpf());
            
            st.executeUpdate();
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
     
    }
    
    @Override
    public void deleteById(Integer cpf){
         PreparedStatement st = null;
        try{
            st = conn.prepareStatement("DELETE FROM client WHERE Id = ?");
            
            st.setInt(1, cpf);
            
            st.executeUpdate();
            
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
        }
    }
    
    @Override
    public Client findById(Integer cpf){
      PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM department WHERE Id = ?");

            st.setInt(1, cpf);
            rs = st.executeQuery();

            if (rs.next()) {
                Client client = new Client();
                client.setCpf(rs.getInt("cpf"));
                client.setName(rs.getString("name"));
                client.setAddress(rs.getString("name"));
                client.setTelephone(rs.getInt("name"));
                client.setEmail(rs.getString("name"));
                return client;
            }
            return null;
        
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }  
    }
    
    @Override
    public List<Client> findAll(){
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM client "
                    + "ORDER BY name");

            rs = st.executeQuery();

            List<Client> list = new ArrayList<>();

            while (rs.next()) {
                Client client = new Client();
                client.setCpf(rs.getInt("cpf"));
                client.setName(rs.getString("name"));
                client.setAddress(rs.getString("name"));
                client.setTelephone(rs.getInt("name"));
                client.setEmail(rs.getString("name"));
                
                list.add(client);
            }
            return list;
        
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }  
}
