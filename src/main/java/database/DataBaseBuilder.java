package database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

class DataBaseBuilder {

    public static void main(String[] args) {
        clear();
        build();
    }

    private static void build() {
        try(Statement stmt = ConnectionFactory.createStatement()){
            stmt.addBatch("""
                    CREATE TABLE Proprietario (
                        id	INTEGER NOT NULL,
                        nome	TEXT NOT NULL,
                        telefone	TEXT,
                        PRIMARY KEY(id AUTOINCREMENT)
                    );
                    """);
            stmt.addBatch("""
                    CREATE TABLE Vendedor (
                    	id	INTEGER NOT NULL,
                    	nome	TEXT NOT NULL,
                    	PRIMARY KEY(id AUTOINCREMENT)
                    );
                    """);
            stmt.addBatch("""
                    CREATE TABLE Veiculo (
                    	id	INTEGER NOT NULL,
                    	nome	TEXT NOT NULL,
                    	marca	TEXT NOT NULL,
                    	dataVenda	TEXT,
                    	valorVenda	REAL NOT NULL,
                    	tipo	INTEGER NOT NULL,
                    	idVendedor	INTEGER,
                    	PRIMARY KEY(id AUTOINCREMENT)
                    )
                    """);
            stmt.addBatch("""
                    CREATE TABLE VeiculoImportado (
                    	idVeiculo	INTEGER NOT NULL,
                    	paisOrigem	TEXT NOT NULL,
                    	FOREIGN KEY(idVeiculo) REFERENCES Veiculo(id) ON DELETE CASCADE
                    );
                    """);
            stmt.addBatch("""
                    CREATE TABLE VeiculoNacional (
                    	idVeiculo	INTEGER NOT NULL,
                    	idProprietario	INTEGER,
                    	FOREIGN KEY(idVeiculo) REFERENCES Veiculo(id) ON DELETE CASCADE,
                    	PRIMARY KEY(idVeiculo AUTOINCREMENT)
                    );
                    """);
            stmt.addBatch("""
                    INSERT INTO Vendedor(id, nome) VALUES(1, 'Gabriel Soad');
                    """);
            stmt.addBatch("""
                    INSERT INTO Vendedor(id, nome) VALUES(2, 'Jones Destro');
                    """);
            stmt.addBatch("""
                    INSERT INTO Vendedor(id, nome) VALUES(3, 'Gabriel Custodio');
                    """);
            stmt.addBatch("""
                    INSERT INTO Veiculo(id, nome, marca, valorVenda, dataVenda, idVendedor, tipo) VALUES(1, 'Ferrari', 'Ferrari', 1000000.00, NULL, NULL, 1);
                    """);
            stmt.addBatch("""
                    INSERT INTO VeiculoImportado(idVeiculo, paisOrigem) VALUES(1, 'Argentina');
                    """);
            stmt.addBatch("""
                    INSERT INTO Veiculo(id, nome, marca, valorVenda, dataVenda, idVendedor, tipo) VALUES(2, 'Fusca', 'Volks', 16000.00, NULL, NULL, 2);
                    """);
            stmt.addBatch("""
                    INSERT INTO VeiculoNacional(idVeiculo, idProprietario) VALUES(2, NULL);
                    """);
            stmt.addBatch("""
                    INSERT INTO Veiculo(id, nome, marca, valorVenda, dataVenda, idVendedor, tipo) VALUES(3, 'Porsche911', 'Porsche', 1000000.00, '2021-05-21', 2, 1);
                    """);
            stmt.addBatch("""
                    INSERT INTO VeiculoImportado(idVeiculo, paisOrigem) VALUES(3, 'Estados Unidos');
                    """);
            stmt.addBatch("""
                    INSERT INTO Veiculo(id, nome, marca, valorVenda, dataVenda, idVendedor, tipo) VALUES(4, 'Maserati', 'Maserati', 1500010.00, '2021-05-01', 2, 1);
                    """);
            stmt.addBatch("""
                    INSERT INTO VeiculoImportado(idVeiculo, paisOrigem) VALUES(4, 'Estados Unidos');
                    """);
            stmt.addBatch("""
                    INSERT INTO Proprietario(id, nome) VALUES(1, 'Renan Alves de Oliveira');
                    """);
            stmt.addBatch("""
                    INSERT INTO Veiculo(id, nome, marca, valorVenda, dataVenda, idVendedor, tipo) VALUES(5, 'Jetta', 'Volks', 30000.00, NULL, NULL, 2);
                    """);
            stmt.addBatch("""
                    INSERT INTO VeiculoNacional(idVeiculo, idProprietario) VALUES(5, 1);
                    """);
            stmt.executeBatch();
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void clear() {
        try{
            Files.deleteIfExists(Paths.get("estacionamento.db"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
