package data.dao;

import data.database.ConnectionFactory;
import domain.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VeiculoDAO implements DAO<Veiculo, Integer> {

    @Override
    public void save(Veiculo veiculo) {
        String sql = "INSERT INTO Veiculo (nome, marca, valorVenda, tipo) VALUES( ?, ?, ?, ?)";
        try(PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)){
            statement.setString(1, veiculo.getNome());
            statement.setString(2, veiculo.getMarca());
            statement.setDouble(3, veiculo.getValorVenda());
            statement.setInt(4, veiculo.getTipo());
            statement.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateSell(Veiculo veiculo) {
        String sql = "UPDATE Veiculo SET idVendedor = ?, dataVenda = ? WHERE id = ?";
        try(PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)){
            statement.setInt(1, veiculo.getVendedor().getId());
            statement.setString(2, String.valueOf(veiculo.getDataVenda()));
            statement.setDouble(3, veiculo.getId());
            statement.execute();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int lastIdSaved() {
        String sql = "SELECT MAX(id) as id FROM Veiculo";
        try(PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)){
            ResultSet result = statement.executeQuery();
            if(result.next())
                return result.getInt("id");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public void delete(Integer idCarro) {throw new RuntimeException("Não implementado"); }

    @Override
    public void update(Veiculo veiculo) {
        String sql = "UPDATE Veiculo SET nome = ?, marca = ?, valorVenda = ? WHERE id = ?";
        try(PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)){
            statement.setString(1, veiculo.getNome());
            statement.setString(2, veiculo.getMarca());
            statement.setDouble(3, veiculo.getValorVenda());
            statement.setInt(4, veiculo.getId());
            statement.execute();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Veiculo loadOne(Integer id) {
        String sql = "SELECT * FROM Veiculo WHERE id = ?";
        Veiculo veiculo = null;
        try(PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)){
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                int tipo  = result.getInt("tipo");
                int idCarro = result.getInt("id");
                String nomeCarro = result.getString("nome");
                String marca = result.getString("marca");
                double valorVenda = result.getDouble("valorVenda");

                int verificarVendedor = result.getInt("idVendedor");

                if(tipo==1){

                    if(verificarVendedor == 0)
                        return new VeiculoImportado(idCarro, nomeCarro, marca, valorVenda);

                    VendedorDAO vendedorDAO = new VendedorDAO();
                    Vendedor vendedor = vendedorDAO.loadOne(verificarVendedor);
                    String data = result.getString("dataVenda");
                    return new VeiculoImportado(idCarro, nomeCarro, marca, valorVenda, data, vendedor);
                }else{

                    if(verificarVendedor == 0)
                        return new VeiculoNacional(idCarro, nomeCarro, marca, valorVenda);

                    VendedorDAO vendedorDAO = new VendedorDAO();
                    Vendedor vendedor = vendedorDAO.loadOne(verificarVendedor);
                    String data = result.getString("dataVenda");
                    return new VeiculoNacional(idCarro,nomeCarro, marca, valorVenda, data, vendedor);
                }
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return veiculo;
    }

    @Override
    public List<Veiculo> loadAll() {
        throw new RuntimeException("Não implementado");
    }

}
