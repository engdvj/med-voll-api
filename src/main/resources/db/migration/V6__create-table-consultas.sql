CREATE TABLE consulta (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          medico_id BIGINT NOT NULL,
                          paciente_id BIGINT NOT NULL,
                          data VARCHAR(16) NOT NULL,  -- Assumindo o formato 'dd-MM-yyyy HH:mm'
                          ativo BOOLEAN NOT NULL DEFAULT TRUE,

                          PRIMARY KEY (id),
                          FOREIGN KEY (medico_id) REFERENCES medicos(id),
                          FOREIGN KEY (paciente_id) REFERENCES pacientes(id)
);