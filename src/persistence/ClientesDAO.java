
package persistence;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Clientes;

public class ClientesDAO {
    
         public int retornarAI(){
             int autoI = 0;
              Connection con = null;
	        PreparedStatement stmt = null;
	        
	        try {
	            con = Conexao.getConnection();
	            stmt = con.prepareStatement("SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'vendas_dogao' AND TABLE_NAME = 'clientes';");
	            ResultSet rs = stmt.executeQuery();
	            while(rs.next()){
                    autoI = rs.getInt("AUTO_INCREMENT");
                    return autoI;
                    }
	        } catch (SQLException ex) {
	            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            try {
	                stmt.close();
	                con.close();
	            } catch (SQLException ex) {
	                Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
                return autoI;
         }
	 public void inserir(Clientes c) {
	        Connection con = null;
	        PreparedStatement stmt = null;
	        
	        try {
	            con = Conexao.getConnection();
	            stmt = con.prepareStatement("insert into cliente (nome, endereco, telefone) values (?, ?, ?)");
	            stmt.setString(1, c.getNome());
	            stmt.setString(2, c.getEndereco());
	            stmt.setString(3, c.getTelefone());
	            stmt.executeUpdate();
	        } catch (SQLException ex) {
	            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            try {
	                stmt.close();
	                con.close();
	            } catch (SQLException ex) {
	                Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	    }
	        public void deletar(int codigoCliente) {
	        Connection con = null;
	        PreparedStatement stmt = null;
	        
	        try {
	            con = Conexao.getConnection();
	            stmt = con.prepareStatement("delete from cliente where clodigo_cliente=?");
	            stmt.setInt(1, codigoCliente);
	            stmt.executeUpdate();
	        } catch (SQLException ex) {
	            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            try {
	                stmt.close();
	                con.close();
	            } catch (SQLException ex) {
	                Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	    }
	        public void alterar(Clientes c) {
	        Connection con = null;
	        PreparedStatement stmt = null;
	        
	        try {
	            con = Conexao.getConnection();
	            stmt = con.prepareStatement("update cliente set nome=?, endereco=?, telefone=? where codigo_cliente=?");
	            stmt.setString(1, c.getNome());
	            stmt.setString(2, c.getEndereco());
	            stmt.setString(3, c.getTelefone());
	            stmt.setInt(4, c.getCodigo());
	            stmt.executeUpdate();
	        } catch (SQLException ex) {
	            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            try {
	                stmt.close();
	                con.close();
	            } catch (SQLException ex) {
	                Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	    }
	      public List<Clientes> listar() throws SQLException{
	        Connection connection = null;
	        PreparedStatement stmt = null;
	        String sql = "select * from cliente";
	        try{
	            connection = Conexao.getConnection();
	            stmt = connection.prepareStatement(sql);
	            List<Clientes> clientes = new ArrayList<>();
	            ResultSet rs = stmt.executeQuery();
	            while(rs.next()){
	            	int codigoCliente = rs.getInt("codigo_cliente");
	                String nome = rs.getString("nome");
	                String endereco = rs.getString("endereco");
	                String telefone = rs.getString("telefone");
	                Clientes add = new Clientes(codigoCliente, nome, endereco, telefone);
	                clientes.add(add);
	            }
	            rs.close();
	            stmt.close();
	            return clientes;
	        }catch (SQLException e){
	            throw new RuntimeException(e);
	        }
	    }  
	     public Clientes buscar(int codigoCliente) {
	        Connection con = null;
	        PreparedStatement stmt = null;
	                
	        try {
	            con = Conexao.getConnection();
	            stmt = con.prepareStatement("select * from cliente where codigo_cliente=?");
	            
	            stmt.setInt(1, codigoCliente);
	            ResultSet rs = stmt.executeQuery();
	            if(rs.next()) {
	                String nome = rs.getString("nome");
	                String endereco = rs.getString("endereco");
	                String telefone = rs.getString("telefone");
	                Clientes c = new Clientes(codigoCliente, nome, endereco, telefone);
	                return c;
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            try {
	                stmt.close();
	                con.close();
	            } catch (SQLException ex) {
	                Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	        return null;
	    }
}
