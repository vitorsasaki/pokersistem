-- Remove coluna legado não utilizada (criada sem mapeamento Hibernate)
ALTER TABLE jogador DROP COLUMN IF EXISTS idpp;

-- Amplia id_pp de INTEGER (4 bytes) para BIGINT
ALTER TABLE jogador
    ALTER COLUMN id_pp TYPE BIGINT USING id_pp::BIGINT;
