package data.dao;

import data.database.ConnectionFactory;
import domain.model.Proprietario;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProprietarioDAO implements DAO<Proprietario, Integer> {
    @Override
    public void save(Proprietario proprietario) {
        String sql = "INSERT INTO Proprietario (nome, telefone) VALUES (?, ?)";
        try(PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)){
            statement.setString(1, proprietario.getNome());
            statement.setString(2, proprietario.getTelefone());
            statement.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public void delete(Integer key) { throw new RuntimeException("Não implementado"); }

    @Override
    public void update(Proprietario proprietario) {
        String sql = "UPDATE Proprietario SET nome = ?, telefone = ? WHERE id = ?";
        try(PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)){
            statement.setString(1, proprietario.getNome());
            statement.setString(2, proprietario.getTelefone());
            statement.setInt(3, proprietario.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Proprietario loadOne(Integer id) {
        String sql = "SELECT * FROM Proprietario WHERE id = ?";
        Proprietario proprietario = null;
        try(PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)){
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                String nomeProprietario = result.getString("nome");
                String telefone = result.getString("telefone");
                return new Proprietario(nomeProprietario, telefone);

            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return proprietario;
    }

    @Override
    public List<Proprietario> loadAll() {
        throw new RuntimeException("Não implementado");
    }

    public int lastIdSaved() {
        String sql = "SELECT MAX(id) as id FROM Proprietario";
        try(PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)){
            ResultSet result = statement.executeQuery();
            if(result.next())
                return result.getInt("id");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
