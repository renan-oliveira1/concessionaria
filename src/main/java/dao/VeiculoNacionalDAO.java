package dao;

import database.ConnectionFactory;
import model.Proprietario;
import model.VeiculoNacional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class VeiculoNacionalDAO implements DAO<VeiculoNacional, Integer> {
    @Override
    public void save(VeiculoNacional veiculo) {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        int lastIdCar = veiculoDAO.lastIdSaved();
        String sql = "INSERT INTO VeiculoNacional (idVeiculo, idProprietario) VALUES (?, ?)";
        try(PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)){
            statement.setInt(1, lastIdCar);
            if(veiculo.getProprietario().isPresent()) {

                ProprietarioDAO proprietarioDAO = new ProprietarioDAO();
                proprietarioDAO.save(veiculo.getProprietario().get());
                int lastIdProprietario = proprietarioDAO.lastIdSaved();

                statement.setInt(2, lastIdProprietario);
            }
            else
                statement.setString(2, null);
            statement.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Integer key) { throw new RuntimeException("Não implementado"); }

    @Override
    public void update(VeiculoNacional veiculo) {
        if(veiculo!=null){
            VeiculoDAO veiculoDAO = new VeiculoDAO();
            veiculoDAO.update(veiculo);

            ProprietarioDAO proprietarioDAO = new ProprietarioDAO();
            Proprietario proprietario = proprietarioDAO.loadOne(veiculo.getId());

            if(veiculo.getProprietario().isPresent()){

                if(proprietario == null){
                    String sql = "UPDATE VeiculoNacional SET idProprietario = ? WHERE idVeiculo = ?";
                    try(PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)){

                        proprietarioDAO.save(veiculo.getProprietario().get());
                        int lastIdProprietario = proprietarioDAO.lastIdSaved();
                        statement.setInt(1, lastIdProprietario);
                        statement.setInt(2, veiculo.getId());

                        statement.executeUpdate();
                    }catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                else{
                    proprietarioDAO.update(veiculo.getProprietario().get());
                }

            }


        }
    }

    @Override
    public VeiculoNacional loadOne(Integer integer) {
        throw new RuntimeException("Não implementado");
    }

    @Override
    public List<VeiculoNacional> loadAll() {
        String sql = "SELECT * FROM VeiculoNacional";
        List<VeiculoNacional> veiculos = new ArrayList<>();
        try(PreparedStatement statement = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while(result.next()){
                VeiculoDAO veiculoDAO = new VeiculoDAO();
                VeiculoNacional veiculoNacional = (VeiculoNacional) veiculoDAO.loadOne(result.getInt("idVeiculo"));

                ProprietarioDAO proprietarioDAO = new ProprietarioDAO();
                if(result.getInt("idProprietario") != 0){
                    Proprietario proprietario = proprietarioDAO.loadOne(result.getInt("idProprietario"));
                    veiculoNacional.setProprietario(proprietario);
                }
                veiculos.add(veiculoNacional);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return veiculos;
    }



}
