
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
import model.ItensDeVendas;

public class ItensDeVendasDAO {
	 public void inserir(ItensDeVendas i) {
	        Connection con = null;
	        PreparedStatement stmt = null;
	        
	        try {
	            con = Conexao.getConnection();
	            stmt = con.prepareStatement("insert into itens_de_venda values (?, ?, ?, ?)");
	            stmt.setInt(1, i.getQuantidadeVendida());
	            stmt.setDouble(2, i.getPrecoPago());
	            stmt.setInt(3, i.getNumeroVenda());
                    stmt.setInt(4, i.getCodigoProduto());
	            stmt.executeUpdate();
	        } catch (SQLException ex) {
	            Logger.getLogger(ItensDeVendasDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            try {
	                stmt.close();
	                con.close();
	            } catch (SQLException ex) {
	                Logger.getLogger(ItensDeVendasDAO.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	    }
	        public void deletar(int numeroVenda, int codigoProduto) {
	        Connection con = null;
	        PreparedStatement stmt = null;
	        
	        try {
	            con = Conexao.getConnection();
	            stmt = con.prepareStatement("delete from vendas where vendas_numero_venda=? and produtos_codigo_produto=?");
	            stmt.setInt(1, numeroVenda);
                    stmt.setInt(2, codigoProduto);
	            stmt.executeUpdate();
	        } catch (SQLException ex) {
	            Logger.getLogger(ItensDeVendasDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            try {
	                stmt.close();
	                con.close();
	            } catch (SQLException ex) {
	                Logger.getLogger(ItensDeVendasDAO.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	    }
	        public void alterar(ItensDeVendas i) {
	        Connection con = null;
	        PreparedStatement stmt = null;
	        
	        try {
	            con = Conexao.getConnection();
	            stmt = con.prepareStatement("update itens_de_venda set quantidade_vendida=?, preco_pago=? where vendas_numero_venda=? and produtos_codigo_produto=?");
	            stmt.setInt(1, i.getQuantidadeVendida());
	            stmt.setDouble(2, i.getPrecoPago());
	            stmt.setInt(3, i.getNumeroVenda());
                    stmt.setInt(4, i.getCodigoProduto());
	            stmt.executeUpdate();
	        } catch (SQLException ex) {
	            Logger.getLogger(ItensDeVendasDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            try {
	                stmt.close();
	                con.close();
	            } catch (SQLException ex) {
	                Logger.getLogger(ItensDeVendasDAO.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	    }
	      public List<ItensDeVendas> listar() throws SQLException{
	        Connection connection = null;
	        PreparedStatement stmt = null;
	        String sql = "select * from itens_de_venda";
	        try{
	            connection = Conexao.getConnection();
	            stmt = connection.prepareStatement(sql);
	            List<ItensDeVendas> itensDeVendas = new ArrayList<>();
	            ResultSet rs = stmt.executeQuery();
	            while(rs.next()){
	            	int quantidadeVendida = rs.getInt("quantidade_vendida");
                        Double precoPago = rs.getDouble("preco_pago");
	                int numeroVenda = rs.getInt("vendas_numero_venda");
                        int codigoProduto = rs.getInt("produtos_codigo_produto");
	                ItensDeVendas add = new ItensDeVendas(quantidadeVendida, precoPago, numeroVenda, codigoProduto);
	                itensDeVendas.add(add);
	            }
	            rs.close();
	            stmt.close();
	            return itensDeVendas;
	        }catch (SQLException e){
	            throw new RuntimeException(e);
	        }
	    }  
	     public ItensDeVendas buscar(int numeroVenda, int codigoProduto) {
	        Connection con = null;
	        PreparedStatement stmt = null;
	                
	        try {
	            con = Conexao.getConnection();
	            stmt = con.prepareStatement("select * from itens_de_venda where vendas_numero_venda=? and produtos_codigo_produto=?");
	            
	            stmt.setInt(1, numeroVenda);
                    stmt.setInt(2, codigoProduto);
	            ResultSet rs = stmt.executeQuery();
	            if(rs.next()) {
	                int quantidadeVendida = rs.getInt("quantidade_vendida");
                        Double precoPago = rs.getDouble("preco_pago");
	                ItensDeVendas i = new ItensDeVendas(quantidadeVendida, precoPago, numeroVenda, codigoProduto);
	                return i;
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(ItensDeVendasDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            try {
	                stmt.close();
	                con.close();
	            } catch (SQLException ex) {
	                Logger.getLogger(ItensDeVendasDAO.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	        return null;
	    }
}
