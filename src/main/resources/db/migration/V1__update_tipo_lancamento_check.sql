-- Atualiza o CHECK de tipo_lancamento para incluir os novos valores do enum
ALTER TABLE lancamento_devedor DROP CONSTRAINT IF EXISTS lancamento_devedor_tipo_lancamento_check;

ALTER TABLE lancamento_devedor
    ADD CONSTRAINT lancamento_devedor_tipo_lancamento_check
    CHECK (tipo_lancamento IS NULL OR tipo_lancamento IN ('DEVE', 'ABATER', 'APROVAR', 'PAGO', 'SAQUE'));
