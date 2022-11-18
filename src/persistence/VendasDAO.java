
package persistence;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Vendas;

public class VendasDAO {
    
    public int retornarAI(){
             int autoI = 0;
              Connection con = null;
	        PreparedStatement stmt = null;
	        
	        try {
	            con = Conexao.getConnection();
	            stmt = con.prepareStatement("SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'vendas_dogao' AND TABLE_NAME = 'vendas';");
	            ResultSet rs = stmt.executeQuery();
	            while(rs.next()){
                    autoI = rs.getInt("AUTO_INCREMENT");
                    return autoI;
                    }
	        } catch (SQLException ex) {
	            Logger.getLogger(VendasDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            try {
	                stmt.close();
	                con.close();
	            } catch (SQLException ex) {
	                Logger.getLogger(VendasDAO.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
                return autoI;
         }
    
	 public void inserir(Vendas v) {
	        Connection con = null;
	        PreparedStatement stmt = null;
	        
	        try {
	            con = Conexao.getConnection();
	            stmt = con.prepareStatement("insert into vendas (data_venda, data_vencimento, tipo_de_venda, cliente_codigo_cliente) values (?, ?, ?, ?)");
	            stmt.setTimestamp(1, new Timestamp(v.getDataVenda().getTime()));
	            stmt.setTimestamp(2, new Timestamp(v.getDataVencimento().getTime()));
	            stmt.setString(3, v.getTipoDeVenda());
                    stmt.setInt(4, v.getCodigoCliente());
	            stmt.executeUpdate();
	        } catch (SQLException ex) {
	            Logger.getLogger(VendasDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            try {
	                stmt.close();
	                con.close();
	            } catch (SQLException ex) {
	                Logger.getLogger(VendasDAO.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	    }
	        public void deletar(int numeroVenda) {
	        Connection con = null;
	        PreparedStatement stmt = null;
	        
	        try {
	            con = Conexao.getConnection();
	            stmt = con.prepareStatement("delete from vendas where numero_venda=?");
	            stmt.setInt(1, numeroVenda);
	            stmt.executeUpdate();
	        } catch (SQLException ex) {
	            Logger.getLogger(VendasDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            try {
	                stmt.close();
	                con.close();
	            } catch (SQLException ex) {
	                Logger.getLogger(VendasDAO.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	    }
	        public void alterar(Vendas v) {
	        Connection con = null;
	        PreparedStatement stmt = null;
	        
	        try {
	            con = Conexao.getConnection();
	            stmt = con.prepareStatement("update vendas set data_venda=?, data_vencimento=?, tipo_de_venda=?, cliente_codigo_cliente=? where numero_venda=?");
	            stmt.setTimestamp(1, new Timestamp(v.getDataVenda().getTime()));
	            stmt.setTimestamp(2, new Timestamp(v.getDataVencimento().getTime()));
	            stmt.setString(3, v.getTipoDeVenda());
                    stmt.setInt(4, v.getCodigoCliente());
	            stmt.executeUpdate();
	        } catch (SQLException ex) {
	            Logger.getLogger(VendasDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            try {
	                stmt.close();
	                con.close();
	            } catch (SQLException ex) {
	                Logger.getLogger(VendasDAO.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	    }
	      public List<Vendas> listar() throws SQLException{
	        Connection connection = null;
	        PreparedStatement stmt = null;
	        String sql = "select * from vendas";
	        try{
	            connection = Conexao.getConnection();
	            stmt = connection.prepareStatement(sql);
	            List<Vendas> vendas = new ArrayList<>();
	            ResultSet rs = stmt.executeQuery();
	            while(rs.next()){
	            	int numeroVendas = rs.getInt("numero_vendas");
	                Date dataVenda = new Date(rs.getTimestamp("data_venda").getTime());
	                Date dataVencimento = new Date(rs.getTimestamp("data_vencimento").getTime());
                        String tipoDeVenda = rs.getString("tipo_de_venda");
	                int codigoCliente = rs.getInt("cliente_codigo_cliente");
	                Vendas add = new Vendas(numeroVendas, dataVenda, dataVencimento, tipoDeVenda, codigoCliente);
	                vendas.add(add);
	            }
	            rs.close();
	            stmt.close();
	            return vendas;
	        }catch (SQLException e){
	            throw new RuntimeException(e);
	        }
	    }  
	     public Vendas buscar(int numeroVenda) {
	        Connection con = null;
	        PreparedStatement stmt = null;
	                
	        try {
	            con = Conexao.getConnection();
	            stmt = con.prepareStatement("select * from vendas where numero_venda=?");
	            
	            stmt.setInt(1, numeroVenda);
	            ResultSet rs = stmt.executeQuery();
	            if(rs.next()) {
	                Date dataVenda = new Date(rs.getTimestamp("data_venda").getTime());
	                Date dataVencimento = new Date(rs.getTimestamp("data_vencimento").getTime());
                        String tipoDeVenda = rs.getString("tipo_de_venda");
	                int codigoCliente = rs.getInt("cliente_codigo_cliente");
	                Vendas v = new Vendas(numeroVenda, dataVenda, dataVencimento, tipoDeVenda, codigoCliente);
	                return v;
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(VendasDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            try {
	                stmt.close();
	                con.close();
	            } catch (SQLException ex) {
	                Logger.getLogger(VendasDAO.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	        return null;
	    }
}
