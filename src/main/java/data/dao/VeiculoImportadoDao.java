package data.dao;

import data.database.ConnectionFactory;
import domain.model.Paises;
import domain.model.VeiculoImportado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoImportadoDao implements IDao<VeiculoImportado, Integer> {
    @Override
    public void save(VeiculoImportado veiculo) {
        VeiculoDao veiculoDAO = new VeiculoDao();
        int lastId = veiculoDAO.lastIdSaved();
        String sql = "INSERT INTO VeiculoImportado (idVeiculo, paisOrigem) VALUES (?, ?)";
        try(PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)){
            statement.setInt(1, lastId);
            statement.setString(2, veiculo.getPaisOrigem().toString());
            statement.execute();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Integer idCarro) { throw new RuntimeException("Não implementado"); }

    @Override
    public void update(VeiculoImportado veiculo) {
        String sql = "UPDATE VeiculoImportado SET paisOrigem = ? WHERE idVeiculo = ?";
        try(PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)){
            statement.setString(1, veiculo.getPaisOrigem().toString());
            statement.setInt(2, veiculo.getId());
            statement.executeUpdate();

            VeiculoDao veiculoDAO = new VeiculoDao();
            veiculoDAO.update(veiculo);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public VeiculoImportado loadOne(Integer integer) {
        return null;
    }

    @Override
    public List<VeiculoImportado> loadAll() {
        String sql = "SELECT * FROM VeiculoImportado";
        List<VeiculoImportado> veiculos = new ArrayList<>();
        try(PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while(result.next()){
                VeiculoDao veiculoDAO = new VeiculoDao();
                String nomePais = result.getString("paisOrigem");
                Paises paisOrigem = Paises.getPaisByString(nomePais);
                VeiculoImportado veiculoImportado = (VeiculoImportado) veiculoDAO.loadOne(result.getInt("idVeiculo"));
                veiculoImportado.setPaisOrigem(paisOrigem);
                veiculos.add(veiculoImportado);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return veiculos;
    }




}
