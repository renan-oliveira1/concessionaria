package util;

import data.dao.*;
import data.repository.*;
import domain.use_case.veiculo.SaveVeiculoUseCase;
import domain.use_case.veiculo.SellVeiculoUseCase;
import domain.use_case.veiculo.UpdateVeiculoUseCase;
import domain.use_case.veiculo.importado.LoadAllImportadoUseCase;
import domain.use_case.veiculo.nacional.LoadAllNacionalUseCase;
import domain.use_case.vendedor.LoadAllSellersUseCase;
import domain.use_case.vendedor.SaveVendedorUseCase;

public class AppDependencies {
    //DAOS
    private static VendedorDao vendedorDao;
    private static VeiculoDao veiculoDao;
    private static VeiculoImportadoDao veiculoImportadoDao;
    private static VeiculoNacionalDao veiculoNacionalDao;
    private static ProprietarioDao proprietarioDao;

    //REPOSITORYS
    private static VendedorRepositoryImpl vendedorRepository;
    private static VeiculoRepositoryImpl veiculoRepository;
    private static VeiculoImportadoRepositoryImpl veiculoImportadoRepository;
    private static VeiculoNacionalRepositoryImpl veiculoNacionalRepository;
    private static ProprietarioRepositoryImpl proprietarioRepository;

    //USE_CASES
    private static LoadAllImportadoUseCase loadAllImportadoUseCase;
    private static LoadAllNacionalUseCase loadAllNacionalUseCase;
    private static LoadAllSellersUseCase loadAllSellersUseCase;
    private static SellVeiculoUseCase sellVeiculoUseCase;
    private static SaveVendedorUseCase saveVendedorUseCase;
    private static SaveVeiculoUseCase saveVeiculoUseCase;
    private static UpdateVeiculoUseCase updateVeiculoUseCase;

    //GET DAOS
    public static VendedorDao getVendedorDao(){
        if(vendedorDao == null){
            vendedorDao = new VendedorDao();
        }
        return vendedorDao;
    }

    public static VeiculoDao getVeiculoDao(){
        if(veiculoDao == null){
            veiculoDao = new VeiculoDao();
        }
        return veiculoDao;
    }

    public static VeiculoImportadoDao getVeiculoImportadoDao(){
        if(veiculoImportadoDao == null){
            veiculoImportadoDao =  new VeiculoImportadoDao();
        }
        return veiculoImportadoDao;
    }

    public static VeiculoNacionalDao getVeiculoNacionalDao(){
        if(veiculoNacionalDao == null){
            veiculoNacionalDao = new VeiculoNacionalDao();
        }
        return  veiculoNacionalDao;
    }

    public static ProprietarioDao getProprietarioDao(){
        if(proprietarioDao == null){
            proprietarioDao = new ProprietarioDao();
        }
        return  proprietarioDao;
    }

    //GET REPOSITORIES
    public static VendedorRepositoryImpl getVendedorRepository(){
        if(vendedorRepository == null){
            vendedorRepository = new VendedorRepositoryImpl(getVendedorDao());
        }
        return vendedorRepository;
    }

    public static VeiculoRepositoryImpl getVeiculoRepository(){
        if(veiculoRepository == null){
            veiculoRepository = new VeiculoRepositoryImpl(getVeiculoDao());
        }
        return veiculoRepository;
    }

    public static VeiculoNacionalRepositoryImpl getVeiculoNacionalRepository(){
        if (veiculoNacionalRepository == null){
            veiculoNacionalRepository = new VeiculoNacionalRepositoryImpl(getVeiculoNacionalDao());
        }
        return  veiculoNacionalRepository;
    }

    public static VeiculoImportadoRepositoryImpl getVeiculoImportadoRepository(){
        if(veiculoImportadoRepository == null){
            veiculoImportadoRepository = new VeiculoImportadoRepositoryImpl(getVeiculoImportadoDao());
        }
        return veiculoImportadoRepository;
    }

    public static ProprietarioRepositoryImpl getProprietarioRepository(){
        if(proprietarioRepository == null){
            proprietarioRepository = new ProprietarioRepositoryImpl(getProprietarioDao());
        }
        return proprietarioRepository;
    }

    //GET USECASES
    public static LoadAllImportadoUseCase loadAllImportadoUseCase(){
        if(loadAllImportadoUseCase == null){
            loadAllImportadoUseCase = new LoadAllImportadoUseCase(getVeiculoImportadoRepository());
        }
        return loadAllImportadoUseCase;
    }

    public static LoadAllNacionalUseCase loadAllNacionalUseCase(){
        if (loadAllNacionalUseCase == null){
            loadAllNacionalUseCase = new LoadAllNacionalUseCase(getVeiculoNacionalRepository());
        }
        return loadAllNacionalUseCase;
    }

    public static LoadAllSellersUseCase loadAllSellersUseCase(){
        if(loadAllSellersUseCase == null){
            loadAllSellersUseCase = new LoadAllSellersUseCase(getVendedorRepository(), getVeiculoNacionalRepository(), getVeiculoImportadoRepository());
        }
        return loadAllSellersUseCase;
    }

    public static SellVeiculoUseCase getSellVeiculoUseCase(){
        if(sellVeiculoUseCase == null){
            sellVeiculoUseCase = new SellVeiculoUseCase(getVeiculoRepository());
        }
        return sellVeiculoUseCase;
    }

    public static SaveVendedorUseCase getSaveVendedorUseCase(){
        if (saveVendedorUseCase == null){
            saveVendedorUseCase = new SaveVendedorUseCase(getVendedorRepository());
        }
        return saveVendedorUseCase;
    }

    public static SaveVeiculoUseCase getSaveVeiculoUseCase(){
        if (saveVeiculoUseCase == null){
            saveVeiculoUseCase = new SaveVeiculoUseCase(getVeiculoRepository(), getVeiculoImportadoRepository(), getVeiculoNacionalRepository());
        }
        return saveVeiculoUseCase;
    }

    public static UpdateVeiculoUseCase getUpdateVeiculoUseCase(){
        if(updateVeiculoUseCase == null){
            updateVeiculoUseCase = new UpdateVeiculoUseCase(getVeiculoImportadoRepository(), getVeiculoNacionalRepository());
        }
        return updateVeiculoUseCase;
    }
}
