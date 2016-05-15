-- GERAR DADOS DE TESTE

-- ALGORITMOS DE CALCULO DE TURNO:
INSERT INTO algoritmo_calculo_turno(id, descricao, designacao, servico_permanente) VALUES
  ('1', '12+24+12+48', 'Algoritmo Turno', TRUE),
  ('2', 'Horário Normal', 'Algoritmo Horario Normal', FALSE),
  ('3', 'Manhã / Tarde / Noite', 'Algoritmo 8 Hrs (M/T/N)', TRUE),
  ('4', 'Sem Turno', 'Sem Turno', FALSE)
;

-- PERIODOS DE CICLO DE TURNO:
INSERT INTO ciclo_turno (ordem_periodo_ciclo, num_horas, periodo_descanso, algoritmo_calculo_turno_id) VALUES
  ('1', '12', FALSE, '1'),
  ('2', '24', TRUE, '1'),
  ('3', '12', FALSE, '1'),
  ('4', '48', TRUE, '1'),
  ('1', '4', FALSE, '2'),
  ('2', '1', TRUE, '2'),
  ('3', '4', FALSE, '2'),
  ('4', '15', TRUE, '2'),
  ('1', '8', FALSE, '3'),
  ('2', '16', TRUE, '3'),
  ('3', '8', FALSE, '3'),
  ('4', '24', TRUE, '3'),
  ('5', '8', FALSE, '3'),
  ('6', '16', TRUE, '3'),
  ('7', '8', FALSE, '3'),
  ('8', '16', TRUE, '3'),
  ('9', '8', FALSE, '3'),
  ('10', '24', TRUE, '3'),
  ('11', '8', FALSE, '3'),
  ('12', '16', TRUE, '3'),
  ('13', '8', FALSE, '3'),
  ('14', '24', TRUE, '3'),
  ('15', '24', TRUE, '4')
;

-- TURNOS
INSERT INTO turno (id, designacao, dt_inicio_ciclo, hr_inicio_ciclo, algoritmo_calculo_turno_id) VALUES
  ('1', '1', '29-11-2015', '08:00', '1'),
  ('2', '2', '30-11-2015', '08:00', '1'),
  ('3', '3', '01-12-2015', '08:00', '1'),
  ('4', '4', '02-12-2015', '08:00', '1'),
  ('5', 'Horário Normal', '01-12-2015', '08:00', '2'),
  ('6', '5 (8 horas)', '01-12-2015', '08:00', '3'),
  ('7', '6 (8 horas)', '01-12-2015', '16:00', '3'),
  ('8', '7 (8 horas)', '01-12-2015', '00:00', '3'),
  ('9', 'Sem Turno', '01-12-2015', '00:00', '4')
;

-- POSTOS FUNCIONAIS
INSERT INTO posto_funcional(id, descricao, designacao) VALUES
  ('1', 'Comandante', 'Cmdt'),
  ('2', '2º comandante', '2ºCmdt'),
  ('3', 'Comandante de Companhia', 'Cmdt Comp.'),
  ('4', 'Adj.Tec', 'Adjunto Técnico'),
  ('5', 'Bombeiro Sapador', 'B. Sap.'),
  ('6', 'Chefe Principal', 'Chefe'),
  ('7', 'Sub Chefe 1ª', 'S/Ch 1ª'),
  ('8', 'Sub Chefe 1ª Classe', 'S/Ch 1ª Cl'),
  ('9', 'Sub Chefe 1ª Classe (Regime Mobilidade)', 'S/Ch 1ª RM'),
  ('10', 'Sub Chefe 2ª', 'S/Ch 2ª'),
  ('11', 'Sub Chefe 2ª Classe', 'S/Ch 2ª Cl'),
  ('12', 'Sub Chefe Principal', 'S/Ch Pr.'),
  ('13', 'Sub Chefe Principal (Regime Mobilidade)', 'S/Ch Pr. RM'),
  ('14', 'Bombeiro Sapador Recruta', 'B. Rec.'),
  ('15', 'Sem posto por omissão', 'Sem Posto')
;

