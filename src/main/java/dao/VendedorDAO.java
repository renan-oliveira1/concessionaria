package dao;

import database.ConnectionFactory;
import model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendedorDAO implements DAO<Vendedor, Integer>{
    @Override
    public void save(Vendedor vendedor) {
        String sql = "INSERT INTO Vendedor (nome) VALUES (?)";
        try(PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)){
            statement.setString(1, vendedor.getNome());
            statement.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void delete(Integer key) { throw new RuntimeException("Não implementado"); }

    @Override
    public void update(Vendedor type) { throw new RuntimeException("Não implementado"); }

    @Override
    public Vendedor loadOne(Integer id) {
        String sql = "SELECT * FROM Vendedor WHERE id = ?";
        Vendedor vendedor = null;
        try(PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)){
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            return new Vendedor(result.getInt("id"), result.getString("nome"));

        }catch (SQLException throwables) {
                throwables.printStackTrace();
        }
        return vendedor;
    }

    @Override
    public List<Vendedor> loadAll() {
        String sql = "SELECT * FROM Vendedor";
        List<Vendedor> vendedores = new ArrayList<>();
        try(PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Vendedor vendedor = new Vendedor(result.getInt("id"), result.getString("nome"));
                vendedores.add(vendedor);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return vendedores;
    }
}
