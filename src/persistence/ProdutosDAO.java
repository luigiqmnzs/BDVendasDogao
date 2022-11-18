
package persistence;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Produtos;

public class ProdutosDAO {
    
    public int retornarAI(){
             int autoI = 0;
              Connection con = null;
	        PreparedStatement stmt = null;
	        
	        try {
	            con = Conexao.getConnection();
	            stmt = con.prepareStatement("SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'vendas_dogao' AND TABLE_NAME = 'produtos';");
	            ResultSet rs = stmt.executeQuery();
	            while(rs.next()){
                    autoI = rs.getInt("AUTO_INCREMENT");
                    return autoI;
                    }
	        } catch (SQLException ex) {
	            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            try {
	                stmt.close();
	                con.close();
	            } catch (SQLException ex) {
	                Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
                return autoI;
         }
    
	 public void inserir(Produtos p) {
	        Connection con = null;
	        PreparedStatement stmt = null;
	        
	        try {
	            con = Conexao.getConnection();
	            stmt = con.prepareStatement("insert into produtos (descricao_produto, valor_compra, valor_venda, quantidade_estoque, estoque_minimo) values (?, ?, ?, ?, ?)");
	            stmt.setString(1, p.getDescricaoProduto());
	            stmt.setDouble(2, p.getValorCompra());
	            stmt.setDouble(3, p.getValorVenda());
                    stmt.setInt(4, p.getQuantidadeEstoque());
                    stmt.setInt(5, p.getEstoqueMinimo());
	            stmt.executeUpdate();
	        } catch (SQLException ex) {
	            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            try {
	                stmt.close();
	                con.close();
	            } catch (SQLException ex) {
	                Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	    }
	        public void deletar(int codigoProduto) {
	        Connection con = null;
	        PreparedStatement stmt = null;
	        
	        try {
	            con = Conexao.getConnection();
	            stmt = con.prepareStatement("delete from produtos where clodigo_produto=?");
	            stmt.setInt(1, codigoProduto);
	            stmt.executeUpdate();
	        } catch (SQLException ex) {
	            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            try {
	                stmt.close();
	                con.close();
	            } catch (SQLException ex) {
	                Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	    }
	        public void alterar(Produtos p) {
	        Connection con = null;
	        PreparedStatement stmt = null;
	        
	        try {
	            con = Conexao.getConnection();
	            stmt = con.prepareStatement("update produtos set descricao_produto=?, valor_compra=?, valor_venda=?, quantidade_estoque=?, estoque_minimo=? where codigo_produto=?");
	            stmt.setString(1, p.getDescricaoProduto());
	            stmt.setDouble(2, p.getValorCompra());
	            stmt.setDouble(3, p.getValorVenda());
                    stmt.setInt(4, p.getQuantidadeEstoque());
                    stmt.setInt(5, p.getEstoqueMinimo());
                     stmt.setInt(6, p.getCodigoProduto());
	            stmt.executeUpdate();
	        } catch (SQLException ex) {
	            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            try {
	                stmt.close();
	                con.close();
	            } catch (SQLException ex) {
	                Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	    }
	      public List<Produtos> listar() throws SQLException{
	        Connection connection = null;
	        PreparedStatement stmt = null;
	        String sql = "select * from produtos";
	        try{
	            connection = Conexao.getConnection();
	            stmt = connection.prepareStatement(sql);
	            List<Produtos> produtos = new ArrayList<>();
	            ResultSet rs = stmt.executeQuery();
	            while(rs.next()){
	            	int codigoProduto = rs.getInt("codigo_produto");
	                String descricaoProduto = rs.getString("descricao_produto");
	                double valorCompra = rs.getDouble("valor_compra");
                        double valorVenda = rs.getDouble("valor_venda");
	                int quantidadeEstoque = rs.getInt("quantidade_estoque");
                        int estoqueMinimo = rs.getInt("estoque_minimo");
	                Produtos add = new Produtos(codigoProduto, descricaoProduto, valorCompra, valorVenda, quantidadeEstoque, estoqueMinimo);
	                produtos.add(add);
	            }
	            rs.close();
	            stmt.close();
	            return produtos;
	        }catch (SQLException e){
	            throw new RuntimeException(e);
	        }
	    }  
	     public Produtos buscar(int codigoProduto) {
	        Connection con = null;
	        PreparedStatement stmt = null;
	                
	        try {
	            con = Conexao.getConnection();
	            stmt = con.prepareStatement("select * from produtos where codigo_produto=?");
	            
	            stmt.setInt(1, codigoProduto);
	            ResultSet rs = stmt.executeQuery();
	            if(rs.next()) {
	                String descricaoProduto = rs.getString("descricao_produto");
	                double valorCompra = rs.getDouble("valor_compra");
                        double valorVenda = rs.getDouble("valor_venda");
	                int quantidadeEstoque = rs.getInt("quantidade_estoque");
                        int estoqueMinimo = rs.getInt("estoque_minimo");
	                Produtos p = new Produtos(codigoProduto, descricaoProduto, valorCompra, valorVenda, quantidadeEstoque, estoqueMinimo);
	                return p;
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            try {
	                stmt.close();
	                con.close();
	            } catch (SQLException ex) {
	                Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	        return null;
	    }
}