-- TIPOS DE PRESENÇA
INSERT INTO tipo_presenca(id, abreviatura, ausencia, descricao, reforco) VALUES
  ('A', 'Doença', TRUE, 'Situação de Doente', FALSE),
  ('B', 'Ac. Serv', TRUE, 'Acidente em Servico', FALSE),
  ('C', 'Casamento', TRUE, 'Dispensa de Casamento', FALSE),
  ('D', 'Nojo', TRUE, 'Dispensa de Nojo', FALSE),
  ('E', 'Ass. Fam.', TRUE, 'Ass. Fam. Menores', FALSE),
  ('SG', 'Act. Sind.', TRUE, 'Actividade Sindical', FALSE),
  ('H', 'F. Injust.', TRUE, 'Falta injustificada', FALSE),
  ('I', 'Lic. Patern.', TRUE, 'Licença de Paternidade', FALSE),
  ('J', 'Inact. Quadro', TRUE, 'Inactividade Quadro', FALSE),
  ('F', 'Ass. Familia', TRUE, 'Ass. Familia', FALSE),
  ('L', 'Lic. Parental', TRUE, 'Licença Parental', FALSE),
  ('M', 'Lic. Nasc.', TRUE, 'Lic. Nascimento', FALSE),
  ('N', 'Hospital', TRUE, 'Hospital', FALSE),
  ('X', 'Ferias Confirmadas', TRUE, 'Ferias Confirmadas', FALSE),
  ('Z', 'Ferias N/ Confirm.', TRUE, 'Ferias N/ Confirm.', FALSE),
  ('T', 'Transferência', TRUE, 'Transferência para outra Unidade', FALSE),
  ('0', 'N/A', TRUE, 'Não aplicável', FALSE),
  ('S', 'Serviço Interno', FALSE, 'Serviços Internos Comandante Companhia / Batalhão / Secção', FALSE),
  ('S0', 'Disp. Compensação', TRUE, 'Dispensa por compensação', FALSE),
  ('1S', 'CPO / CH CIDADE  (Reforço)', FALSE, 'Comandante de Permanencia às Operações / Chefe de Serviço à Cidade', TRUE),
  ('2S', 'Ch. 1ª Interv.  (Reforço)', FALSE, 'Chefe de 1ª Intervenção', TRUE),
  ('3S', 'S/chefes  (Reforço)', FALSE, 'Sub Chefes', TRUE),
  ('4S', 'Linha (Reforço)', FALSE, 'Pessoal de Linha', TRUE),
  ('5S', 'Motoristas (Reforço)', FALSE, 'Motoristas', TRUE),
  ('6S', 'Mg/Mac/Nisac  (Reforço)', FALSE, 'Mergulhadores / Maqueiros / Apoio Especializado', TRUE),
  ('9S', 'Formaçãpo  (Reforço)', FALSE, 'FAORMAÇÃO', TRUE)
;
INSERT INTO tipo_presenca(id, abreviatura, ausencia, descricao, reforco, tipo_presenca_em_reforco_id) VALUES
  ('S1', 'CPO / CH CIDADE', FALSE, 'Comandante de Permanencia às Operações / Chefe de Serviço à Cidade', FALSE, '1S'),
  ('S2', 'Ch. 1ª Interv.', FALSE, 'Chefe de 1ª Intervenção', FALSE, '2S'),
  ('S3', 'S/chefes', FALSE, 'Sub Chefes', FALSE, '3S'),
  ('S4', 'Linha', FALSE, 'Pessoal de Linha', FALSE, '4S'),
  ('S5', 'Motoristas', FALSE, 'Motoristas', FALSE, '5S'),
  ('S6', 'Mg/Mac/Nisac', FALSE, 'Mergulhadores / Maqueiros / Apoio Especializado', FALSE, '6S'),
  ('S9', 'Formação', FALSE, 'Formação', FALSE, '9S')
;

-- TIPOS DE UNIDADES ESTRUTURAIS
INSERT INTO tipo_unidade_estrutural(id, descricao, designacao, nivel_hierarquico_maximo_mae) VALUES
  ('1', 'unidade composta por dois ou mais batalhões', 'Regimento', '0'),
  ('2', 'subdivisão de um regimento, formada por um número determinado de companhias', 'Batalhão', '1'),
  ('3', 'subunidade de nível inferior ao batalhão', 'Companhia', '2'),
  ('4', 'subunidade de nível inferior à companhia', 'Secção', '3')
;

-- UNIDADES ESTRUTURAIS
INSERT INTO unidade_estrutural(id, designacao, nivel_hierarquico, tipo_unidade_estrutural_id) VALUES
  ('1','Regimento Sapadores Bombeiros','1','1')
;
INSERT INTO unidade_estrutural(id, designacao, nivel_hierarquico, tipo_unidade_estrutural_id, unidade_estrutural_mae_id) VALUES
  ('2','2º Batalhão','2','2','1'),
  ('3','3ª Companhia','3','3','2')
;

-- INSTALAÇÕES
INSERT INTO instalacao (id, descricao, designacao, localizacao, unidade_estrutural_id) VALUES
  ('1', 'Quartel - Sede', 'S', 'Av. Rio de Janeiro', '3'),
  ('2', 'Quartel - Externo', 'E', 'Colombo', '3')
;

-- FORMAÇÃO
INSERT INTO formacao(id, descricao, designacao, validade) VALUES
  ('1', 'Carta Condução +3500Kg', 'Condução de Pesados', '20'),
  ('2', 'Curso Mecanica geral', 'Mecânica geral', '-1'),
  ('3', 'Torneiro mecancico', 'Torneiro', '-1'),
  ('4', 'Curso de cozinha', 'Cozinheiro', '-1'),
  ('5', 'Não disponível', 'N/D', '-1')
;

-- TIPOS DE UNIDADES OPERACIONAIS
INSERT INTO  tipo_unidade_operacional(id, descricao, designacao) VALUES
  ('1', 'VLCI', ''),
  ('2', 'VUCI', ''),
  ('3', 'VE', ''),
  ('4', 'VTTU', ''),
  ('5', 'VOPE', ''),
  ('6', 'VCOT', ''),
  ('7', 'OPDAE', ''),
  ('8', 'COZINHA', ''),
  ('9', 'BAR', ''),
  ('10', 'PLANTÃO GARAGEM', '')


;

